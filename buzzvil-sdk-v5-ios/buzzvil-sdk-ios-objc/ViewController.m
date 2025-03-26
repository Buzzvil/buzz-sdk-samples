#import "ViewController.h"
#import "UIButton+Custom.h"
#import "Native/NativeViewController.h"
#import "Native/Carousel/CarouselViewController.h"
#import "Interstitial/InterstitialViewController.h"
#import "Feed/FeedViewController.h"
#import "Feed/FeedContainerViewController.h"
#import "Banner/BannerViewController.h"
#import "MissionPack/MissionPackViewController.h"

static NSString * const kNavigationItemTitle = @"BuzzvilSDK-ObjectiveC";
static NSString * const kNativeButtonTitle = @"Native";
static NSString * const kInterstitialButtonTitle = @"Interstitial";
static NSString * const kFeedButtonTitle = @"Feed";
static NSString * const kCarouselButtonTitle = @"Carousel";
static NSString * const kFeedContainerButtonTitle = @"Feed Container";
static NSString * const kBannerButtonTitle = @"Banner";
static NSString * const kMissionPackButtonTitle = @"MissionPack";
static CGFloat const kButtonMargin = 12;

@interface ViewController ()

@property (nonatomic, strong, readonly) UIStackView *rootStackView;
@property (nonatomic, strong, readonly) UIButton *nativeButton;
@property (nonatomic, strong, readonly) UIButton *carouselButton;
@property (nonatomic, strong, readonly) UIButton *interstitialButton;
@property (nonatomic, strong, readonly) UIButton *feedButton;
@property (nonatomic, strong, readonly) UIButton *feedContainerButton;
@property (nonatomic, strong, readonly) UIButton *bannerButton;
@property (nonatomic, strong, readonly) UIButton *missionPackButton;

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
  self.navigationItem.title = kNavigationItemTitle;
  
  _rootStackView = [[UIStackView alloc] initWithFrame:CGRectZero];
  [self.view addSubview:_rootStackView];
  _rootStackView.axis = UILayoutConstraintAxisVertical;
  _rootStackView.spacing = kButtonMargin;
  _rootStackView.distribution = UIStackViewDistributionFillEqually;
  
  _nativeButton = [UIButton buttonWithType:UIButtonTypeSystem];
  [_nativeButton setTitle:kNativeButtonTitle forState:UIControlStateNormal];
  [_nativeButton applyCustomStyle];
  
  _interstitialButton = [UIButton buttonWithType:UIButtonTypeSystem];
  [_interstitialButton setTitle:kInterstitialButtonTitle forState:UIControlStateNormal];
  [_interstitialButton applyCustomStyle];
  
  _carouselButton = [UIButton buttonWithType:UIButtonTypeSystem];
  [_carouselButton setTitle:kCarouselButtonTitle forState:UIControlStateNormal];
  [_carouselButton applyCustomStyle];
  
  _feedButton = [UIButton buttonWithType:UIButtonTypeSystem];
  [_feedButton setTitle:kFeedButtonTitle forState:UIControlStateNormal];
  [_feedButton applyCustomStyle];
  
  _feedContainerButton = [UIButton buttonWithType:UIButtonTypeSystem];
  [_feedContainerButton setTitle:kFeedContainerButtonTitle forState:UIControlStateNormal];
  [_feedContainerButton applyCustomStyle];
  
  _bannerButton = [UIButton buttonWithType:UIButtonTypeSystem];
  [_bannerButton setTitle:kBannerButtonTitle forState:UIControlStateNormal];
  [_bannerButton applyCustomStyle];
  
  _missionPackButton = [UIButton buttonWithType:UIButtonTypeSystem];
  [_missionPackButton setTitle:kMissionPackButtonTitle forState:UIControlStateNormal];
  [_missionPackButton applyCustomStyle];
  
  [_rootStackView addArrangedSubview: _nativeButton];
  [_rootStackView addArrangedSubview: _interstitialButton];
  [_rootStackView addArrangedSubview: _feedButton];
  [_rootStackView addArrangedSubview: _feedContainerButton];
  [_rootStackView addArrangedSubview: _bannerButton];
  [_rootStackView addArrangedSubview: _missionPackButton];
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
  [_feedButton addGestureRecognizer:[[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(pushFeedViewController:)]];
  [_nativeButton addGestureRecognizer:[[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(pushNativeViewController:)]];
  [_carouselButton addGestureRecognizer:[[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(pushCarouselViewController:)]];
  [_interstitialButton addGestureRecognizer:[[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(pushInterstitialViewController:)]];
  [_feedContainerButton addGestureRecognizer:[[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(pushFeedContainerViewController:)]];
  [_bannerButton addGestureRecognizer:[[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(pushBannerViewController:)]];
  [_missionPackButton addGestureRecognizer:[[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(pushMissionPackViewController:)]];
}

- (void)pushFeedViewController:(id)sender {
  FeedViewController *feedViewController = [[FeedViewController alloc] init];
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

- (void)pushFeedContainerViewController:(id)sender {
  FeedContainerViewController *feedContainerViewController = [[FeedContainerViewController alloc] init];
  [self.navigationController pushViewController:feedContainerViewController animated:YES];
}

- (void)pushBannerViewController:(id)sender {
  BannerViewController *bannerViewController = [[BannerViewController alloc] init];
  [self.navigationController pushViewController:bannerViewController animated:YES];
}

- (void)pushMissionPackViewController:(id)sender {
  MissionPackViewController *missionPackViewController = [[MissionPackViewController alloc] init];
  [self.navigationController pushViewController:missionPackViewController animated:YES];
}

@end
