@import Foundation;

NS_ASSUME_NONNULL_BEGIN

typedef NSString * BZVRevenueType;

extern BZVRevenueType const BZVRevenueTypeUnknown;
extern BZVRevenueType const BZVRevenueTypeCPC;
extern BZVRevenueType const BZVRevenueTypeCPM;
extern BZVRevenueType const BZVRevenueTypeCPV;
extern BZVRevenueType const BZVRevenueTypeCPY;
extern BZVRevenueType const BZVRevenueTypeCPS;
extern BZVRevenueType const BZVRevenueTypeEXCPS;

extern BZVRevenueType BZVRevenueTypeFromRawString(NSString *string);

NS_ASSUME_NONNULL_END
