#import "BenefitHubViewController.h"
@import BuzzvilSDK;

@interface BenefitHubViewController ()

@property (nonatomic, strong) BuzzBenefitHub *benefitHub;
@property (nonatomic, strong) UIStackView *stackView;
@property (nonatomic, strong) UIButton *showBenefitHubButton;
@property (nonatomic, strong) UIButton *showLuckyBoxButton;
@property (nonatomic, strong) UIButton *showMissionPackButton;
@property (nonatomic, strong) UIButton *showHistoryButton;

@end

@implementation BenefitHubViewController

- (void)viewWillAppear:(BOOL)animated {
  [super viewWillAppear:animated];
  
  self.navigationController.navigationBarHidden = NO;
}

- (void)viewDidLoad {
  [super viewDidLoad];
  
  [self setupView];
  [self setupLayout];
}

- (void)setupView {
  self.view.backgroundColor = [UIColor systemBackgroundColor];
  
  self.showBenefitHubButton = [self createButtonWithTitle:@"Show BenefitHub" action:@selector(showBenefitHub)];
  self.showLuckyBoxButton = [self createButtonWithTitle:@"Show LuckyBox" action:@selector(showLuckyBox)];
  self.showMissionPackButton = [self createButtonWithTitle:@"Show MissionPack" action:@selector(showMissionPack)];
  self.showHistoryButton = [self createButtonWithTitle:@"Show History" action:@selector(showHistory)];
  
  self.stackView = [[UIStackView alloc] initWithArrangedSubviews:@[self.showBenefitHubButton, self.showLuckyBoxButton, self.showMissionPackButton, self.showHistoryButton]];
  self.stackView.axis = UILayoutConstraintAxisVertical;
  self.stackView.spacing = 8;
  self.stackView.distribution = UIStackViewDistributionFillEqually;
  
  [self.view addSubview:self.stackView];
  
  NSArray *buttonsToSetupAppearance = @[self.showBenefitHubButton, self.showLuckyBoxButton, self.showMissionPackButton, self.showHistoryButton];
  
  for (UIButton *button in buttonsToSetupAppearance) {
    [self setupAppearanceOfButton:button];
  }
}

- (UIButton *)createButtonWithTitle:(NSString *)title action:(SEL)action {
  UIButton *button = [UIButton buttonWithType:UIButtonTypeSystem];
  [button setTitle:title forState:UIControlStateNormal];
  [button addTarget:self action:action forControlEvents:UIControlEventTouchUpInside];
  return button;
}

- (void)setupAppearanceOfButton:(UIButton *)button {
  button.backgroundColor = [UIColor systemBlueColor];
  button.layer.cornerRadius = 8;
  [button setTitleColor:[UIColor whiteColor] forState:UIControlStateNormal];
  button.titleLabel.font = [UIFont systemFontOfSize:16];
  button.translatesAutoresizingMaskIntoConstraints = NO;
  [NSLayoutConstraint activateConstraints:@[
    [button.heightAnchor constraintEqualToConstant:32]
  ]];
}

- (void)setupLayout {
  self.stackView.translatesAutoresizingMaskIntoConstraints = NO;
  [NSLayoutConstraint activateConstraints:@[
    [self.stackView.topAnchor constraintEqualToAnchor:self.view.safeAreaLayoutGuide.topAnchor],
    [self.stackView.leadingAnchor constraintEqualToAnchor:self.view.safeAreaLayoutGuide.leadingAnchor],
    [self.stackView.trailingAnchor constraintEqualToAnchor:self.view.safeAreaLayoutGuide.trailingAnchor],
    [self.stackView.bottomAnchor constraintEqualToAnchor:self.view.safeAreaLayoutGuide.bottomAnchor],
  ]];
}

- (void)showBenefitHub {
  self.benefitHub = [[BuzzBenefitHub alloc] init];
  [self.benefitHub showOn:self];
}

- (void)showLuckyBox {
  self.benefitHub = [[BuzzBenefitHub alloc] init];
  BuzzBenefitHubConfig * benefithubConfig = [BuzzBenefitHubConfig configWith:^(BuzzBenefitHubConfigBuilder * _Nonnull builder) {
    builder.queryParams = [BuzzBenefitHubPage.luckyBox toRedirectQueryParams];
  }];
  
  [self.benefitHub setConfig:benefithubConfig];
  [self.benefitHub showOn:self];
}

- (void)showMissionPack {
  self.benefitHub = [[BuzzBenefitHub alloc] init];
  BuzzBenefitHubConfig * benefithubConfig = [BuzzBenefitHubConfig configWith:^(BuzzBenefitHubConfigBuilder * _Nonnull builder) {
    builder.queryParams = [BuzzBenefitHubPage.missionPack toRedirectQueryParams];
  }];
  
  [self.benefitHub setConfig:benefithubConfig];
  [self.benefitHub showOn:self];
}

- (void)showHistory {
  self.benefitHub = [[BuzzBenefitHub alloc] init];
  BuzzBenefitHubConfig * benefithubConfig = [BuzzBenefitHubConfig configWith:^(BuzzBenefitHubConfigBuilder * _Nonnull builder) {
    builder.queryParams = [BuzzBenefitHubPage.history toRedirectQueryParams];
  }];
  
  [self.benefitHub setConfig:benefithubConfig];
  [self.benefitHub showOn:self];
}

@end
