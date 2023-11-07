#import <Foundation/Foundation.h>

@interface BABProduct : NSObject

@property (nonatomic, assign, readonly) float price;
@property (nonatomic, assign, readonly) float discountedPrice;
@property (nonatomic, copy, readonly) NSString *category;
@property (nonatomic, assign, readonly) BOOL isSpotlighted;

- (instancetype)initWithDictionary:(NSDictionary *)dictionary;

@end
