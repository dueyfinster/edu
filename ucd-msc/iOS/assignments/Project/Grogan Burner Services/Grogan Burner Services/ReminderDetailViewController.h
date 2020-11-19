//
//  ReminderDetailViewController.h
//  Grogan Burner Services
//
//  Created by Neil Grogan on 19/04/2014.
//  Copyright (c) 2014 Grogan Burner Services. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <EventKit/EventKit.h>
#import <EventKitUI/EventKitUI.h>

@interface ReminderDetailViewController : UIViewController
@property (nonatomic, strong) EKReminder *reminder;
@end
