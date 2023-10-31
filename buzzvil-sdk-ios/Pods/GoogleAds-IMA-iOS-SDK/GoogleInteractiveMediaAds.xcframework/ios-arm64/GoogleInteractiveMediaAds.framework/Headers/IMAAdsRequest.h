//
//  IMAAdsRequest.h
//  GoogleIMA3
//
//  Copyright (c) 2013 Google Inc. All rights reserved.
//
//  Declares a simple ad request class.

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@class IMAAdDisplayContainer;
@class IMAAVPlayerContentPlayhead;
@class IMAAVPlayerVideoDisplay;
@class IMAPictureInPictureProxy;
@class IMASecureSignals;
@protocol IMAContentPlayhead;

/**
 * Data class describing the ad request.
 */
@interface IMAAdsRequest : NSObject

/**
 * Specifies the full URL to use for ads loading from an ad server. Required
 * for any <code>adsRequest</code>. For details on constructing the ad tag url,
 * see <a href="https://support.google.com/dfp_premium/answer/1068325">
 * Create a main ad video tag manually</a>.
 */
@property(nonatomic, copy, readonly, nullable) NSString *adTagUrl;

/**
 * Specifies a VAST, VMAP, or ad rules response to be used instead of making a
 * request through an ad tag URL. This can be useful for debugging and other situations
 * in which an ad response is already available.
 */
@property(nonatomic, copy, readonly, nullable) NSString *adsResponse;

/**
 * The ad display container.
 */
@property(nonatomic, readonly) IMAAdDisplayContainer *adDisplayContainer;

/**
 * The user context.
 */
@property(nonatomic, readonly, nullable) id userContext;

/**
 * Specifies whether the player intends to start the content and ad in
 * response to a user action or whether they will be automatically played.
 * Changing this setting will have no impact on ad playback.
 */
@property(nonatomic) BOOL adWillAutoPlay;

/**
 * Specifies whether the player intends to start the content and ad with no volume.
 * Changing this setting will have no impact on ad playback.
 */
@property(nonatomic) BOOL adWillPlayMuted;

/**
 * Specifies whether the player intends to continuously play the content videos one after
 * another similar to TV broadcast. Not calling this function leaves the setting as unknown.
 * Note: Changing this setting will have no impact on ad playback.
 */
@property(nonatomic) BOOL continuousPlayback;

/**
 * Specifies the duration of the content in seconds to be shown. Used in AdX requests. This
 * parameter is optional.
 */
@property(nonatomic) float contentDuration;

/**
 * Specifies the keywords used to describe the content to be shown. Used in AdX requests. This
 * parameter is optional.
 */
@property(nonatomic, copy, nullable) NSArray<NSString *> *contentKeywords;

/**
 * Specifies the title of the content to be shown. Used in AdX requests. This parameter is
 * optional.
 */
@property(nonatomic, copy, nullable) NSString *contentTitle;

/**
 * Specifies the Secure Signal with custom data for this ads request. Secure Signal with custom
 * data is an encrypted blob containing signals collected by the publisher and previously agreed
 * upon by the publisher and bidder. The Secure Signal with custom data can be cleared out by
 * passing null to this function.
 */
@property(nonatomic, strong, nullable) IMASecureSignals *secureSignals;

/**
 * Specifies the universal link to the content's screen. If provided, this parameter is passed to
 * the OM SDK. See <a
 * href="//developer.apple.com/documentation/uikit/inter-process_communication/allowing_apps_and_websites_to_link_to_your_content">Apple
 * documentation</a> for more information.
 */
@property(nonatomic, copy, nullable) NSURL *contentURL;

/**
 * Specifies the VAST load timeout in milliseconds for the initial request and any subsequent
 * wrappers. This parameter is optional and will override the default timeout.
 */
@property(nonatomic) float vastLoadTimeout;

/**
 * Specifies the maximum amount of time to wait in seconds, after calling requestAds,
 * before requesting the ad tag URL. This can be used to stagger requests during a
 * live-stream event, in order to mitigate spikes in the number of requests.
 */
@property(nonatomic) float liveStreamPrefetchSeconds;

