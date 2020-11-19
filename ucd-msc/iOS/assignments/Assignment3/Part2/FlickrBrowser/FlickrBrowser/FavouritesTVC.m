//
//  FavouritesTVC.m
//  FlickrBrowser
//
//  Created by Neil Grogan on 30/03/2014.
//  Copyright (c) 2014 Grogan Burner Services. All rights reserved.
//

#import "FavouritesTVC.h"
#import "AppDelegate.h"
#import "Photo+Flickr.h"
#import "FlickrFetcher.h"
#import "PhotosTVC.h"
#import "PhotoVC.h"

@interface FavouritesTVC ()
@property (nonatomic, strong) NSManagedObjectContext *context;
@property (nonatomic, strong) NSArray *photos;
@end

@implementation FavouritesTVC

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
    }
    return self;
}

- (NSManagedObjectContext *)context
{
    if (!_context)
    {
        _context = ((AppDelegate *)[UIApplication sharedApplication].delegate).managedObjectContext;
    }
    return _context;
}

- (void)setupFetchedResultsController
{
    NSFetchRequest *request = [NSFetchRequest fetchRequestWithEntityName:@"Photo"];
    request.sortDescriptors = @[[NSSortDescriptor sortDescriptorWithKey:@"title" ascending:YES selector:@selector(localizedCaseInsensitiveCompare:)]];
    self.fetchedResultsController = [[NSFetchedResultsController alloc] initWithFetchRequest:request managedObjectContext:self.context sectionNameKeyPath:nil cacheName:nil];
}

- (void)viewDidLoad
{
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    [self setupFetchedResultsController];
}

- (BOOL)tableView:(UITableView *)tableView canEditRowAtIndexPath:(NSIndexPath *)indexPath
{
    return YES;
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}


- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:@"Cell" forIndexPath:indexPath];
    
    // Configure the cell...
    Photo *photo = [self.fetchedResultsController objectAtIndexPath:indexPath];
    cell.textLabel.text = photo.title;
    //cell.detailTextLabel.text = [NSString stringWithFormat:@"%d", photographer.photos.count];
    
    UIGraphicsBeginImageContextWithOptions(CGSizeMake(cell.contentView.frame.size.height, cell.contentView.frame.size.height), NO, 0.0);
    cell.imageView.image = UIGraphicsGetImageFromCurrentImageContext();
    cell.imageView.contentMode = UIViewContentModeScaleAspectFit;
    UIGraphicsEndImageContext();
    
    NSURL *url = [[NSURL alloc] initWithString:photo.imageURL];
    [FlickrFetcher startFlickrFetch:url completion:^(NSData *jsonData) {
        if (jsonData)
        {
            UIImage *thumbnailImage = [UIImage imageWithData:jsonData];
            dispatch_async(dispatch_get_main_queue(), ^{
                cell.imageView.image = thumbnailImage;
            });
        }
    }];
    
    return cell;
}


- (void)tableView:(UITableView *)tableView commitEditingStyle:(UITableViewCellEditingStyle)editingStyle forRowAtIndexPath:(NSIndexPath *)indexPath
{
    if (editingStyle == UITableViewCellEditingStyleDelete)
    {
        Photo *photo = [self.fetchedResultsController objectAtIndexPath:indexPath];
        [self.context deleteObject:photo];
    }
}

- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender
{
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
    
    if ([segue.identifier isEqualToString:@"Show PhotoVC"])
    {
        UITableViewCell *cell =  (UITableViewCell *)sender;
        NSIndexPath *indexPath = [self.tableView indexPathForCell:cell];
        Photo *photo = [self.fetchedResultsController objectAtIndexPath:indexPath];
        NSURL *url = [[NSURL alloc] initWithString:photo.imageURL];
        [segue.destinationViewController setPhotoURL:url];
        [segue.destinationViewController navigationItem].title = cell.textLabel.text;
    }
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
