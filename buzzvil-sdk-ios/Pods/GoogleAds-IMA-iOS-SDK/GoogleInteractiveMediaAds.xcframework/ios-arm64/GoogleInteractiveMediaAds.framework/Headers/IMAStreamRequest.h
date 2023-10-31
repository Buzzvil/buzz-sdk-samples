//
//  IMAStreamRequest.h
//  GoogleIMA3
//
//  Copyright (c) 2015 Google Inc. All rights reserved.
//
//  Declares a simple stream request class.

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@class IMAAdDisplayContainer;
@class IMASecureSignals;
@protocol IMAVideoDisplay;

/**
 * Data class describing the stream request.
 */
@interface IMAStreamRequest : NSObject

/**
 * The stream display container for displaying the ad UI.
 */
@property(nonatomic, readonly) IMAAdDisplayContainer *adDisplayContainer;

/**
 * The video display where the stream can be played.
 */
@property(nonatomic, readonly) id<IMAVideoDisplay> videoDisplay;

/**
 * The stream request API key. It's configured through the
 * <a href="//support.google.com/dfp_premium/answer/6381445">
 * DFP Admin UI</a> and provided to the publisher to unlock their content.
 * It verifies the applications that are attempting to access the content.
 */
@property(nonatomic, copy, nullable) NSString *apiKey;

/**
 * The stream request authorization token. This is used in place of the API key for stricter
 * content authorization. The publisher can control individual content streams authorized based
 * on this token.
 */
@property(nonatomic, copy, nullable) NSString *authToken;

/**
 * The ID to be used to debug the stream with the stream activity monitor. This is used to provide
 * a convenient way to allow publishers to find a stream log in the stream activity monitor tool.
 */
@property(nonatomic, copy, nullable) NSString *streamActivityMonitorID;

/**
 * You can override a limited set of ad tag parameters on your stream request.
 * <a href="//support.google.com/dfp_premium/answer/7320899">
 * Supply targeting parameters to your stream</a> provides more information.
 *
 * You can use the dai-ot and dai-ov parameters for stream variant preference.
 * See <a href="//support.google.com/dfp_premium/answer/7320898">
 * Override Stream Variant Parameters</a> for more information.
 */
@property(nonatomic, copy, nullable) NSDictionary<NSString *, NSString *> *adTagParameters;

/**
 * The suffix that the SDK will append to the query of the stream manifest URL. Do not include the
 * '?' separator at the start. The SDK will account for the existence of parameters in the URL
 * already, removing existing ones that collide with ones supplied here. This suffix needs to be
 * sanitized and encoded as the SDK will not do this.
 */
@property(nonatomic, copy, nullable) NSString *manifestURLSuffix;

/**
 * Specifies the universal link to the content's screen. If provided, this parameter is passed to
 * the OM SDK. See <a
 * href="//developer.apple.com/documentation/uikit/inter-process_communication/allowing_apps_and_websites_to_link_to_your_content">Apple
 * documentation</a> for more information.
 */
@property(nonatomic, copy, nullable) NSURL *contentURL;

/**
 * Enables the addition of a nonce to the request. This is needed to transmit monetization signals
 * to Google servers when ads are requested server side from a non Google server. Defaults to false.
 * :nodoc:
 */
@property(nonatomic, assign) BOOL enableNonce;

/**
 * Specifies the Secure Signal with custom data for this stream request. Secure Signal with custom
 * data is an encrypted blob containing signals collected by the publisher and previously agreed
 * upon by the publisher and bidder. The Secure Signal with custom data can be cleared out by
 * passing null to this function.
 */
@property(nonatomic, strong, nullable) IMASecureSignals *secureSignals;

/**
 * The user context.
 */
@property(nonatomic, readonly, nullable) id userContext;

/**
 * :nodoc:
 */
- (instancetype)init NS_UNAVAILABLE;

@end

NS_ASSUME_NONNULL_END
