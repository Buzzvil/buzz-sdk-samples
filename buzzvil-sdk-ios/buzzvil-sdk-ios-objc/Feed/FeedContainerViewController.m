@import BuzzvilSDK;
#import "FeedContainerViewController.h"

@interface FeedContainerViewController ()

@property (nonatomic, strong, readonly) BZVBuzzAdFeed *buzzAdFeed;

@end

@implementation FeedContainerViewController

- (void)viewDidLoad {
  [super viewDidLoad];

  _benefitHub = [BZVBuzzAdFeed feedWithBlock:^(BZVBenefitHubBuilder * _Nonnull builder) {}];
  [self displayContentViewController:[_buzzAdFeed viewController]];
}

- (void)displayContentViewController:(UIViewController*)contentViewController {
  [self addChildViewController:contentViewController];
  contentViewController.view.frame = self.view.bounds;
  [self.view addSubview:contentViewController.view];
  [contentViewController didMoveToParentViewController:self];
}

@end
