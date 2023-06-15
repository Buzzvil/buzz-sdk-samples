#import "CarouselViewController.h"

@import BuzzAdBenefit;
@import Toast;

#import "AppConstant.h"
#import "CarouselCell.h"
#import "FeedPromotionCell.h"
#import "CarouselFeedEntryView.h"

static NSString * const kNavigationItemTitle = @"Carousel";

// MARK: 네이티브 2.0 캐러셀 구현 - UICollectionView 구현하기
@interface CarouselViewController () <UICollectionViewDelegate, UICollectionViewDataSource>

@property (nonatomic, assign, readonly) NSInteger adRequestCount;
@property (nonatomic, assign, readwrite) NSInteger loadedAdCount;
@property (nonatomic, assign, readonly) NSInteger feedPromotionSlideCount;
@property (nonatomic, strong, readonly) UICollectionView *carouselCollectionView;
// MARK: 네이티브 2.0 캐러셀 구현 - UIPageControl 구현하기
@property (nonatomic, strong, readonly) UIPageControl *pageControl;
// MARK: 네이티브 2.0 캐러셀 구현 - 피드 엔트리 포인트
@property (nonatomic, strong, readonly) CarouselFeedEntryView *feedEntryView;
@property (nonatomic, strong, readonly) BZVNativeAd2Pool *pool;

@end

@implementation CarouselViewController

// MARK: 네이티브 2.0 캐러셀 구현 - 무한 루프 구현하기
- (NSInteger)infiniteItemCount {
  return _loadedAdCount * 1000;
}

// MARK: 네이티브 2.0 캐러셀 구현 - 앞뒤 광고 아이템을 부분적으로 노출하기
- (NSInteger)spacing {
  // MARK: 네이티브 2.0 캐러셀 구현 - 광고 아이템 사이의 여백 조절하기
  return 8;
}

- (void)viewDidLoad {
  [super viewDidLoad];
  
  _adRequestCount = 5;
  _feedPromotionSlideCount = 1;
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
  // MARK: 네이티브 2.0 캐러셀 구현 - 앞뒤 광고 아이템을 부분적으로 노출하기
//  flowLayout.minimumLineSpacing = [self spacing];
  flowLayout.minimumInteritemSpacing = 0;
  
  _carouselCollectionView = [[UICollectionView alloc] initWithFrame:CGRectZero collectionViewLayout:flowLayout];
  [_carouselCollectionView registerClass:[CarouselCell class] forCellWithReuseIdentifier:@"CarouselCell"];
  [_carouselCollectionView registerClass:[FeedPromotionCell class] forCellWithReuseIdentifier:@"FeedPromotionCell"];
  _carouselCollectionView.delegate = self;
  _carouselCollectionView.dataSource = self;
  _carouselCollectionView.showsHorizontalScrollIndicator = NO;
  _carouselCollectionView.pagingEnabled = YES;
  // MARK: 네이티브 2.0 캐러셀 구현 - 앞뒤 광고 아이템을 부분적으로 노출하기
//  _carouselCollectionView.pagingEnabled = NO;
//  _carouselCollectionView.decelerationRate = UIScrollViewDecelerationRateFast;
//  _carouselCollectionView.contentInset = UIEdgeInsetsMake(0, 2 * [self spacing], 0, 2 * [self spacing]);
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
      // MARK: 네이티브 2.0 캐러셀 구현 - 무한 루프 구현하기
//      [strongSelf moveCarouselToMiddle];
      // 실제로 할당받은 광고의 개수(adCount)에 피드 진입 슬라이드 개수(feedPromotionSlideCount)를 더해서 cell 개수(loadedAdCount)를 설정합니다.
      strongSelf.loadedAdCount = adCount + strongSelf.feedPromotionSlideCount;
      [strongSelf.carouselCollectionView reloadData];
    }
  } errorHandler:^(NSError * _Nonnull error) {
    __strong typeof(self) strongSelf = weakSelf;
    if (strongSelf) {
      [strongSelf.view.window makeToast:[NSString stringWithFormat:@"Error: %@", error.localizedDescription]];
      strongSelf.carouselCollectionView.hidden = YES;
      strongSelf.feedEntryView.hidden = YES;
      // 광고 할당이 실패했을 때, 피드 진입 슬라이드 1개가 보이도록 구성합니다.
      strongSelf.loadedAdCount = strongSelf.feedPromotionSlideCount;
      [strongSelf.carouselCollectionView reloadData];
    }
  }];
}

