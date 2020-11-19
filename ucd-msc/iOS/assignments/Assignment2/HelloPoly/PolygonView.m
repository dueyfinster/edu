//
//  PolygonView.m
//  HelloPoly
//
//  Created by Neil Grogan on 27/01/2014.
//  Copyright (c) 2014 Grogan Burner Services. All rights reserved.
//

#import "PolygonView.h"

@implementation PolygonView

- (void)initPropertiesToDefaultValues
{
    self.fillColor = [[UIColor blueColor] colorWithAlphaComponent:0.5];
    self.strokeColor = [UIColor blackColor];
    self.lineWidth = 2.0;
}

- (id)initWithFrame:(CGRect)frame
{
    self = [super initWithFrame:frame];
    if (self) {
        // Initialization code
        [self initPropertiesToDefaultValues];
    }
    return self;
}

- (id)initWithCoder:(NSCoder *)aDecoder
{
    self = [super initWithCoder:aDecoder];
    if (self)
    {
        [self initPropertiesToDefaultValues];
    }
    return self;
}


// Only override drawRect: if you perform custom drawing.
// An empty implementation adversely affects performance during animation.
- (void)drawRect:(CGRect)rect
{
    // Drawing code
    CGRect insetRect = CGRectInset(rect, self.lineWidth / 2, self.lineWidth / 2);
    //figure out why delegate is nil
    NSArray *points = [self.delegate pointsInRect:insetRect];

    if (points.count > 2)
    {
        CGContextRef context = UIGraphicsGetCurrentContext();
        CGContextSetStrokeColorWithColor(context, self.strokeColor.CGColor);
        CGContextSetFillColorWithColor(context, self.fillColor.CGColor);
        CGContextSetLineWidth(context, self.lineWidth);
        CGContextSetLineCap(context, kCGLineCapRound);
        CGPoint aPoint =  [points[0] CGPointValue];
        CGContextMoveToPoint(context, aPoint.x, aPoint.y);
        for (int i = 1; i < points.count; i++)
        {
            aPoint =  [points[i] CGPointValue];
            CGContextAddLineToPoint(context, aPoint.x, aPoint.y);
        }
        CGContextClosePath(context);
        CGContextDrawPath(context, kCGPathFillStroke);
    }
}


@end
