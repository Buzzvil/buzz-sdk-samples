@import BuzzvilSDK;
#import "BenefitHubNavigationCustomContainerViewController.h"

@interface BenefitHubNavigationCustomContainerViewController()

@property (nonatomic, strong, readonly) BuzzBenefitHub *benefitHub;

@end

@implementation BenefitHubNavigationCustomContainerViewController

- (void)viewDidLoad {
  [super viewDidLoad];
  
  self.navigationController.delegate = self;
  
  _benefitHub = [[BuzzBenefitHub alloc] init];
  BuzzBenefitHubConfig * buzzBenefitConfig = [BuzzBenefitHubConfig configWith:^(BuzzBenefitHubConfigBuilder * _Nonnull builder) {
    builder.isNavigationBarHidden = NO;
  }];
  [_benefitHub setConfig:buzzBenefitConfig];
  
  [self displayContentViewController:[_benefitHub createViewController]];
}

- (void)displayContentViewController:(UIViewController*)contentViewController {
  [self addChildViewController:contentViewController];
  contentViewController.view.frame = self.view.bounds;
  [self.view addSubview:contentViewController.view];
  [contentViewController didMoveToParentViewController:self];
}

- (void)backButtonTapped:(id)sender {
  [self.benefitHub handleBackNavigation];
}

- (void)customNavigationBar:(UINavigationController*)navigationController viewController:(UIViewController*)viewController {
  navigationController.navigationBar.tintColor = UIColor.redColor;
  viewController.navigationItem.largeTitleDisplayMode = UINavigationItemLargeTitleDisplayModeNever;
  UIBarButtonItem * navigationButton = [[UIBarButtonItem alloc] initWithTitle:@"Back" style:UIBarButtonItemStylePlain target:self action:@selector(backButtonTapped:)];
  viewController.navigationItem.leftBarButtonItem = navigationButton;
  
  UINavigationBar * navigationBar = navigationController.navigationBar;
  UINavigationBarAppearance * appearance = [[UINavigationBarAppearance alloc] init];
  [appearance configureWithOpaqueBackground];
  appearance.backgroundColor = UIColor.systemBlueColor;
  
  navigationBar.standardAppearance = appearance;
  navigationBar.scrollEdgeAppearance = appearance;
}

- (void)navigationController:(UINavigationController *)navigationController willShowViewController:(UIViewController *)viewController animated:(BOOL)animated {
  [self customNavigationBar:navigationController viewController:viewController];
}

@end

