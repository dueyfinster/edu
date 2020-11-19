//
//  MapVC.h
//  Grogan Burner Services
//
//  Created by Neil Grogan on 01/04/2014.
//  Copyright (c) 2014 Grogan Burner Services. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <MapKit/MapKit.h>

#define LOC @"Location"
#define LAT @"lat"
#define LON @"long"


#define OFFICE_NAME @"Grogan Burner Services Office"
#define OFFICE_ADDRESS @"Ballyda, Danesfort, Co. Kilkenny"

#define ENGINEER_NAME @"Michael Grogan"
#define ENGINEER_TITLE @"GBS Service Engineer"




@class MapVC;

@protocol MapVCDelegate <NSObject>
- (void)mapVCDelegate:(MapVC *)mapVC didUpdateUserLocation:(CLLocationCoordinate2D)location;
@end


@interface MapVC : UIViewController <MKMapViewDelegate>
@property (weak, nonatomic) IBOutlet MKMapView *mapView;
@property (weak, nonatomic) IBOutlet MKMapView *activityIndicator;
@property (nonatomic, assign) id<MapVCDelegate> delegate;
@end
