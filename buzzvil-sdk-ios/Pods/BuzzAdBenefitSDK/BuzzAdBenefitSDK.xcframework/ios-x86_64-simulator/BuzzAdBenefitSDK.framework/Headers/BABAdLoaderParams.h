#import <Foundation/Foundation.h>

@class BABRevenueType;

NS_ASSUME_NONNULL_BEGIN

@interface BABAdLoaderParams : NSObject

@property (nonatomic, copy, readwrite, nullable) NSNumber *size;
@property (nonatomic, copy, readwrite, nullable) NSArray<BABRevenueType *> *revenueTypes;
@property (nonatomic, copy, readwrite, nullable) NSArray<NSString *> *categories;
@property (nonatomic, copy, readwrite, nullable) NSArray<NSString *> *cpsCategories;

+ (instancetype)params;

@end

NS_ASSUME_NONNULL_END
