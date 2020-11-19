//
//  CalculatorModel.h
//  Calculator
//
//  Created by Neil Grogan on 09/01/2014.
//  Copyright (c) 2014 University College Dublin. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface CalculatorModel : NSObject{
    double _operand;
}

- (void)setOperand:(double)operand;
- (double)performOperation:(NSString *)operation;

@end
