#import <Foundation/Foundation.h>

@interface BuzzLandingInfo : NSObject

@property (nonatomic, copy, readonly) NSString *title;
@property (nonatomic, copy, readonly) NSURL *url;
@property (nonatomic, assign, readonly) long durationInMillis;
@property (nonatomic, assign, readonly) long reward;

- (instancetype)initWithTitle:(NSString *)title
                          url:(NSURL *)url
             durationInMillis:(long)durationInMillis
                       reward:(long)reward;

@end
