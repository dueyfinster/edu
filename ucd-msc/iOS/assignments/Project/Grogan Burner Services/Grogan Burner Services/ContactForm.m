//
//  ContactForm.m
//  Grogan Burner Services
//
//  Created by Neil Grogan on 18/04/2014.
//  Copyright (c) 2014 Grogan Burner Services. All rights reserved.
//

#import "ContactForm.h"

@implementation ContactForm


- (NSArray *)fields
{
    return @[
             
             @{FXFormFieldKey: @"Name", FXFormFieldHeader: @"Details", FXFormFieldType: FXFormFieldTypeText, @"textField.autocapitalizationType": @(UITextAutocapitalizationTypeNone)},
             @{FXFormFieldKey: @"Email", FXFormFieldTitle: @"Email Address", FXFormFieldType: FXFormFieldTypeEmail},
             @{FXFormFieldKey: @"phoneNo", FXFormFieldType: FXFormFieldTypeNumber},
             @{FXFormFieldKey: @"fuelType", FXFormFieldTitle: @"Fuel I use is...", FXFormFieldOptions: @[@"Oil", @"Gas"]},
             @{FXFormFieldKey: @"request", FXFormFieldTitle: @"I want to request a...", FXFormFieldOptions: @[@"Service", @"Repair"]},
             ];
    
}


- (NSArray *)extraFields
{
    return @[
             
             //this field doesn't correspond to any property of the form
             //it's just an action button. the action will be called on first
             //object in the responder chain that implements the submitForm
             //method, which in this case would be the AppDelegate
             
             @{FXFormFieldTitle: @"Send Request", FXFormFieldHeader: @"", FXFormFieldAction: @"formSubmit:", @"textLabel.color": [UIColor colorWithRed:0.0 green:122.0/255.0 blue:1.0 alpha:1.0]},
             
             ];
}

@end
