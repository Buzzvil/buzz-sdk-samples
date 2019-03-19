//
//  BABInterstitialConfig.h
//  BABInterstitial
//
//  Created by Jaehee Ko on 21/02/2019.
//  Copyright © 2019 Buzzvil. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <BuzzAdBenefitNative/BABStateValue.h>

NS_ASSUME_NONNULL_BEGIN

@interface BABInterstitialConfig : NSObject

@property (nonatomic, strong) UIImage *topIcon;
@property (nonatomic, strong) NSString *titleText;
@property (nonatomic, strong) UIColor *titleTextColor;
@property (nonatomic, strong) UIColor *backgroundColor;

@property (nonatomic, strong) BABStateValue<UIImage *> *ctaViewIcon;
@property (nonatomic, strong) BABStateValue<UIColor *> *ctaViewBackgroundColor;
@property (nonatomic, strong) BABStateValue<UIColor *> *ctaViewTextColor;

// Dialog only
@property (nonatomic, strong) NSString *closeText;

// BottomSheet only
@property (nonatomic, assign) NSUInteger adCount;

@end

NS_ASSUME_NONNULL_END
