//
//  ContactUsViewController.m
//  Grogan Burner Services
//
//  Created by Neil Grogan on 18/04/2014.
//  Copyright (c) 2014 Grogan Burner Services. All rights reserved.
//

#import "ContactUsViewController.h"
#import "ContactForm.h"
#import "FXForms.h"
#import "AppDelegate.h"
#import "Customer.h"
#import <EventKit/EventKit.h>


@interface ContactUsViewController ()
@property (weak, nonatomic) IBOutlet UITableView *tableView;
@property (strong, nonatomic) ContactForm *form;
@end

@implementation ContactUsViewController

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

- (void)viewWillAppear:(BOOL)animated
{
    [super viewWillAppear:animated];

    
}

- (void)viewDidLoad
{
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    AppDelegate *appDelegate = [[UIApplication sharedApplication]delegate];
    _managedObjectContext = [appDelegate managedObjectContext];
    FXFormViewController *controller = [[FXFormViewController alloc] init];
    ContactForm *cf = [[ContactForm alloc] init];
    
    Customer *me = [self getCustomerInfo];
    
    if(me != nil){
        cf.Name = me.name;
        cf.Email = me.email;
    }
    controller.formController.form = cf;
    self.formController.form = cf;
    [self.tableView reloadData];
   
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}


- (void)formSubmit:(UITableViewCell<FXFormFieldCell> *)cell
{
    self.form = cell.field.form;
    
    //NSLog(form.Name);
    
    if(self.form.Name == nil){
        [[[UIAlertView alloc] initWithTitle:@"Error" message:@"Please enter your name before proceeding!" delegate:nil cancelButtonTitle:nil otherButtonTitles:@"Okay", nil] show];
    }else if(self.form.Email == nil || self.form.phoneNo == nil){
        [[[UIAlertView alloc] initWithTitle:@"Error" message:@"You must enter either an email address or phone number so we can contact you!" delegate:nil cancelButtonTitle:nil otherButtonTitles:@"Okay", nil] show];
    }else{
        //Success
        //[[[UIAlertView alloc] initWithTitle:@"Login Form Submitted" message:nil delegate:nil cancelButtonTitle:nil otherButtonTitles:@"OK", nil] show];
        
        UIActionSheet *actionSheet = [[UIActionSheet alloc] initWithTitle:nil delegate:self cancelButtonTitle:@"Cancel" destructiveButtonTitle:nil otherButtonTitles:@"Send via SMS", @"Send via Email", nil];
        
        [actionSheet showInView:self.view];
    }
}

-(Customer *) getCustomerInfo{
    Customer *cust = nil;
    
    NSFetchRequest *request = [[NSFetchRequest alloc]initWithEntityName:@"Customer"];
    
    NSError *error = nil;
    
    NSArray *results = [_managedObjectContext executeFetchRequest:request error:&error];
    
    if([results count] >0){
        cust = [results objectAtIndex:0];
    }
    
    return cust;
}

- (void)actionSheet:(UIActionSheet *)actionSheet clickedButtonAtIndex:(NSInteger)buttonIndex
{
    NSLog(@"You pressed the %@ button", [actionSheet buttonTitleAtIndex:buttonIndex]);
    if(buttonIndex ==0){
        [self showSMS:self.form];
    }else if(buttonIndex == 1){
        [self showEmail:self.form];
    }
}

- (void)showSMS:(ContactForm *)form {
    
    if(![MFMessageComposeViewController canSendText]) {
        UIAlertView *warningAlert = [[UIAlertView alloc] initWithTitle:@"Error" message:@"Your device doesn't support SMS!" delegate:nil cancelButtonTitle:@"OK" otherButtonTitles:nil];
        [warningAlert show];
        return;
    }
    
    NSString *fuel;
    
    if(form.fuelType ==0){
        fuel = @"Oil";
    }else{
        fuel = @"Gas";
    }
    
    
    NSString *serviceType;
    
    if(form.request ==0){
        serviceType = @"Service";
    }else{
        serviceType = @"Repair";
    }
    
    NSArray *recipents = @[@"0873791474"];
    NSString *message = [NSString stringWithFormat:@"Hello Michael, I need a %@ for my %@ heating appliance. Regards, %@", serviceType, fuel, form.Name];
    
    MFMessageComposeViewController *messageController = [[MFMessageComposeViewController alloc] init];
    messageController.messageComposeDelegate = self;
    [messageController setRecipients:recipents];
    [messageController setBody:message];
    
    // Present message view controller on screen
    [self presentViewController:messageController animated:YES completion:nil];
}

- (void)messageComposeViewController:(MFMessageComposeViewController *)controller
                 didFinishWithResult:(MessageComposeResult)result {
    
    switch (result)
    {
        case MessageComposeResultCancelled:
            NSLog(@"Text Message cancelled");
            break;
        case MessageComposeResultFailed:
            NSLog(@"Text Message failed");
            break;
        case MessageComposeResultSent:
            NSLog(@"Text Message sent");
            [self performSegueWithIdentifier:@"sucContactForm" sender:self];
            break;
        default:
            break;
    }
    [self dismissViewControllerAnimated:YES completion:nil];
}


-(void)showEmail:(ContactForm *)form {
    NSString *fuel;
    
    if(form.fuelType ==0){
        fuel = @"Oil";
    }else{
        fuel = @"Gas";
    }
    
    
    NSString *serviceType;
    
    if(form.request ==0){
        serviceType = @"Service";
    }else{
        serviceType = @"Repair";
    }
    
    // Email Subject
    NSString *emailTitle = [NSString stringWithFormat:@"%@ %@ Request", fuel, serviceType];

    // Email Content
    NSString *messageBody = [NSString stringWithFormat:@"Hello Michael, I need a %@ for my %@ heating appliance. Regards, %@", serviceType, fuel, form.Name];

    // To address
    NSArray *toRecipents = [NSArray arrayWithObject:@"neil.grogan@ucdconnect.ie"];
    
    MFMailComposeViewController *mc = [[MFMailComposeViewController alloc] init];
    mc.mailComposeDelegate = self;
    [mc setSubject:emailTitle];
    [mc setMessageBody:messageBody isHTML:NO];
    [mc setToRecipients:toRecipents];
    
    // Present mail view controller on screen
    [self presentViewController:mc animated:YES completion:NULL];
}

- (void) mailComposeController:(MFMailComposeViewController *)controller didFinishWithResult:(MFMailComposeResult)result error:(NSError *)error
{
    switch (result)
    {
        case MFMailComposeResultCancelled:
            NSLog(@"Mail cancelled");
            break;
        case MFMailComposeResultSaved:
            NSLog(@"Mail saved");
            break;
        case MFMailComposeResultSent:
            NSLog(@"Mail sent");
            [self performSegueWithIdentifier:@"sucContactForm" sender:self];
            break;
        case MFMailComposeResultFailed:
            NSLog(@"Mail sent failure: %@", [error localizedDescription]);
            break;
        default:
            break;
    }
    
    // Close the Mail Interface
    [self dismissViewControllerAnimated:YES completion:NULL];
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

