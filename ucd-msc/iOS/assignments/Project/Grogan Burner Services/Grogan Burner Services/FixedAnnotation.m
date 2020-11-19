//
//  Annotation.m
//  Grogan Burner Services
//
//  Created by Neil Grogan on 01/04/2014.
//  Copyright (c) 2014 Grogan Burner Services. All rights reserved.
//

#import "FixedAnnotation.h"

@implementation FixedAnnotation

@synthesize coordinate;
@synthesize title;
@synthesize subtitle;

- (id)initWithCoordinates:(CLLocationCoordinate2D)location
                placeName:(NSString *)placeName
              description:(NSString *)description;
{
    self = [super init];
    if (self)
    {
        coordinate = location;
        title = placeName;
        subtitle = description;
    }
    
    return self;
}

@end
