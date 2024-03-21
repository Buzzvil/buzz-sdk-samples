#import "BannerViewController.h"

@import BuzzAdBenefit;

static NSString * const kNavigationItemTitle = @"Banner";
static CGFloat const kStackViewSpacing = 8;

@interface BannerViewController () <BZVBannerDelegate>

@property (nonatomic, strong, readonly) UIStackView *stackView;
@property (nonatomic, strong, readonly) UILabel *banner50Label;
@property (nonatomic, strong, readonly) BZVBannerView *banner50View;
@property (nonatomic, strong, readonly) UILabel *banner100Label;
@property (nonatomic, strong, readonly) BZVBannerView *banner100View;
@property (nonatomic, strong, readonly) UILabel *bannerDynamicLabel;
@property (nonatomic, strong, readonly) BZVBannerView *bannerDynamicView;

@end

@implementation BannerViewController

- (void)viewDidLoad {
  [super viewDidLoad];
  
  [self setupView];
  [self setupLayout];
}

- (void)viewWillAppear:(BOOL)animated {
  [super viewWillAppear:animated];
  
  [_banner50View requestAd];
  [_banner100View requestAd];
  [_bannerDynamicView requestAd];
}

- (void)viewWillDisappear:(BOOL)animated {
  [super viewWillDisappear:animated];
  
  [_banner50View removeAd];
  [_banner100View removeAd];
  [_bannerDynamicView removeAd];
}

- (void)setupView {
  self.navigationItem.title = kNavigationItemTitle;
  self.view.backgroundColor = UIColor.whiteColor;
  
  _banner50Label = [[UILabel alloc] initWithFrame:CGRectZero];
  _banner50Label.text = @"Banner W320xH50";
  _banner50Label.textColor = UIColor.blackColor;
  _banner50Label.font = [UIFont boldSystemFontOfSize:14];
  
  _banner50View = [[BZVBannerView alloc] initWithFrame:CGRectZero];
  _banner50View.delegate = self;
  [_banner50View setBannerAdWithRootVC:self placementId:@"PLACEMENT_ID" size:BZVBannerSizeW320h50];
  
  _banner100Label = [[UILabel alloc] initWithFrame:CGRectZero];
  _banner100Label.text = @"Banner W320xH100";
  _banner100Label.textColor = UIColor.blackColor;
  _banner100Label.font = [UIFont boldSystemFontOfSize:14];
  
  _banner100View = [[BZVBannerView alloc] initWithFrame:CGRectZero];
  _banner100View.delegate = self;
  [_banner100View setBannerAdWithRootVC:self placementId:@"PLACEMENT_ID" size:BZVBannerSizeW320h100];
  
  _bannerDynamicLabel = [[UILabel alloc] initWithFrame:CGRectZero];
  _bannerDynamicLabel.text = @"Banner Dynamic";
  _bannerDynamicLabel.textColor = UIColor.blackColor;
  _bannerDynamicLabel.font = [UIFont boldSystemFontOfSize:14];
  
  _bannerDynamicView = [[BZVBannerView alloc] initWithFrame:CGRectZero];
  _bannerDynamicView.delegate = self;
  [_bannerDynamicView setBannerAdWithRootVC:self placementId:@"PLACEMENT_ID" size:BZVBannerSizeDynamic];
  
  _stackView = [[UIStackView alloc] initWithArrangedSubviews:@[
    _banner50Label,
    _banner50View,
    _banner100Label,
    _banner100View,
    _bannerDynamicLabel,
    _bannerDynamicView,
  ]];
  _stackView.axis = UILayoutConstraintAxisVertical;
  _stackView.alignment = UIStackViewAlignmentFill;
  _stackView.distribution = UIStackViewDistributionFill;
  _stackView.spacing = kStackViewSpacing;
  [self.view addSubview:_stackView];
}

- (void)setupLayout {
  _stackView.translatesAutoresizingMaskIntoConstraints = NO;
  _banner50View.translatesAutoresizingMaskIntoConstraints = NO;
  _banner100View.translatesAutoresizingMaskIntoConstraints = NO;
  _bannerDynamicView.translatesAutoresizingMaskIntoConstraints = NO;
  [_bannerDynamicView setContentHuggingPriority:UILayoutPriorityDefaultLow forAxis:UILayoutConstraintAxisVertical];
  [_bannerDynamicView setContentCompressionResistancePriority:UILayoutPriorityDefaultLow forAxis:UILayoutConstraintAxisVertical];
  [NSLayoutConstraint activateConstraints:@[
    [_stackView.topAnchor constraintEqualToAnchor:self.view.safeAreaLayoutGuide.topAnchor constant:16],
    [_stackView.leadingAnchor constraintEqualToAnchor:self.view.safeAreaLayoutGuide.leadingAnchor constant:16],
    [_stackView.trailingAnchor constraintEqualToAnchor:self.view.safeAreaLayoutGuide.trailingAnchor constant:-16],
    [_stackView.bottomAnchor constraintLessThanOrEqualToAnchor:self.view.safeAreaLayoutGuide.bottomAnchor constant:-16],
    [_banner50View.heightAnchor constraintEqualToConstant:50],
    [_banner100View.heightAnchor constraintEqualToConstant:100],
  ]];
}

#pragma mark - BZVBannerDelegate
- (void)onBannerLoadedWithApid:(NSString * _Nonnull)apid {
  // Banner에 광고가 할당 되었을 때 호출 됩니다.
}

- (void)onBannerFailedWithApid:(NSString * _Nonnull)apid error:(BZVBannerError * _Nonnull)error {
  // Banner에 광고 할당이 실패했을 때 호출 됩니다.
}

- (void)onBannerClickedWithApid:(NSString * _Nonnull)apid {
  // Banner가 클릭되었을 때 호출 됩니다.
}

- (void)onBannerRemovedWithApid:(NSString * _Nonnull)apid {
  // Banner가 제거되었을 떄 호출 됩니다.
}

@end
