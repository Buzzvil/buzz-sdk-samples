#import <UIKit/UIKit.h>
#import "BABNativeAdPresenterProtocol.h"

NS_ASSUME_NONNULL_BEGIN

@class BABAd;
@protocol BZVNativeAdEventSessionProvider;
@protocol BABNativeAdViewProtocol;
@protocol BABNativeAdInteractorProtocol;

@interface BABNativeAdPresenter : NSObject <BABNativeAdPresenterProtocol>

@property (nonatomic, readonly) BABAd *ad;

- (instancetype)initWithAd:(BABAd *)ad
                      view:(UIView <BABNativeAdViewProtocol> *)view
                interactor:(id<BABNativeAdInteractorProtocol>)interactor;

- (void)setEventSessionProvider:(id<BZVNativeAdEventSessionProvider>)eventSessionProvider;

@end

NS_ASSUME_NONNULL_END
