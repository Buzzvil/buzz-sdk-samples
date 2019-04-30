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

@interface ViewController () <BABNativeAdViewDelegate, BABInterstitialAdHandlerDelegate>

@end

@implementation ViewController

- (void)viewDidLoad {
  [super viewDidLoad];

  _adView.delegate = self;
}

- (IBAction)loadNativeButtonTapped:(id)sender {
  BABAdLoader *adLoader = [[BABAdLoader alloc] initWithUnitId:@"166467299612761"];
  [adLoader loadAdWithOnSuccess:^(BABAd * _Nonnull ad) {
    self.titleLabel.text = ad.creative.title;
    self.descriptionLabel.text = ad.creative.body;
    [self.iconImageView sd_setImageWithURL:[NSURL URLWithString:ad.creative.iconUrl]];
    [self.ctaButton setTitle:ad.creative.callToAction forState:UIControlStateNormal];

    self.adView.ad = ad;
    self.adView.mediaView = self.mediaView;
    self.adView.clickableViews = @[self.ctaButton, self.iconImageView];

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
//  config.adViewHolderClass = [BABDefaultAdViewHolder class];
//  config.articleViewHolderClass = [BABDefaultArticleViewHolder class];

  BABFeedHandler *feedHandler = [[BABFeedHandler alloc] initWithConfig:config];
//  [self presentViewController:[feedHandler populateViewController] animated:YES completion:nil];
  [self.navigationController pushViewController:[feedHandler populateViewController] animated:YES];
}

#pragma mark - BABNativeAdViewDelegate

- (UIViewController *)BABNativeAdViewViewControlleForPresentingFullscreen {
  return self;
}

- (void)BABNativeAdView:(BABNativeAdView *)adView didImpressAd:(BABAd *)ad {

}

- (void)BABNativeAdView:(BABNativeAdView *)adView didClickAd:(BABAd *)ad {

}

- (void)BABNativeAdView:(BABNativeAdView *)adView willRequestRewardForAd:(BABAd *)ad {

}

- (void)BABNativeAdView:(BABNativeAdView *)adView didRequestRewardForAd:(BABAd *)ad withResult:(int)result {

}

- (void)BABNativeAdView:(BABNativeAdView *)adView didParticipateAd:(BABAd *)ad {

}

#pragma mark - BABInterstitialAdHandlerDelegate

- (void)BABInterstitialAdHandlerDidSucceedLoadingAd:(BABInterstitialAdHandler *)adLoader {

}

- (void)BABInterstitialAdHandlerDidFailToLoadAd:(BABInterstitialAdHandler *)adLoader {

}

@end
