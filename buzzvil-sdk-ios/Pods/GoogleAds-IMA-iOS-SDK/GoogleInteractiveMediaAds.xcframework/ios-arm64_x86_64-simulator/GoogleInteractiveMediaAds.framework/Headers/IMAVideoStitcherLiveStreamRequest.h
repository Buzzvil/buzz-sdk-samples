#import "IMAPictureInPictureProxy.h"
#import "IMAPodStreamRequest.h"

NS_ASSUME_NONNULL_BEGIN

@class IMAAdDisplayContainer;
@protocol IMAVideoDisplay;

/** Data object describing a stream request for a Google video stitcher live serving stream. */
@interface IMAVideoStitcherLiveStreamRequest : IMAPodStreamRequest

/** The live stream ID for the stream. */
@property(nonatomic, readonly) NSString *liveStreamEventID;

/** The region for the stream. */
@property(nonatomic, readonly) NSString *region;

/** The project number for the stream. */
@property(nonatomic, readonly) NSString *projectNumber;

/** The OAuth Token for the stream. */
@property(nonatomic, readonly) NSString *OAuthToken;

/**
 * Initializes a stream request instance with the given network code and custom
 * asset key. Uses the given ad display container to display the stream.
 *
 * @param liveStreamEventID The live stream ID for the stream.
 * @param region The region for the stream.
 * @param projectNumber The project number for the stream.
 * @param OAuthToken The OAuth Token for the stream.
 * @param networkCode The network code for the stream.
 * @param customAssetKey The asset key for the stream.
 * @param adDisplayContainer The IMAAdDisplayContainer for rendering the ad UI.
 * @param videoDisplay The IMAVideoDisplay where the stream will be played.
 * @param userContext The user context for tracking requests (optional)
 *
 * @return The IMAVideoStitcherLiveStreamRequest instance.
 */
- (instancetype)initWithLiveStreamEventID:(NSString *)liveStreamEventID
                                   region:(NSString *)region
                            projectNumber:(NSString *)projectNumber
                               OAuthToken:(NSString *)OAuthToken
                              networkCode:(NSString *)networkCode
                           customAssetKey:(NSString *)customAssetKey
                       adDisplayContainer:(IMAAdDisplayContainer *)adDisplayContainer
                             videoDisplay:(id<IMAVideoDisplay>)videoDisplay
                              userContext:(nullable id)userContext;

/**
 * Initializes a stream request instance with the given network code and custom
 * asset key. Uses the given ad display container to display the stream. Uses the picture in picture
 * proxy to track PIP events.
 *
 * @param liveStreamEventID The live stream ID for the stream.
 * @param region The region for the stream.
 * @param projectNumber The project number for the stream.
 * @param OAuthToken The OAuth Token for the stream.
 * @param networkCode The network code for the stream.
 * @param customAssetKey The asset key for the stream.
 * @param adDisplayContainer The IMAAdDisplayContainer for rendering the ad UI.
 * @param videoDisplay The IMAVideoDisplay where the stream will be played.
 * @param pictureInPictureProxy The IMAPictureInPictureProxy for tracking PIP events.
 * @param userContext The user context for tracking requests (optional)
 *
 * @return The IMAVideoStitcherLiveStreamRequest instance.
 */
- (instancetype)initWithLiveStreamEventID:(NSString *)liveStreamEventID
                                   region:(NSString *)region
                            projectNumber:(NSString *)projectNumber
                               OAuthToken:(NSString *)OAuthToken
                              networkCode:(NSString *)networkCode
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
