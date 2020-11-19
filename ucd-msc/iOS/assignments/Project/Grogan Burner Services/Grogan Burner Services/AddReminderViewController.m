//
//  AddReminderViewController.m
//  Grogan Burner Services
//
//  Created by Neil Grogan on 10/04/2014.
//  Copyright (c) 2014 Grogan Burner Services. All rights reserved.
//

#import "AddReminderViewController.h"
#import "AddReminderForm.h"
#import "FXForms.h"
#import <EventKit/EventKit.h>


@interface AddReminderViewController ()
@property (weak, nonatomic) IBOutlet UITableView *tableView;
@property (nonatomic, strong) EKEventStore *store;
@property (nonatomic, strong) EKCalendar *defaultCalendar;
@end

@implementation AddReminderViewController

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
    }
    return self;
}

- (void)viewDidLoad
{
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    FXFormViewController *controller = [[FXFormViewController alloc] init];
    controller.formController.form = [[AddReminderForm alloc] init];
    self.formController.form = [[AddReminderForm alloc] init];
    [self.tableView reloadData];
    self.store = [[EKEventStore alloc] init];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (void)addReminder:(UITableViewCell<FXFormFieldCell> *)cell
{
    AddReminderForm *form = cell.field.form;
    
    //NSLog(form.Name);
    
    if(form.When == nil | form.Name == nil){
        [[[UIAlertView alloc] initWithTitle:@"Error" message:@"Please enter a title and time before proceeding!" delegate:nil cancelButtonTitle:nil otherButtonTitles:@"Yes Sir!", nil] show];
    }else{
        //Success
        //[[[UIAlertView alloc] initWithTitle:@"Login Form Submitted" message:nil delegate:nil cancelButtonTitle:nil otherButtonTitles:@"OK", nil] show];
        
        self.defaultCalendar = self.store.defaultCalendarForNewReminders;
        
        EKReminder *reminder = [EKReminder reminderWithEventStore:_store];
        
        reminder.title = form.Name;
        
        reminder.calendar = self.defaultCalendar;
        NSCalendar *calendar            = [[NSCalendar alloc] initWithCalendarIdentifier:NSGregorianCalendar];
        NSDateComponents *components = [calendar components:(NSYearCalendarUnit  | NSMonthCalendarUnit | NSDayCalendarUnit | NSHourCalendarUnit  | NSMinuteCalendarUnit| NSSecondCalendarUnit) fromDate:form.When];
        reminder.dueDateComponents = components;
        
        NSMutableArray *myAlarmsArray = [[NSMutableArray alloc] init];
        EKAlarm *alarm1 = [EKAlarm alarmWithAbsoluteDate:form.When];
        [myAlarmsArray addObject:alarm1];
        
        reminder.alarms = myAlarmsArray;
        
        reminder.notes = @"Added via GBS App";
        
        NSError *err;
        
        BOOL success = [self.store saveReminder:reminder commit:YES error:&err];
        
        if (!success) {
            NSLog(@"Error creating reminder");
            
        }else{
            [self performSegueWithIdentifier:@"sucRem" sender:self];
        }
    }
    
    NSString *dateString = [NSDateFormatter localizedStringFromDate:form.When
                                                          dateStyle:NSDateFormatterShortStyle
                                                          timeStyle:NSDateFormatterFullStyle];
    NSLog(@"%@",dateString);

    //now we can display a form value in our alert
}





/*
#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender
{
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
}
*/

@end
