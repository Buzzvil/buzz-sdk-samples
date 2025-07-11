#import "EntryPointViewController.h"
@import BuzzvilSDK;

@interface EntryPointViewController ()

@property (nonatomic, strong) UIStackView *stackView;
@property (nonatomic, strong) UIButton *showFabButton;
@property (nonatomic, strong) UIButton *showBannerButton;
@property (nonatomic, strong) UIButton *showPopupButton;
@property (nonatomic, strong) UIButton *showBottomSheetButton;
@property (nonatomic, strong) UIButton *customEntryButton;
@property (nonatomic, strong) BuzzEntryPointBannerView *entryBannerView;
@property (nonatomic, strong) UIView *spacingView;
@property (nonatomic, strong) UIActivityIndicatorView *activityIndicator;
@property (nonatomic, assign) BOOL isFabPresented;

@end

@implementation EntryPointViewController

- (void)viewDidLoad {
  [super viewDidLoad];
  [self setup];
  [self loadEntryPoints];
}

- (void)setup {
  self.view.backgroundColor = [UIColor systemBackgroundColor];
  
  self.stackView = [[UIStackView alloc] initWithFrame:CGRectZero];
  self.stackView.axis = UILayoutConstraintAxisVertical;
  self.stackView.spacing = 12;
  self.stackView.distribution = UIStackViewDistributionFill;
  self.stackView.translatesAutoresizingMaskIntoConstraints = NO;
  
  self.showFabButton = [self createButtonWithTitle:@"Show FAB" action:@selector(showFabButtonTapped)];
  self.showBannerButton = [self createButtonWithTitle:@"Show Banner" action:@selector(showBannerButtonTapped)];
  self.showPopupButton = [self createButtonWithTitle:@"Show Popup" action:@selector(showPopupButtonTapped)];
  self.showBottomSheetButton = [self createButtonWithTitle:@"Show BottomSheet" action:@selector(showBottomSheetButtonTapped)];
  self.customEntryButton = [self createButtonWithTitle:@"Custom Entry" action:@selector(customEntryButtonTapped)];
  
  self.entryBannerView = [[BuzzEntryPointBannerView alloc] initWithFrame:CGRectZero];
  self.entryBannerView.hidden = YES;
  self.entryBannerView.translatesAutoresizingMaskIntoConstraints = NO;
  
  self.spacingView = [[UIView alloc] initWithFrame:CGRectZero];
  [self.spacingView setContentHuggingPriority:UILayoutPriorityDefaultLow forAxis:UILayoutConstraintAxisVertical];
  [self.spacingView setContentCompressionResistancePriority:UILayoutPriorityDefaultLow forAxis:UILayoutConstraintAxisVertical];
  
  self.activityIndicator = [[UIActivityIndicatorView alloc] initWithActivityIndicatorStyle:UIActivityIndicatorViewStyleMedium];
  self.activityIndicator.hidesWhenStopped = YES;
  self.activityIndicator.translatesAutoresizingMaskIntoConstraints = NO;
  
  [self.view addSubview:self.stackView];
  [self.stackView addArrangedSubview:self.showFabButton];
  [self.stackView addArrangedSubview:self.showBannerButton];
  [self.stackView addArrangedSubview:self.showPopupButton];
  [self.stackView addArrangedSubview:self.showBottomSheetButton];
  [self.stackView addArrangedSubview:self.customEntryButton];
  [self.stackView addArrangedSubview:self.entryBannerView];
  [self.stackView addArrangedSubview:self.spacingView];
  [self.view addSubview:self.activityIndicator];
  
  NSArray<UIButton *> *buttons = @[
    self.showFabButton,
    self.showBannerButton,
    self.showPopupButton,
    self.showBottomSheetButton,
    self.customEntryButton
  ];
  
  for (UIButton *button in buttons) {
    [self setupAppearanceOfButton:button];
  }
  
  [NSLayoutConstraint activateConstraints:@[
    [self.stackView.topAnchor constraintEqualToAnchor:self.view.safeAreaLayoutGuide.topAnchor],
    [self.stackView.leadingAnchor constraintEqualToAnchor:self.view.safeAreaLayoutGuide.leadingAnchor],
    [self.stackView.trailingAnchor constraintEqualToAnchor:self.view.safeAreaLayoutGuide.trailingAnchor],
    [self.stackView.bottomAnchor constraintEqualToAnchor:self.view.safeAreaLayoutGuide.bottomAnchor constant:-100],
    
    [self.entryBannerView.heightAnchor constraintEqualToAnchor:self.entryBannerView.widthAnchor multiplier:(112.0/351.0)],
    
    [self.activityIndicator.topAnchor constraintEqualToAnchor:self.view.safeAreaLayoutGuide.topAnchor],
    [self.activityIndicator.leadingAnchor constraintEqualToAnchor:self.view.safeAreaLayoutGuide.leadingAnchor],
    [self.activityIndicator.trailingAnchor constraintEqualToAnchor:self.view.safeAreaLayoutGuide.trailingAnchor],
    [self.activityIndicator.bottomAnchor constraintEqualToAnchor:self.view.safeAreaLayoutGuide.bottomAnchor],
  ]];
}

