//
//  CustomCtaView.m
//  BABIntegrationQAObjC
//
//  Created by Hyunsu Park on 2022/01/24.
//

#import "CustomCtaView.h"

// MARK: 4.2. CTA 버튼 자체 구현하기
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

#pragma mark - BZVCtaViewProtocol
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

  [_rewardImageView setImage:[UIImage imageNamed:@"ic_coin"]];
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

  [_rewardImageView setImage:[UIImage imageNamed:@"ic_check"]];
  [_ctaLabel setText:@"YOUR_PARTICIPATED_TEXT"];
}

#pragma mark - UI setup
- (void)setupView {
  self.alignment = UIStackViewAlignmentCenter;
  self.axis = UILayoutConstraintAxisHorizontal;
  self.backgroundColor = UIColor.systemRedColor;
  self.distribution = UIStackViewDistributionEqualSpacing;
  self.layer.cornerRadius = 4;
  self.layoutMargins = UIEdgeInsetsMake(4, 4, 4, 4);
  [self setLayoutMarginsRelativeArrangement:YES];

  _rewardImageView = [[UIImageView alloc] initWithFrame:CGRectZero];
  [self addArrangedSubview:_rewardImageView];

  _rewardLabel = [[UILabel alloc] initWithFrame:CGRectZero];
  [self addArrangedSubview:_rewardLabel];

  _ctaLabel = [[UILabel alloc] initWithFrame:CGRectZero];
  [self addArrangedSubview:_ctaLabel];
}

@end
