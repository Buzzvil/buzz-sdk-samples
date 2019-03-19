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
#import <SDWebImage/UIImageView+WebCache.h>

@interface ViewController () <BABNativeAdViewDelegate, BABInterstitialAdLoaderDelegate>

@end

@implementation ViewController

- (void)viewDidLoad {
  [super viewDidLoad];

  _adView.delegate = self;
}

- (IBAction)loadNativeButtonTapped:(id)sender {
  BABAdLoader *adLoader = [[BABAdLoader alloc] initWithUnitId:@"YOUR_UNIT_ID"];
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
  BABInterstitialAdLoader *adLoader = [[BABInterstitialAdLoader alloc] initWithUnitId:@"YOUR_UNIT_ID" type:BABInterstitialDialog];
  adLoader.delegate = self;
  [adLoader show:self withConfig:nil];
}

- (IBAction)loadBottomSheetButtonTapped:(id)sender {
  BABInterstitialAdLoader *adLoader = [[BABInterstitialAdLoader alloc] initWithUnitId:@"YOUR_UNIT_ID" type:BABInterstitialBottomSheet];
  adLoader.delegate = self;
  [adLoader show:self withConfig:nil];
}

#pragma mark - BABNativeAdViewDelegate

- (UIViewController *)BABNativeAdViewViewControlleForPresentingFullscreen {
  return self;
}

- (void)BABNativeAdView:(BABNativeAdView *)adView didImpressAd:(BABAd *)ad {

}

- (void)BABNativeAdView:(BABNativeAdView *)adView didClickAd:(BABAd *)ad {

}

- (void)BABNativeAdView:(BABNativeAdView *)adView didParticipateAd:(BABAd *)ad {

}

#pragma mark - BABInterstitialAdLoaderDelegate

- (void)BABInterstitialAdLoaderDidSucceedLoadingAd:(BABInterstitialAdLoader *)adLoader {

}

- (void)BABInterstitialAdLoaderDidFailToLoadAd:(BABInterstitialAdLoader *)adLoader {

}

@end
