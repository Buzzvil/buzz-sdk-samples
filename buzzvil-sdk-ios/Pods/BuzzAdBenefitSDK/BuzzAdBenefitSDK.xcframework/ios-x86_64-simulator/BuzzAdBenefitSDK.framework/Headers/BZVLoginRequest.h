@import Foundation;

@class BZVLoginRequestBuilder;

NS_ASSUME_NONNULL_BEGIN

typedef NS_ENUM(NSUInteger, BZVUserGender) {
  BZVUserGenderUnknown,
  BZVUserGenderMale,
  BZVUserGenderFemale,
};

typedef void (^BZVLoginRequestBuilderBlock)(BZVLoginRequestBuilder *builder);

@interface BZVLoginRequest : NSObject

@property (nonatomic, copy, readonly) NSString *userId;
@property (nonatomic, assign, readonly) NSInteger birthYear;
@property (nonatomic, assign, readonly) BZVUserGender gender;
@property (nonatomic, assign, readonly) BOOL showAppTrackingTransparencyDialog;

@end

@interface BZVLoginRequest (Builder)

+ (instancetype)requestWithBlock:(BZVLoginRequestBuilderBlock)block;

@end

@interface BZVLoginRequestBuilder : NSObject

@property (nonatomic, copy, readwrite) NSString *userId;
@property (nonatomic, assign, readwrite) NSInteger birthYear;
@property (nonatomic, assign, readwrite) BZVUserGender gender;
@property (nonatomic, assign, readwrite) BOOL showAppTrackingTransparencyDialog;

- (BZVLoginRequest *)build;

@end

NS_ASSUME_NONNULL_END
