@import BuzzAdBenefitFeed;

NS_ASSUME_NONNULL_BEGIN

@interface FeedEntryIconWithMessage : BABFeedEntryView

@property (nonatomic, strong, readonly) UIView *iconBackgoundView;
@property (nonatomic, strong, readonly) UIImageView *iconImageView;
@property (nonatomic, strong, readonly) UILabel *messageLabel;

@end

NS_ASSUME_NONNULL_END
