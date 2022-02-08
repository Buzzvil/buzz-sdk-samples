#import "WebToFeedViewController.h"

@import WebKit;
@import BuzzAdBenefit;

static NSString * const kNavigationItemTitle = @"Web to Feed";

@interface WebToFeedViewController () <WKScriptMessageHandler> {
  WKWebView *_webView;
}

@end

@implementation WebToFeedViewController

- (void)loadView {
  WKWebViewConfiguration *config = [[WKWebViewConfiguration alloc] init];
  WKUserContentController *contentController = [[WKUserContentController alloc] init];
  [contentController addScriptMessageHandler:self name:@"BuzzAdBenefitJSBridge"];
  config.userContentController = contentController;

  _webView = [[WKWebView alloc] initWithFrame:CGRectZero configuration:config];
  _webView.allowsBackForwardNavigationGestures = YES;
  self.view = _webView;
}

- (void)viewDidLoad {
  [super viewDidLoad];
  
  self.navigationItem.title = kNavigationItemTitle;
  
  NSURL *url = [[NSBundle mainBundle] URLForResource:@"WebToFeedSample" withExtension:@"html"];
  NSString *html = [NSString stringWithContentsOfURL:url encoding:NSUTF8StringEncoding error:nil];
  [_webView loadHTMLString:html baseURL:nil];
}

- (void)userContentController:(WKUserContentController *)userContentController didReceiveScriptMessage:(WKScriptMessage *)message {
  if ([message.body isEqualToString:@"showFeed"]) {
    // Show Feed
    [UIView animateWithDuration:0.1 animations:^{
      BZVBuzzAdFeed *buzzAdFeed = [BZVBuzzAdFeed feedWithBlock:^(BZVBuzzAdFeedBuilder * _Nonnull builder) {
      }];
      UIViewController *feedViewController = buzzAdFeed.viewController;
      [self.navigationController presentViewController:feedViewController animated:YES completion:nil];
    }];
  }
}

@end
