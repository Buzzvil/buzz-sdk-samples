@import BuzzvilSDK;
#import "BenefitHubContainerViewController.h"

@interface BenefitHubContainerViewController ()

@property (nonatomic, strong, readonly) BuzzBenefitHub *benefitHub;

@end

@implementation BenefitHubContainerViewController

- (void)viewDidLoad {
  [super viewDidLoad];
  
  _benefitHub = [[BuzzBenefitHub alloc] init];
  [self displayContentViewController:[_benefitHub getViewController]];
}

- (void)displayContentViewController:(UIViewController*)contentViewController {
  [self addChildViewController:contentViewController];
  contentViewController.view.frame = self.view.bounds;
  [self.view addSubview:contentViewController.view];
  [contentViewController didMoveToParentViewController:self];
}

@end
