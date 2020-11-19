//
//  ViewController.h
//  Calculator
//
//  Created by Neil Grogan on 08/01/2014.
//  Copyright (c) 2014 University College Dublin. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "CalculatorModel.h"

@interface ViewController : UIViewController{
    CalculatorModel *_calculatorModel;
    IBOutlet UILabel *_calcDisplay;
}

- (IBAction)digitPressed:(UIButton *)sender;
- (IBAction)operationPressed:(UIButton *)sender;

@end
