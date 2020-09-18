//
//  WebViewController.m
//  BABSample
//
//  Created by Jaehee Ko on 09/07/2019.
//  Copyright © 2019 Buzzvil. All rights reserved.
//

#import <WebKit/WebKit.h>
#import "WebViewController.h"
#import <BuzzAdBenefit/BuzzAdBenefit.h>
#import <BuzzAdBenefitWebInterface/BuzzAdBenefitWebInterface.h>

@interface WebViewController () <WKScriptMessageHandler> {
  WKWebView *_webView;
  BABWebInterface *_webInterface;
}

@end

@implementation WebViewController

- (void)viewDidLoad {
  [super viewDidLoad];

  WKWebViewConfiguration *config = [[WKWebViewConfiguration alloc] init];
  WKUserContentController *contentController = [[WKUserContentController alloc] init];
  [contentController addScriptMessageHandler:self name:BuzzAdBenefitWebInterfaceName];
  config.userContentController = contentController;

  _webView = [[WKWebView alloc] initWithFrame:self.view.bounds configuration:config];
  _webView.allowsBackForwardNavigationGestures = YES;
  [self.view addSubview:_webView];

  _webInterface = [[BABWebInterface alloc] initWithWebView:_webView];

  if (_url) {
    NSMutableURLRequest *request = [NSMutableURLRequest requestWithURL:_url cachePolicy:NSURLRequestReloadIgnoringCacheData timeoutInterval:10.0f];
    [_webView loadRequest:request];
  }
}

- (void)userContentController:(WKUserContentController *)userContentController didReceiveScriptMessage:(WKScriptMessage *)message {
  [_webInterface handleScriptMessage:message];
}

@end
