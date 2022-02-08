#import "CustomBrowserViewController.h"
#import <BuzzAdBenefitBase/BuzzAdBenefitBase.h>
#import <BuzzAdBenefitNative/BuzzAdBenefitNative.h>

@interface CustomBrowserViewController ()

@property (nonatomic, strong, readonly) UIButton *closeButton;
@property (nonatomic, strong, readonly) UIViewController *browserViewController;

@end

@implementation CustomBrowserViewController

- (void)viewDidLoad {
  [super viewDidLoad];

  [self setupView];
  [self setupLayout];
  [self setupEvent];
}

- (void)setupView {
  self.view.backgroundColor = UIColor.whiteColor;

  _closeButton = [UIButton buttonWithType:UIButtonTypeSystem];
  [_closeButton setImage:[UIImage imageNamed:@"ic_close"] forState:UIControlStateNormal];
  [self.view addSubview:_closeButton];

  _browserViewController = [BuzzAdBrowser.sharedInstance browserViewController];
  [self addChildViewController:_browserViewController];
  [self.view addSubview:_browserViewController.view];
  [_browserViewController didMoveToParentViewController:self];
}

- (void)setupLayout {
  _closeButton.translatesAutoresizingMaskIntoConstraints = NO;
  [NSLayoutConstraint activateConstraints:@[
    [_closeButton.trailingAnchor constraintEqualToAnchor:self.view.layoutMarginsGuide.trailingAnchor],
    [_closeButton.topAnchor constraintEqualToAnchor:self.view.layoutMarginsGuide.topAnchor constant:16],
  ]];

  _browserViewController.view.translatesAutoresizingMaskIntoConstraints = NO;
  [NSLayoutConstraint activateConstraints:@[
    [_browserViewController.view.leadingAnchor constraintEqualToAnchor:self.view.leadingAnchor],
    [_browserViewController.view.trailingAnchor constraintEqualToAnchor:self.view.trailingAnchor],
    [_browserViewController.view.topAnchor constraintEqualToAnchor:_closeButton.bottomAnchor constant:16],
    [_browserViewController.view.bottomAnchor constraintEqualToAnchor:self.view.bottomAnchor],
  ]];
}

- (void)setupEvent {
  [_closeButton addGestureRecognizer:[[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(dismiss:)]];
}

- (void)dismiss:(id)sender {
  [self dismissViewControllerAnimated:YES completion:nil];
}

@end
