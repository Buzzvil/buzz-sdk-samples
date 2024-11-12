@import BuzzvilSDK;
#import "MissionPackViewController.h"

@interface MissionPackViewController()

@property (nonatomic, strong, readonly) BuzzAdBenefitMissionPack * missionPack;

@end

@implementation MissionPackViewController

- (void)viewDidLoad {
  [super viewDidLoad];
  
  _missionPack = [[BuzzAdBenefitMissionPack alloc] initWithUnitId:@"YOUR_MISSION_PACK_UNIT_ID"];
}

- (void)checkCanOpenMissionPack {
  [_missionPack canOpenOnSuccess:^{
    // missionPack 이 열릴 수 있을 때 호출 됩니다.
  } onFailure:^(NSError * _Nonnull error) {
    // missionPack 이 열릴 수 없을 때 호출 됩니다.
  }];
}

- (void)showMissionPack {
  [_missionPack showOn:self onFailure:^(NSError * _Nonnull error) {
    // missionPack 이 열릴 수 없을 때 호출 됩니다.
  }];
}

@end
