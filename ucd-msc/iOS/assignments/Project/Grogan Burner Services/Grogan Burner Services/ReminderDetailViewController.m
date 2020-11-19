//
//  ReminderDetailViewController.m
//  Grogan Burner Services
//
//  Created by Neil Grogan on 19/04/2014.
//  Copyright (c) 2014 Grogan Burner Services. All rights reserved.
//

#import "ReminderDetailViewController.h"
#import <EventKit/EventKit.h>
#import <EventKitUI/EventKitUI.h>

@interface ReminderDetailViewController ()
@property (weak, nonatomic) IBOutlet UILabel *reminderDateLabel;
@property (weak, nonatomic) IBOutlet UILabel *reminderAlarmLabel;
@property (weak, nonatomic) IBOutlet UILabel *reminderNotesLabel;
@property (strong, nonatomic) NSString *dueDateText;
@property (strong, nonatomic) NSString *alarmText;
@property (strong, nonatomic) NSString *noteText;
@end

@implementation ReminderDetailViewController

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
    self.reminderDateLabel.text = self.dueDateText;
    self.reminderAlarmLabel.text = self.alarmText;
    self.reminderNotesLabel.text = self.noteText;
    
}

- (void) setReminder:(EKReminder *)reminder{
    NSArray *alarms = [reminder alarms];
    EKAlarm *alarm = [alarms objectAtIndex:0];
    NSDate *alarmDate = [alarm absoluteDate];
    NSDateFormatter *formatter = [[NSDateFormatter alloc] init];
    [formatter setDateFormat:@"hh:mma EEE, MMM d, ''yy"];
    NSString *stringFromAlarmDate = [formatter stringFromDate:alarmDate];
    
    self.alarmText = stringFromAlarmDate;

    NSDateComponents *components = [reminder dueDateComponents];
    NSDate *dueDate = [components date];
    NSString *stringFromDueDate = [formatter stringFromDate:dueDate];
    
    self.dueDateText = stringFromDueDate;
    
    self.noteText = [reminder notes];
    
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
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
