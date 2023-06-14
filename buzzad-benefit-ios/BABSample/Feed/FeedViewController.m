#import "FeedViewController.h"

@import BuzzAdBenefit;
@import Toast;

#import "ContainerViewController.h"
#import "UIButton+Custom.h"

static NSString * const kNavigationItemTitle = @"Feed";
static CGFloat const kStackViewSpacing = 8;
static CGFloat const kStackViewHorizontalMargin = 4;
static CGFloat const kArrangedSubviewHeight = 48;

@interface FeedViewController ()

@property (nonatomic, strong, readonly) UIScrollView *scrollView;
@property (nonatomic, strong, readonly) UIStackView *stackView;
@property (nonatomic, strong, readonly) UIButton *presentFeedButton;
@property (nonatomic, strong, readonly) UIButton *pushFeedButton;
@property (nonatomic, strong, readonly) UIButton *addFeedToContainerViewControllerButton;
@property (nonatomic, strong, readonly) UIButton *showPrivacyPolicyUiButton;
@property (nonatomic, strong, readonly) UIButton *grantPrivacyPolicyButton;
@property (nonatomic, strong, readonly) UIButton *revokePrivacyPolicyButton;
@property (nonatomic, strong, readonly) BZVBuzzAdFeed *buzzAdFeed;

@end

@implementation FeedViewController

- (void)viewDidLoad {
  [super viewDidLoad];

  [self setupView];
  [self setupLayout];
  [self setupEvent];

  _buzzAdFeed = [BZVBuzzAdFeed feedWithBlock:^(BZVBuzzAdFeedBuilder * _Nonnull builder) {
    // MARK: 피드 기본 설정 - 기본 설정 이외의 Feed 표시하기
//    builder.config = [BZVFeedConfig configWithBlock:^(BZVFeedConfigBuilder * _Nonnull builder) {
//      builder.unitId = @"SECOND_FEED_UNIT_ID";
//    }];
  }];
}

// MARK: 피드 기본 설정 - 피드 지면 표시하기
- (void)presentFeed:(id)sender {
  BZVFeedViewController *feedViewController = [_buzzAdFeed viewController];
  [self presentViewController:feedViewController animated:YES completion:nil];

  // MARK: 피드 기본 설정 - 광고 할당 및 표시하기
//  [_buzzAdFeed loadOnSuccess:^{
//    NSInteger feedTotalReward = self.buzzAdFeed.availableRewards; // 적립 가능한 총 포인트 금액
//
//    BZVFeedViewController *feedViewController = self.buzzAdFeed.viewController;
//    [self presentViewController:feedViewController animated:YES completion:nil];
//  } onFailure:^(NSError * _Nonnull error) {
//    // 광고가 없을 경우 호출됩니다. error를 통해 원인을 알 수 있습니다.
//  }];
}

- (void)pushFeed:(id)sender {
  BZVFeedViewController *feedViewController = [_buzzAdFeed viewController];
  [self.navigationController pushViewController:feedViewController animated:YES];
}

- (void)addFeedToContainer:(id)sender {
  ContainerViewController *containerViewController = [[ContainerViewController alloc] init];
  [self.navigationController pushViewController:containerViewController animated:YES];
}


- (void)showPrivacyPolicyUi:(id)sender {
  BZVPrivacyPolicyManager *privacyPolicyManager = [BuzzAdBenefit privacyPolicyManager];
  
  // 만약 Feed 이외의 화면에서 개인정보 수집 동의 UI 를 노출하고 싶을 경우 아래와 같이 사용할 수 있습니다.
  __weak typeof(self) weakSelf = self;
  [privacyPolicyManager presentConsentFormOnViewController:self onComplete:^(BOOL isAccepted) {
    __strong typeof(self) strongSelf = weakSelf;
    if (isAccepted) {
      // 개인정보 수집동의 UI 에서 동의한 경우
      
      [strongSelf.view.window makeToast:[NSString stringWithFormat:@"PrivacyPolicy is accepted"]];
    } else {
      // 개인정보 수집동의 UI 에서 거절한 경우
      [strongSelf.view.window makeToast:[NSString stringWithFormat:@"PrivacyPolicy is not accepted"]];
    }
  }];
}

