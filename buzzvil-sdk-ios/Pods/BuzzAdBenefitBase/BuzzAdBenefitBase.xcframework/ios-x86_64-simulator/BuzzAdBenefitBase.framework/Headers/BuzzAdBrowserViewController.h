#import <UIKit/UIKit.h>

@protocol BABBottomSheetPresentable;
@class BuzzLandingInfo;

@interface BuzzAdBrowserViewController : UIViewController <BABBottomSheetPresentable>

- (instancetype)initWithLandingInfo:(BuzzLandingInfo *)landingInfo;

@end
