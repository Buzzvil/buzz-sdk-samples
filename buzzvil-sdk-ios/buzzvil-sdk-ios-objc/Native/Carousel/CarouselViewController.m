@import BuzzAdBenefitSDK;
#import "CarouselViewController.h"
#import "CarouselCell.h"
#import "FeedPromotionCell.h"
#import "CarouselFeedEntryView.h"

static NSString * const kUnitId = @"YOUR_UNIT_ID";

@interface CarouselViewController () <UICollectionViewDelegate, UICollectionViewDataSource>

// 최대 10개의 광고를 요청할 수 있습니다.
@property (nonatomic, assign, readonly) NSInteger adRequestCount;

// 실제 할당받은 광고의 개수입니다.
// 이 값은 collectionView datasource의 collectionView(_:numberOfItemsInSection:) 함수의 return 값으로 사용합니다.
@property (nonatomic, assign, readwrite) NSInteger loadedAdCount;

@property (nonatomic, assign, readonly) NSInteger feedPromotionSlideCount;

// 광고 중복 할당을 막기 위해 하나의 캐러셀에 하나의 NativeAd2Pool 인스턴스를 생성하여 사용합니다.
@property (nonatomic, strong, readonly) BZVNativeAd2Pool *pool;

// 광고를 표시할 Carousel CollectionView
@property (nonatomic, strong, readonly) UICollectionView *carouselCollectionView;

@property (nonatomic, strong, readonly) UIPageControl *pageControl;

@property (nonatomic, strong, readonly) CarouselFeedEntryView *feedEntryView;

@end

@implementation CarouselViewController

- (NSInteger)infiniteItemCount {
  return _loadedAdCount * 1000;
}

- (void)viewDidLoad {
  [super viewDidLoad];
  
  _adRequestCount = 5;
  _feedPromotionSlideCount = 1;
  _loadedAdCount = 0;
  _pool = [[BZVNativeAd2Pool alloc] initWithUnitId:kUnitId];
  
  [self setupCarousel];
  [self setupView];
  [self setupLayout];
}

- (void)setupCarousel {
  __weak typeof(self) weakSelf = self;
  // BZVNativeAd2Pool에 광고 할당을 요청합니다.
  [self.pool loadAdsWithCount:self.adRequestCount completionHandler:^(NSInteger adCount) {
    __strong typeof(self) strongSelf = weakSelf;
    if (strongSelf) {
      // 실제로 할당받은 광고의 개수(adCount)에 베네핏허브 진입 슬라이드 개수(feedPromotionSlideCount)를 더해서 cell 개수(loadedAdCount)를 설정합니다.
      strongSelf.loadedAdCount = adCount + strongSelf.feedPromotionSlideCount;
      
      // 광고 할당이 완료되면 carouselCollectionView를 갱신합니다.
      [strongSelf.carouselCollectionView reloadData];
      
      strongSelf.pageControl.numberOfPages = adCount;
      
      // 무한루프 구현 시
      // 총 cell 개수를 pageControl 개수로 설정합니다.
      // strongSelf.pageControl.numberOfPages = [strongSelf infiniteItemCount];
      // [strongSelf moveCarouselToMiddle];
    }
  } errorHandler:^(NSError * _Nonnull error) {
    // 광고 할당 실패 시 발생하는 NSError 오류 코드에 대한 자세한 내용은 오류 코드가 나타납니다 토픽을 참고하세요.
  }];
}

- (void)setupView {
  UICollectionViewFlowLayout *flowLayout = [[UICollectionViewFlowLayout alloc] init];
  flowLayout.scrollDirection = UICollectionViewScrollDirectionHorizontal;
  flowLayout.minimumLineSpacing = 0;
  flowLayout.minimumInteritemSpacing = 0;
  
  // 앞뒤 광고 아이템을 부분적으로 노출하기
  // flowLayout의 minimumLineSpacing을 spacing 값으로 설정합니다.
//  flowLayout.minimumLineSpacing = [self spacing];
  
  // flowLayout을 carouselCollectionView에 설정하세요.
  _carouselCollectionView = [[UICollectionView alloc] initWithFrame:CGRectZero collectionViewLayout:flowLayout];
  [_carouselCollectionView registerClass:[CarouselCell class] forCellWithReuseIdentifier:@"CarouselCell"];
  _carouselCollectionView.delegate = self;
  _carouselCollectionView.dataSource = self;
  _carouselCollectionView.showsHorizontalScrollIndicator = NO;
  _carouselCollectionView.pagingEnabled = YES;
  
  // isPagingEnabled를 false로 설정합니다.
   _carouselCollectionView.pagingEnabled = NO;
   // 아래 속성들을 설정합니다.
   _carouselCollectionView.decelerationRate = UIScrollViewDecelerationRateFast;
   _carouselCollectionView.contentInset = UIEdgeInsetsMake(0, 2 * [self spacing], 0, 2 * [self spacing]);
  
  [self.view addSubview:_carouselCollectionView];
  
  _pageControl = [[UIPageControl alloc] initWithFrame:CGRectZero];
  _pageControl.currentPage = 0;
  _pageControl.userInteractionEnabled = NO;
  [self.view addSubview:self.pageControl];
  
  _feedEntryView = [[CarouselFeedEntryView alloc] initWithFrame:CGRectZero];
  [self.view addSubview:self.feedEntryView];
}

