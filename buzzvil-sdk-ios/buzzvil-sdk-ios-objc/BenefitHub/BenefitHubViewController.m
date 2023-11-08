@import BuzzvilSDK;
#import "BenefitHubViewController.h"

@interface BenefitHubViewController ()

@end

@implementation BenefitHubViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
}

// 베네핏허브 표시하기
- (void)showBenefitHub {
  BZVBenefitHub *benefitHub = [BZVBenefitHub benefitHubWithBlock:^(BZVBenefitHubBuilder * _Nonnull builder) {}];
  [benefitHub showOn:self];
}

// 베네핏허브 표시하기
- (void)showBenefitHubWithUnitID {
  BZVBenefitHub *benefitHub = [BZVBenefitHub benefitHubWithBlock:^(BZVBenefitHubBuilder * _Nonnull builder) {
    builder.config = [BZVBenefitHubConfig configWithBlock:^(BZVBenefitHubConfigBuilder * _Nonnull builder) {
      builder.unitID = @"SECOND_FEED_UNIT_ID";
    }];
  }];
  [benefitHub showOn:self];
}

// 적립 가능한 포인트 표시하기
- (void)getAvailiableReward {
  BZVBenefitHub *benefitHub = [BZVBenefitHub benefitHubWithBlock:^(BZVBenefitHubBuilder * _Nonnull builder) {}];
  [benefitHub loadOnSuccess:^() {
      // 적립 가능한 포인트를 직접 구현한 UI에 업데이트합니다.
    } onFailure:^(NSError * _Nonnull error) {
      // 적립 가능한 포인트를 가져올 수 없는 경우
  }];
}

@end
