//
//  RemindersListViewController.m
//  Grogan Burner Services
//
//  Created by Neil Grogan on 10/04/2014.
//  Copyright (c) 2014 Grogan Burner Services. All rights reserved.
//

#import "RemindersListViewController.h"
#import "ReminderDetailViewController.h"
#import <EventKit/EventKit.h>
#import <EventKitUI/EventKitUI.h>

@interface RemindersListViewController () <EKEventEditViewDelegate>
@property (nonatomic, strong) EKEventStore *eventStore;
@property (nonatomic, strong) EKCalendar *defaultCalendar;
@property (nonatomic, strong) NSMutableArray *remindersList;
@property (weak, nonatomic) IBOutlet UIBarButtonItem *addButton;
@end


@implementation RemindersListViewController

#pragma mark -
#pragma mark View lifecycle

- (void)viewDidLoad
{
    [super viewDidLoad];
	// Initialize the event store
	self.eventStore = [[EKEventStore alloc] init];
    // Initialize the events list
	self.remindersList = [[NSMutableArray alloc] initWithCapacity:0];
    // The Add button is initially disabled
    self.addButton.enabled = NO;
}


-(void)viewDidAppear:(BOOL)animated
{
    [super viewDidAppear:animated];
    // Check whether we are authorized to access Calendar
    [self checkEventStoreAccessForCalendar];
}


// This method is called when the user selects an event in the table view. It configures the destination
// event view controller with this event.
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender
{
    if ([[segue identifier] isEqualToString:@"showReminderViewController"])
    {
        NSIndexPath *indexPath = [self.tableView indexPathForSelectedRow];
        EKReminder *reminder = [self.remindersList objectAtIndex:indexPath.row];
        UITableViewCell *cell =  (UITableViewCell *)sender;
        [segue.destinationViewController navigationItem].title = cell.textLabel.text;
        [segue.destinationViewController setReminder:reminder];
    }
}

- (IBAction)unwindToRemindersViewController:(UIStoryboardSegue *)segue { }


- (IBAction)sucRem:(UIStoryboardSegue *)segue {
    [self accessGrantedForReminders];
}

#pragma mark -
#pragma mark Table View

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
	return self.remindersList.count;
}


- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
	UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:@"eventCell" forIndexPath:indexPath];
    
    // Get the event at the row selected and display its title
    cell.textLabel.text = [[self.remindersList objectAtIndex:indexPath.row] title];
    return cell;
}


#pragma mark -
#pragma mark Access Calendar

// Check the authorization status of our application for Calendar
-(void)checkEventStoreAccessForCalendar
{
    EKAuthorizationStatus status = [EKEventStore authorizationStatusForEntityType:EKEntityTypeReminder];
    
    switch (status)
    {
            // Update our UI if the user has granted access to their Calendar
        case EKAuthorizationStatusAuthorized: [self accessGrantedForReminders];
            break;
            // Prompt the user for access to Calendar if there is no definitive answer
        case EKAuthorizationStatusNotDetermined: [self requestReminderAccess];
            break;
            // Display a message if the user has denied or restricted access to Calendar
        case EKAuthorizationStatusDenied:
        case EKAuthorizationStatusRestricted:
        {
            UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"Privacy Warning" message:@"Permission was not granted for Calendar"
                                                           delegate:nil
                                                  cancelButtonTitle:@"OK"
                                                  otherButtonTitles:nil];
            [alert show];
        }
            break;
        default:
            break;
    }
}


// Prompt the user for access to their Calendar
-(void)requestReminderAccess
{
    [self.eventStore requestAccessToEntityType:EKEntityTypeReminder completion:^(BOOL granted, NSError *error)
     {
         if (granted)
         {
             RemindersListViewController * __weak weakSelf = self;
             // Let's ensure that our code will be executed from the main queue
             dispatch_async(dispatch_get_main_queue(), ^{
                 // The user has granted access to their Calendar; let's populate our UI with all events occuring in the next 24 hours.
                 [weakSelf accessGrantedForReminders];
             });
         }
     }];
}


