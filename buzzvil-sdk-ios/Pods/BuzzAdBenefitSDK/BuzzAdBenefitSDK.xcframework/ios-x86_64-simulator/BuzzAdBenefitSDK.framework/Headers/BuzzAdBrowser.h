#import <UIKit/UIKit.h>

@class BuzzLandingInfo;
@class BuzzAdBrowserViewController;
@protocol BuzzAdBrowserEventDelegate;
@protocol BABBottomSheetPresentable;

@interface BuzzAdBrowser : NSObject

@property (nonatomic, weak, readwrite) id<BuzzAdBrowserEventDelegate> delegate;
@property (nonatomic, assign, readonly) BOOL isOpen;

+ (BuzzAdBrowser *)sharedInstance;

- (void)setLandingInfo:(BuzzLandingInfo *)landingInfo;

- (void)open;
- (UIViewController<BABBottomSheetPresentable> *)browserViewController;

- (void)addBrowserEventDelegate:(id<BuzzAdBrowserEventDelegate>)delegate DEPRECATED_MSG_ATTRIBUTE("Set delegate property instead");
- (void)removeBrowserEventDelegate:(id<BuzzAdBrowserEventDelegate>)delegate DEPRECATED_MSG_ATTRIBUTE("Set delegate property to nil instead");

@end
