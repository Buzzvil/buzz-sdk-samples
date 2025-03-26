@import BuzzvilSDK;
#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@interface CustomLauncher : NSObject <BZVLauncher>

// 런처와 관련된 이벤트를 수신할 수 있습니다.
@property (nonatomic, weak, readwrite, nullable) id<BZVLauncherDelegate> delegate;

@end

NS_ASSUME_NONNULL_END