- (void)moveCarouselToMiddle {
  NSInteger middleIndex = ((self.loadedAdCount * 1000) / 2) % _loadedAdCount;
  NSIndexPath *indexPath = [NSIndexPath indexPathForItem:middleIndex inSection:0];
  [self.carouselCollectionView scrollToItemAtIndexPath:indexPath atScrollPosition:UICollectionViewScrollPositionCenteredVertically | UICollectionViewScrollPositionCenteredHorizontally animated:NO];
  _pageControl.currentPage = indexPath.item;
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

// MARK: 네이티브 2.0 캐러셀 구현 - 앞뒤 광고 아이템을 부분적으로 노출하기
//
// TODO: Remove `(void)scrollViewDidScroll:(UIScrollView *)scrollView`
//
//- (void)scrollViewWillEndDragging:(UIScrollView *)scrollView withVelocity:(CGPoint)velocity targetContentOffset:(inout CGPoint *)targetContentOffset {
//  CGFloat cellWidth = scrollView.frame.size.width - (4 * [self spacing]);
//  CGFloat cellWidthWithSpace = cellWidth + [self spacing];
//  CGFloat estimatedIndex = scrollView.contentOffset.x / cellWidthWithSpace;
//  NSInteger index;
//
//  if (velocity.x > 0) {
//    index = (NSInteger)ceil(estimatedIndex);
//  } else if (velocity.x < 0) {
//    index = (NSInteger)floor(estimatedIndex);
//  } else {
//    index = (NSInteger)round(estimatedIndex);
//  }
//  _pageControl.currentPage = index;
//
//  targetContentOffset->x = index * cellWidthWithSpace - (2 * [self spacing]);
//  targetContentOffset->y = 0;
//}

#pragma mark - UICollectionViewDataSource

- (NSInteger)collectionView:(UICollectionView *)collectionView numberOfItemsInSection:(NSInteger)section {
  return _loadedAdCount;
  
  // MARK: 네이티브 2.0 캐러셀 구현 - 무한 루프 구현하기
//  return [self infiniteItemCount];
}

- (UICollectionViewCell *)collectionView:(UICollectionView *)collectionView cellForItemAtIndexPath:(NSIndexPath *)indexPath {
  if ((indexPath.item % _loadedAdCount) == _loadedAdCount - 1) {
    // last index인 경우 FeedPromotionCell을 반환합니다.
    FeedPromotionCell *cell = [collectionView dequeueReusableCellWithReuseIdentifier:@"FeedPromotionCell" forIndexPath:indexPath];
    [cell bind];
    return cell;
  } else {
    // last index가 아닌 경우 CarouselCell을 반환합니다.
    CarouselCell *cell = [collectionView dequeueReusableCellWithReuseIdentifier:@"CarouselCell" forIndexPath:indexPath];
    [cell setPool:_pool forAdKey:indexPath.item];
    [cell bind];
    return cell;
  }
  
  // MARK: 네이티브 2.0 캐러셀 구현 - 로딩 화면 구현하기
  //  [cell setupLoading];
  
  // MARK: 네이티브 2.0 캐러셀 구현 - 광고 이벤트 리스너 등록하기
  //  [cell setupEventListeners];
  
  //  [cell setPool:_pool forAdKey:indexPath.item];
  // MARK: 네이티브 2.0 캐러셀 구현 - 무한 루프 구현하기
  //  [cell setPool:_pool forIndex:indexPath.item % self.loadedAdCount];
}

#pragma mark - UICollectionViewDelegateFlowLayout

- (CGSize)collectionView:(UICollectionView *)collectionView layout:(UICollectionViewLayout *)collectionViewLayout sizeForItemAtIndexPath:(NSIndexPath *)indexPath {
  return CGSizeMake(collectionView.frame.size.width, collectionView.frame.size.height);
}

// MARK: 네이티브 2.0 캐러셀 구현 - 앞뒤 광고 아이템을 부분적으로 노출하기
//- (CGSize)collectionView:(UICollectionView *)collectionView layout:(UICollectionViewLayout *)collectionViewLayout sizeForItemAtIndexPath:(NSIndexPath *)indexPath {
//  return CGSizeMake(collectionView.frame.size.width - (4 * [self spacing]), collectionView.frame.size.height);
//}

@end
