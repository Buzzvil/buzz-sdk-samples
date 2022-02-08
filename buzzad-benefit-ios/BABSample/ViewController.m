#import "ViewController.h"

#import "Feed/FeedViewController.h"
#import "FeedEntry/FeedEntryViewController.h"
#import "Interstitial/InterstitialViewController.h"
#import "Native/NativeViewController.h"
#import "Web/WebViewController.h"
#import "WebToFeed/WebToFeedViewController.h"
#import "CustomBrowserViewController.h"
#import "UIButton+Custom.h"

@import BuzzAdBenefit;

static NSString * const kLoginText = @"LOGIN";
static NSString * const kLogoutText = @"LOGOUT";
static NSString * const kNavigationItemTitle = @"BuzzAdBenefit";
static CGFloat const kStackViewSpacing = 8;
static CGFloat const kStackViewHorizontalMargin = 4;
static CGFloat const kArrangedSubviewHeight = 48;

@interface ViewController () <BZVLauncher>

@property (nonatomic, strong, readonly) UIBarButtonItem *loginButtonItem;
@property (nonatomic, strong, readonly) UIScrollView *scrollView;
@property (nonatomic, strong, readonly) UIStackView *stackView;
@property (nonatomic, strong, readonly) UIButton *nativeButton;
@property (nonatomic, strong, readonly) UIButton *interstitialButton;
@property (nonatomic, strong, readonly) UIButton *feedButton;
@property (nonatomic, strong, readonly) UIButton *feedEntryButton;
@property (nonatomic, strong, readonly) UIButton *webButton;
@property (nonatomic, strong, readonly) UIButton *webToFeedButton;


@end

@implementation ViewController

@synthesize delegate;

- (void)viewDidLoad {
  [super viewDidLoad];

  [self setupView];
  [self setupLayout];
  [self setupEvent];
  
  // MARK: 7. 광고 참여 방식 변경하기
//  [BuzzAdBenefit setLauncher:self];
}

- (void)setupView {
  self.navigationItem.title = kNavigationItemTitle;
  self.view.backgroundColor = UIColor.whiteColor;

  _loginButtonItem = [[UIBarButtonItem alloc] initWithTitle:kLoginText style:UIBarButtonItemStylePlain target:nil action:nil];
  self.navigationItem.leftBarButtonItem = _loginButtonItem;

  _scrollView = [[UIScrollView alloc] initWithFrame:CGRectZero];
  [self.view addSubview:_scrollView];

  _nativeButton = [UIButton buttonWithType:UIButtonTypeSystem];
  [_nativeButton setTitle:@"Native" forState:UIControlStateNormal];
  [_nativeButton applyCustomStyle];

  _interstitialButton = [UIButton buttonWithType:UIButtonTypeSystem];
  [_interstitialButton setTitle:@"Interstitial" forState:UIControlStateNormal];
  [_interstitialButton applyCustomStyle];

  _feedButton = [UIButton buttonWithType:UIButtonTypeSystem];
  [_feedButton setTitle:@"Feed" forState:UIControlStateNormal];
  [_feedButton applyCustomStyle];

  _feedEntryButton = [UIButton buttonWithType:UIButtonTypeSystem];
  [_feedEntryButton setTitle:@"Feed Entry" forState:UIControlStateNormal];
  [_feedEntryButton applyCustomStyle];

  _webButton = [UIButton buttonWithType:UIButtonTypeSystem];
  [_webButton setTitle:@"Web" forState:UIControlStateNormal];
  [_webButton applyCustomStyle];

  _webToFeedButton = [UIButton buttonWithType:UIButtonTypeSystem];
  [_webToFeedButton setTitle:@"Web to Feed" forState:UIControlStateNormal];
  [_webToFeedButton applyCustomStyle];

  _stackView = [[UIStackView alloc] initWithArrangedSubviews:@[
    _nativeButton,
    _interstitialButton,
    _feedButton,
    _feedEntryButton,
    _webButton,
    _webToFeedButton,
  ]];
  _stackView.axis = UILayoutConstraintAxisVertical;
  _stackView.spacing = kStackViewSpacing;
  [_scrollView addSubview:_stackView];
}

