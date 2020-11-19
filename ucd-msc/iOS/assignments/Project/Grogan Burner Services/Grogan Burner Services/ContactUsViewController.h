//
//  ContactUsViewController.h
//  Grogan Burner Services
//
//  Created by Neil Grogan on 18/04/2014.
//  Copyright (c) 2014 Grogan Burner Services. All rights reserved.
//

#import "FXForms.h"
#import <MessageUI/MessageUI.h>

@interface ContactUsViewController : FXFormViewController <FXFormFieldViewController, MFMessageComposeViewControllerDelegate, MFMailComposeViewControllerDelegate, UIActionSheetDelegate>
// CoreData
@property (readonly, strong, nonatomic) NSManagedObjectContext *managedObjectContext;
@property (readonly, strong, nonatomic) NSManagedObjectModel *managedObjectModel;
@property (readonly, strong, nonatomic) NSPersistentStoreCoordinator *persistentStoreCoordinator;
@end
