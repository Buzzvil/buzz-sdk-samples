@import BuzzvilSDK;
#import "BenefitHubContainerViewController.h"

@interface BenefitHubContainerViewController ()

@property (nonatomic, strong, readonly) BZVBenefitHub *benefitHub;

@end

@implementation BenefitHubContainerViewController

- (void)viewDidLoad {
  [super viewDidLoad];

  _benefitHub = [BZVBenefitHub benefitHubWithBlock:^(BZVBenefitHubBuilder * _Nonnull builder) {}];
  [self displayContentViewController:[_benefitHub viewController]];
}

- (void)displayContentViewController:(UIViewController*)contentViewController {
  [self addChildViewController:contentViewController];
  contentViewController.view.frame = self.view.bounds;
  [self.view addSubview:contentViewController.view];
  [contentViewController didMoveToParentViewController:self];
}

@end