- (void)setupLayout {
  _scrollView.translatesAutoresizingMaskIntoConstraints = NO;
  [NSLayoutConstraint activateConstraints:@[
    [_scrollView.leadingAnchor constraintEqualToAnchor:self.view.layoutMarginsGuide.leadingAnchor],
    [_scrollView.trailingAnchor constraintEqualToAnchor:self.view.layoutMarginsGuide.trailingAnchor],
    [_scrollView.topAnchor constraintEqualToAnchor:self.view.layoutMarginsGuide.topAnchor],
    [_scrollView.bottomAnchor constraintEqualToAnchor:self.view.bottomAnchor],
  ]];

  _stackView.translatesAutoresizingMaskIntoConstraints = NO;
  [NSLayoutConstraint activateConstraints:@[
    [_stackView.leadingAnchor constraintEqualToAnchor:_scrollView.leadingAnchor],
    [_stackView.trailingAnchor constraintEqualToAnchor:_scrollView.trailingAnchor],
    [_stackView.topAnchor constraintEqualToAnchor:_scrollView.topAnchor],
    [_stackView.bottomAnchor constraintEqualToAnchor:_scrollView.bottomAnchor],
    [_stackView.widthAnchor constraintEqualToAnchor:_scrollView.widthAnchor constant:-kStackViewHorizontalMargin*2],
  ]];

  for (UIView *arrangedSubview in _stackView.arrangedSubviews) {
    [self setupSizeConstraintsOfArrangedSubview:arrangedSubview];
  }
}

- (void)setupSizeConstraintsOfArrangedSubview:(UIView *)arrangedSubview {
  arrangedSubview.translatesAutoresizingMaskIntoConstraints = NO;
  [NSLayoutConstraint activateConstraints:@[
    [arrangedSubview.widthAnchor constraintEqualToAnchor:_stackView.widthAnchor],
    [arrangedSubview.heightAnchor constraintEqualToConstant:kArrangedSubviewHeight],
  ]];
}

- (void)setupEvent {
  _loginButtonItem.target = self;
  _loginButtonItem.action = @selector(toggleLogin:);

  [_feedButton addGestureRecognizer:[[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(pushFeedViewController:)]];
  [_nativeButton addGestureRecognizer:[[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(pushNativeViewController:)]];
  [_interstitialButton addGestureRecognizer:[[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(pushInterstitialViewController:)]];
  [_feedEntryButton addGestureRecognizer:[[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(pushFeedEntryViewController:)]];
  [_webButton addGestureRecognizer:[[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(pushWebViewController:)]];
  [_webToFeedButton addGestureRecognizer:[[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(pushWebToFeedViewController:)]];
}

- (void)toggleLogin:(id)sender {
  // MARK: 2. 로그인 요청하기
  if (![BuzzAdBenefit isLoggedIn]) {
    [BuzzAdBenefit loginWithBlock:^(BZVLoginRequestBuilder * _Nonnull builder) {
      builder.userId = @"USER_ID";
      builder.birthYear = 1995;
      builder.gender = BZVUserGenderMale; // 남성 사용자
    } onSuccess:^{
      [self.loginButtonItem setTitle:kLogoutText];
    } onFailure:^(NSError * _Nonnull error) {
    }];
  } else {
    [BuzzAdBenefit logout];
    [self.loginButtonItem setTitle:kLoginText];
  }
}

- (void)pushFeedViewController:(id)sender {
  FeedViewController *feedViewController = [[FeedViewController alloc] init];
  [self.navigationController pushViewController:feedViewController animated:YES];
}

- (void)pushNativeViewController:(id)sender {
  NativeViewController *nativeViewController = [[NativeViewController alloc] init];
  [self.navigationController pushViewController:nativeViewController animated:YES];
}

- (void)pushInterstitialViewController:(id)sender {
  InterstitialViewController *interstitialViewController = [[InterstitialViewController alloc] init];
  [self.navigationController pushViewController:interstitialViewController animated:YES];
}

- (void)pushFeedEntryViewController:(id)sender {
  FeedEntryViewController *feedEntryViewController = [[FeedEntryViewController alloc] init];
  [self.navigationController pushViewController:feedEntryViewController animated:YES];
}

- (void)pushWebViewController:(id)sender {
  WebViewController *webViewController = [[WebViewController alloc] init];
  NSString *samplePageUrl = [[[NSBundle mainBundle] infoDictionary] objectForKey:@"BuzzAdBenefitJSSamplePageUrl"];
  webViewController.url = [NSURL URLWithString:samplePageUrl];
  [self.navigationController pushViewController:webViewController animated:YES];
}

- (void)pushWebToFeedViewController:(id)sender {
  WebToFeedViewController *webToFeedViewController = [[WebToFeedViewController alloc] init];
  [self.navigationController pushViewController:webToFeedViewController animated:YES];
}

#pragma mark - BZVLauncher
- (void)openWithLaunchInfo:(BZVLaunchInfo *)launchInfo {
  CustomBrowserViewController *browser = [[CustomBrowserViewController alloc] init];
  browser.modalPresentationStyle = UIModalPresentationPageSheet;
  if (self.presentedViewController) {
    [self.presentedViewController presentViewController:browser animated:YES completion:nil];
  } else {
    [self.navigationController presentViewController:browser animated:YES completion:nil];
  }
}

@end
