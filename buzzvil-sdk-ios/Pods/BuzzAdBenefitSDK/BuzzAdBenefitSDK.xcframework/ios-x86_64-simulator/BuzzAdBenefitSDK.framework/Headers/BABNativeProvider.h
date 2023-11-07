#import <UIKit/UIKit.h>

@class BABAd;
@class BABNativeAdInteractor;
@class BABNativeAdPresenter;
@class BABArticle;
@class BABArticlePresenter;
@class BuzzConversionTracker;
@protocol BABNativeAdViewProtocol;
@protocol BABArticleViewProtocol;

@interface BABNativeProvider : NSObject

@property (nonatomic, copy) BuzzConversionTracker *conversionTracker;

- (BABNativeAdPresenter *)createNativeAdPresenterWithAd:(BABAd *)ad
                                                   view:(UIView <BABNativeAdViewProtocol> *)view;

- (BABArticlePresenter *)createArticlePresenterWithArticle:(BABArticle *)article
                                                      view:(id<BABArticleViewProtocol>)view;


@end
