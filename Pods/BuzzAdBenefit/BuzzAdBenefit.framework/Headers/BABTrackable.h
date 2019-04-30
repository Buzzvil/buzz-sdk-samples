//
//  BABTrackable.h
//  BAB
//
//  Created by Jaehee Ko on 20/03/2019.
//  Copyright © 2019 Buzzvil. All rights reserved.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@protocol BABTrackable <NSObject>

- (NSString *)idendifier;

- (BOOL)isImpressed;
- (void)setAsImpressed;
- (NSArray<NSString *> *)impressionTrackingUrls;

- (BOOL)isClicked;
- (void)setAsClicked;
- (NSArray<NSString *> *)clickTrackingUrls;

@optional
- (BOOL)isParticipated;
- (void)setAsParticipated;
- (NSString *)participateTrackingUrl;
- (NSDictionary *)participateTrackingParams;

- (NSString *)playtimeTrackingUrl;
- (NSDictionary *)playtimeTrackingParams;

@end

NS_ASSUME_NONNULL_END
