#import "ViewController.h"
#import "UIButton+Custom.h"
#import "Native/NativeViewController.h"
#import "Native/Carousel/CarouselViewController.h"
#import "Interstitial/InterstitialViewController.h"
#import "Feed/FeedViewController.h"
#import "Feed/FeedContainerViewController.h"

static NSString * const kNavigationItemTitle = @"BuzzvilSDK-ObjectiveC";
static NSString * const kNativeButtonTitle = @"Native";
static NSString * const kInterstitialButtonTitle = @"Interstitial";
static NSString * const kFeedButtonTitle = @"Feed";
static NSString * const kCarouselButtonTitle = @"Carousel";
static NSString * const kFeedContainerButtonTitle = @"Feed Container";
static CGFloat const kButtonMargin = 12;
static CGFloat const kButtonAspectRatio = 1.5;

@interface ViewController ()

@property (nonatomic, strong, readonly) UIButton *nativeButton;
@property (nonatomic, strong, readonly) UIButton *carouselButton;
@property (nonatomic, strong, readonly) UIButton *interstitialButton;
@property (nonatomic, strong, readonly) UIButton *feedButton;
@property (nonatomic, strong, readonly) UIButton *feedContainerButton;

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
  
  _nativeButton = [UIButton buttonWithType:UIButtonTypeSystem];
  [self.view addSubview:_nativeButton];
  [_nativeButton setTitle:kNativeButtonTitle forState:UIControlStateNormal];
  [_nativeButton applyCustomStyle];
  
  _interstitialButton = [UIButton buttonWithType:UIButtonTypeSystem];
  [self.view addSubview:_interstitialButton];
  [_interstitialButton setTitle:kInterstitialButtonTitle forState:UIControlStateNormal];
  [_interstitialButton applyCustomStyle];
  
  _carouselButton = [UIButton buttonWithType:UIButtonTypeSystem];
  [self.view addSubview:_carouselButton];
  [_carouselButton setTitle:kCarouselButtonTitle forState:UIControlStateNormal];
  [_carouselButton applyCustomStyle];
  
  
  _feedButton = [UIButton buttonWithType:UIButtonTypeSystem];
  [self.view addSubview:_feedButton];
  [_feedButton setTitle:kFeedButtonTitle forState:UIControlStateNormal];
  [_feedButton applyCustomStyle];
  
  _feedContainerButton = [UIButton buttonWithType:UIButtonTypeSystem];
  [self.view addSubview:_feedContainerButton];
  [_feedContainerButton setTitle:kFeedContainerButtonTitle forState:UIControlStateNormal];
  [_feedContainerButton applyCustomStyle];
}

- (void) setupLayout {
  _nativeButton.translatesAutoresizingMaskIntoConstraints = NO;
  [NSLayoutConstraint activateConstraints:@[
    [_nativeButton.topAnchor constraintEqualToAnchor:self.view.safeAreaLayoutGuide.topAnchor constant:kButtonMargin],
    [_nativeButton.leadingAnchor constraintEqualToAnchor:self.view.safeAreaLayoutGuide.leadingAnchor constant:kButtonMargin],
    [_nativeButton.trailingAnchor constraintEqualToAnchor:self.view.safeAreaLayoutGuide.centerXAnchor constant:-kButtonMargin/2],
    [_nativeButton.widthAnchor constraintEqualToAnchor:_nativeButton.heightAnchor multiplier:kButtonAspectRatio],
  ]];
  
  _interstitialButton.translatesAutoresizingMaskIntoConstraints = NO;
  [NSLayoutConstraint activateConstraints:@[
    [_interstitialButton.topAnchor constraintEqualToAnchor:self.view.safeAreaLayoutGuide.topAnchor constant:kButtonMargin],
    [_interstitialButton.leadingAnchor constraintEqualToAnchor:self.view.safeAreaLayoutGuide.centerXAnchor constant:kButtonMargin/2],
    [_interstitialButton.trailingAnchor constraintEqualToAnchor:self.view.safeAreaLayoutGuide.trailingAnchor constant:-kButtonMargin],
    [_interstitialButton.widthAnchor constraintEqualToAnchor:_interstitialButton.heightAnchor multiplier:kButtonAspectRatio],
  ]];
  
  _carouselButton.translatesAutoresizingMaskIntoConstraints = NO;
  [NSLayoutConstraint activateConstraints:@[
    [_carouselButton.topAnchor constraintEqualToAnchor:_nativeButton.bottomAnchor constant:kButtonMargin],
    [_carouselButton.leadingAnchor constraintEqualToAnchor:self.view.safeAreaLayoutGuide.leadingAnchor constant:kButtonMargin],
    [_carouselButton.trailingAnchor constraintEqualToAnchor:self.view.safeAreaLayoutGuide.centerXAnchor constant:-kButtonMargin / 2],
    [_carouselButton.widthAnchor constraintEqualToAnchor:_carouselButton.heightAnchor multiplier:kButtonAspectRatio],
  ]];
  
  _feedButton.translatesAutoresizingMaskIntoConstraints = NO;
  [NSLayoutConstraint activateConstraints:@[
    [_feedButton.topAnchor constraintEqualToAnchor:_interstitialButton.bottomAnchor constant:kButtonMargin],
    [_feedButton.leadingAnchor constraintEqualToAnchor:self.view.safeAreaLayoutGuide.centerXAnchor constant:kButtonMargin/2],
    [_feedButton.trailingAnchor constraintEqualToAnchor:self.view.safeAreaLayoutGuide.trailingAnchor constant:-kButtonMargin],
    [_feedButton.widthAnchor constraintEqualToAnchor:_feedButton.heightAnchor multiplier:kButtonAspectRatio],
  ]];
  
  _feedContainerButton.translatesAutoresizingMaskIntoConstraints = NO;
  [NSLayoutConstraint activateConstraints:@[
    [_feedContainerButton.topAnchor constraintEqualToAnchor:_carouselButton.bottomAnchor constant:kButtonMargin],
    [_feedContainerButton.leadingAnchor constraintEqualToAnchor:self.view.safeAreaLayoutGuide.leadingAnchor constant:kButtonMargin],
    [_feedContainerButton.trailingAnchor constraintEqualToAnchor:self.view.safeAreaLayoutGuide.centerXAnchor constant:-kButtonMargin / 2],
    [_feedContainerButton.widthAnchor constraintEqualToAnchor:_feedContainerButton.heightAnchor multiplier:kButtonAspectRatio],
  ]];
}


- (void)setupEvent {
  [_feedButton addGestureRecognizer:[[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(pushFeedViewController:)]];
  [_nativeButton addGestureRecognizer:[[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(pushNativeViewController:)]];
  [_carouselButton addGestureRecognizer:[[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(pushCarouselViewController:)]];
  [_interstitialButton addGestureRecognizer:[[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(pushInterstitialViewController:)]];
  [_feedContainerButton addGestureRecognizer:[[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(pushFeedContainerViewController:)]];
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

@end
