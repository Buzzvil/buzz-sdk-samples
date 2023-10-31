//
//  IMACompanionAdSlot.h
//  GoogleIMA3
//
//  Copyright (c) 2013 Google Inc. All rights reserved.
//
//  Declares a data class that describes a companion ad slot.

#import <UIKit/UIKit.h>

NS_ASSUME_NONNULL_BEGIN

/**
 * Fluid companion ads have no fixed size, but rather adapt to fit the creative
 * content they display. Set width and height to fluid size to allow companion
 * slot to be filled by fluid companion ad.
 */
extern const NSInteger kIMAFluidSize;

@class IMACompanionAdSlot;

#pragma mark IMACompanionDelegate

/**
 * Delegate to receive events from the companion ad slot.
 */
@protocol IMACompanionDelegate <NSObject>

@optional

/**
 * Called when the slot is either filled or not filled.
 *
 * @param slot   the IMACompanionAdSlot receiving the event
 * @param filled is the slot filled or not
 */
- (void)companionSlot:(IMACompanionAdSlot *)slot filled:(BOOL)filled;

/**
 * Called when the slot is clicked on by the user and will
 * successfully navigate away.
 *
 * @param slot   the IMACompanionAdSlot receiving the event
 */
- (void)companionSlotWasClicked:(IMACompanionAdSlot *)slot;

@end

#pragma mark - IMACompanionAdSlot

/**
 * Ad slot for companion ads. The SDK will put a subview inside the provided
 * UIView container. The companion will be matched to the width and height
 * provided here. This class cannot be instantiated on tvOS, where companion ads
 * are not available.
 */
@interface IMACompanionAdSlot : NSObject

/**
 * The view the companion will be rendered in. Display this view in your
 * application before video ad starts.
 */
@property(nonatomic, readonly) UIView *view;

/**
 * Width of the slot, in pixels. This value is sent to the DFP ad server for
 * targeting.
 */
@property(nonatomic, readonly) NSInteger width;

/**
 * Height of the slot, in pixels. This value is sent to the DFP ad server for
 * targeting.
 */
@property(nonatomic, readonly) NSInteger height;

/**
 * The IMACompanionDelegate for receiving events from the companion ad slot.
 */
@property(nonatomic, weak, nullable) id<IMACompanionDelegate> delegate;

/**
 * Initializes an instance of a IMACompanionAdSlot with design ad width and height.
 *
 * @param view   the UIView that will contain the companion ad
 * @param width  the chosen width of the ad
 * @param height the chosen height of the ad
 *
 * @return the IMACompanionAdSlot instance
 */
- (instancetype)initWithView:(UIView *)view
                       width:(NSInteger)width
                      height:(NSInteger)height __TVOS_UNAVAILABLE;

/**
 * Initializes an instance of a IMACompanionAdSlot with fluid size.
 *
 * @param view the UIView that will contain the companion ad.
 *
 * @return the IMACompanionAdSlot instance
 */
- (instancetype)initWithView:(UIView *)view __TVOS_UNAVAILABLE;

/**
 * :nodoc:
 */
- (instancetype)init NS_UNAVAILABLE;

@end

NS_ASSUME_NONNULL_END
