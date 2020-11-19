//
//  MapVC.m
//  Grogan Burner Services
//
//  Created by Neil Grogan on 01/04/2014.
//  Copyright (c) 2014 Grogan Burner Services. All rights reserved.
//

#import <MapKit/MapKit.h>
#import "MapVC.h"
#import "FixedAnnotation.h"
#import "LiveAnnotation.h"
#import "SVPulsingAnnotationView.h"
#import "DataFetcher.h"

@interface MapVC ()
@property (nonatomic, strong) id<MKAnnotation> annotation;
@property (nonatomic, strong) id<MKAnnotation> myLoc;
@end

@implementation MapVC


- (FixedAnnotation *)annotation
{
    if (!_annotation)
    {
        _annotation = [FixedAnnotation new];
    }
    return _annotation;
}

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
    }
    return self;
}

- (void)viewWillAppear:(BOOL)animated
{
    [super viewWillAppear:animated];
    [self.mapView setShowsUserLocation: YES];
}

- (void)viewWillDisappear:(BOOL)animated
{
    [super viewWillDisappear:animated];
    [self.mapView setShowsUserLocation: NO];
}


- (void)viewDidLoad
{
    [super viewDidLoad];
    
    self.mapView.delegate = self;
        
    CLLocationCoordinate2D coordinate = CLLocationCoordinate2DMake(52.5810797,
                                                                   -7.2450925);
    
    FixedAnnotation* pinAnnotation = [[FixedAnnotation alloc] initWithCoordinates:coordinate
                                        placeName:OFFICE_NAME
                                      description:OFFICE_ADDRESS];
    
    [self.mapView addAnnotation:pinAnnotation];
    [self liveLocationFromServer];
    
}

- (IBAction)refreshMap:(id)sender {
    [self liveLocationFromServer];
}

- (void) liveLocationFromServer{
    __block CLLocationCoordinate2D coordinate;
    
    UIActivityIndicatorView *spinner = [[UIActivityIndicatorView alloc] initWithActivityIndicatorStyle:UIActivityIndicatorViewStyleGray];
    [spinner startAnimating];
    UIBarButtonItem *button = self.navigationItem.rightBarButtonItem;
    self.navigationItem.rightBarButtonItem = [[UIBarButtonItem alloc] initWithCustomView:spinner];
    
    [DataFetcher startFetch:[DataFetcher getURL:@"location"] completion:^(NSData *jsonData) {
        if (jsonData)
        {
            NSError *e2 = nil;
            NSArray *JSONarray = [NSJSONSerialization JSONObjectWithData:jsonData options: NSJSONReadingMutableContainers error: &e2];
            for(int i=0;i<[JSONarray count];i++)
            {
                NSArray *ar2 = [[JSONarray objectAtIndex:i]objectForKey:LOC];
                NSLog(@"%@",[[ar2 objectAtIndex:i]objectForKey:LAT]);
                NSLog(@"%@",[[ar2 objectAtIndex:i]objectForKey:LON]);
                double lat = [[[ar2 objectAtIndex:i]objectForKey:LAT] doubleValue];
                double lon = [[[ar2 objectAtIndex:i]objectForKey:LON] doubleValue];
                coordinate = CLLocationCoordinate2DMake(lat,lon);
            }

        dispatch_async(dispatch_get_main_queue(), ^{
            [spinner stopAnimating];
            self.navigationItem.rightBarButtonItem = button;
            [self.mapView removeAnnotation:_myLoc];
            _myLoc = [[LiveAnnotation alloc] initWithCoordinates:coordinate
                                                       placeName:ENGINEER_NAME
                                                     description:ENGINEER_TITLE];
            [self.mapView addAnnotation:_myLoc];
            
        });

        }
    }];
}


- (MKAnnotationView *)mapView:(MKMapView *)mapView viewForAnnotation:(id<MKAnnotation>)annotation {
    if([annotation isKindOfClass:[LiveAnnotation class]]) {
        static NSString *identifier = @"currentLocation";
        SVPulsingAnnotationView *pulsingView = (SVPulsingAnnotationView *)[self.mapView dequeueReusableAnnotationViewWithIdentifier:identifier];
        
        if(pulsingView == nil) {
            pulsingView = [[SVPulsingAnnotationView alloc] initWithAnnotation:annotation reuseIdentifier:identifier];
            pulsingView.annotationColor = [UIColor colorWithRed:0.678431 green:0 blue:0 alpha:1];
        }
        
        pulsingView.canShowCallout = YES;
        return pulsingView;
    }
    
    return nil;
}


- (void)mapView:(MKMapView *)mapView didUpdateUserLocation:(MKUserLocation *)userLocation
{
    MKCoordinateRegion region = MKCoordinateRegionMakeWithDistance(userLocation.coordinate, 200000, 200000);

[self.mapView setRegion:[self.mapView regionThatFits:region] animated:YES];

}


- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
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
