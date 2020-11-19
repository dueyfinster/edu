//
//  ViewController.m
//  HelloPoly
//
//  Created by Neil Grogan on 20/01/2014.
//  Copyright (c) 2014 Grogan Burner Services. All rights reserved.
//

#import "ViewController.h"

@interface ViewController ()

@end

@implementation ViewController

- (PolygonShape *)model
{
    if (!_model)
    {
        _model = [PolygonShape new];
    }
    return _model;
}


- (void)updateUI
{
    self.numberOfSides.text = self.model.name;
    self.stepper.value = self.model.numberOfSides;
    [self.numberOfSides sizeToFit];
    [self.polyView setNeedsDisplay];
}

- (NSArray *)pointsInRect:(CGRect)rect
{
    return [self.model pointsInRect:rect];
}

- (void)viewDidLoad
{
    [super viewDidLoad];
    NSLog(@"I’m in the view load");
    [self updateUI];
	// Do any additional setup after loading the view, typically from a nib.    
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (IBAction)decrease:(UIButton *)sender {
    self.model.numberOfSides--;
    [self updateUI];
}

- (IBAction)increase:(UIButton *)sender {
    self.model.numberOfSides++;
    [self updateUI];
}

- (IBAction)stepSides:(UIStepper *)sender {
    self.model.numberOfSides = sender.value;
    [self updateUI];
}

- (void)viewDidUnload {
    [self setNumberOfSides:nil];
    [self setModel:nil];
    [super viewDidUnload];
}

@end
