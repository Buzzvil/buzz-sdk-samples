//
//  IMAAdDisplayContainer.h
//  GoogleIMA3
//
//  Copyright (c) 2014 Google Inc. All rights reserved.
//
//  Declares the IMAAdDisplayContainer interface that manages the views,
//  ad slots, and displays used for ad playback.

#import <UIKit/UIKit.h>

NS_ASSUME_NONNULL_BEGIN

@class IMACompanionAdSlot;
@class IMAFriendlyObstruction;

/**
 * The IMAAdDisplayContainer is responsible for managing the ad container view and companion ad
 * slots used for ad playback.
 */
@interface IMAAdDisplayContainer : NSObject

/**
 * View containing the video display and ad related UI. This view must be present in the view
 * hierarchy in order to make ad or stream requests.
 */
@property(nonatomic, readonly) UIView *adContainer;

/**
 * View controller containing the ad container. Used to present ad UI in child view controllers. It
 * must be non-nil in order to make ad or stream requests, and it must be in the view hierarchy
 * before ad playback.
 */
@property(nonatomic, weak, nullable) UIViewController *adContainerViewController;

/** The environment to focus on during an ad break. Nil if there is not currently an ad break. */
@property(nonatomic, readonly, nullable) id<UIFocusEnvironment> focusEnvironment;

/** List of companion ad slots. Can be nil or empty. */
@property(nonatomic, readonly, nullable) NSArray<IMACompanionAdSlot *> *companionSlots;

/**
 * Initializes IMAAdDisplayContainer for rendering the ad and displaying the ad UI without any
 * companion slots.
 *
 * @param adContainer The view where the ad will be rendered. Fills the view's bounds.
 * @param adContainerViewController The view controller containing the ad container. If not provided
 *     here, must be set on the property before making an ads or stream request.
 *
 * @return A new IMAAdDisplayContainer instance
 */
- (instancetype)initWithAdContainer:(UIView *)adContainer
                     viewController:(nullable UIViewController *)adContainerViewController;

/**
 * Initializes IMAAdDisplayContainer for rendering the ad and displaying the ad UI.
 *
 * @param adContainer    The view where the ad will be rendered. Fills the view's bounds.
 * @param adContainerViewController The view controller containing the ad container. If not provided
 *     here, must be set on the property before making an ads or stream request.
 * @param companionSlots The array of IMACompanionAdSlots. Can be nil or empty.
 *
 * @return A new IMAAdDisplayContainer instance
 */
- (instancetype)initWithAdContainer:(UIView *)adContainer
                     viewController:(nullable UIViewController *)adContainerViewController
                     companionSlots:(nullable NSArray<IMACompanionAdSlot *> *)companionSlots;

/** :nodoc: */
- (instancetype)init NS_UNAVAILABLE;

/**
 * Registers a view that overlays or obstructs this container as "friendly" for viewability
 * measurement purposes.
 *
 * See <a
 * href="https://developers.google.com/interactive-media-ads/docs/sdks/ios/omsdk">Open Measurement
 * in the IMA SDK</a> for guidance on what is and what is not allowed to be registered.
 *
 * @param friendlyObstruction An obstruction to be marked as "friendly" until unregistered.
 */
- (void)registerFriendlyObstruction:(IMAFriendlyObstruction *)friendlyObstruction;

/** Unregisters all previously registered friendly obstructions. */
- (void)unregisterAllFriendlyObstructions;

/** :nodoc: */
- (void)registerVideoControlsOverlay:(UIView *)videoControlsOverlay
    DEPRECATED_MSG_ATTRIBUTE("Use registerFriendlyObstruction: instead.");

/** :nodoc: */
- (void)unregisterAllVideoControlsOverlays DEPRECATED_MSG_ATTRIBUTE(
    "Use unregisterAllFriendlyObstructions: instead.");

@end

NS_ASSUME_NONNULL_END
