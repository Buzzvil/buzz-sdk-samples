#import "WebViewController.h"

@import WebKit;
@import BuzzAdBenefit;

static NSString * const kNavigationItemTitle = @"Web";

@interface WebViewController () <WKScriptMessageHandler> {
  WKWebView *_webView;
  BZVWebInterface *_webInterface;
}

@end

@implementation WebViewController

- (void)loadView {
  WKWebViewConfiguration *config = [[WKWebViewConfiguration alloc] init];
  WKUserContentController *contentController = [[WKUserContentController alloc] init];
  [contentController addScriptMessageHandler:self name:BuzzAdBenefitWebInterfaceName];
  config.userContentController = contentController;

  _webView = [[WKWebView alloc] initWithFrame:CGRectZero configuration:config];
  _webView.allowsBackForwardNavigationGestures = YES;
  self.view = _webView;

  _webInterface = [[BZVWebInterface alloc] initWithWebView:_webView];
}

- (void)viewDidLoad {
  [super viewDidLoad];

  self.navigationItem.title = kNavigationItemTitle;

  if (_url) {
    NSMutableURLRequest *request = [NSMutableURLRequest requestWithURL:_url cachePolicy:NSURLRequestReloadIgnoringCacheData timeoutInterval:10.0f];
    [_webView loadRequest:request];
  }
}

- (void)userContentController:(WKUserContentController *)userContentController didReceiveScriptMessage:(WKScriptMessage *)message {
  [_webInterface handleScriptMessage:message];
}

@end
