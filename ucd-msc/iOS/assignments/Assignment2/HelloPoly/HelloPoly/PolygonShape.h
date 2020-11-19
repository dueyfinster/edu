//
//  PolygonShape.h
//  HelloPoly
//
//  Created by Neil Grogan on 23/01/2014.
//  Copyright (c) 2014 Grogan Burner Services. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface PolygonShape : UIViewController
@property (nonatomic) NSUInteger numberOfSides;
@property (weak, readonly) NSString *name;
- (NSArray *)pointsInRect:(CGRect)rect;
@end
