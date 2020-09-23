#import "BrowserViewController.h"
#import <BuzzAdBenefit/BuzzAdBenefit.h>

@implementation BrowserViewController {
  UIViewController *_browserVC;
}

- (void)viewDidLoad {
  [super viewDidLoad];

  _browserVC = [BuzzAdBrowser.sharedInstance browserViewController];
  _browserVC.view.translatesAutoresizingMaskIntoConstraints = false;
  [self.view addSubview:_browserVC.view];
  [_browserVC didMoveToParentViewController:self];

  [_browserVC.view.topAnchor constraintEqualToAnchor:self.view.safeAreaLayoutGuide.topAnchor].active = YES;
  [_browserVC.view.bottomAnchor constraintEqualToAnchor:self.view.bottomAnchor].active = YES;
  [_browserVC.view.leadingAnchor constraintEqualToAnchor:self.view.leadingAnchor].active = YES;
  [_browserVC.view.trailingAnchor constraintEqualToAnchor:self.view.trailingAnchor].active = YES;
}

@end
