//
//  ContactForm.h
//  Grogan Burner Services
//
//  Created by Neil Grogan on 18/04/2014.
//  Copyright (c) 2014 Grogan Burner Services. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "FXForms.h"


typedef NS_ENUM(NSInteger, FuelType)
{
    Oil = 0,
    Gas
};

typedef NS_ENUM(NSInteger, ServiceType)
{
    Service = 0,
    Repair
};

@interface ContactForm : NSObject <FXForm>
@property (nonatomic, copy) NSString *Name;
@property (nonatomic, copy) NSString *Email;
@property (nonatomic, copy) NSString *phoneNo;
@property (nonatomic, assign) FuelType fuelType;
@property (nonatomic, assign) ServiceType request;
@end
