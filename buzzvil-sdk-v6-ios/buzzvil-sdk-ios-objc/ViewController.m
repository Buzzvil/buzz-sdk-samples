#import "ViewController.h"
#import "UIButton+Custom.h"
#import "Native/NativeViewController.h"
#import "Native/Carousel/CarouselViewController.h"
#import "Interstitial/InterstitialViewController.h"
#import "BenefitHub/BenefitHubViewController.h"
#import "BenefitHub/BenefitHubContainerViewController.h"
#import "BenefitHub/BenefitHubNavigationCustomContainerViewController.h"
#import "Banner/BannerViewController.h"

@import BuzzvilSDK;

@interface ViewController ()

@property (nonatomic, strong, readonly) UIStackView *rootStackView;
@property (nonatomic, strong, readonly) UIButton *nativeButton;
@property (nonatomic, strong, readonly) UIButton *carouselButton;
@property (nonatomic, strong, readonly) UIButton *interstitialButton;
@property (nonatomic, strong, readonly) UIButton *benefitHubButton;
@property (nonatomic, strong, readonly) UIButton *benefitHubContainerButton;
@property (nonatomic, strong, readonly) UIButton *benefitHubNaviCustomContainerButton;
@property (nonatomic, strong, readonly) UIButton *bannerButton;
@property (nonatomic, strong, readonly) UIButton *inquiryButton;
@property (nonatomic, strong, readonly) UILabel *privacyConsentStatusLabel;
@property (nonatomic, strong, readonly) UIButton *loadPrivacyConsentStatusButton;
@property (nonatomic, strong, readonly) UIButton *grantPrivacyConsentButton;

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
  
  _benefitHubNaviCustomContainerButton = [UIButton buttonWithType:UIButtonTypeSystem];
  [_benefitHubNaviCustomContainerButton setTitle:@"BenefitHub navi custom Container" forState:UIControlStateNormal];
  [_benefitHubNaviCustomContainerButton applyCustomStyle];

  _bannerButton = [UIButton buttonWithType:UIButtonTypeSystem];
  [_bannerButton setTitle:@"Banner" forState:UIControlStateNormal];
  [_bannerButton applyCustomStyle];
  
  _inquiryButton = [UIButton buttonWithType:UIButtonTypeSystem];
  [_inquiryButton setTitle:@"Inquiry" forState:UIControlStateNormal];
  [_inquiryButton applyCustomStyle];
  
  _privacyConsentStatusLabel = [[UILabel alloc] initWithFrame:CGRectZero];
  _privacyConsentStatusLabel.text = @"UNKNWON_STATUS";
  _privacyConsentStatusLabel.numberOfLines = 0;
  
  _loadPrivacyConsentStatusButton = [UIButton buttonWithType:UIButtonTypeSystem];
  [_loadPrivacyConsentStatusButton setTitle:@"Load PrivacyConsent Status" forState:UIControlStateNormal];
  [_loadPrivacyConsentStatusButton applyCustomStyle];
  
  _grantPrivacyConsentButton = [UIButton buttonWithType:UIButtonTypeSystem];
  [_grantPrivacyConsentButton setTitle:@"Grant PrivacyConsent" forState:UIControlStateNormal];
  [_grantPrivacyConsentButton applyCustomStyle];

  [_rootStackView addArrangedSubview: _nativeButton];
  [_rootStackView addArrangedSubview: _interstitialButton];
  [_rootStackView addArrangedSubview: _carouselButton];
  [_rootStackView addArrangedSubview: _benefitHubButton];
  [_rootStackView addArrangedSubview: _benefitHubContainerButton];
  [_rootStackView addArrangedSubview: _benefitHubNaviCustomContainerButton];
  [_rootStackView addArrangedSubview: _bannerButton];
  [_rootStackView addArrangedSubview: _inquiryButton];
  [_rootStackView addArrangedSubview: _privacyConsentStatusLabel];
  [_rootStackView addArrangedSubview: _loadPrivacyConsentStatusButton];
  [_rootStackView addArrangedSubview: _grantPrivacyConsentButton];
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
  [_benefitHubNaviCustomContainerButton addGestureRecognizer:[[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(pushBenefitHubNaviCustomContainerViewController:)]];
  [_bannerButton addGestureRecognizer:[[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(pushBannerViewController:)]];
  [_inquiryButton addGestureRecognizer:[[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(showInquiryPage:)]];
  [_loadPrivacyConsentStatusButton addGestureRecognizer:[[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(loadPrivacyConsentStatus:)]];
  [_grantPrivacyConsentButton addGestureRecognizer:[[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(grantPribacyConsent:)]];
}

- (void)pushBenefitHubViewController:(id)sender {
  BenefitHubViewController *benefitHubViewController = [[BenefitHubViewController alloc] init];
  [self.navigationController pushViewController:benefitHubViewController animated:YES];
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
  BenefitHubContainerViewController *benefitHubContainerViewController = [[BenefitHubContainerViewController alloc] init];
  [self.navigationController pushViewController:benefitHubContainerViewController animated:YES];
}

- (void)pushBenefitHubNaviCustomContainerViewController:(id)sender {
  BenefitHubNavigationCustomContainerViewController *benefitHubNaviCustomConatiner = [[BenefitHubNavigationCustomContainerViewController alloc] init];
  [self.navigationController pushViewController:benefitHubNaviCustomConatiner animated:YES];
}

- (void)pushBannerViewController:(id)sender {
  BannerViewController *bannerViewController = [[BannerViewController alloc] init];
  [self.navigationController pushViewController:bannerViewController animated:YES];
}

- (void)showInquiryPage:(id)sender {
  [[BuzzAdBenefit sharedInstance] openInquiryPageWithUnitId:@"YOUR_UNIT_ID"];
}

- (void)loadPrivacyConsentStatus:(id)sender {
  __weak typeof(self) weakSelf = self;
  
  [BuzzAdBenefit.sharedInstance loadPrivacyConsentStatusOnSuccess:^(enum BuzzPrivacyConsentStatus status) {
    __strong typeof(self) strongSelf = weakSelf;
    switch (status) {
      case BuzzPrivacyConsentStatusGranted:
        strongSelf.privacyConsentStatusLabel.text = @"Granted";
        break;
      case BuzzPrivacyConsentStatusRevoked:
        strongSelf.privacyConsentStatusLabel.text = @"Revoked";
        break;
    }
  } onFailure:^(NSError * _Nonnull error) {
    __strong typeof(self) strongSelf = weakSelf;
    
    strongSelf.privacyConsentStatusLabel.text = [NSString stringWithFormat:@"LoadPrivacyConsentStatus is failed Error: %@", error.localizedDescription];
  }];
}

- (void)grantPribacyConsent:(id)sender {
  __weak typeof(self) weakSelf = self;
  
  [BuzzAdBenefit.sharedInstance grantPrivacyConsentOnSuccess:^{
    __strong typeof(self) strongSelf = weakSelf;
    strongSelf.privacyConsentStatusLabel.text = @"Granted";
  } onFailure:^(NSError * _Nonnull error) {
    __strong typeof(self) strongSelf = weakSelf;
    strongSelf.privacyConsentStatusLabel.text = [NSString stringWithFormat:@"GrantPrivacyPolicy is failed Error: %@", error.localizedDescription];
  }];
}

@end
