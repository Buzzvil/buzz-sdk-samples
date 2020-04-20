//
//  ViewController.h
//  BABSample
//
//  Created by Jaehee Ko on 18/12/2018.
//  Copyright © 2018 Buzzvil. All rights reserved.
//

#import <UIKit/UIKit.h>

@class BABNativeAdView;
@class BABMediaView;
@class CarouselView;

@interface ViewController : UIViewController

@property (nonatomic, strong) IBOutlet BABNativeAdView *adView;
@property (nonatomic, strong) IBOutlet UILabel *titleLabel;
@property (nonatomic, strong) IBOutlet UILabel *descriptionLabel;
@property (nonatomic, strong) IBOutlet UIImageView *iconImageView;
@property (nonatomic, strong) IBOutlet UIButton *ctaButton;
@property (nonatomic, strong) IBOutlet BABMediaView *mediaView;
@property (nonatomic, strong) IBOutlet UISwitch *launcherSwitch;
@property (nonatomic, strong) IBOutlet UISwitch *shouldClickSwitch;
@property (nonatomic, strong) IBOutlet UIBarButtonItem *loginButton;
@property (nonatomic, strong) IBOutlet CarouselView *carouselView;
@property (nonatomic, strong) IBOutlet UIView *container;
@end

