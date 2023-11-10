#import "CustomBrowserViewController.h"
@import BuzzvilSDK;

@interface CustomBrowserViewController ()

@property (nonatomic, strong, readonly) UIViewController *browserViewController;

@end

@implementation CustomBrowserViewController

- (void)viewDidLoad {
  [super viewDidLoad];
  
  _browserViewController = [[BuzzAdBrowser sharedInstance] browserViewController];
  [self displayContentViewController:_browserViewController];
}

- (void)displayContentViewController:(UIViewController *)contentViewController {
  [self addChildViewController:contentViewController];
  contentViewController.view.frame = self.view.bounds;
  [self.view addSubview:contentViewController.view];
  [contentViewController didMoveToParentViewController:self];
}

@end
