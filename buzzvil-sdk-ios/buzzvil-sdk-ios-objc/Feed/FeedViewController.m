@import BuzzvilSDK;
#import "FeedViewController.h"

@interface FeedViewController ()

@end

@implementation FeedViewController

- (void)viewDidLoad {
  [super viewDidLoad];
  // Do any additional setup after loading the view.
  [self showFeed];
}

// 베네핏허브 표시하기
- (void)showFeed {
  BZVBuzzAdFeed *buzzAdFeed = [BZVBuzzAdFeed feedWithBlock:^(BZVBuzzAdFeedBuilder * _Nonnull builder) {}];
  [benefitHub showOn:self];
}

// 베네핏허브 표시하기
- (void)showFeedWithUnitID {
  BZVBuzzAdFeed *buzzAdFeed = [BZVBuzzAdFeed feedWithBlock:^(BZVBuzzAdFeedBuilder * _Nonnull builder) {
    builder.config = [BZVFeedConfig configWithBlock:^(BZVFeedConfigBuilder * _Nonnull builder) {
      builder.unitID = @"SECOND_FEED_UNIT_ID";
    }];
  }];
  [benefitHub showOn:self];
}

// 적립 가능한 포인트 표시하기
- (void)getAvailiableReward {
  BZVBuzzAdFeed *buzzAdFeed = [BZVBuzzAdFeed feedWithBlock:^(BZVBuzzAdFeedBuilder * _Nonnull builder) {}];
  [buzzAdFeed loadOnSuccess:^() {
    // 적립 가능한 포인트를 직접 구현한 UI에 업데이트합니다.
    NSInteger availiableRewards = [buzzAdFeed availableRewards];
  } onFailure:^(NSError * _Nonnull error) {
    // 적립 가능한 포인트를 가져올 수 없는 경우
  }];
}

@end
