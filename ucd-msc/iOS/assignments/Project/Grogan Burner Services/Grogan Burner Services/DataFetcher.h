//
//  DataFetcher.h
//  Grogan Burner Services
//
//  Created by Neil Grogan on 08/04/2014.
//  Copyright (c) 2014 Grogan Burner Services. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface DataFetcher : NSObject
+ (NSURL *) getURL:(NSString *)urlPart;
+ (void) startFetch:(NSURL *)url completion:(void (^)(NSData *data))completion;
@end
