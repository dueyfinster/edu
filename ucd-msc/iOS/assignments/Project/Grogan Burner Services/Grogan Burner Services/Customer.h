//
//  Customer.h
//  Grogan Burner Services
//
//  Created by Neil Grogan on 20/04/2014.
//  Copyright (c) 2014 Grogan Burner Services. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <CoreData/CoreData.h>


@interface Customer : NSManagedObject

@property (nonatomic, retain) NSString * address;
@property (nonatomic, retain) NSString * email;
@property (nonatomic, retain) NSString * id;
@property (nonatomic, retain) NSString * name;
@property (nonatomic, retain) NSString * phone;
@property (nonatomic, retain) NSManagedObject *newRelationship;

@end
