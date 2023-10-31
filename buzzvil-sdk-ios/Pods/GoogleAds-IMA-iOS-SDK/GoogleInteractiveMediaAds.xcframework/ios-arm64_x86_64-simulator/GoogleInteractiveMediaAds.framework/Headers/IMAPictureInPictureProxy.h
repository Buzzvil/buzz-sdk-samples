//
//  IMAPictureInPictureProxy.h
//  GoogleIMA3
//
//  Copyright (c) 2015 Google Inc. All rights reserved.
//
//  Defines a proxy for wrapping an AVPlayerViewControllerDelegate or
//  AVPictureInPictureControllerDelegate for handling Picture-in-Picture.

#import <AVKit/AVKit.h>
#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@protocol AVPlayerViewControllerDelegate;
@protocol AVPictureInPictureControllerDelegate;

/**
 * A proxy class for allowing the SDK to detect entering and exiting
 * Picture-in-Picture.
 *
 * To use the proxy, create an instance of IMAPictureInPictureProxy with the
 * Picture-in-Picture delegate as an argument, and then simply set the
 * Picture-in-Picture controller's delegate to the proxy. See
 * <a href="https://developers.google.com/interactive-media-ads/docs/sdks/ios/picture_in_picture">
 * Picture in Picture</a> for more details.
 */
#if TARGET_OS_IOS || (TARGET_OS_TV && __TV_OS_VERSION_MAX_ALLOWED >= 140000)
@interface IMAPictureInPictureProxy
    : NSProxy <AVPictureInPictureControllerDelegate, AVPlayerViewControllerDelegate>
#else
@interface IMAPictureInPictureProxy : NSObject
#endif
/**
 * Whether or not Picture-in-Picture is currently active.
 */
@property(nonatomic, readonly, getter=isPictureInPictureActive) BOOL pictureInPictureActive;

/**
 * Whether or not Picture-in-Picture is supported on this device.
 */
+ (BOOL)isPictureInPictureSupported;

/**
 * Instantiates an IMAPictureInPictureProxy that will proxy delegate
 * messages from an AVPictureInPictureController, and forward them
 * to the AVPictureInPictureControllerDelegate passed on init.
 *
 * @param delegate the AVPictureInPictureControllerDelegate
 *
 * @return an IMAPictureInPictureProxy instance
 */
- (instancetype)initWithAVPictureInPictureControllerDelegate:
    (id<AVPictureInPictureControllerDelegate>)delegate API_AVAILABLE(ios(9.0), tvos(14.0));

/**
 * Instantiates an IMAPictureInPictureProxy that will proxy delegate
 * messages from an AVPlayerViewController, and forward them to the
 * AVPlayerViewControllerDelegate passed on init.
 *
 * @param delegate the AVPlayerViewControllerDelegate
 *
 * @return an IMAPictureInPictureProxy instance
 */
- (instancetype)initWithAVPlayerViewControllerDelegate:(id<AVPlayerViewControllerDelegate>)delegate
    API_AVAILABLE(ios(9.0), tvos(14.0));

@end

NS_ASSUME_NONNULL_END
