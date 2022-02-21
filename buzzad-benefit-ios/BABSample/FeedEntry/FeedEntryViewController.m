#import "FeedEntryViewController.h"

@import BuzzAdBenefit;

#import "FeedEntryIcon.h"
#import "FeedEntryBanner.h"
#import "FeedEntryButton.h"
#import "FeedEntryIconWithMessage.h"

static CGFloat const kHorizontalMarginSmall = 8;
static CGFloat const kVerticalMarginSmall = 8;
static CGFloat const kVerticalMarginMedium = 16;
static NSTimeInterval const kAnimationDuration = 0.2;

@interface FeedEntryViewController () {
  UILabel *_iconLabel;
  FeedEntryIcon *_feedEntryIcon;

  UILabel *_iconWithMessageLabel;
  FeedEntryIconWithMessage *_feedEntryIconWithMessage;

  UILabel *_bannerLabel;
  FeedEntryBanner *_feedEntryBanner;

  UILabel *_buttonLabel;
  FeedEntryButton *_feedEntryButton;
}

@end

@implementation FeedEntryViewController

- (void)viewDidLoad {
  [super viewDidLoad];

  [self setupView];
  [self setupLayout];
  [self preloadFeed];
}

- (void)preloadFeed {
  [_feedEntryIconWithMessage.buzzAdFeed loadOnSuccess:^{
    // Available rewards can be inaccurate after
    //  1. ads are rewarded
    //  2. new ads are loaded by scrolling feed
    [self updateFeedEntryIconWithMessageTextWithReward:self->_feedEntryIconWithMessage.buzzAdFeed.availableRewards];
  } onFailure:^(NSError * _Nonnull error) {
  }];

  [_feedEntryButton.buzzAdFeed loadOnSuccess:^{
    // Available rewards can be inaccurate after
    //  1. ads are rewarded
    //  2. new ads are loaded by scrolling feed
    [self updateFeedEntryButtonTextWithReward:self->_feedEntryButton.buzzAdFeed.availableRewards];
  } onFailure:^(NSError * _Nonnull error) {
  }];
}

- (void)updateFeedEntryIconWithMessageTextWithReward:(NSInteger)reward {
  NSString *rewardText = [self stringFromReward:reward];
  NSString *fullText = [NSString stringWithFormat:@"Get up to %@ points", rewardText];
  NSMutableAttributedString *attributedText = [[NSMutableAttributedString alloc] initWithString:fullText];
  [attributedText addAttribute:NSFontAttributeName
                         value:[UIFont boldSystemFontOfSize:12]
                         range:[[attributedText string] rangeOfString:rewardText]];
  [attributedText addAttribute:NSForegroundColorAttributeName
                         value:[UIColor colorWithRed:87/255.0 green:176/255.0 blue:255/255.0 alpha:1]
                         range:[[attributedText string] rangeOfString:rewardText]];
  [UIView transitionWithView:_feedEntryIconWithMessage.messageLabel
                    duration:kAnimationDuration
                     options:UIViewAnimationOptionTransitionCrossDissolve
                  animations:^{
    [self->_feedEntryIconWithMessage.messageLabel setAttributedText:attributedText];
  } completion:nil];
}

- (void)updateFeedEntryButtonTextWithReward:(NSInteger)reward {
  NSString *rewardText = [self stringFromReward:reward];
  NSString *fullText = [NSString stringWithFormat:@"Get up to %@ points", rewardText];
  NSMutableAttributedString *attributedText = [[NSMutableAttributedString alloc] initWithString:fullText];
  [attributedText addAttribute:NSFontAttributeName
                         value:[UIFont boldSystemFontOfSize:12]
                         range:[[attributedText string] rangeOfString:rewardText]];
  [UIView transitionWithView:_feedEntryButton.button
                    duration:kAnimationDuration
                     options:UIViewAnimationOptionTransitionCrossDissolve
                  animations:^{
    [self->_feedEntryButton.button setAttributedTitle:attributedText forState:UIControlStateNormal];
  } completion:nil];
}

