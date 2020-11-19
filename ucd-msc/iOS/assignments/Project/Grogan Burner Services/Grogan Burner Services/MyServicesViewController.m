//
//  MyServicesViewController.m
//  Grogan Burner Services
//
//  Created by Neil Grogan on 06/04/2014.
//  Copyright (c) 2014 Grogan Burner Services. All rights reserved.
//

#import "MyServicesViewController.h"
#import "JBBarChartView.h"
#define UIColorFromHex(hex) [UIColor colorWithRed:((float)((hex & 0xFF0000) >> 16))/255.0 green:((float)((hex & 0xFF00) >> 8))/255.0 blue:((float)(hex & 0xFF))/255.0 alpha:1.0]

#define kJBColorBarChartBarBlue UIColorFromHex(0x08bcef)
#define kJBColorBarChartBarGreen UIColorFromHex(0x34b234)

// Numerics
CGFloat const kJBBarChartViewControllerChartHeight = 98.0f;
CGFloat const kJBBarChartViewControllerChartPadding = 10.0f;
CGFloat const kJBBarChartViewControllerBarPadding = 1;
NSInteger const kJBBarChartViewControllerNumBars = 4;
NSInteger const kJBBarChartViewControllerMaxBarHeight = 20;
NSInteger const kJBBarChartViewControllerMinBarHeight = 10;

@interface MyServicesViewController () <JBBarChartViewDelegate, JBBarChartViewDataSource>
@property (weak, nonatomic) IBOutlet UILabel *barChartTitle;
@property (weak, nonatomic) IBOutlet JBBarChartView *barChartView;
@property (weak, nonatomic) IBOutlet UIView *individualBarView;
@property (weak, nonatomic) IBOutlet UILabel *individualBarValue;
@property (weak, nonatomic) IBOutlet UILabel *individualBarText;
@property (nonatomic, strong) NSArray *chartData;
@end

@implementation MyServicesViewController

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
    }
    return self;
}

- (void)initFakeData
{
    NSMutableArray *mutableChartData = [NSMutableArray array];
    for (int i=0; i<kJBBarChartViewControllerNumBars-1; i++)
    {
        [mutableChartData addObject:[NSNumber numberWithInt:[self getRandomNumberBetween:15 to:100]]];
        
    }
    [mutableChartData addObject:[NSNumber numberWithInt:100]];
    _chartData = [NSArray arrayWithArray:mutableChartData];
}


- (void)viewDidLoad
{
    [super viewDidLoad];
    [self initFakeData];
    
    // Do any additional setup after loading the view.
    JBBarChartView *barChartView = [[JBBarChartView alloc] init];
    self.barChartView.frame = CGRectMake(kJBBarChartViewControllerChartPadding, kJBBarChartViewControllerChartPadding, self.barChartView.bounds.size.width - (kJBBarChartViewControllerChartPadding * 2), self.barChartView.bounds.size.height);
    self.barChartView.delegate = self;
    self.barChartView.dataSource = self;
    NSString *title = @"Boiler Efficiency";
    self.barChartTitle.text = title;
    
    NSNumber *average = [self meanOf:_chartData];
    self.individualBarValue.text = [NSString stringWithFormat:@"%@%@", average, @"%"];
    
    [self.view addSubview:barChartView];
    
    [self.barChartView reloadData];

}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (NSUInteger)numberOfBarsInBarChartView:(JBBarChartView *)barChartView
{
    return kJBBarChartViewControllerNumBars;
}

- (NSUInteger)barPaddingForBarChartView:(JBBarChartView *)barChartView
{
    return kJBBarChartViewControllerBarPadding;
}

- (UIColor *)barSelectionColorForBarChartView:(JBBarChartView *)barChartView
{
    return [UIColor whiteColor];
}

- (CGFloat)barChartView:(JBBarChartView *)barChartView heightForBarViewAtAtIndex:(NSUInteger)index{
    
    return [[self.chartData objectAtIndex:index] floatValue];
}

- (UIView *)barChartView:(JBBarChartView *)barChartView barViewAtIndex:(NSUInteger)index
{
    UIView *barView = [[UIView alloc] init];
    barView.backgroundColor = (index % 2 == 0) ? kJBColorBarChartBarBlue : kJBColorBarChartBarGreen;
    return barView;
}

- (void)barChartView:(JBBarChartView *)barChartView didSelectBarAtIndex:(NSUInteger)index touchPoint:(CGPoint)touchPoint
{
    // Update view
     NSNumber *valueNumber = [self.chartData objectAtIndex:index];
    NSString *noValue = [NSString stringWithFormat:@"%@%@", valueNumber, @"%"];
    self.individualBarValue.text = noValue;
    self.individualBarText.text = @"Efficiency";
}

- (void)didUnselectBarChartView:(JBBarChartView *)barChartView
{
     self.individualBarText.text = @"Average Efficiency";
    NSNumber *average = [self meanOf:_chartData];
    self.individualBarValue.text = [NSString stringWithFormat:@"%@%@", average, @"%"];

}

- (NSNumber *)meanOf:(NSArray *)array
{
    double runningTotal = 0.0;
    
    for(NSNumber *number in array)
    {
        runningTotal += [number doubleValue];
    }
    
    return [NSNumber numberWithDouble:(runningTotal / [array count])];
}

-(int)getRandomNumberBetween:(int)from to:(int)to {
    
    return (int)from + arc4random() % (to-from+1);
}


/*
#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender
{
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
}
*/

@end
