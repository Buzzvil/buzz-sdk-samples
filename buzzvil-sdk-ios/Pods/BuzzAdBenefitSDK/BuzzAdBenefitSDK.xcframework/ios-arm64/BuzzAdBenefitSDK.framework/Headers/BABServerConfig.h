#import <Foundation/Foundation.h>

typedef NS_ENUM(NSInteger, BABEnvironment) {
  BABEnvironmentDev,
  BABEnvironmentStaging,
  BABEnvironmentStagingQA,
  BABEnvironmentStagingQA2,
  BABEnvironmentProduction,
  BABEnvironmentProductionMini
};

@interface BABServerConfig : NSObject

@property (nonatomic, assign, readonly) BABEnvironment env;

+ (instancetype)productionConfig;

+ (instancetype)productionMiniConfig;

+ (instancetype)stagingConfig;

+ (instancetype)stagingQAConfig;

+ (instancetype)stagingQA2Config;

+ (instancetype)devConfig;

@end
