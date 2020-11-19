//
//  PolygonView.h
//  HelloPoly
//
//  Created by Neil Grogan on 27/01/2014.
//  Copyright (c) 2014 Grogan Burner Services. All rights reserved.
//

#import <UIKit/UIKit.h>

@class PolygonView;

@protocol PolygonViewDelegate <NSObject>
- (NSArray *)pointsInRect:(CGRect)rect;
@end

@interface PolygonView : UIView
@property (nonatomic, weak) IBOutlet id <PolygonViewDelegate> delegate;
@property (nonatomic) CGFloat lineWidth;
@property (nonatomic, strong) UIColor *strokeColor;
@property (nonatomic, strong) UIColor *fillColor;
@end
