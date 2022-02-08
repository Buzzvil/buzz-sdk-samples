#import "FeedViewController.h"

@import BuzzAdBenefit;

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
@property (nonatomic, strong, readonly) BZVBuzzAdFeed *buzzAdFeed;

@end

@implementation FeedViewController

- (void)viewDidLoad {
  [super viewDidLoad];

  [self setupView];
  [self setupLayout];
  [self setupEvent];

  _buzzAdFeed = [BZVBuzzAdFeed feedWithBlock:^(BZVBuzzAdFeedBuilder * _Nonnull builder) {
    // MARK: 3.1. 기본 설정 이외의 Feed 표시하기
//    builder.config = [BZVFeedConfig configWithBlock:^(BZVFeedConfigBuilder * _Nonnull builder) {
//      builder.unitId = @"SECOND_FEED_UNIT_ID";
//    }];
  }];
}

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

  _addFeedToContainerViewControllerButton = [UIButton buttonWithType:UIButtonTypeSystem];
  [_addFeedToContainerViewControllerButton setTitle:@"Add Feed To Container" forState:UIControlStateNormal];
  [_addFeedToContainerViewControllerButton applyCustomStyle];

  _stackView = [[UIStackView alloc] initWithArrangedSubviews:@[
    _presentFeedButton,
    _pushFeedButton,
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
  [_addFeedToContainerViewControllerButton addGestureRecognizer:[[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(addFeedToContainer:)]];
}

// MARK: 3.1. 피드 지면 표시하기
- (void)presentFeed:(id)sender {
  BZVFeedViewController *feedViewController = [_buzzAdFeed viewController];
  [self presentViewController:feedViewController animated:YES completion:nil];

  // MARK: 3.1. 광고 할당 및 표시하기
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

@end
