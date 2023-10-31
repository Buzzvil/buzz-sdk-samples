@class BZVLaunchInfo;
@protocol BZVLauncher;

typedef NS_ENUM(NSInteger, BZVLauncherStatus) {
  BZVLauncherStatusPageLoadFailed,
  BZVLauncherStatusLandingConditionNotSatisfied,
  BZVLauncherStatusDeepLinkOpened
};

NS_ASSUME_NONNULL_BEGIN

@protocol BZVLauncherDelegate <NSObject>

- (void)launcher:(id<BZVLauncher>)launcher didSucceedToLaunchWithInfo:(BZVLaunchInfo *)launchInfo;
- (void)launcher:(id<BZVLauncher>)launcher didFailToLaunchWithInfo:(BZVLaunchInfo *)launchInfo status:(BZVLauncherStatus)status;
- (void)launcher:(id<BZVLauncher>)launcher didOpenExternalBrowserWithInfo:(BZVLaunchInfo *)launchInfo;

@end

@protocol BZVLauncher <NSObject>

@property (nonatomic, weak, readwrite, nullable) id<BZVLauncherDelegate> delegate;

- (void)openWithLaunchInfo:(BZVLaunchInfo *)launchInfo;

@end

NS_ASSUME_NONNULL_END
