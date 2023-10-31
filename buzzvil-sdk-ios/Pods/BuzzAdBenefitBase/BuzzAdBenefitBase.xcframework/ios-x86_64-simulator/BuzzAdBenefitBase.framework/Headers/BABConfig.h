#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

typedef enum {
  BABEnvTest = 0,
  BABEnvProduction
} BABEnv;

@interface BABConfig : NSObject

@property (nonatomic, assign, readonly) BABEnv environment;
@property (nonatomic, assign, readonly) BOOL logging;
@property (nonatomic, copy, readonly) NSString *appId;

- (instancetype)initWithAppId:(NSString *)appId;
- (instancetype)initWithAppId:(NSString *)appId
                  environment:(BABEnv)environment
                      logging:(BOOL)logging;
@end

NS_ASSUME_NONNULL_END

