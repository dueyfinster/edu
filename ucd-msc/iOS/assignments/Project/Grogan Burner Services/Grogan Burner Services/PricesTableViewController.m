//
//  PricesTableViewController.m
//  Grogan Burner Services
//
//  Created by Neil Grogan on 08/04/2014.
//  Copyright (c) 2014 Grogan Burner Services. All rights reserved.
//

#import "PricesTableViewController.h"
#import "DataFetcher.h"

@interface PricesTableViewController ()
@property (nonatomic, strong) NSMutableArray *dataModel;
@property (weak, nonatomic) IBOutlet UIBarButtonItem *refreshButton;
@property (nonatomic, strong) UIActivityIndicatorView *spinner;
@property (nonatomic, strong, readwrite) NSMutableSet* delegates;
@end

@implementation PricesTableViewController


- (NSMutableArray *)dataModel
{
    if (!_dataModel)
    {
        _dataModel = [NSMutableArray array];
    }
    return _dataModel;
}

- (id)initWithStyle:(UITableViewStyle)style
{
    self = [super initWithStyle:style];
    if (self) {
        // Custom initialization
    }
    return self;
}

- (void)viewDidLoad
{
    [super viewDidLoad];
    self.dataModel = nil;
    [self.tableView reloadData];
    
    [self pricesFromServer];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}
- (IBAction)refreshPricesFromServer:(id)sender {
    self.dataModel = nil;
    [self.tableView reloadData];
    [self pricesFromServer];
}

- (void) pricesFromServer{
    
    self.spinner = [[UIActivityIndicatorView alloc] initWithActivityIndicatorStyle:UIActivityIndicatorViewStyleGray];
    [self.spinner startAnimating];
    UIBarButtonItem *button = _refreshButton;
    self.navigationItem.rightBarButtonItem = [[UIBarButtonItem alloc] initWithCustomView:self.spinner];
    
    [DataFetcher startFetch:[DataFetcher getURL:@"price"] completion:^(NSData *jsonData) {
        if (jsonData)
        {
            NSError *e2 = nil;
            NSArray *JSONarray = [NSJSONSerialization JSONObjectWithData:jsonData options: NSJSONReadingMutableContainers error: &e2];
            for(int i=0;i<[JSONarray count];i++)
            {
                
                NSArray *ar2 = [[JSONarray objectAtIndex:i]objectForKey:PRICES];
                NSLog(@"%@",[[ar2 objectAtIndex:i]objectForKey:OIL]);
                NSLog(@"%@",[[ar2 objectAtIndex:i]objectForKey:GAS]);
                //double oil = [[[ar2 objectAtIndex:i]objectForKey:OIL] doubleValue];
                //NSString *gas = [[ar2 objectAtIndex:i]objectForKey:GAS];
                 //double gas = [[[ar2 objectAtIndex:i]objectForKey:BER] doubleValue];
                NSMutableDictionary *tags = [ar2 objectAtIndex:i]; //[[ar2 objectAtIndex:i]objectForKey:GAS];
                
                NSArray *keys;
                int i, count;
                id key;
                NSArray *value;
                
                keys = [tags allKeys];
                count = (int)[keys count];
                for (i = 0; i < count; i++)
                {
                    key = [keys objectAtIndex: i];
                    value = [tags objectForKey: key];
                    [self.dataModel addObject:@{@"section_name":key,@"section_content" : value}];
                    NSLog (@"Key: %@ for value: %@", key, value);
                }
                //[tags arrayByAddingObject:[[ar2 objectAtIndex:i]objectForKey:GAS]];
                //for(NSMutableDictionary *oj in tags){
                //
                //}
                
            }
            
            dispatch_async(dispatch_get_main_queue(), ^{
                [self.tableView reloadData];
                [self.spinner stopAnimating];
                self.navigationItem.rightBarButtonItem = button;
            });
        }else{
            /*UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"No network connection"
                                                            message:@"You must be connected to the internet to use this app."
                                                           delegate:nil
                                                  cancelButtonTitle:@"OK"
                                                  otherButtonTitles:nil];
            [alert show];*/
        }
    }];
}

-(void) alertView:(UIAlertView *)alertView clickedButtonAtIndex:(NSInteger)buttonIndex{
    if (buttonIndex == 0) {
        
        [self.spinner stopAnimating];
        
        self.navigationItem.rightBarButtonItem = _refreshButton;
    }
    
}

#pragma mark - Table view data source

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView
{
    return 1;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
    return self.dataModel.count;
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:@"Cell" forIndexPath:indexPath];
    
    //NSArray *sectionContent = self.dataModel[indexPath.section][@"section_content"];
    
    // Configure the cell...
    cell.textLabel.text =  self.dataModel[indexPath.row][@"section_name"];
    NSString *detail = [self.dataModel[indexPath.row][@"section_content"] description];
    NSNumberFormatter *f = [[NSNumberFormatter alloc] init];
    NSNumber *numpric = [f numberFromString: detail];
    
    if(numpric != nil){
        cell.detailTextLabel.text = [NSString stringWithFormat:@"%@%@", EURO, detail];
    }else{
        cell.detailTextLabel.text =  detail;
    }
    
    
    
    return cell;
}


- (NSDictionary *) indexKeyedDictionaryFromArray:(NSArray *)array
{
    id objectInstance;
    NSUInteger indexKey = 0;
    
    NSMutableDictionary *mutableDictionary = [[NSMutableDictionary alloc] init];
    for (objectInstance in array)
        [mutableDictionary setObject:[NSNumber numberWithUnsignedInt:indexKey++] forKey:objectInstance];
    
    return mutableDictionary;
}

/*
// Override to support conditional editing of the table view.
- (BOOL)tableView:(UITableView *)tableView canEditRowAtIndexPath:(NSIndexPath *)indexPath
{
    // Return NO if you do not want the specified item to be editable.
    return YES;
}
*/

/*
// Override to support editing the table view.
- (void)tableView:(UITableView *)tableView commitEditingStyle:(UITableViewCellEditingStyle)editingStyle forRowAtIndexPath:(NSIndexPath *)indexPath
{
    if (editingStyle == UITableViewCellEditingStyleDelete) {
        // Delete the row from the data source
        [tableView deleteRowsAtIndexPaths:@[indexPath] withRowAnimation:UITableViewRowAnimationFade];
    } else if (editingStyle == UITableViewCellEditingStyleInsert) {
        // Create a new instance of the appropriate class, insert it into the array, and add a new row to the table view
    }   
}
*/

/*
// Override to support rearranging the table view.
- (void)tableView:(UITableView *)tableView moveRowAtIndexPath:(NSIndexPath *)fromIndexPath toIndexPath:(NSIndexPath *)toIndexPath
{
}
*/

/*
// Override to support conditional rearranging of the table view.
- (BOOL)tableView:(UITableView *)tableView canMoveRowAtIndexPath:(NSIndexPath *)indexPath
{
    // Return NO if you do not want the item to be re-orderable.
    return YES;
}
*/

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