// This method is called when the user has granted permission to Calendar
-(void)accessGrantedForReminders
{
    // Let's get the default calendar associated with our event store
    self.defaultCalendar = self.eventStore.defaultCalendarForNewReminders;
    // Enable the Add button
    self.addButton.enabled = YES;
    // Fetch all events happening in the next 24 hours and put them into eventsList
    self.remindersList = [self fetchReminders];
    // Update the UI with the above events
    [self.tableView reloadData];
}


#pragma mark -
#pragma mark Fetch events

// Fetch all reminders
- (NSMutableArray *)fetchReminders
{
    
    NSDate *startDate = [NSDate date];
    NSDateComponents *tomorrowDateComponents = [[NSDateComponents alloc] init];
    tomorrowDateComponents.day = 100;
    NSDate *endDate = [[NSCalendar currentCalendar] dateByAddingComponents:tomorrowDateComponents
                                                                    toDate:startDate
                                                                   options:0];
    
    dispatch_semaphore_t sema = dispatch_semaphore_create(0);

    
    NSArray *calendarArray = [NSArray arrayWithObject:self.defaultCalendar];
    
    
    NSPredicate *predicate = [self.eventStore predicateForIncompleteRemindersWithDueDateStarting:startDate ending:endDate calendars:calendarArray];
	
	// Fetch all events that match the predicate
	//NSMutableArray *events = [NSMutableArray arrayWithArray:[self.eventStore eventsMatchingPredicate:predicate]];
    NSMutableArray *events = [[NSMutableArray alloc] init];
    
    [self.eventStore fetchRemindersMatchingPredicate:predicate completion:^(NSArray *reminders) {
        for(EKReminder *reminder in reminders){
            if ([reminder.notes rangeOfString:@"GBS"].location == NSNotFound) {
                [events addObject:reminder];
            }
        }
        [events addObjectsFromArray:reminders];
        dispatch_semaphore_signal(sema);
    }];
    
    dispatch_semaphore_wait(sema, DISPATCH_TIME_FOREVER);

    
	return events;
}


#pragma mark -
#pragma mark EKEventEditViewDelegate

// Overriding EKEventEditViewDelegate method to update event store according to user actions.
- (void)eventEditViewController:(EKEventEditViewController *)controller
		  didCompleteWithAction:(EKEventEditViewAction)action
{
    RemindersListViewController * __weak weakSelf = self;
	// Dismiss the modal view controller
    [self dismissViewControllerAnimated:YES completion:^
     {
         if (action != EKEventEditViewActionCanceled)
         {
             dispatch_async(dispatch_get_main_queue(), ^{
                 // Re-fetch all events happening in the next 24 hours
                 weakSelf.remindersList = [self fetchReminders];
                 // Update the UI with the above events
                 [weakSelf.tableView reloadData];
             });
         }
     }];
}


// Set the calendar edited by EKEventEditViewController to our chosen calendar - the default calendar.
- (EKCalendar *)eventEditViewControllerDefaultCalendarForNewEvents:(EKEventEditViewController *)controller
{
	return self.defaultCalendar;
}

// Override to support conditional editing of the table view.
- (BOOL)tableView:(UITableView *)tableView canEditRowAtIndexPath:(NSIndexPath *)indexPath
{
    // Return NO if you do not want the specified item to be editable.
    return YES;
}

// Override to support editing the table view.
- (void)tableView:(UITableView *)tableView commitEditingStyle:(UITableViewCellEditingStyle)editingStyle forRowAtIndexPath:(NSIndexPath *)indexPath
{
    if (editingStyle == UITableViewCellEditingStyleDelete)
    {
        // Delete the row from the data source
        EKReminder *reminder = (EKReminder *)[self.remindersList objectAtIndex:indexPath.row];
      
        NSError *err;
        
        BOOL success = [self.eventStore removeReminder:reminder commit:YES error:&err];
        
        if (!success) {
            NSLog(@"Error deleting reminder");
        }else{
            [self.remindersList removeObjectAtIndex:indexPath.row];
            [self.tableView deleteRowsAtIndexPaths:[NSArray arrayWithObject:indexPath] withRowAnimation:UITableViewRowAnimationFade];
        }
    }
}

@end
