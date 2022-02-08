////
////  ViewController.m
////  BABSample
////
////  Created by Jaehee Ko on 18/12/2018.
////  Copyright © 2018 Buzzvil. All rights reserved.
////
//
//#import "ViewController.h"
//#import <BuzzAdBenefit/BuzzAdBenefit.h>
//#import <BuzzAdBenefitNative/BuzzAdBenefitNative.h>
//#import <BuzzAdBenefitInterstitial/BuzzAdBenefitInterstitial.h>
//#import <BuzzAdBenefitFeed/BuzzAdBenefitFeed.h>
//#import <SDWebImage/UIImageView+WebCache.h>
//#import <SafariServices/SafariServices.h>
//#import <Toast/Toast.h>
//#import "WebViewController.h"
//#import "WebToFeedViewController.h"
//#import "CarouselView.h"
//#import "BrowserViewController.h"
//#import "FeedEntryViewController.h"
//
//@interface ViewController () <BABNativeAdViewDelegate, BABInterstitialAdHandlerDelegate, BABLauncher>
//
//@end
//
//@implementation ViewController
//
//- (void)viewDidLoad {
//  [super viewDidLoad];
//
//  [CSToastManager setTapToDismissEnabled:YES];
//  [CSToastManager setQueueEnabled:YES];
//  [CSToastManager setDefaultDuration:1];
//
//  [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(sessionRegistered:) name:BABSessionRegisteredNotification object:nil];
//
//  _adView.delegate = self;
//}
//
//- (BOOL)shouldAutorotate {
//  return NO;
//}
//
//- (UIInterfaceOrientationMask)supportedInterfaceOrientations {
//  return UIInterfaceOrientationMaskPortrait;
//}
//
//- (IBAction)loadNativeButtonTapped:(id)sender {
//  [self.container bringSubviewToFront:self.adView];
//
//  BABAdLoader *adLoader = [[BABAdLoader alloc] initWithUnitId:@"198784894780152"];
//  [adLoader loadAdWithOnSuccess:^(BABAd * _Nonnull ad) {
//    self.titleLabel.text = ad.creative.title;
//    self.descriptionLabel.text = ad.creative.body;
//    [self.iconImageView sd_setImageWithURL:[NSURL URLWithString:ad.creative.iconUrl]];
//
//    if (ad.isParticipated || ad.reward <= 0) {
//      [self.ctaButton setTitle:[NSString stringWithFormat:@"%@", ad.creative.callToAction] forState:UIControlStateNormal];
//    } else {
//      [self.ctaButton setTitle:[NSString stringWithFormat:@"+%d %@", (int)(ad.reward), ad.creative.callToAction] forState:UIControlStateNormal];
//    }
//
//    self.adView.ad = ad;
//    self.adView.mediaView = self.mediaView;
//    self.adView.clickableViews = @[self.ctaButton, self.iconImageView, self.mediaView];
//  } onFailure:^(BABError *error) {
//    NSString *errorMsg;
//    switch(error.code) {
//      case BABUnknownError:
//        errorMsg = @"Unknown";
//        break;
//      case BABServerError:
//        errorMsg = @"ServerError";
//        break;
//      case BABInvalidRequest:
//        errorMsg = @"InvalidRequest";
//        break;
//      case BABRequestTimeout:
//        errorMsg = @"Timeout";
//        break;
//      case BABEmptyResponse:
//        errorMsg = @"EmptyResponse";
//        break;
//      default:
//        errorMsg = @"Unknown";
//        break;
//    }
//    [self.view.window makeToast:[NSString stringWithFormat:@"Ad load failed with error: %@", errorMsg]];
//  }];
//}
//
//- (IBAction)loadCarouselButtonTapped:(id)sender {
//  BABAdLoader *adLoader = [[BABAdLoader alloc] initWithUnitId:@"198784894780152"];
//  [self.container bringSubviewToFront:self.carouselView];
//  [adLoader loadAdsWithSize:5 onSuccess:^(NSArray<BABAd *> *ads) {
//    if (ads.count > 0) {
//      [self.carouselView renderAds:ads];
//    } else {
//      [self.carouselView renderAds:@[]];
//    }
//  } onFailure:^(BABError *error) {
//    [self.carouselView renderAds:@[]];
//  }];
//}
//
//- (IBAction)loadInterstitialButtonTapped:(id)sender {
//  BABInterstitialAdHandler *adLoader = [[BABInterstitialAdHandler alloc] initWithUnitId:@"198784894780152" type:BABInterstitialDialog];
//  adLoader.delegate = self;
//  [adLoader show:self withConfig:nil];
//}
//
//- (IBAction)loadBottomSheetButtonTapped:(id)sender {
//  BABInterstitialAdHandler *adLoader = [[BABInterstitialAdHandler alloc] initWithUnitId:@"198784894780152" type:BABInterstitialBottomSheet];
//  adLoader.delegate = self;
//  [adLoader show:self withConfig:nil];
//}
//
//- (IBAction)loadFeedButtonTapped:(id)sender {
//  BABFeedConfig *config = [[BABFeedConfig alloc] initWithUnitId:@"235299148396323"];
//  config.title = @"꿀 피드";
//  config.articlesEnabled = YES;
////  config.articleCategories = @[BABArticleCategoryNews];
//  config.separatorColor = [UIColor colorWithWhite:0.8 alpha:1];
//  config.separatorHeight = 1 / UIScreen.mainScreen.scale;
//  config.separatorHorizontalMargin = 10;
//
//  BABFeedHandler *feedHandler = [[BABFeedHandler alloc] initWithConfig:config];
//  [self presentViewController:[feedHandler populateViewController] animated:YES completion:nil];
////  [self.navigationController pushViewController:[feedHandler populateViewController] animated:YES];
//
//}
//
//- (IBAction)loadTestWebPage:(id)sender {
//  WebViewController *webViewController = [[WebViewController alloc] init];
//  NSString *samplePageUrl = [[[NSBundle mainBundle] infoDictionary] objectForKey:@"BuzzAdBenefitJSSamplePageUrl"];
//  webViewController.url = [NSURL URLWithString:samplePageUrl];
//  [self.navigationController pushViewController:webViewController animated:YES];
//}
//
//- (IBAction)loadWebToFeedPage:(id)sender {
//  WebToFeedViewController *webToFeedViewController = [[WebToFeedViewController alloc] init];
//  [self.navigationController pushViewController:webToFeedViewController animated:YES];
//}
//
//- (IBAction)launcherSwitchChanged:(id)sender {
//  if (_launcherSwitch.isOn) {
//    [BuzzAdBenefit setLauncher:self];
//  } else {
//    [BuzzAdBenefit setLauncher:nil];
//  }
//}
//
//- (IBAction)loginButtonTapped:(id)sender {
//  if (BuzzAdBenefit.sharedInstance.userProfile.isSessionRegistered) {
//    [BuzzAdBenefit setUserProfile:nil];
//    [_loginButton setTitle:@"LOGIN"];
//  } else {
//    BABUserProfile *userProfile = [[BABUserProfile alloc] initWithUserId:[NSString stringWithFormat:@"iOS_TEST_%u", arc4random() % 10000] birthYear:1985 gender:BABUserGenderMale];
//    [BuzzAdBenefit setUserProfile:userProfile];
//  }
//}
//
//- (IBAction)feedEntryButtonTapped:(UIButton *)sender {
//  BABFeedConfig *config = [[BABFeedConfig alloc] initWithUnitId:@"235299148396323"];
//  config.title = @"꿀 피드";
//  config.articlesEnabled = YES;
////  config.articleCategories = @[BABArticleCategoryNews];
//  config.separatorColor = [UIColor colorWithWhite:0.8 alpha:1];
//  config.separatorHeight = 1 / UIScreen.mainScreen.scale;
//  config.separatorHorizontalMargin = 10;
//
//  FeedEntryViewController *viewController = [[FeedEntryViewController alloc] init];
//  [viewController setFeedConfig:config];
//  [self.navigationController pushViewController:viewController animated:YES];
//}
//
//#pragma mark - BABLauncher
//
//- (void)openWithLaunchInfo:(BABLaunchInfo *)launchInfo {
//  [self openWithLaunchInfo:launchInfo delegate:nil];
//}
//
//- (void)openWithLaunchInfo:(BABLaunchInfo *)launchInfo delegate:(id<BABLauncherEventDelegate>)delegate {
//  BrowserViewController *browserViewController = [[BrowserViewController alloc] init];
//
//  if (self.presentedViewController) {
//    [self.presentedViewController presentViewController:browserViewController animated:YES completion:nil];
//  } else {
//    [self.navigationController presentViewController:browserViewController animated:YES completion:nil];
//  }
//}
//
//#pragma mark - BABNativeAdViewDelegate
//
//- (void)BABNativeAdView:(BABNativeAdView *)adView didImpressAd:(BABAd *)ad {
//  [self.view.window makeToast:[NSString stringWithFormat:@"\"%@\" impressed", ad.creative.title]];
//}
//
//- (BOOL)BABNativeAdView:(BABNativeAdView *)adView shouldClickAd:(BABAd *)ad {
//  [self.view.window makeToast:[NSString stringWithFormat:@"\"%@\" should click: %@", ad.creative.title, _shouldClickSwitch.isOn ? @"YES" : @"NO"]];
//  return _shouldClickSwitch.isOn;
//}
//
//- (void)BABNativeAdView:(BABNativeAdView *)adView didClickAd:(BABAd *)ad {
//  [self.view.window makeToast:[NSString stringWithFormat:@"\"%@\" clicked", ad.creative.title]];
//}
//
//- (void)BABNativeAdView:(BABNativeAdView *)adView willRequestRewardForAd:(BABAd *)ad {
//  [self.view.window makeToast:[NSString stringWithFormat:@"\"%@\" reward request started", ad.creative.title]];
//}
//
//- (void)BABNativeAdView:(BABNativeAdView *)adView didRewardForAd:(BABAd *)ad withResult:(BABRewardResult)result {
//  switch (result) {
//    case BABRewardResultSuccess:
//      [self.view.window makeToast:[NSString stringWithFormat:@"\"%@\" rewarded", ad.creative.title]];
//      break;
//    case BABRewardResultBrowserNotLaunched:
//      [self.view.window makeToast:[NSString stringWithFormat:@"\"%@\" browser not launched", ad.creative.title]];
//      break;
//    case BABRewardResultTooShortToParticipate:
//      [self.view.window makeToast:[NSString stringWithFormat:@"\"%@\" too short to participate", ad.creative.title]];
//      break;
//    case BABRewardResultAlreadyParticipated:
//      [self.view.window makeToast:[NSString stringWithFormat:@"\"%@\" already participated", ad.creative.title]];
//      break;
//    default:
//      [self.view.window makeToast:[NSString stringWithFormat:@"\"%@\" reward failed: %d", ad.creative.title, result]];
//      break;
//  }
//}
//
//- (void)BABNativeAdView:(BABNativeAdView *)adView didParticipateAd:(BABAd *)ad {
//  if (ad.isParticipated || ad.reward <= 0) {
//    [self.ctaButton setTitle:[NSString stringWithFormat:@"%@", ad.creative.callToAction] forState:UIControlStateNormal];
//  } else {
//    [self.ctaButton setTitle:[NSString stringWithFormat:@"+%d %@", (int)(ad.reward), ad.creative.callToAction] forState:UIControlStateNormal];
//  }
//}
//
//#pragma mark - BABInterstitialAdHandlerDelegate
//
//- (void)BABInterstitialAdHandlerDidSucceedLoadingAd:(BABInterstitialAdHandler *)adLoader {
//
//}
//
//- (void)BABInterstitialAdHandler:(BABInterstitialAdHandler *)adLoader didFailToLoadAdWithError:(BABError *)error {
//  NSString *errorMsg;
//  switch(error.code) {
//    case BABUnknownError:
//      errorMsg = @"Unknown";
//      break;
//    case BABServerError:
//      errorMsg = @"ServerError";
//      break;
//    case BABInvalidRequest:
//      errorMsg = @"InvalidRequest";
//      break;
//    case BABRequestTimeout:
//      errorMsg = @"Timeout";
//      break;
//    case BABEmptyResponse:
//      errorMsg = @"EmptyResponse";
//      break;
//    default:
//      errorMsg = @"Unknown";
//      break;
//  }
//  [self.view.window makeToast:[NSString stringWithFormat:@"Interstitial Ad load failed with error: %@", errorMsg]];
//}
//
//- (void)sessionRegistered:(NSNotification *)notification {
//  [_loginButton setTitle:@"LOGOUT"];
//  [self.navigationController.view.window makeToast:@"Session registered"];
//}
//
//@end
