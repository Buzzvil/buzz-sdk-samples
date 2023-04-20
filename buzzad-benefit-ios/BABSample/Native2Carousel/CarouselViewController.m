#import "CarouselViewController.h"

@import BuzzAdBenefit;
@import Toast;

#import "AppConstant.h"
#import "CarouselCell.h"
#import "CarouselFeedEntryView.h"

static NSString * const kNavigationItemTitle = @"Carousel";

// MARK: 네이티브 2.0 캐러셀 구현 - UICollectionView 구현하기
@interface CarouselViewController () <UICollectionViewDelegate, UICollectionViewDataSource>

@property (nonatomic, assign, readonly) NSInteger adRequestCount;
@property (nonatomic, assign, readwrite) NSInteger loadedAdCount;
@property (nonatomic, strong, readonly) UICollectionView *carouselCollectionView;
// MARK: 네이티브 2.0 캐러셀 구현 - UIPageControl 구현하기
@property (nonatomic, strong, readonly) UIPageControl *pageControl;
// MARK: 네이티브 2.0 캐러셀 구현 - 피드 엔트리 포인트
@property (nonatomic, strong, readonly) CarouselFeedEntryView *feedEntryView;
@property (nonatomic, strong, readonly) BZVNativeAd2Pool *pool;

@end

@implementation CarouselViewController

- (void)viewDidLoad {
  [super viewDidLoad];
  
  _adRequestCount = 5;
  _loadedAdCount = 0;
  _pool = [[BZVNativeAd2Pool alloc] initWithUnitId:FEED_UNIT_ID];
  
  [self setupView];
  [self setupLayout];
  [self setupCarousel];
}

- (void)setupView {
  self.navigationItem.title = kNavigationItemTitle;
  self.view.backgroundColor = UIColor.whiteColor;
  
  UICollectionViewFlowLayout *flowLayout = [[UICollectionViewFlowLayout alloc] init];
  flowLayout.scrollDirection = UICollectionViewScrollDirectionHorizontal;
  flowLayout.minimumLineSpacing = 0;
  flowLayout.minimumInteritemSpacing = 0;
  
  _carouselCollectionView = [[UICollectionView alloc] initWithFrame:CGRectZero collectionViewLayout:flowLayout];
  [_carouselCollectionView registerClass:[CarouselCell class] forCellWithReuseIdentifier:@"CarouselCell"];
  _carouselCollectionView.delegate = self;
  _carouselCollectionView.dataSource = self;
  _carouselCollectionView.showsHorizontalScrollIndicator = NO;
  _carouselCollectionView.pagingEnabled = YES;
  [self.view addSubview:_carouselCollectionView];
  
  _pageControl = [[UIPageControl alloc] initWithFrame:CGRectZero];
  _pageControl.currentPage = 0;
  _pageControl.userInteractionEnabled = NO;
  _pageControl.pageIndicatorTintColor = UIColor.lightGrayColor;
  _pageControl.currentPageIndicatorTintColor = UIColor.grayColor;
  [self.view addSubview:self.pageControl];
  
  _feedEntryView = [[CarouselFeedEntryView alloc] initWithFrame:CGRectZero];
  [self.view addSubview:self.feedEntryView];
}

- (void)setupLayout {
  _carouselCollectionView.translatesAutoresizingMaskIntoConstraints = NO;
  [NSLayoutConstraint activateConstraints:@[
    [_carouselCollectionView.topAnchor constraintEqualToAnchor:self.view.safeAreaLayoutGuide.topAnchor constant:16],
    [_carouselCollectionView.leadingAnchor constraintEqualToAnchor:self.view.safeAreaLayoutGuide.leadingAnchor],
    [_carouselCollectionView.trailingAnchor constraintEqualToAnchor:self.view.safeAreaLayoutGuide.trailingAnchor],
    [_carouselCollectionView.heightAnchor constraintEqualToAnchor:self.carouselCollectionView.widthAnchor multiplier:0.85],
  ]];
  
  _pageControl.translatesAutoresizingMaskIntoConstraints = NO;
  [NSLayoutConstraint activateConstraints:@[
    [_pageControl.topAnchor constraintEqualToAnchor:_feedEntryView.bottomAnchor constant:16],
    [_pageControl.centerXAnchor constraintEqualToAnchor:_carouselCollectionView.centerXAnchor],
  ]];
  
  _feedEntryView.translatesAutoresizingMaskIntoConstraints = NO;
  [NSLayoutConstraint activateConstraints:@[
    [_feedEntryView.topAnchor constraintEqualToAnchor:_carouselCollectionView.bottomAnchor constant:8],
    [_feedEntryView.centerXAnchor constraintEqualToAnchor:_carouselCollectionView.centerXAnchor],
  ]];
}

- (void)setupCarousel {
  __weak typeof(self) weakSelf = self;
  [self.pool loadAdsWithCount:self.adRequestCount completionHandler:^(NSInteger adCount) {
    __strong typeof(self) strongSelf = weakSelf;
    if (strongSelf) {
      strongSelf.loadedAdCount = adCount;
      strongSelf.pageControl.numberOfPages = adCount;
      [strongSelf.carouselCollectionView reloadData];
      [strongSelf setCollectionViewInitialPosition];
    }
  } errorHandler:^(NSError * _Nonnull error) {
    __strong typeof(self) strongSelf = weakSelf;
    if (strongSelf) {
      [strongSelf.view.window makeToast:[NSString stringWithFormat:@"Error: %@", error.localizedDescription]];
      strongSelf.carouselCollectionView.hidden = YES;
      strongSelf.feedEntryView.hidden = YES;
    }
  }];
}

- (void)setCollectionViewInitialPosition {
  NSInteger middleIndex = (_loadedAdCount/2) % _loadedAdCount;
  NSIndexPath *indexPath = [NSIndexPath indexPathForItem:middleIndex inSection:0];
  [self.carouselCollectionView scrollToItemAtIndexPath:indexPath atScrollPosition:(UICollectionViewScrollPositionCenteredVertically | UICollectionViewScrollPositionCenteredHorizontally) animated:NO];
  self.pageControl.currentPage = indexPath.item;
}

#pragma mark - UICollectionViewDelegate

- (void)scrollViewDidScroll:(UIScrollView *)scrollView {
  CGFloat centerOffsetX = scrollView.contentOffset.x + (scrollView.frame.size.width / 2);
  CGFloat pageWidth = scrollView.frame.size.width;
  
  if (pageWidth == 0) {
    return;
  }
  _pageControl.currentPage = (NSInteger)centerOffsetX / (NSInteger)pageWidth;
}

#pragma mark - UICollectionViewDataSource

- (NSInteger)collectionView:(UICollectionView *)collectionView numberOfItemsInSection:(NSInteger)section {
  return _loadedAdCount;
}

- (UICollectionViewCell *)collectionView:(UICollectionView *)collectionView cellForItemAtIndexPath:(NSIndexPath *)indexPath {
  CarouselCell *cell = [collectionView dequeueReusableCellWithReuseIdentifier:@"CarouselCell" forIndexPath:indexPath];
  [cell setPool:_pool forAdKey:indexPath.item];
  [cell bind];
  return cell;
}

#pragma mark - UICollectionViewDelegateFlowLayout

- (CGSize)collectionView:(UICollectionView *)collectionView layout:(UICollectionViewLayout *)collectionViewLayout sizeForItemAtIndexPath:(NSIndexPath *)indexPath {
  return CGSizeMake(collectionView.frame.size.width, collectionView.frame.size.height);
}

@end