- (void)grantPrivacyPolicy:(id)sender {
  BZVPrivacyPolicyManager *privacyPolicyManager = [BuzzAdBenefit privacyPolicyManager];
  [privacyPolicyManager grantConsent];
  [self.view.window makeToast:[NSString stringWithFormat:@"grantPrivacyPolicy"]];
}

- (void)revokePrivacyPolicy:(id)sender {
  BZVPrivacyPolicyManager *privacyPolicyManager = [BuzzAdBenefit privacyPolicyManager];
  [privacyPolicyManager revokeConsent];
  [self.view.window makeToast:[NSString stringWithFormat:@"revokePrivacyPolicy"]];
}

#pragma mark - UI setup
- (void)setupView {
  self.navigationItem.title = kNavigationItemTitle;
  self.view.backgroundColor = UIColor.whiteColor;

  _scrollView = [[UIScrollView alloc] initWithFrame:CGRectZero];
  [self.view addSubview:_scrollView];

  _presentFeedButton = [UIButton buttonWithType:UIButtonTypeSystem];
  [_presentFeedButton setTitle:@"Present Feed" forState:UIControlStateNormal];
  [_presentFeedButton applyCustomStyle];

  _pushFeedButton = [UIButton buttonWithType:UIButtonTypeSystem];
  [_pushFeedButton setTitle:@"Push Feed" forState:UIControlStateNormal];
  [_pushFeedButton applyCustomStyle];
  
  _showPrivacyPolicyUiButton = [UIButton buttonWithType:UIButtonTypeSystem];
  [_showPrivacyPolicyUiButton setTitle:@"Show PrivacyPolicy UI" forState:UIControlStateNormal];
  [_showPrivacyPolicyUiButton applyCustomStyle];
  
  _grantPrivacyPolicyButton = [UIButton buttonWithType:UIButtonTypeSystem];
  [_grantPrivacyPolicyButton setTitle:@"Grant PrivacyPolicy" forState:UIControlStateNormal];
  [_grantPrivacyPolicyButton applyCustomStyle];
  
  _revokePrivacyPolicyButton = [UIButton buttonWithType:UIButtonTypeSystem];
  [_revokePrivacyPolicyButton setTitle:@"Revoke PrivacyPolicy" forState:UIControlStateNormal];
  [_revokePrivacyPolicyButton applyCustomStyle];

  _addFeedToContainerViewControllerButton = [UIButton buttonWithType:UIButtonTypeSystem];
  [_addFeedToContainerViewControllerButton setTitle:@"Add Feed To Container" forState:UIControlStateNormal];
  [_addFeedToContainerViewControllerButton applyCustomStyle];

  _stackView = [[UIStackView alloc] initWithArrangedSubviews:@[
    _presentFeedButton,
    _pushFeedButton,
    _showPrivacyPolicyUiButton,
    _grantPrivacyPolicyButton,
    _revokePrivacyPolicyButton,
    _addFeedToContainerViewControllerButton,
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
  [_presentFeedButton addGestureRecognizer:[[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(presentFeed:)]];
  [_pushFeedButton addGestureRecognizer:[[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(pushFeed:)]];
  [_showPrivacyPolicyUiButton addGestureRecognizer:[[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(showPrivacyPolicyUi:)]];
  [_grantPrivacyPolicyButton addGestureRecognizer:[[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(grantPrivacyPolicy:)]];
  [_revokePrivacyPolicyButton addGestureRecognizer:[[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(revokePrivacyPolicy:)]];
  [_addFeedToContainerViewControllerButton addGestureRecognizer:[[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(addFeedToContainer:)]];
}

@end
