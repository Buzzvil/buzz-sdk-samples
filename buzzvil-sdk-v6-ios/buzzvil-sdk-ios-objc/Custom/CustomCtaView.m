#import "CustomCtaView.h"

@interface CustomCtaView ()

@property (nonatomic, strong, readonly) UIImageView *rewardImageView;
@property (nonatomic, strong, readonly) UILabel *rewardLabel;
@property (nonatomic, strong, readonly) UILabel *ctaLabel;

@end

@implementation CustomCtaView

- (instancetype)initWithCoder:(NSCoder *)coder {
  if (self = [super initWithCoder:coder]) {
    [self setupView];
  }
  return self;
}

- (instancetype)initWithFrame:(CGRect)frame {
  if (self = [super initWithFrame:frame]) {
    [self setupView];
  }
  return self;
}

- (void)setupView {
  _rewardImageView = [[UIImageView alloc] initWithFrame:CGRectZero];
  [self addArrangedSubview:_rewardImageView];

  _rewardLabel = [[UILabel alloc] initWithFrame:CGRectZero];
  [self addArrangedSubview:_rewardLabel];

  _ctaLabel = [[UILabel alloc] initWithFrame:CGRectZero];
  [self addArrangedSubview:_ctaLabel];
}

#pragma mark - BuzzCtaViewProtocol
- (void)renderRewardNotAvailableViewStateWithCtaText:(NSString *)ctaText {
  // 리워드가 없는 광고에 대한 CTA 뷰 레이아웃을 정의합니다.
  [_rewardImageView setHidden:YES];
  [_rewardLabel setHidden:YES];
  [_ctaLabel setText:ctaText];
}

- (void)renderRewardAvailableViewStateWithCtaText:(NSString *)ctaText reward:(NSInteger)reward {
  // 리워드가 있는 광고에 대한 CTA 뷰 레이아웃을 정의합니다.
  [_rewardImageView setHidden:NO];
  [_rewardLabel setHidden:NO];
  
  [_rewardImageView setImage:[UIImage imageNamed:@"YOUR_REWARD_IMAGE"]];
  [_rewardLabel setText:[NSString stringWithFormat:@"+%ld", reward]];
  [_ctaLabel setText:ctaText];
}

- (void)renderParticipatingViewStateWithCtaText:(NSString *)ctaText {
  // 참여 확인 중인 광고에 대한 CTA 뷰 레이아웃을 정의합니다.
  [_rewardImageView setHidden:YES];
  [_rewardLabel setHidden:YES];
  [_ctaLabel setText:@"YOUR_PARTICIPATING_TEXT"];
}

- (void)renderParticipatedViewStateWithCtaText:(NSString *)ctaText {
  // 참여 완료한 광고에 대한 CTA 뷰 레이아웃을 정의합니다.
  [_rewardImageView setHidden:NO];
  [_rewardLabel setHidden:YES];
  
  [_rewardImageView setImage:[UIImage imageNamed:@"YOUR_PARTICIPATED_IMAGE"]];
  [_ctaLabel setText:@"YOUR_PARTICIPATED_TEXT"];
}

@end
