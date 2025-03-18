#import "ViewController.h"
#import "UIButton+Custom.h"
#import "Native/NativeViewController.h"
#import "Native/Carousel/CarouselViewController.h"
#import "Interstitial/InterstitialViewController.h"
#import "BenefitHub/BenefitHubViewController.h"
#import "BenefitHub/BenefitHubContainerViewController.h"
#import "Banner/BannerViewController.h"

@interface ViewController ()

@property (nonatomic, strong, readonly) UIStackView *rootStackView;
@property (nonatomic, strong, readonly) UIButton *nativeButton;
@property (nonatomic, strong, readonly) UIButton *carouselButton;
@property (nonatomic, strong, readonly) UIButton *interstitialButton;
@property (nonatomic, strong, readonly) UIButton *benefitHubButton;
@property (nonatomic, strong, readonly) UIButton *benefitHubContainerButton;
@property (nonatomic, strong, readonly) UIButton *bannerButton;

@end

@implementation ViewController

- (void)viewDidLoad {
  [super viewDidLoad];
  [self setupView];
  [self setupLayout];
  [self setupEvent];
}

#pragma mark - UI setup
- (void)setupView {
  self.view.backgroundColor = [UIColor systemBackgroundColor];
  self.navigationItem.title = @"BuzzvilSDK-ObjectiveC";
  
  _rootStackView = [[UIStackView alloc] initWithFrame:CGRectZero];
  [self.view addSubview:_rootStackView];
  _rootStackView.axis = UILayoutConstraintAxisVertical;
  _rootStackView.spacing = 12;
  _rootStackView.distribution = UIStackViewDistributionFillEqually;
  
  _nativeButton = [UIButton buttonWithType:UIButtonTypeSystem];
  [_nativeButton setTitle:@"Native" forState:UIControlStateNormal];
  [_nativeButton applyCustomStyle];
  
  _interstitialButton = [UIButton buttonWithType:UIButtonTypeSystem];
  [_interstitialButton setTitle:@"Interstitial" forState:UIControlStateNormal];
  [_interstitialButton applyCustomStyle];
  
  _carouselButton = [UIButton buttonWithType:UIButtonTypeSystem];
  [_carouselButton setTitle:@"Carousel" forState:UIControlStateNormal];
  [_carouselButton applyCustomStyle];
  
  _benefitHubButton = [UIButton buttonWithType:UIButtonTypeSystem];
  [_benefitHubButton setTitle:@"BenefitHub" forState:UIControlStateNormal];
  [_benefitHubButton applyCustomStyle];
  
  _benefitHubContainerButton = [UIButton buttonWithType:UIButtonTypeSystem];
  [_benefitHubContainerButton setTitle:@"BenefitHub Container" forState:UIControlStateNormal];
  [_benefitHubContainerButton applyCustomStyle];
  
  _bannerButton = [UIButton buttonWithType:UIButtonTypeSystem];
  [_bannerButton setTitle:@"Banner" forState:UIControlStateNormal];
  [_bannerButton applyCustomStyle];
  
  [_rootStackView addArrangedSubview: _nativeButton];
  [_rootStackView addArrangedSubview: _interstitialButton];
  [_rootStackView addArrangedSubview: _benefitHubButton];
  [_rootStackView addArrangedSubview: _benefitHubContainerButton];
  [_rootStackView addArrangedSubview: _bannerButton];
}

- (void) setupLayout {
  _rootStackView.translatesAutoresizingMaskIntoConstraints = NO;
  [NSLayoutConstraint activateConstraints:@[
    [_rootStackView.topAnchor constraintEqualToAnchor:self.view.safeAreaLayoutGuide.topAnchor],
    [_rootStackView.leadingAnchor constraintEqualToAnchor:self.view.safeAreaLayoutGuide.leadingAnchor],
    [_rootStackView.trailingAnchor constraintEqualToAnchor:self.view.safeAreaLayoutGuide.trailingAnchor],
    [_rootStackView.bottomAnchor constraintEqualToAnchor:self.view.safeAreaLayoutGuide.bottomAnchor],
  ]];
}


- (void)setupEvent {
  [_benefitHubButton addGestureRecognizer:[[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(pushBenefitHubViewController:)]];
  [_nativeButton addGestureRecognizer:[[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(pushNativeViewController:)]];
  [_carouselButton addGestureRecognizer:[[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(pushCarouselViewController:)]];
  [_interstitialButton addGestureRecognizer:[[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(pushInterstitialViewController:)]];
  [_benefitHubContainerButton addGestureRecognizer:[[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(pushBenefitHubContainerViewController:)]];
  [_bannerButton addGestureRecognizer:[[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(pushBannerViewController:)]];
}

- (void)pushBenefitHubViewController:(id)sender {
  BenefitHubViewController *feedViewController = [[BenefitHubViewController alloc] init];
  [self.navigationController pushViewController:feedViewController animated:YES];
}

- (void)pushNativeViewController:(id)sender {
  NativeViewController *nativeViewController = [[NativeViewController alloc] init];
  [self.navigationController pushViewController:nativeViewController animated:YES];
}

- (void)pushCarouselViewController:(id)sender {
  CarouselViewController *carouselViewController = [[CarouselViewController alloc] init];
  [self.navigationController pushViewController:carouselViewController animated:YES];
}

- (void)pushInterstitialViewController:(id)sender {
  InterstitialViewController *interstitialViewController = [[InterstitialViewController alloc] init];
  [self.navigationController pushViewController:interstitialViewController animated:YES];
}

- (void)pushBenefitHubContainerViewController:(id)sender {
  BenefitHubContainerViewController *feedContainerViewController = [[BenefitHubContainerViewController alloc] init];
  [self.navigationController pushViewController:feedContainerViewController animated:YES];
}

- (void)pushBannerViewController:(id)sender {
  BannerViewController *bannerViewController = [[BannerViewController alloc] init];
  [self.navigationController pushViewController:bannerViewController animated:YES];
}

@end
