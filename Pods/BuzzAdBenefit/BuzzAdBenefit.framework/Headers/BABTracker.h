//
//  BABTracker.h
//  BAB
//
//  Created by Jaehee Ko on 22/01/2019.
//  Copyright © 2019 Buzzvil. All rights reserved.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@class BABAd;
@protocol BABTrackable;

@interface BABTracker : NSObject

- (void)impressed:(id<BABTrackable>)trackable;
- (void)clicked:(id<BABTrackable>)trackable;
- (void)participated:(id<BABTrackable>)trackable;
- (void)videoPlayedForSeconds:(NSTimeInterval)seconds onTrackable:(id<BABTrackable>)trackable;

@end

NS_ASSUME_NONNULL_END
