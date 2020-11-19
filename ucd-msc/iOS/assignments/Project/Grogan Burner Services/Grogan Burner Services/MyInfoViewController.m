//
//  YourInfoViewController.m
//  Grogan Burner Services
//
//  Created by Neil Grogan on 06/04/2014.
//  Copyright (c) 2014 Grogan Burner Services. All rights reserved.
//

#import "MyInfoViewController.h"
#import "AppDelegate.h"
#import <FacebookSDK/FacebookSDK.h>
#import "Customer.h"

@interface MyInfoViewController () <FBLoginViewDelegate>
@property (strong, nonatomic) IBOutlet UILabel *statusLabel;
@property (strong, nonatomic) IBOutlet FBProfilePictureView *profilePictureView;
@property (weak, nonatomic) IBOutlet FBLoginView *loginView;
@property (strong, nonatomic) IBOutlet UILabel *nameLabel;

@end

@implementation MyInfoViewController

// CoreData
@synthesize managedObjectContext = _managedObjectContext;
@synthesize managedObjectModel = _managedObjectModel;
@synthesize persistentStoreCoordinator = _persistentStoreCoordinator;


- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
    }
    return self;
}

- (void)viewDidLoad
{
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    _loginView.readPermissions = @[@"basic_info", @"email", @"user_likes"];
     _loginView.delegate = self;
    AppDelegate *appDelegate = [[UIApplication sharedApplication]delegate];
    _managedObjectContext = [appDelegate managedObjectContext];
}

// This method will be called when the user information has been fetched
- (void)loginViewFetchedUserInfo:(FBLoginView *)loginView
                            user:(id<FBGraphUser>)user {
    self.profilePictureView.profileID = user.id;
    self.nameLabel.text = user.name;
   // NSLog(@"%@",[user objectForKey:@"email"]);
     //NSLog(@"%@",[user objectForKey:@"name"]);
    
    NSString *email = (NSString *)[user objectForKey:@"email"];
    Customer *lookedup = [self getCustomerInfo:email];
    
    if(lookedup == nil){
        Customer *customer = nil;
        customer = [NSEntityDescription insertNewObjectForEntityForName:@"Customer" inManagedObjectContext:_managedObjectContext];
        NSString *name = [user name];
        [customer setEmail:email];
        [customer setName:name];
    }else{
        lookedup.email = [user objectForKey:@"email"];
        lookedup.name = [user objectForKey:@"name"];
        NSError *error;
        [self.managedObjectContext save:&error];
    }
}

-(Customer *) getCustomerInfo:(NSString*) email{
    Customer *cust = nil;
    
    // Retrieve the entity from the local store -- much like a table in a database
    NSEntityDescription *entity = [NSEntityDescription entityForName:@"Customer" inManagedObjectContext:_managedObjectContext];
    NSFetchRequest *request = [[NSFetchRequest alloc] init];
    [request setEntity:entity];
    
    // Set the predicate -- much like a WHERE statement in a SQL database
    NSPredicate *predicate = [NSPredicate predicateWithFormat:@"email == %@", email];
    [request setPredicate:predicate];
    
    // Set the sorting -- mandatory, even if you're fetching a single record/object
    NSSortDescriptor *sortDescriptor = [[NSSortDescriptor alloc] initWithKey:@"email" ascending:YES];
    NSArray *sortDescriptors = [[NSArray alloc] initWithObjects:sortDescriptor, nil];
    [request setSortDescriptors:sortDescriptors];
    
    // Request the data -- NOTE, this assumes only one match
    NSError *error = nil;
    NSArray *objRet = [_managedObjectContext executeFetchRequest:request error:&error];
    
    if([objRet count] >0){
        cust = [objRet objectAtIndex:0];
    }
    
    return cust;
}

// Logged-in user experience
- (void)loginViewShowingLoggedInUser:(FBLoginView *)loginView {
    self.statusLabel.text = @"You're logged in as:";
}

// Logged-out user experience
- (void)loginViewShowingLoggedOutUser:(FBLoginView *)loginView {
    self.profilePictureView.profileID = nil;
    self.nameLabel.text = @"";
    self.statusLabel.text= @"You're not logged in!";
}

- (BOOL)openSessionWithAllowLoginUI:(BOOL)allowLoginUI completionHandler:(FBSessionStateHandler)handler {
    
    // We pass this permissions array into our request.
    // I only request email, but there are many more options.
    //
    NSArray *permissions = @[@"email", @"basic_info"];
    
    return [FBSession
            openActiveSessionWithReadPermissions:permissions
            allowLoginUI:allowLoginUI
            completionHandler:^(FBSession *session,
                                FBSessionState state,
                                NSError *error) {
                
                if (handler)
                    handler(session, state, error);
            }];
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
