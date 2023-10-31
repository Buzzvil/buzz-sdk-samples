@import Foundation;

NS_ASSUME_NONNULL_BEGIN

@interface BBSEventMapper : NSObject

/// Objective-C는 Dictionary에 대한 컴파일 타입의 호환성을 지원하지 않음
/// BuzzBooster Public API send Event의 NSDictionary<NSString *, NSString *> *인 eventValues에
/// @{@"key", @1} 인 형태로 제공해도 컴파일 타임은 통과하고 런타임에 문제가 발생함.
/// 이를 방지하고자 이렇게 넘어온 값들은 임의적으로 NSString 처리
/// 처리된 값: __NSCFBoolean, __NSCFNumber, NSArray, NSDictionary, NSSet
/// UUID, Date 등은 description으로 처리
- (NSDictionary<NSString *, NSString *> *)map:(NSDictionary *)dictionary;

@end

NS_ASSUME_NONNULL_END