- (UIButton *)createButtonWithTitle:(NSString *)title action:(SEL)selector {
  UIButton *button = [[UIButton alloc] initWithFrame:CGRectZero];
  [button setTitle:title forState:UIControlStateNormal];
  [button addTarget:self action:selector forControlEvents:UIControlEventTouchUpInside];
  button.translatesAutoresizingMaskIntoConstraints = NO;
  return button;
}

- (void)setupAppearanceOfButton:(UIButton *)button {
  button.backgroundColor = [UIColor systemBlueColor];
  button.layer.cornerRadius = 8;
  [button setTitleColor:[UIColor whiteColor] forState:UIControlStateNormal];
  button.titleLabel.font = [UIFont systemFontOfSize:16];
  [NSLayoutConstraint activateConstraints:@[
    [button.heightAnchor constraintEqualToConstant:48]
  ]];
}

- (void)loadEntryPoints {
  [self.activityIndicator startAnimating];
  
  __weak typeof(self) weakSelf = self;
  [[BuzzEntryPoint shared] loadOnSuccess:^(NSArray * _Nonnull entryPoints) {
    __strong typeof(self) strongSelf = weakSelf;
    [strongSelf.activityIndicator stopAnimating];
  } onFailure:^(NSError * _Nonnull error) {
    __strong typeof(self) strongSelf = weakSelf;
    [strongSelf.activityIndicator stopAnimating];
  }];
}

- (void)showFabButtonTapped {
  if (![[BuzzEntryPoint shared] canShowWithType:BuzzEntryPointTypeFab] || self.isFabPresented) {
    return;
  }
  [[BuzzEntryPoint shared] showFabOn:self];
  self.isFabPresented = YES;
}

- (void)showBannerButtonTapped {
  if (![[BuzzEntryPoint shared] canShowWithType:BuzzEntryPointTypeBanner]) {
    return;
  }
  self.entryBannerView.hidden = NO;
  [[BuzzEntryPoint shared] showBannerOn:self.entryBannerView];
}

- (void)showPopupButtonTapped {
  if (![[BuzzEntryPoint shared] canShowWithType:BuzzEntryPointTypePopup]) {
    return;
  }
  [[BuzzEntryPoint shared] showPopupOn:self];
}

- (void)showBottomSheetButtonTapped {
  if (![[BuzzEntryPoint shared] canShowWithType:BuzzEntryPointTypeBottomSheet]) {
    return;
  }
  [[BuzzEntryPoint shared] showBottomSheetOn:self];
}

- (void)customEntryButtonTapped {
  [[BuzzEntryPoint shared] customEntryPointClickedWithIdentifier:@"YOUR_CUSTOM_ENTRY_IDENTIFIER" on:self];
}

@end
