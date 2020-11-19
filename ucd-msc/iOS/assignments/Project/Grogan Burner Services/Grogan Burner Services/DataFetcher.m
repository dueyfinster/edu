//
//  DataFetcher.m
//  Grogan Burner Services
//
//  Created by Neil Grogan on 08/04/2014.
//  Copyright (c) 2014 Grogan Burner Services. All rights reserved.
//

#import "DataFetcher.h"

@implementation DataFetcher


+ (NSURL *)getURL:(NSString *)urlPart{
    
    NSBundle* mainBundle = [NSBundle mainBundle];
    NSURL *url = [[NSURL alloc] initWithString:[[mainBundle objectForInfoDictionaryKey:@"url"] stringByAppendingString:urlPart]];
    
    return url;
}

+ (void)startFetch:(NSURL *)url completion:(void (^)(NSData *data))completion
{
    NSURLRequest *request = [NSURLRequest requestWithURL:url];
    NSURLSessionConfiguration *configuration = [NSURLSessionConfiguration ephemeralSessionConfiguration];
    NSURLSession *session = [NSURLSession sessionWithConfiguration:configuration];
    NSURLSessionDownloadTask *task = [session downloadTaskWithRequest:request completionHandler:^(NSURL *location, NSURLResponse *response, NSError *error) {
        NSData *data = nil;
        if (!error)
        {
            data = [NSData dataWithContentsOfURL:location];
        }
        completion(data);
    }];
    [task resume];
}




- (void)locationsFromJSONFile:(NSURL *)url {
    
    // Create a NSURLRequest with the given URL
    NSURLRequest *request = [NSURLRequest requestWithURL:url
                                             cachePolicy:NSURLRequestReloadIgnoringLocalAndRemoteCacheData
                                         timeoutInterval:30.0];
    
    // Get the data
    NSURLResponse *response;
    NSData *data = [NSURLConnection sendSynchronousRequest:request returningResponse:&response error:nil];
    
    if(data != nil){
        // Now create a NSDictionary from the JSON data
        NSError *e = nil;
        NSArray *jsonArray = [NSJSONSerialization JSONObjectWithData:data options: NSJSONReadingMutableContainers error: &e];
        
        if (!jsonArray) {
            NSLog(@"Error parsing JSON: %@", e);
        } else {
            for(NSDictionary *item in jsonArray) {
                NSLog(@"Item: %@", item);
            }
        }
        
        
        NSError *jsonError = nil;
        id jsonObject = [NSJSONSerialization JSONObjectWithData:data options:kNilOptions error:&jsonError];
        
        if ([jsonObject isKindOfClass:[NSArray class]]) {
            NSLog(@"its an array!");
            NSArray *jsonArray = (NSArray *)jsonObject;
            NSLog(@"jsonArray - %@",jsonArray[0]);
        }
        else {
            NSLog(@"its probably a dictionary");
            NSDictionary *jsonDictionary = (NSDictionary *)jsonObject;
            NSLog(@"jsonDictionary - %@",jsonDictionary);
        }
        
        NSError *e2 = nil;
        NSArray *JSONarray = [NSJSONSerialization JSONObjectWithData: data options: NSJSONReadingMutableContainers error: &e2];
        for(int i=0;i<[JSONarray count];i++)
        {
            /*
            NSArray *ar2 = [[JSONarray objectAtIndex:i]objectForKey:LOC];
            NSLog(@"%@",[[ar2 objectAtIndex:i]objectForKey:LAT]);
            NSLog(@"%@",[[ar2 objectAtIndex:i]objectForKey:LON]);
            double lat = [[[ar2 objectAtIndex:i]objectForKey:LAT] doubleValue];
            double lon = [[[ar2 objectAtIndex:i]objectForKey:LON] doubleValue];
            coordinate = CLLocationCoordinate2DMake(lat,lon);
             */
        }
        
        
        
        /* Iterate through the array of dictionaries
         //for(NSDictionary *dict in tArray) {
         // Create a new Location object for each one and initialise it with information in the dictionary
         //Location *location = [[Location alloc] initWithJSONDictionary:dict];
         /// Add the Location object to the array
         //[locations addObject:location];
         
         NSArray *loc = [dict objectForKey:@"Location"];
         
         NSLog(@"----");
         NSLog(@"Latitude: %@", loc[0] );
         //NSLog(@"Longitude: %@", loc[1] );
         NSLog(@"----");
         }*/
    }
}

@end
