#import "ContainerViewController.h"

@import BuzzAdBenefit;

// MARK: 피드 고급 설정 - 하위 뷰 컨트롤러로 Feed 연동하기
@interface ContainerViewController ()

@property (nonatomic, strong, readonly) BZVBuzzAdFeed *buzzAdFeed;

@end

@implementation ContainerViewController

- (void)viewDidLoad {
  [super viewDidLoad];

  _buzzAdFeed = [BZVBuzzAdFeed feedWithBlock:^(BZVBuzzAdFeedBuilder * _Nonnull builder) {}];
  [self displayContentViewController:[_buzzAdFeed viewController]];
}

- (void)displayContentViewController:(UIViewController*)contentViewController {
  [self addChildViewController:contentViewController];
  contentViewController.view.frame = self.view.bounds;
  [self.view addSubview:contentViewController.view];
  [contentViewController didMoveToParentViewController:self];
}

@end
