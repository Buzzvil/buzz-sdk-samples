//
//  BABDefaultAdViewHolder.m
//  BABNative
//
//  Created by Jaehee Ko on 21/02/2019.
//  Copyright © 2019 Buzzvil. All rights reserved.
//

#import "CustomAdViewHolder.h"
#import <BuzzAdBenefit/BuzzAdBenefit.h>
#import <SDWebImage/UIImageView+WebCache.h>
#import <Toast/Toast.h>

@interface CustomAdViewHolder () <BABNativeAdViewDelegate> {
  CGFloat _defaultRewardIconLeading;
}
@property (nonatomic, strong) IBOutlet BABNativeAdView *adView;
@property (nonatomic, strong) IBOutlet UILabel *titleLabel;
@property (nonatomic, strong) IBOutlet UILabel *descriptionLabel;
@property (nonatomic, strong) IBOutlet UIImageView *iconImageView;
@property (nonatomic, strong) IBOutlet UIView *ctaButtonView;
@property (nonatomic, strong) IBOutlet UIImageView *rewardIcon;
@property (nonatomic, strong) IBOutlet UILabel *ctaButtonTextLabel;
@property (nonatomic, strong) IBOutlet BABMediaView *mediaView;
@property (nonatomic, strong) IBOutlet NSLayoutConstraint *rewardIconWidth;
@property (nonatomic, strong) IBOutlet NSLayoutConstraint *rewardIconHeight;
@property (nonatomic, strong) IBOutlet NSLayoutConstraint *rewardIconLeading;

@end

@implementation CustomAdViewHolder

- (instancetype)initWithCoder:(NSCoder *)aDecoder {
  if (self = [super initWithCoder:aDecoder]) {
    [self loadFromNib];
  }
  return self;
}

- (instancetype)initWithFrame:(CGRect)frame {
  if (self = [super initWithFrame:frame]) {
    [self loadFromNib];
  }
  return self;
}

- (void)loadFromNib {
  [self addSubview:[[NSBundle mainBundle] loadNibNamed:@"CustomAdViewHolder" owner:self options:nil][0]];
  [_adView setTranslatesAutoresizingMaskIntoConstraints:NO];
  [_adView edgesToContainer:self];
  _adView.delegate = self;
  _defaultRewardIconLeading = _rewardIconLeading.constant;
}

- (void)renderAd:(BABAd *)ad {
  [super renderAd:ad];
  
  _titleLabel.text = ad.creative.title;

  if (ad.creative.body) {
    NSMutableParagraphStyle *paragraphStyle = [[NSMutableParagraphStyle alloc] init];
    paragraphStyle.lineSpacing = 5;

    NSMutableAttributedString *descriptionString = [[NSMutableAttributedString alloc] initWithString:ad.creative.body];
    [descriptionString addAttribute:NSParagraphStyleAttributeName value:paragraphStyle range:NSMakeRange(0, [descriptionString length])];
    _descriptionLabel.attributedText = descriptionString;
  } else {
    _descriptionLabel.attributedText = nil;
  }

  if (ad.creative.iconUrl) {
    [_iconImageView sd_setImageWithURL:[NSURL URLWithString:ad.creative.iconUrl]];
  } else {
    [_iconImageView setImage:nil];
  }

  [self renderCTAViewForAd:ad];

  _adView.ad = ad;
  _adView.mediaView = _mediaView;
  _adView.clickableViews = @[_ctaButtonView, _titleLabel, _descriptionLabel, _iconImageView, _mediaView];
}

- (void)renderCTAViewForAd:(BABAd *)ad {
  if ([ad isParticipated]) {
    [self.rewardIcon setImage:[UIImage imageNamed:@"participated_ico"]];
    self.ctaButtonTextLabel.text = ad.creative.callToAction;
  } else {
    [_rewardIcon setImage:nil];
    _rewardIconWidth.constant = 0;
    _rewardIconLeading.constant = 0;

    if ((int)ad.reward > 0) {
      _rewardIcon.clipsToBounds = true;
      _rewardIcon.hidden = false;
      _rewardIconWidth.constant = _rewardIconHeight.constant;
      _rewardIconLeading.constant = _defaultRewardIconLeading;

      [_rewardIcon setImage:[UIImage imageNamed:@"point_ico"]];
      _ctaButtonTextLabel.text = [NSString stringWithFormat:@"+%d %@", (int)ad.reward, ad.creative.callToAction];
    } else {
      _ctaButtonTextLabel.text = [NSString stringWithFormat:@"%@", ad.creative.callToAction];
    }
  }
}

#pragma mark - BABNativeAdViewDelegate

- (void)BABNativeAdView:(BABNativeAdView *)adView didImpressAd:(BABAd *)ad {
  [self.window makeToast:[NSString stringWithFormat:@"\"%@\" impressed", ad.creative.title]];
}

- (void)BABNativeAdView:(BABNativeAdView *)adView didClickAd:(BABAd *)ad {
 [self.window makeToast:[NSString stringWithFormat:@"\"%@\" clicked", ad.creative.title]];
}

- (void)BABNativeAdView:(BABNativeAdView *)adView willRequestRewardForAd:(BABAd *)ad {
  [self.window makeToast:[NSString stringWithFormat:@"\"%@\" reward request started", ad.creative.title]];
}

- (void)BABNativeAdView:(BABNativeAdView *)adView didRewardForAd:(BABAd *)ad withResult:(BABRewardResult)result {
  switch (result) {
    case BABRewardResultSuccess:
      [self.window makeToast:[NSString stringWithFormat:@"\"%@\" rewarded", ad.creative.title]];
      break;
    case BABRewardResultBrowserNotLaunched:
      [self.window makeToast:[NSString stringWithFormat:@"\"%@\" browser not launched", ad.creative.title]];
      break;
    case BABRewardResultTooShortToParticipate:
      [self.window makeToast:[NSString stringWithFormat:@"\"%@\" too short to participate", ad.creative.title]];
      break;
    case BABRewardResultAlreadyParticipated:
      [self.window makeToast:[NSString stringWithFormat:@"\"%@\" already participated", ad.creative.title]];
      break;
    default:
      [self.window makeToast:[NSString stringWithFormat:@"\"%@\" reward failed: %d", ad.creative.title, result]];
      break;
  }
}

- (void)BABNativeAdView:(BABNativeAdView *)adView didParticipateAd:(BABAd *)ad {
  [self renderCTAViewForAd:ad];
}

@end