- (NSString *)stringFromReward:(NSInteger)reward {
  NSNumberFormatter* numberFormatter = [[NSNumberFormatter alloc] init];
  [numberFormatter setFormatterBehavior: NSNumberFormatterBehavior10_4];
  [numberFormatter setNumberStyle: NSNumberFormatterDecimalStyle];
  return [numberFormatter stringFromNumber:[NSNumber numberWithInteger:reward]];
}

#pragma mark - UI setup
- (void)setupView {
  self.view.backgroundColor = UIColor.whiteColor;

  _iconLabel = [[UILabel alloc] initWithFrame:CGRectZero];
  _iconLabel.font = [UIFont boldSystemFontOfSize:14];
  _iconLabel.text = @"Icon";
  _iconLabel.textColor = UIColor.blackColor;
  [self.view addSubview:_iconLabel];

  _feedEntryIcon = [[FeedEntryIcon alloc] initWithFrame:CGRectZero];
  _feedEntryIcon.iconImageView.image = [UIImage imageNamed:@"ic_coin"];
  [self.view addSubview:_feedEntryIcon];

  _iconWithMessageLabel = [[UILabel alloc] initWithFrame:CGRectZero];
  _iconWithMessageLabel.font = [UIFont boldSystemFontOfSize:14];
  _iconWithMessageLabel.text = @"Icon + Message";
  _iconWithMessageLabel.textColor = UIColor.blackColor;
  [self.view addSubview:_iconWithMessageLabel];

  _feedEntryIconWithMessage = [[FeedEntryIconWithMessage alloc] initWithFrame:CGRectZero];
  _feedEntryIconWithMessage.iconImageView.image = [UIImage imageNamed:@"ic_coin"];
  [_feedEntryIconWithMessage.messageLabel setText:@"Get Extra Points Now!"];
  [self.view addSubview:_feedEntryIconWithMessage];

  _bannerLabel = [[UILabel alloc] initWithFrame:CGRectZero];
  _bannerLabel.font = [UIFont boldSystemFontOfSize:14];
  _bannerLabel.text = @"Banner";
  _bannerLabel.textColor = UIColor.blackColor;
  [self.view addSubview:_bannerLabel];

  _feedEntryBanner = [[FeedEntryBanner alloc] initWithFrame:CGRectZero];
  [self.view addSubview:_feedEntryBanner];

  _buttonLabel = [[UILabel alloc] initWithFrame:CGRectZero];
  _buttonLabel.font = [UIFont boldSystemFontOfSize:14];
  _buttonLabel.text = @"Button";
  _buttonLabel.textColor = UIColor.blackColor;
  [self.view addSubview:_buttonLabel];

  _feedEntryButton = [[FeedEntryButton alloc] initWithFrame:CGRectZero];
  [_feedEntryButton.button setTitle:@"Get extra points now!" forState:UIControlStateNormal];
  [self.view addSubview:_feedEntryButton];
}

