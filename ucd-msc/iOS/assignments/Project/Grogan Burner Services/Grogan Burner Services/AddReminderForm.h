//
//  AddReminderForm.h
//  Grogan Burner Services
//
//  Created by Neil Grogan on 10/04/2014.
//  Copyright (c) 2014 Grogan Burner Services. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "FXForms.h"

@interface AddReminderForm : NSObject <FXForm>
@property (nonatomic, copy) NSString *Name;
@property (nonatomic, strong) NSDate *When;

@end
