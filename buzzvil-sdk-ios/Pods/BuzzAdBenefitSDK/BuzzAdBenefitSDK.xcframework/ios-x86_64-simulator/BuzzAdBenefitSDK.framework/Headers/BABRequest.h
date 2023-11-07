#import <Foundation/Foundation.h>
#import "BABError.h"

NS_ASSUME_NONNULL_BEGIN

@protocol BABRequestHeaderDelegate <NSObject>

- (NSDictionary *)getHeader;

@end

@interface BABRequest : NSObject

@property (nonatomic, assign) BOOL ignoreDefaultParams;
@property (nonatomic, weak, readwrite) id<BABRequestHeaderDelegate> headerDelegate;

+ (BABRequest *)requestWithBaseUrl:(NSString *)baseUrl;
+ (BABRequest *)requestWithoutBaseUrl;

- (void)get:(NSString *)path
  withParams:(NSDictionary *)params
   onSuccess:(void (^)(NSDictionary *response))onSuccess
   onFailure:(void (^)(BABError * error))onFailure;
- (void)get:(NSString *)path
 withParams:(NSDictionary *)params
    timeout:(NSTimeInterval)timeout
  onSuccess:(void (^)(NSDictionary *response))onSuccess
  onFailure:(void (^)(BABError * error))onFailure;
- (void)post:(NSString *)path
  withParams:(NSDictionary *)params
   onSuccess:(void (^)(NSDictionary *response))onSuccess
   onFailure:(void (^)(BABError *error))onFailure;
- (void)post:(NSString *)path
  withParams:(NSDictionary *)params
     timeout:(NSTimeInterval)timeout
   onSuccess:(void (^)(NSDictionary *response))onSuccess
   onFailure:(void (^)(BABError *error))onFailure;
- (void)post:(NSString *)path
  withParams:(NSDictionary *)params
      isJson:(Boolean)isJson
   onSuccess:(void (^)(NSDictionary *response))onSuccess
   onFailure:(void (^)(BABError *error))onFailure;
- (void)post:(NSString *)path
  withParams:(NSDictionary *)params
      isJson:(Boolean)isJson
     timeout:(NSTimeInterval)timeout
   onSuccess:(void (^)(NSDictionary *response))onSuccess
   onFailure:(void (^)(BABError *error))onFailure;

@end

NS_ASSUME_NONNULL_END
