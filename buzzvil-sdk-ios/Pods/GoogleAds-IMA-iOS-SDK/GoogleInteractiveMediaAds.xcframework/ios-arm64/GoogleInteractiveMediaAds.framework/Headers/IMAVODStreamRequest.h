//
//  IMAVODStreamRequest.h
//  GoogleIMA3_ios
//
//  Declares a representation of a stream request for on-demand streams.
//

#import "IMAStreamRequest.h"

NS_ASSUME_NONNULL_BEGIN

@class IMAAdDisplayContainer;
@class IMAPictureInPictureProxy;
@protocol IMAVideoDisplay;

/**
 * Data object describing a VOD stream request.
 */

@interface IMAVODStreamRequest : IMAStreamRequest

/**
 * The stream request content source ID. This is used to determine the
 * content source of the stream.
 */
@property(nonatomic, copy, readonly) NSString *contentSourceID;

/**
 * The stream request video ID. This is used to determine which specific video
 * stream should be played.
 */
@property(nonatomic, copy, readonly) NSString *videoID;

/**
 * Initializes a stream request instance with the given content source ID and video ID.
 * Uses the given ad display container to display the stream. This is used for on-demand streams.
 *
 * @param contentSourceID    the content source ID for this stream
 * @param videoID            the video identifier for this stream
 * @param adDisplayContainer the IMAAdDisplayContainer for rendering the ad UI
 * @param videoDisplay       the IMAVideoDisplay for playing the stream
 * @param userContext The user context for tracking requests (optional)
 *
 * @return the IMAVODStreamRequest instance
 */
- (instancetype)initWithContentSourceID:(NSString *)contentSourceID
                                videoID:(NSString *)videoID
                     adDisplayContainer:(IMAAdDisplayContainer *)adDisplayContainer
                           videoDisplay:(id<IMAVideoDisplay>)videoDisplay
                            userContext:(nullable id)userContext;

/**
 * Initializes a stream request instance with the given content source ID and video ID.
 * Uses the given ad display container to display the stream. This is used for on-demand streams.
 * Uses the picture in picture proxy to track PIP events.
 *
 * @param contentSourceID       the content source ID for this stream
 * @param videoID               the video identifier for this stream
 * @param adDisplayContainer    the IMAAdDisplayContainer for rendering the ad UI
 * @param videoDisplay          the IMAVideoDisplay for playing the stream
 * @param pictureInPictureProxy the IMAPictureInPictureProxy for tracking PIP events
 * @param userContext The user context for tracking requests (optional)
 *
 * @return the IMAVODStreamRequest instance
 */
- (instancetype)initWithContentSourceID:(NSString *)contentSourceID
                                videoID:(NSString *)videoID
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