/**
 * Initializes an ads request instance with the given canned ads response and ad display
 * container with Picture-in-Picture support. Serial ad requests may reuse the
 * same IMAAdDisplayContainer by first calling [IMAAdsManager destroy] on its
 * current adsManager. Concurrent requests must use different ad containers.
 *
 * @param adsResponse           the canned ads response
 * @param adDisplayContainer    the IMAAdDisplayContainer for rendering the ad UI
 * @param avPlayerVideoDisplay  the IMAAVPlayerVideoDisplay for rendering ads
 * @param pictureInPictureProxy the IMAPictureInPictureProxy for tracking PIP events
 * @param userContext           the user context for tracking requests (optional)
 *
 * @return the IMAAdsRequest instance
 */
- (instancetype)initWithAdsResponse:(NSString *)adsResponse
                 adDisplayContainer:(IMAAdDisplayContainer *)adDisplayContainer
               avPlayerVideoDisplay:(IMAAVPlayerVideoDisplay *)avPlayerVideoDisplay
              pictureInPictureProxy:(IMAPictureInPictureProxy *)pictureInPictureProxy
                        userContext:(nullable id)userContext API_AVAILABLE(ios(9.0), tvos(14.0));

/**
 * Initializes an ads request instance with the given canned ads response and ad display
 * container. Serial ad requests may reuse the same IMAAdDisplayContainer by
 * first calling [IMAAdsManager destroy] on its current adsManager. Concurrent
 * requests must use different ad containers. Does not support Picture-in-Picture.
 *
 * @param adsResponse        the canned ads response
 * @param adDisplayContainer the IMAAdDisplayContainer for rendering the ad UI
 * @param contentPlayhead    the IMAContentPlayhead for the content player (optional)
 * @param userContext        the user context for tracking requests (optional)
 *
 * @return the IMAAdsRequest instance
 */
- (instancetype)initWithAdsResponse:(NSString *)adsResponse
                 adDisplayContainer:(IMAAdDisplayContainer *)adDisplayContainer
                    contentPlayhead:(nullable NSObject<IMAContentPlayhead> *)contentPlayhead
                        userContext:(nullable id)userContext NS_DESIGNATED_INITIALIZER;

/**
 * Initializes an ads request instance with the given ad tag URL and ad display
 * container with Picture-in-Picture support. Serial ad requests may reuse the
 * same IMAAdDisplayContainer by first calling [IMAAdsManager destroy] on its
 * current adsManager. Concurrent requests must use different ad containers.
 *
 * @param adTagUrl              the ad tag URL
 * @param adDisplayContainer    the IMAAdDisplayContainer for rendering the ad UI
 * @param avPlayerVideoDisplay  the IMAAVPlayerVideoDisplay for rendering ads
 * @param pictureInPictureProxy the IMAPictureInPictureProxy for tracking PIP events
 * @param userContext           the user context for tracking requests (optional)
 *
 * @return the IMAAdsRequest instance
 */
- (instancetype)initWithAdTagUrl:(NSString *)adTagUrl
              adDisplayContainer:(IMAAdDisplayContainer *)adDisplayContainer
            avPlayerVideoDisplay:(IMAAVPlayerVideoDisplay *)avPlayerVideoDisplay
           pictureInPictureProxy:(IMAPictureInPictureProxy *)pictureInPictureProxy
                     userContext:(nullable id)userContext API_AVAILABLE(ios(9.0), tvos(14.0));

/**
 * Initializes an ads request instance with the given ad tag URL and ad display
 * container. Serial ad requests may reuse the same IMAAdDisplayContainer by
 * first calling [IMAAdsManager destroy] on its current adsManager. Concurrent
 * requests must use different ad containers. Does not support Picture-in-Picture.
 *
 * @param adTagUrl           the ad tag URL
 * @param adDisplayContainer the IMAAdDisplayContainer for rendering the ad UI
 * @param contentPlayhead    the IMAContentPlayhead for the content player (optional)
 * @param userContext        the user context for tracking requests (optional)
 *
 * @return the IMAAdsRequest instance
 */
- (instancetype)initWithAdTagUrl:(NSString *)adTagUrl
              adDisplayContainer:(IMAAdDisplayContainer *)adDisplayContainer
                 contentPlayhead:(nullable NSObject<IMAContentPlayhead> *)contentPlayhead
                     userContext:(nullable id)userContext NS_DESIGNATED_INITIALIZER;

/**
 * :nodoc:
 */
- (instancetype)init NS_UNAVAILABLE;

@end

NS_ASSUME_NONNULL_END
