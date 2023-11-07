//
//  BABTracker.h
//  BAB
//
//  Created by Jaehee Ko on 22/01/2019.
//  Copyright © 2019 Buzzvil. All rights reserved.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@protocol BABTrackable;
@protocol BABTrackerRequestProtocol;

@interface BABTracker : NSObject

- (instancetype)init NS_UNAVAILABLE;
- (instancetype)initWithRequest:(id<BABTrackerRequestProtocol>)request NS_DESIGNATED_INITIALIZER;

- (void)trackImpressionUrls:(NSArray<NSString *> *)urls;
- (void)trackClickUrls:(NSArray<NSString *> *)urls;
- (void)videoPlayedForSeconds:(NSTimeInterval)seconds onTrackable:(id<BABTrackable>)trackable;
- (void)vastClicked:(id<BABTrackable>)trackable;

@end

NS_ASSUME_NONNULL_END
