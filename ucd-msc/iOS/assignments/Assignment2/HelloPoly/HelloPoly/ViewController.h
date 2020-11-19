//
//  ViewController.h
//  HelloPoly
//
//  Created by Neil Grogan on 20/01/2014.
//  Copyright (c) 2014 Grogan Burner Services. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "PolygonShape.h"
#import "PolygonView.h"

@interface ViewController : UIViewController  <PolygonViewDelegate>
@property (weak, nonatomic) IBOutlet PolygonView *polyView;
@property (strong, nonatomic) IBOutlet PolygonShape *model;
@property (weak, nonatomic) IBOutlet UIStepper *stepper;
@property (weak, nonatomic) IBOutlet UILabel *numberOfSides;
- (IBAction)decrease:(id)sender;
- (IBAction)increase:(id)sender;

@end
