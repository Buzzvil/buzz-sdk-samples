#import "CustomLauncher.h"
#import "CustomBrowserViewController.h"

@implementation CustomLauncher

- (void)openWithLaunchInfo:(BZVLaunchInfo *)launchInfo {
  // launchInfo의 인자를 확인하여 광고 또는 콘텐츠인지 미리 판단할 수 있습니다.
  if (launchInfo.ad != nil) {
    if (launchInfo.ad.isDeepLink) {
      // 딥링크 광고일 경우 필요한 작업을 할 수 있습니다.
    }
  } else if (launchInfo.article != nil){
    NSString *sourceURL = launchInfo.article.sourceURL;
  }

  // Custom Browser 실행
  CustomBrowserViewController *customBrowserViewController = [[CustomBrowserViewController alloc] init];
  [YOUR_ROOT_VIEW_CONTROLLER.presentedViewController presentViewController:customBrowserViewController animated:YES completion:nil];
}

@end