- (void)setupLayout {
  _iconLabel.translatesAutoresizingMaskIntoConstraints = NO;
  [NSLayoutConstraint activateConstraints:@[
    [_iconLabel.leadingAnchor constraintEqualToAnchor:self.view.layoutMarginsGuide.leadingAnchor constant:kHorizontalMarginSmall],
    [_iconLabel.topAnchor constraintEqualToAnchor:self.view.layoutMarginsGuide.topAnchor constant:kVerticalMarginMedium],
  ]];

  _feedEntryIcon.translatesAutoresizingMaskIntoConstraints = NO;
  [NSLayoutConstraint activateConstraints:@[
    [_feedEntryIcon.leadingAnchor constraintEqualToAnchor:self.view.layoutMarginsGuide.leadingAnchor constant:kHorizontalMarginSmall],
    [_feedEntryIcon.topAnchor constraintEqualToAnchor:_iconLabel.bottomAnchor constant:kVerticalMarginSmall],
    [_feedEntryIcon.widthAnchor constraintEqualToConstant:32],
    [_feedEntryIcon.heightAnchor constraintEqualToConstant:32],
  ]];

  _iconWithMessageLabel.translatesAutoresizingMaskIntoConstraints = NO;
  [NSLayoutConstraint activateConstraints:@[
    [_iconWithMessageLabel.leadingAnchor constraintEqualToAnchor:self.view.layoutMarginsGuide.leadingAnchor constant:kHorizontalMarginSmall],
    [_iconWithMessageLabel.topAnchor constraintEqualToAnchor:_feedEntryIcon.bottomAnchor constant:kVerticalMarginMedium],
  ]];

  _feedEntryIconWithMessage.translatesAutoresizingMaskIntoConstraints = NO;
  [NSLayoutConstraint activateConstraints:@[
    [_feedEntryIconWithMessage.leadingAnchor constraintEqualToAnchor:self.view.layoutMarginsGuide.leadingAnchor constant:kHorizontalMarginSmall],
    [_feedEntryIconWithMessage.trailingAnchor constraintEqualToAnchor:_feedEntryIconWithMessage.messageLabel.trailingAnchor constant:4],
    [_feedEntryIconWithMessage.topAnchor constraintEqualToAnchor:_iconWithMessageLabel.bottomAnchor constant:kVerticalMarginSmall],
    [_feedEntryIconWithMessage.heightAnchor constraintEqualToConstant:32],
  ]];

  _bannerLabel.translatesAutoresizingMaskIntoConstraints = NO;
  [NSLayoutConstraint activateConstraints:@[
    [_bannerLabel.leadingAnchor constraintEqualToAnchor:self.view.layoutMarginsGuide.leadingAnchor constant:kHorizontalMarginSmall],
    [_bannerLabel.topAnchor constraintEqualToAnchor:_feedEntryIconWithMessage.bottomAnchor constant:kVerticalMarginMedium],
  ]];

  _feedEntryBanner.translatesAutoresizingMaskIntoConstraints = NO;
  [NSLayoutConstraint activateConstraints:@[
    [_feedEntryBanner.leadingAnchor constraintEqualToAnchor:self.view.layoutMarginsGuide.leadingAnchor constant:kHorizontalMarginSmall],
    [_feedEntryBanner.trailingAnchor constraintEqualToAnchor:self.view.layoutMarginsGuide.trailingAnchor constant:-kHorizontalMarginSmall],
    [_feedEntryBanner.topAnchor constraintEqualToAnchor:_bannerLabel.bottomAnchor constant:kVerticalMarginSmall],
    [_feedEntryBanner.heightAnchor constraintEqualToConstant:100],
  ]];

  _buttonLabel.translatesAutoresizingMaskIntoConstraints = NO;
  [NSLayoutConstraint activateConstraints:@[
    [_buttonLabel.leadingAnchor constraintEqualToAnchor:self.view.layoutMarginsGuide.leadingAnchor constant:kHorizontalMarginSmall],
    [_buttonLabel.topAnchor constraintEqualToAnchor:_feedEntryBanner.bottomAnchor constant:kVerticalMarginMedium],
  ]];

  _feedEntryButton.translatesAutoresizingMaskIntoConstraints = NO;
  [NSLayoutConstraint activateConstraints:@[
    [_feedEntryButton.leadingAnchor constraintEqualToAnchor:self.view.layoutMarginsGuide.leadingAnchor constant:kHorizontalMarginSmall],
    [_feedEntryButton.topAnchor constraintEqualToAnchor:_buttonLabel.bottomAnchor constant:kVerticalMarginSmall],
    [_feedEntryButton.widthAnchor constraintEqualToAnchor:_feedEntryButton.button.widthAnchor],
    [_feedEntryButton.heightAnchor constraintEqualToAnchor:_feedEntryButton.button.heightAnchor],
  ]];
}

@end
