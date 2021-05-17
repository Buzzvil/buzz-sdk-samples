#import <WebKit/WebKit.h>
#import "WebToFeedViewController.h"
#import <BuzzAdBenefit/BuzzAdBenefit.h>
@import BuzzAdBenefitFeed;

@interface WebToFeedViewController () <WKScriptMessageHandler> {
  WKWebView *_webView;
}

@end

@implementation WebToFeedViewController

- (void)viewDidLoad {
  [super viewDidLoad];
  WKWebViewConfiguration *config = [[WKWebViewConfiguration alloc] init];
  WKUserContentController *contentController = [[WKUserContentController alloc] init];
  [contentController addScriptMessageHandler:self name:@"BuzzAdBenefitJSBridge"];
  config.userContentController = contentController;
  
  _webView = [[WKWebView alloc] initWithFrame:self.view.bounds configuration:config];
  NSURL *url = [[NSBundle mainBundle] URLForResource:@"WebToFeedSample" withExtension:@"html"];
  NSString *html = [NSString stringWithContentsOfURL:url encoding:NSUTF8StringEncoding error:nil];
  [_webView loadHTMLString:html baseURL:nil];
  [self.view addSubview:_webView];
}

- (void)userContentController:(WKUserContentController *)userContentController didReceiveScriptMessage:(WKScriptMessage *)message {
  if ([message.body isEqualToString:@"showFeed"]) {
    // Show Feed
    [UIView animateWithDuration:0.1 animations:^{
      BABFeedConfig *config = [[BABFeedConfig alloc] initWithUnitId:@"YOUR_FEED_UNIT_ID"];
      BABFeedHandler *handler = [[BABFeedHandler alloc] initWithConfig:config];
      UIViewController *feedViewController = [handler populateViewController];
      [self.navigationController presentViewController:feedViewController animated:YES completion:nil];
    }];
  }
}

@end
