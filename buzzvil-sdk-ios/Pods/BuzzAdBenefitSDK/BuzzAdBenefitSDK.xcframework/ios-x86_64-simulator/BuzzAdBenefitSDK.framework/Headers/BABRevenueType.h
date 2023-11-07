@import Foundation;

NS_ASSUME_NONNULL_BEGIN

typedef NSString * BABRevenueTypeValue;

static BABRevenueTypeValue const BABRevenueTypeValueUnknown       = @"";
static BABRevenueTypeValue const BABRevenueTypeValueCPC           = @"cpc";
static BABRevenueTypeValue const BABRevenueTypeValueCPM           = @"cpm";
static BABRevenueTypeValue const BABRevenueTypeValueCPV           = @"cpv";
static BABRevenueTypeValue const BABRevenueTypeValueCPY           = @"cpy";
static BABRevenueTypeValue const BABRevenueTypeValueCPS           = @"cps";
static BABRevenueTypeValue const BABRevenueTypeValueEXCPS         = @"-cps";

@interface BABRevenueType : NSObject

@property (nonatomic, copy, readonly) NSString *rawValue;

- (instancetype)initWithRawValue:(NSString *)rawValue;

@end

NS_ASSUME_NONNULL_END
