//
//  BABTrackerRequestProtocol.h
//  BAB
//
//  Created by Jaehee Ko on 28/01/2019.
//  Copyright © 2019 Buzzvil. All rights reserved.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@protocol BABTrackerRequestProtocol <NSObject>

- (void)get:(NSString *)trackingUrl;
- (void)post:(NSString *)trackingUrl
  withParams:(nullable NSDictionary *)params
      asJSON:(BOOL)asJSON
   onSuccess:(nullable void (^)(NSDictionary *response))onSuccess
   onFailure:(nullable void (^)(NSError * _Nullable error, NSHTTPURLResponse * _Nullable response))onFailure;

@end

NS_ASSUME_NONNULL_END
