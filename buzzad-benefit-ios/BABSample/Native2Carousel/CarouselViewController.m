#import "CarouselViewController.h"

@import BuzzAdBenefit;
@import Toast;

#import "AppConstant.h"
//#import "CarouselCell.h"

static NSString * const kNavigationItemTitle = @"Carousel";

@interface CarouselViewController () <UICollectionViewDelegate, UICollectionViewDataSource>

@property (nonatomic, assign, readonly) NSInteger adRequestCount;
@property (nonatomic, assign, readwrite) NSInteger loadedAdCount;
@property (nonatomic, strong, readonly) UICollectionView *carouselCollectionView;
@property (nonatomic, strong, readonly) UIPageControl *pageControl;
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
  
  _carouselCollectionView = [[UICollectionView alloc] initWithFrame:CGRectZero];
  //[_carouselCollectionView registerClass:[CarouselCell class] forCellWithReuseIdentifier:@"CarouselCell"];
  _carouselCollectionView.delegate = self;
  _carouselCollectionView.dataSource = self;
  _carouselCollectionView.showsHorizontalScrollIndicator = NO;
  _carouselCollectionView.pagingEnabled = YES;
  [self.view addSubview:_carouselCollectionView];
  
  _pageControl = [[UIPageControl alloc] initWithFrame:CGRectZero];
  _pageControl.currentPage = 0;
  _pageControl.userInteractionEnabled = NO;
  [self.view addSubview:self.pageControl];
}

- (void)setupLayout {
  _carouselCollectionView.translatesAutoresizingMaskIntoConstraints = NO;
  [NSLayoutConstraint activateConstraints:@[
    [_carouselCollectionView.topAnchor constraintEqualToAnchor:self.view.safeAreaLayoutGuide.topAnchor constant:16],
    [_carouselCollectionView.leadingAnchor constraintEqualToAnchor:self.view.safeAreaLayoutGuide.leadingAnchor],
    [_carouselCollectionView.trailingAnchor constraintEqualToAnchor:self.view.safeAreaLayoutGuide.trailingAnchor],
    [_carouselCollectionView.heightAnchor constraintEqualToAnchor:self.carouselCollectionView.widthAnchor multiplier:0.65],
  ]];
  
  _pageControl.translatesAutoresizingMaskIntoConstraints = NO;
  [NSLayoutConstraint activateConstraints:@[
    [_pageControl.topAnchor constraintEqualToAnchor:_carouselCollectionView.bottomAnchor constant:16],
    [_pageControl.centerXAnchor constraintEqualToAnchor:_carouselCollectionView.centerXAnchor],
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
  //CarouselCell *cell = [collectionView dequeueReusableCellWithReuseIdentifier:@"CarouselCell" forIndexPath:indexPath];
  return [[UICollectionViewCell alloc] initWithFrame:CGRectZero];
}

#pragma mark - UICollectionViewDelegateFlowLayout

- (CGSize)collectionView:(UICollectionView *)collectionView layout:(UICollectionViewLayout *)collectionViewLayout sizeForItemAtIndexPath:(NSIndexPath *)indexPath {
  return CGSizeMake(collectionView.frame.size.width, collectionView.frame.size.height);
}

@end
