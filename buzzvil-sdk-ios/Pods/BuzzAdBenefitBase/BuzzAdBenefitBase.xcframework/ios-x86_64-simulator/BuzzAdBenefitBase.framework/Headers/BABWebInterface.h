//
//  BABWebInterface.h
//  BABWebInterface
//
//  Created by Jaehee Ko on 25/07/2019.
//  Copyright © 2019 Buzzvil. All rights reserved.
//

#import <Foundation/Foundation.h>

@class WKWebView;
@class WKScriptMessage;

static NSUInteger const BABWebInterfaceVersion = 2;
static NSString *const BABWebInterfaceName = @"BuzzAdBenefitNative";

@interface BABWebInterface : NSObject

@property (nonatomic, weak, readonly) WKWebView *webView;

- (instancetype)initWithWebView:(WKWebView *)webView;
- (void)handleScriptMessage:(WKScriptMessage *)message;

@end


