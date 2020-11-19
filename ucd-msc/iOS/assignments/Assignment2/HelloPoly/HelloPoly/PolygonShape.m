//
//  PolygonShape.m
//  HelloPoly
//
//  Created by Neil Grogan on 23/01/2014.
//  Copyright (c) 2014 Grogan Burner Services. All rights reserved.
//

#import "PolygonShape.h"

@interface PolygonShape ()

@end

@implementation PolygonShape

- (instancetype)init
{
    self = [super init];
    if (self)
    {
        _numberOfSides = 8;
    }
    return self;
}

- (NSString *)description
{
    return self.name;
}

- (void)setNumberOfSides:(NSUInteger)numberOfSides
{
    if (numberOfSides < 3 || numberOfSides > 12) return;
    _numberOfSides = numberOfSides;
}



- (NSArray *)pointsInRect:(CGRect)rect
{
    CGPoint center =  CGPointMake(rect.size.width / 2.0, rect.size.height / 2.0);
    center.x += rect.origin.x;
    center.y += rect.origin.y;
    CGFloat radius = (rect.size.width > rect.size.height) ? rect.size.height : rect.size.width;
    radius /= 2.0;
    CGFloat angle = 2.0 * M_PI / self.numberOfSides;
    CGPoint vertex;
    NSMutableArray *vertices = [NSMutableArray arrayWithCapacity:self.numberOfSides];
    for (int angleIndex = 0; angleIndex < self.numberOfSides; angleIndex++)
    {
        vertex.x = cos(angle * angleIndex - M_PI_2) * radius + center.x;
        vertex.y = sin(angle * angleIndex - M_PI_2) * radius + center.y;
        [vertices addObject:[NSValue valueWithCGPoint:vertex]];
    }
    return [vertices copy];
}


- (NSString *)name
{
    NSArray *names = @[@"Triangle",
                       @"Square",@"Pentagon",@"Hexagon",
                       @"Heptagon",@"Octagon",@"Nonagon",
                       @"Decagon",@"Hendecagon",@"Dodecagon"];
    return [names objectAtIndex:self.numberOfSides - 3];
}

@end
