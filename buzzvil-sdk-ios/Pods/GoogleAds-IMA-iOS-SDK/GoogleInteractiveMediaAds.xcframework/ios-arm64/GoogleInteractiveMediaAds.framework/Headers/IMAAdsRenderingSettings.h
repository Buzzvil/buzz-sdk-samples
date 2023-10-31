#import <UIKit/UIKit.h>

NS_ASSUME_NONNULL_BEGIN

/**
 * The default value of |bitrate property|, causes the effective bitrate to
 * be automatically selected.
 */
extern const NSInteger kIMAAutodetectBitrate;

#pragma mark IMALinkOpenerDelegate

/**
 * Signals that a link has been opened/closed.
 * For an external app (Mobile Safari/App deep link), the delegate is only notified
 * before opening.
 */
@protocol IMALinkOpenerDelegate <NSObject>

@optional

/**
 * Called when Safari or app deep link is about to be opened.
 *
 * @param linkOpener the receiving object
 */
- (void)linkOpenerWillOpenExternalApplication:(NSObject *)linkOpener;

/**
 * Called before in-app browser/app store opens.
 *
 * @param linkOpener the receiving object.
 */
- (void)linkOpenerWillOpenInAppLink:(NSObject *)linkOpener;

/**
 * Called when the in app browser/app-store is shown on the screen.
 *
 * @param linkOpener the receiving object
 */
- (void)linkOpenerDidOpenInAppLink:(NSObject *)linkOpener;

/**
 * Called when in-app browser/app-store is about to close.
 *
 * @param linkOpener the receiving object
 */
- (void)linkOpenerWillCloseInAppLink:(NSObject *)linkOpener;

/**
 * Called when in-app browser/app-store finishes closing.
 *
 * @param linkOpener the receiving object
 */
- (void)linkOpenerDidCloseInAppLink:(NSObject *)linkOpener;

@end

#pragma mark - IMAAdsRenderingSettings

/**
 * Set of properties that influence how ads are rendered.
 */
@interface IMAAdsRenderingSettings : NSObject

/**
 * If specified, the SDK will play the media with MIME type on the list.
 * List of strings specifying the MIME types. When empty, the SDK will
 * use its default list of MIME types supported on iOS.
 * Example: @[ @"video/mp4", @"application/x-mpegURL" ]
 * The property is an empty array by default.
 */
@property(nonatomic, copy, nullable) NSArray<NSString *> *mimeTypes;

/**
 * Maximum recommended bitrate. The value is in kbit/s.
 * SDK will pick media with bitrate below the specified max, or the closest
 * bitrate if there is no media with smaller bitrate found.
 * Default value, |kIMAAutodetectBitrate|, means the bitrate will be selected
 * by the SDK, using the currently detected network speed (cellular or Wi-Fi).
 */
@property(nonatomic) NSInteger bitrate;

/**
 * Timeout (in seconds) when loading a video ad media file. If loading takes
 * longer than this timeout, the ad playback is canceled and the next ad in the
 * pod plays, if available. Use -1 for the default of 8 seconds.
 */
@property(nonatomic) NSTimeInterval loadVideoTimeout;

/**
 * For VMAP and ad rules playlists, only play ad breaks scheduled after this time (in seconds).
 * This setting is strictly after the specified time. For example, setting playAdsAfterTime to
 * 15 will ignore an ad break scheduled to play at 15s.
 */
@property(nonatomic) NSTimeInterval playAdsAfterTime;

/**
 * Specifies the list of UI elements that should be visible.
 * This property may be ignored for AdSense/AdX ads. For valid values, see
 * <a href="../Enums/IMAUiElementType.html">IMAUiElementType</a>. This field
 * is ignored on tvOS, where UI elements are unavailable.
 */
@property(nonatomic, copy, nullable) NSArray<NSNumber *> *uiElements;

/**
 * Whether or not to disable ad UI for non TrueView ads. Check Ad.getDisableUi to check if this
 * request was honored. Default is false.
 * :nodoc:
 */
@property(nonatomic) BOOL disableUi;

/**
 * Whether or not the SDK will preload ad media. Default is YES.
 */
@property(nonatomic) BOOL enablePreloading;

/**
 * Specifies the optional UIViewController that will be used to open links in-app.
 * When nil, tapping the video ad "Learn More" button or companion ads
 * will result in opening Safari browser. Setting this allows the SDK to open links in-app. This
 * field is ignored on tvOS, where Safari is not available.
 */
@property(nonatomic, weak, nullable) UIViewController *linkOpenerPresentingController;

/**
 * The IMALinkOpenerDelegate to be notified when a link is opened/closed.
 * Web links are unavailable on tvOS, but this delegate will be used to notify for deep links.
 */
@property(nonatomic, weak, nullable) id<IMALinkOpenerDelegate> linkOpenerDelegate;

/**
 * Toggle this to allow web links to open externally.
 * Default is false, ignored if @c linkOpenerPresentingController is nil.
 * :nodoc:
 */
@property(nonatomic) BOOL openWebLinksExternally;

@end

NS_ASSUME_NONNULL_END
