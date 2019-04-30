//
//  BABLauncher.h
//  BAB
//
//  Created by Jaehee Ko on 30/01/2019.
//  Copyright © 2019 Buzzvil. All rights reserved.
//

NS_ASSUME_NONNULL_BEGIN

@class BABAd;

@protocol BABLauncher <NSObject>

- (void)openUrl:(NSURL *)url object:(nullable id)object userInfo:(nullable NSDictionary *)userInfo;

@end

NS_ASSUME_NONNULL_END
