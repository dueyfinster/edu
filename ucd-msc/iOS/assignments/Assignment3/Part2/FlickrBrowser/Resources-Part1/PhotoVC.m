//
//  PhotoVC.m
//  FlickrBrowser
//
//  Created by comp41550 on 18/03/2014.
//  Copyright (c) 2014 comp41550. All rights reserved.
//

#import "PhotoVC.h"
#import "FlickrFetcher.h"
#import "AppDelegate.h"
#import "Photo.h"

@interface PhotoVC ()
@property (nonatomic, weak) IBOutlet UIImageView *imageView;
@end

@implementation PhotoVC
// CoreData 
@synthesize managedObjectContext = _managedObjectContext;
@synthesize managedObjectModel = _managedObjectModel;
@synthesize persistentStoreCoordinator = _persistentStoreCoordinator;

- (void)viewDidLoad
{
    [super viewDidLoad];
    AppDelegate *appDelegate = [[UIApplication sharedApplication]delegate];
    _managedObjectContext = [appDelegate managedObjectContext];
}

- (void)setPhotoURL:(NSURL *)photoURL
{
    _photoURL = photoURL;

    UIActivityIndicatorView *spinner = [[UIActivityIndicatorView alloc] initWithActivityIndicatorStyle:UIActivityIndicatorViewStyleGray];
    [spinner startAnimating];
    UIBarButtonItem *button = self.navigationItem.rightBarButtonItem;
    self.navigationItem.rightBarButtonItem = [[UIBarButtonItem alloc] initWithCustomView:spinner];
    
    [FlickrFetcher startFlickrFetch:_photoURL completion:^(NSData *data) {
        if (data)
        {
            UIImage *image = [UIImage imageWithData:data];
            dispatch_async(dispatch_get_main_queue(), ^{
                self.imageView.image = image;
                [spinner stopAnimating];
                self.navigationItem.rightBarButtonItem = button;
            });
        }
    }];
}



- (IBAction)favouritesButton:(id)sender {
    NSLog(@"Neil is cool");
    NSLog( @"url is %@", [_photoURL absoluteString]);
    NSLog( @"title is %@", self.navigationItem.title);
    Photo *photo = nil;
    photo = [NSEntityDescription insertNewObjectForEntityForName:@"Photo" inManagedObjectContext:_managedObjectContext];
    photo.imageURL = [_photoURL absoluteString];
    photo.title =self.navigationItem.title;

    //myPhoto.property. //= [_photoURL absoluteString];
    //[myPhoto setImageUrl: [_photoURL absoluteString]];
}
@end
