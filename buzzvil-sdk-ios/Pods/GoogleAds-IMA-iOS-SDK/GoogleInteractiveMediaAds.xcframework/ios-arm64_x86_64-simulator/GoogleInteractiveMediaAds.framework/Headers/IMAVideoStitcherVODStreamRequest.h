#import "IMAPictureInPictureProxy.h"
#import "IMAStreamRequest.h"

NS_ASSUME_NONNULL_BEGIN

@class IMAAdDisplayContainer;
@protocol IMAVideoDisplay;

/**
 * Data object describing a stream request for a Google video stitcher video on demand serving
 * stream.
 */
@interface IMAVideoStitcherVODStreamRequest : IMAStreamRequest

/** The adTagURL for the stream. */
@property(nonatomic, readonly) NSString *adTagURL;

/** The URL of the content source for the stream. */
@property(nonatomic, readonly) NSString *contentSourceURL;

/** The networkCode associate with the stream. */
@property(nonatomic, readonly) NSString *networkCode;

/** The OAuth Token for the stream. */
@property(nonatomic, readonly) NSString *OAuthToken;

/** The project number for the stream. */
@property(nonatomic, readonly) NSString *projectNumber;

/** The region for the stream. */
@property(nonatomic, readonly) NSString *region;

/**
 * Initializes a stream request instance with the given network code, content source url, ad tag
 * url, project number, region, and OAuth token. Uses the given ad display container to display the
 * stream.
 *
 * @param adTagURL The adTagURL for the stream.
 * @param contentSourceURL The contentSourceURL for the stream.
 * @param networkCode The networkCode for the stream.
 * @param OAuthToken The OAuth token for the stream.
 * @param projectNumber The projectNumber for the stream.
 * @param region The region for the stream.
 * @param adDisplayContainer The IMAAdDisplayContainer for rendering the ad UI.
 * @param videoDisplay The IMAVideoDisplay where the stream will be played.
 * @param userContext The user context for tracking requests (optional)
 *
 * @return The IMAVideoStitcherVODStreamRequest instance.
 */
- (instancetype)initWithAdTagURL:(NSString *)adTagURL
                          region:(NSString *)region
                   projectNumber:(NSString *)projectNumber
                      OAuthToken:(NSString *)OAuthToken
                     networkCode:(NSString *)networkCode
                contentSourceURL:(NSString *)contentSourceURL
              adDisplayContainer:(IMAAdDisplayContainer *)adDisplayContainer
                    videoDisplay:(id<IMAVideoDisplay>)videoDisplay
                     userContext:(nullable id)userContext;
/**
 * Initializes a stream request instance with the given network code, content source url, ad tag
 * url, project number, region, and OAuth token. Uses the given ad display container to display the
 * stream. Uses the picture in picture proxy to track PIP events.
 *
 * @param adTagURL The adTagURL code for the stream.
 * @param contentSourceURL The contentSourceURL code for the stream.
 * @param networkCode The networkCode for the stream.
 * @param OAuthToken The OAuth Token for the stream.
 * @param projectNumber The projectNumber for the stream.
 * @param region The region for the stream.
 * @param adDisplayContainer The IMAAdDisplayContainer for rendering the ad UI.
 * @param videoDisplay The IMAVideoDisplay where the stream will be played.
 * @param pictureInPictureProxy The IMAPictureInPictureProxy for tracking PIP events.
 * @param userContext The user context for tracking requests (optional)
 *
 * @return The IMAVideoStitcherVODStreamRequest instance.
 */
- (instancetype)initWithAdTagURL:(NSString *)adTagURL
                          region:(NSString *)region
                   projectNumber:(NSString *)projectNumber
                      OAuthToken:(NSString *)OAuthToken
                     networkCode:(NSString *)networkCode
                contentSourceURL:(NSString *)contentSourceURL
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
