//
//  AddReminderForm.m
//  Grogan Burner Services
//
//  Created by Neil Grogan on 10/04/2014.
//  Copyright (c) 2014 Grogan Burner Services. All rights reserved.
//

#import "AddReminderForm.h"

@implementation AddReminderForm


- (NSArray *)fields
{
    return @[
             
             @{FXFormFieldKey: @"Name", FXFormFieldHeader: @"Details", FXFormFieldPlaceholder: @"Call for Oil Service", FXFormFieldType: FXFormFieldTypeText},
             @{FXFormFieldKey: @"When", FXFormFieldType: FXFormFieldTypeDateTime},
             ];
}


- (NSArray *)extraFields
{
    return @[
             
             //this field doesn't correspond to any property of the form
             //it's just an action button. the action will be called on first
             //object in the responder chain that implements the submitForm
             //method, which in this case would be the AppDelegate
             
             @{FXFormFieldTitle: @"Add Reminder", FXFormFieldHeader: @"", FXFormFieldAction: @"addReminder:", @"textLabel.color": [UIColor colorWithRed:0.0 green:122.0/255.0 blue:1.0 alpha:1.0]},
             
             ];
}

@end
