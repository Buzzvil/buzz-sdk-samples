//
//  ViewController.m
//  BABSample
//
//  Created by Jaehee Ko on 18/12/2018.
//  Copyright © 2018 Buzzvil. All rights reserved.
//

#import "ViewController.h"
#import <BuzzAdBenefit/BuzzAdBenefit.h>
#import <BuzzAdBenefitNative/BuzzAdBenefitNative.h>
#import <BuzzAdBenefitInterstitial/BuzzAdBenefitInterstitial.h>
#import <BuzzAdBenefitFeed/BuzzAdBenefitFeed.h>
#import <SDWebImage/UIImageView+WebCache.h>
#import <SafariServices/SafariServices.h>
#import <Toast/Toast.h>
#import "CustomAdViewHolder.h"

@interface ViewController () <BABNativeAdViewDelegate, BABInterstitialAdHandlerDelegate, BABLauncher>

@end

@implementation ViewController

- (void)viewDidLoad {
  [super viewDidLoad];

  [CSToastManager setTapToDismissEnabled:YES];
  [CSToastManager setQueueEnabled:YES];
  [CSToastManager setDefaultDuration:1];

  _adView.delegate = self;
}

- (IBAction)loadNativeButtonTapped:(id)sender {
  BABAdLoader *adLoader = [[BABAdLoader alloc] initWithUnitId:@"166467299612761"];
  [adLoader loadAdWithOnSuccess:^(BABAd * _Nonnull ad) {
    self.titleLabel.text = ad.creative.title;
    self.descriptionLabel.text = ad.creative.body;
    [self.iconImageView sd_setImageWithURL:[NSURL URLWithString:ad.creative.iconUrl]];

    if (ad.isParticipated || ad.reward <= 0) {
      [self.ctaButton setTitle:[NSString stringWithFormat:@"%@", ad.creative.callToAction] forState:UIControlStateNormal];
    } else {
      [self.ctaButton setTitle:[NSString stringWithFormat:@"+%d %@", (int)(ad.reward), ad.creative.callToAction] forState:UIControlStateNormal];
    }

    self.adView.ad = ad;
    self.adView.mediaView = self.mediaView;
    self.adView.clickableViews = @[self.ctaButton, self.iconImageView, self.mediaView];

  } onFailure:^(NSError * _Nullable error) {

  }];
}

- (IBAction)loadInterstitialButtonTapped:(id)sender {
  BABInterstitialAdHandler *adLoader = [[BABInterstitialAdHandler alloc] initWithUnitId:@"166467299612761" type:BABInterstitialDialog];
  adLoader.delegate = self;
  [adLoader show:self withConfig:nil];
}

- (IBAction)loadBottomSheetButtonTapped:(id)sender {
  BABInterstitialAdHandler *adLoader = [[BABInterstitialAdHandler alloc] initWithUnitId:@"166467299612761" type:BABInterstitialBottomSheet];
  adLoader.delegate = self;
  [adLoader show:self withConfig:nil];
}

- (IBAction)loadFeedButtonTapped:(id)sender {
  BABFeedConfig *config = [[BABFeedConfig alloc] initWithUnitId:@"166467299612761"];
  config.title = @"꿀 피드";
  config.articlesEnabled = YES;
  config.articleCategories = @[BABArticleCategorySports, BABArticleCategoryFun];
  config.adViewHolderClass = [CustomAdViewHolder class];
//  config.articleViewHolderClass = [BABDefaultArticleViewHolder class];

  BABFeedHandler *feedHandler = [[BABFeedHandler alloc] initWithConfig:config];
//  [self presentViewController:[feedHandler populateViewController] animated:YES completion:nil];
  [self.navigationController pushViewController:[feedHandler populateViewController] animated:YES];

}

- (IBAction)launcherSwitchChanged:(id)sender {
  if (_launcherSwitch.isOn) {
    [BuzzAdBenefit setLauncher:self];
  } else {
    [BuzzAdBenefit setLauncher:nil];
  }
}

#pragma mark - BABLauncher

- (void)openUrl:(NSURL *)url object:(id)object userInfo:(NSDictionary *)userInfo {
  if (@available(iOS 9.0, *)) {
    SFSafariViewController *safariViewController = [[SFSafariViewController alloc] initWithURL:url];
    if (self.presentedViewController) {
      [self.presentedViewController presentViewController:safariViewController animated:YES completion:nil];
    } else {
      [self.navigationController presentViewController:safariViewController animated:YES completion:nil];
    }
  } else {
    [UIApplication.sharedApplication openURL:url];
  }
}

#pragma mark - BABNativeAdViewDelegate

- (void)BABNativeAdView:(BABNativeAdView *)adView didImpressAd:(BABAd *)ad {
  [self.view.window makeToast:[NSString stringWithFormat:@"\"%@\" impressed", ad.creative.title]];
}

- (void)BABNativeAdView:(BABNativeAdView *)adView didClickAd:(BABAd *)ad {
  [self.view.window makeToast:[NSString stringWithFormat:@"\"%@\" clicked", ad.creative.title]];
}

- (void)BABNativeAdView:(BABNativeAdView *)adView willRequestRewardForAd:(BABAd *)ad {
  [self.view.window makeToast:[NSString stringWithFormat:@"\"%@\" reward request started", ad.creative.title]];
}

- (void)BABNativeAdView:(BABNativeAdView *)adView didRewardForAd:(BABAd *)ad withResult:(BABRewardResult)result {
  switch (result) {
    case BABRewardResultSuccess:
      [self.view.window makeToast:[NSString stringWithFormat:@"\"%@\" rewarded", ad.creative.title]];
      break;
    case BABRewardResultBrowserNotLaunched:
      [self.view.window makeToast:[NSString stringWithFormat:@"\"%@\" browser not launched", ad.creative.title]];
      break;
    case BABRewardResultTooShortToParticipate:
      [self.view.window makeToast:[NSString stringWithFormat:@"\"%@\" too short to participate", ad.creative.title]];
      break;
    case BABRewardResultAlreadyParticipated:
      [self.view.window makeToast:[NSString stringWithFormat:@"\"%@\" already participated", ad.creative.title]];
      break;
    default:
      [self.view.window makeToast:[NSString stringWithFormat:@"\"%@\" reward failed: %d", ad.creative.title, result]];
      break;
  }
}

- (void)BABNativeAdView:(BABNativeAdView *)adView didParticipateAd:(BABAd *)ad {
  if (ad.isParticipated || ad.reward <= 0) {
    [self.ctaButton setTitle:[NSString stringWithFormat:@"%@", ad.creative.callToAction] forState:UIControlStateNormal];
  } else {
    [self.ctaButton setTitle:[NSString stringWithFormat:@"+%d %@", (int)(ad.reward), ad.creative.callToAction] forState:UIControlStateNormal];
  }
}

#pragma mark - BABInterstitialAdHandlerDelegate

- (void)BABInterstitialAdHandlerDidSucceedLoadingAd:(BABInterstitialAdHandler *)adLoader {

}

- (void)BABInterstitialAdHandlerDidFailToLoadAd:(BABInterstitialAdHandler *)adLoader {

}

@end