- (void)setupLayout {
  // eg. auto layout constraints for carouselCollectionView
  self.carouselCollectionView.translatesAutoresizingMaskIntoConstraints = NO;
  [NSLayoutConstraint activateConstraints:@[
    [self.carouselCollectionView.topAnchor constraintEqualToAnchor:self.view.safeAreaLayoutGuide.topAnchor constant:16],
    [self.carouselCollectionView.leadingAnchor constraintEqualToAnchor:self.view.safeAreaLayoutGuide.leadingAnchor],
    [self.carouselCollectionView.trailingAnchor constraintEqualToAnchor:self.view.safeAreaLayoutGuide.trailingAnchor],
    [self.carouselCollectionView.heightAnchor constraintEqualToAnchor:self.carouselCollectionView.widthAnchor multiplier:0.65]
  ]];
  
  // eg. auto layout constraints for pageControl
  _pageControl.translatesAutoresizingMaskIntoConstraints = NO;
  [NSLayoutConstraint activateConstraints:@[
    [_pageControl.topAnchor constraintEqualToAnchor:_carouselCollectionView.bottomAnchor constant:16],
    [_pageControl.centerXAnchor constraintEqualToAnchor:_carouselCollectionView.centerXAnchor],
  ]];
}

// 무한 루프 구현 시
//- (void)moveCarouselToMiddle {
//  NSInteger middleIndex = ((self.loadedAdCount * 1000) / 2) % _loadedAdCount;
//  NSIndexPath *indexPath = [NSIndexPath indexPathForItem:middleIndex inSection:0];
//  [self.carouselCollectionView scrollToItemAtIndexPath:indexPath atScrollPosition:UICollectionViewScrollPositionCenteredVertically | UICollectionViewScrollPositionCenteredHorizontally animated:NO];
//  _pageControl.currentPage = indexPath.item;
//}

// 앞뒤 광고 아이템을 부분적으로 노출하기
- (NSInteger)spacing {
  return 8;
}


#pragma mark - UICollectionViewDataSource

- (NSInteger)collectionView:(UICollectionView *)collectionView numberOfItemsInSection:(NSInteger)section {
  return _loadedAdCount;
  // 무한루프 구현 시
  // return [self infiniteItemCount];
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
    
    // 로딩 화면 구현하기
    [cell setupLoading];
    
    // 광고 이벤트 리스너ㅓ 등록하기
    [cell setupEventListeners];
    
    [cell setPool:_pool forAdKey:indexPath.item];
    [cell bind];

    return cell;
  }
}

// 무한루프 구현 시
//- (UICollectionViewCell *)collectionView:(UICollectionView *)collectionView cellForItemAtIndexPath:(NSIndexPath *)indexPath {
//  CarouselCell *cell = [collectionView dequeueReusableCellWithReuseIdentifier:@"CarouselCell" forIndexPath:indexPath];
//  
//  // 할당된 고유 광고 개수로 모듈러 연산하여 index를 적용합니다.
//  [cell setPool:_pool forAdKey:indexPath.item % self.loadedAdCount];
//  [cell bind];
//  return cell;
//}

//
- (UICollectionViewCell *)collectionView:(UICollectionView *)collectionView c12ellForItemAtIndexPath:(NSIndexPath *)indexPath {
  CarouselCell *cell = [collectionView dequeueReusableCellWithReuseIdentifier:@"CarouselCell" forIndexPath:indexPath];
  [cell setPool:_pool forAdKey:indexPath.item];
  [cell bind];
  return cell;
}

#pragma mark - UICollectionViewDelegateFlowLayout

- (CGSize)collectionView:(UICollectionView *)collectionView layout:(UICollectionViewLayout *)collectionViewLayout sizeForItemAtIndexPath:(NSIndexPath *)indexPath {
  return CGSizeMake(collectionView.frame.size.width, collectionView.frame.size.height);
}

// 앞뒤 광고 아이템을 부분적으로 노출하기
//- (CGSize)collectionView:(UICollectionView *)collectionView layout:(UICollectionViewLayout *)collectionViewLayout sizeForItemAtIndexPath:(NSIndexPath *)indexPath {
//  return CGSizeMake(collectionView.frame.size.width - (4 * [self spacing]), collectionView.frame.size.height);
//}

#pragma mark - UICollectionViewDelegate

- (void)scrollViewDidScroll:(UIScrollView *)scrollView {
  CGFloat centerOffsetX = scrollView.contentOffset.x + (scrollView.frame.size.width / 2);
  CGFloat pageWidth = scrollView.frame.size.width;
  
  // pageWidth가 0이 되는 경우 division by zero를 방지합니다.
  if (pageWidth == 0) {
    return;
  }
  // 현재 캐러셀 아이템의 인덱스를 pageControl의 currentPage에 지정합니다.
  _pageControl.currentPage = (NSInteger)centerOffsetX / (NSInteger)pageWidth;
}

// 앞뒤 광고 아이템을 부분적으로 노출하기
// 캐러셀 아이템이 화면에서 일정 부분 넘어가면 다음 아이템으로 이동하도록 구현합니다.
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

@end
