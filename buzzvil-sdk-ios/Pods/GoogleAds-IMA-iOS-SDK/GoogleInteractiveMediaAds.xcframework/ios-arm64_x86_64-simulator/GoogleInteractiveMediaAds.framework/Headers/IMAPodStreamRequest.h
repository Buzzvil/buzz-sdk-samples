#import "IMAPictureInPictureProxy.h"
#import "IMAStreamRequest.h"

NS_ASSUME_NONNULL_BEGIN

@class IMAAdDisplayContainer;
@protocol IMAVideoDisplay;

/** Data object describing a stream request for a pod serving stream. */
@interface IMAPodStreamRequest : IMAStreamRequest

/** The network code for the stream request. */
@property(nonatomic, copy, readonly) NSString *networkCode;

/** The custom asset key for the stream request. */
@property(nonatomic, copy, readonly) NSString *customAssetKey;

/**
 * Initializes a stream request instance with the given network code and custom
 * asset key. Uses the given ad display container to display the stream.
 *
 * @param networkCode The network code for the stream.
 * @param customAssetKey The asset key for the stream.
 * @param adDisplayContainer The IMAAdDisplayContainer for rendering the ad UI.
 * @param videoDisplay The IMAVideoDisplay where the stream will be played.
 * @param userContext The user context for tracking requests (optional)
 *
 * @return The IMAPodStreamRequest instance.
 */
- (instancetype)initWithNetworkCode:(NSString *)networkCode
                     customAssetKey:(NSString *)customAssetKey
                 adDisplayContainer:(IMAAdDisplayContainer *)adDisplayContainer
                       videoDisplay:(id<IMAVideoDisplay>)videoDisplay
                        userContext:(nullable id)userContext;

/**
 * Initializes a stream request instance with the given network code and custom
 * asset key. Uses the given ad display container to display the stream. Uses the picture in picture
 * proxy to track PIP events.
 *
 * @param networkCode The network code for the stream.
 * @param customAssetKey The asset key for the stream.
 * @param adDisplayContainer The IMAAdDisplayContainer for rendering the ad UI.
 * @param videoDisplay The IMAVideoDisplay where the stream will be played.
 * @param pictureInPictureProxy The IMAPictureInPictureProxy for tracking PIP events.
 * @param userContext The user context for tracking requests (optional)
 *
 * @return The IMAPodStreamRequest instance.
 */
- (instancetype)initWithNetworkCode:(NSString *)networkCode
                     customAssetKey:(NSString *)customAssetKey
                 adDisplayContainer:(IMAAdDisplayContainer *)adDisplayContainer
                       videoDisplay:(id<IMAVideoDisplay>)videoDisplay
              pictureInPictureProxy:(nullable IMAPictureInPictureProxy *)pictureInPictureProxy
                        userContext:(nullable id)userContext;

/**
 * :nodoc:
 */
- (instancetype)init NS_UNAVAILABLE;

@end

NS_ASSUME_NONNULL_END
