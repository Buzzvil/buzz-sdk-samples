//
//  BuzzAdBenefit.h
//  BuzzAdBenefit
//
//  Created by Jaehee Ko on 10/12/2018.
//  Copyright © 2018 Buzzvil. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <BuzzAdBenefit/BABConfig.h>
#import <BuzzAdBenefit/BABUserProfile.h>
#import <BuzzAdBenefit/BABUserPreference.h>
#import <BuzzAdBenefit/BABAdLoader.h>
#import <BuzzAdBenefit/BABAd.h>
#import <BuzzAdBenefit/BABCreative.h>
#import <BuzzAdBenefit/BABVideoAdMetadata.h>
#import <BuzzAdBenefit/BABVideoAdMetadataLoader.h>
#import <BuzzAdBenefit/BABTracker.h>
#import <BuzzAdBenefit/BABReachability.h>
#import <BuzzAdBenefit/BABLauncher.h>
#import <BuzzAdBenefit/BABArticleLoader.h>
#import <BuzzAdBenefit/BABArticle.h>
#import <BuzzAdBenefit/NSString+URL.h>

NS_ASSUME_NONNULL_BEGIN

@class BABConfig;
@class BABUserProfile;
@class BABUserPreference;

extern NSString *const BABSessionRegisteredNotification;

@interface BuzzAdBenefit: NSObject

@property (nonatomic, readonly, nullable) BABConfig *config;
@property (nonatomic, readonly, nullable) BABUserProfile *userProfile;
@property (nonatomic, readonly, nullable) BABUserPreference *userPreference;
@property (nonatomic, readonly) id<BABLauncher> adLauncher;

+ (BuzzAdBenefit *)sharedInstance;
+ (void)initializeWithConfig:(BABConfig *)config;
+ (void)setUserProfile:(nullable BABUserProfile *)userProfile;
+ (void)setUserPreference:(nullable BABUserPreference *)userPreference;
+ (void)setAdLauncher:(id<BABLauncher>)adLauncher;

@end

NS_ASSUME_NONNULL_END
