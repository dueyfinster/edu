//
//  ViewController.m
//  Calculator
//
//  Created by Neil Grogan on 08/01/2014.
//  Copyright (c) 2014 University College Dublin. All rights reserved.
//

#import "ViewController.h"

@interface ViewController ()

@end

@implementation ViewController

- (void)digitPressed:(UIButton *)sender{
    _calcDisplay.text = sender.titleLabel.text;
}

- (void)operationPressed:(UIButton *)sender{
    return;
}

- (void)viewDidLoad
{
    [super viewDidLoad];
	// Do any additional setup after loading the view, typically from a nib.
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

@end
