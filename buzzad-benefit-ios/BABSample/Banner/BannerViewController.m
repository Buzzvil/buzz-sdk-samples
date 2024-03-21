#import "BannerViewController.h"

@import BuzzAdBenefit;
@import Toast;

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
  [_banner50View setBannerAdWithRootVC:self placementId:@"dbe834db-9c91-4f08-b32d-f4ee46808d51" size:BZVBannerSizeW320h50];
  
  _banner100Label = [[UILabel alloc] initWithFrame:CGRectZero];
  _banner100Label.text = @"Banner W320xH100";
  _banner100Label.textColor = UIColor.blackColor;
  _banner100Label.font = [UIFont boldSystemFontOfSize:14];
  
  _banner100View = [[BZVBannerView alloc] initWithFrame:CGRectZero];
  _banner100View.delegate = self;
  [_banner100View setBannerAdWithRootVC:self placementId:@"b52b5f27-1b92-4569-8c9d-d3c465aff97f" size:BZVBannerSizeW320h100];
  
  _bannerDynamicLabel = [[UILabel alloc] initWithFrame:CGRectZero];
  _bannerDynamicLabel.text = @"Banner Dynamic";
  _bannerDynamicLabel.textColor = UIColor.blackColor;
  _bannerDynamicLabel.font = [UIFont boldSystemFontOfSize:14];
  
  _bannerDynamicView = [[BZVBannerView alloc] initWithFrame:CGRectZero];
  _bannerDynamicView.delegate = self;
  [_bannerDynamicView setBannerAdWithRootVC:self placementId:@"a7b63f99-aa3c-435b-9280-5fac3d2bc035" size:BZVBannerSizeDynamic];
  
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
  dispatch_async(dispatch_get_main_queue(), ^{
    [self.view makeToast:[NSString stringWithFormat:@"onBannerLoaded %@", apid]];
  });
}

- (void)onBannerFailedWithApid:(NSString * _Nonnull)apid error:(BZVBannerError * _Nonnull)error {
  dispatch_async(dispatch_get_main_queue(), ^{
    [self.view makeToast:[NSString stringWithFormat:@"onBannerFailed %@ (%@)", apid, error.message]];
  });
}

- (void)onBannerClickedWithApid:(NSString * _Nonnull)apid {
  dispatch_async(dispatch_get_main_queue(), ^{
    [self.view makeToast:[NSString stringWithFormat:@"onBannerClicked %@", apid]];
  });
}

- (void)onBannerRemovedWithApid:(NSString * _Nonnull)apid {
  dispatch_async(dispatch_get_main_queue(), ^{
    [self.view makeToast:[NSString stringWithFormat:@"onBannerRemoved %@", apid]];
  });
}

@end
