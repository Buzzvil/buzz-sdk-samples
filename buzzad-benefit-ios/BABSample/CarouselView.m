//
//  CarouselView.m
//  BABSample
//
//  Created by Jaehee Ko on 20/04/2020.
//  Copyright © 2020 Buzzvil. All rights reserved.
//

#import "CarouselView.h"
#import <BuzzAdBenefit/BuzzAdBenefit.h>
#import <BuzzAdBenefitNative/BuzzAdBenefitNative.h>

NSString *CarouselItemReuseIdentifier = @"CarouselItem";
const CGFloat CarouselItemSpacing = 12;
const int CarouselItemAdViewTag = 2222;

@interface CarouselView () <UICollectionViewDataSource, UICollectionViewDelegate, UICollectionViewDelegateFlowLayout> {
  UIScrollView *_pagingScrollView;
  NSArray <BABAd *> *_ads;
}

@property (nonatomic, strong) IBOutlet UICollectionView *collectionView;
@property (nonatomic, strong) IBOutlet UIPageControl *pageControl;

@end

@implementation CarouselView

- (instancetype)initWithFrame:(CGRect)frame {
  if (self = [super initWithFrame:frame]) {
    [self initialize];
  }
  return self;
}

- (instancetype)initWithCoder:(NSCoder *)coder {
  if (self = [super initWithCoder:coder]) {
    [self initialize];
  }
  return self;
}

- (void)initialize {
  UIView *container = [[NSBundle mainBundle] loadNibNamed:@"CarouselView" owner:self options:nil][0];
  [self addSubview:container];
  [container setTranslatesAutoresizingMaskIntoConstraints:NO];
  [NSLayoutConstraint activateConstraints:@[
    [container.widthAnchor constraintEqualToAnchor:self.widthAnchor],
    [container.heightAnchor constraintEqualToAnchor:self.heightAnchor],
    [container.topAnchor constraintEqualToAnchor:self.topAnchor],
    [container.leadingAnchor constraintEqualToAnchor:self.leadingAnchor],
  ]];

  [_collectionView registerClass:[UICollectionViewCell class] forCellWithReuseIdentifier:CarouselItemReuseIdentifier];

  _pagingScrollView = [[UIScrollView alloc] initWithFrame:_collectionView.frame];
   _pagingScrollView.hidden = YES;
   _pagingScrollView.pagingEnabled = YES;
   _pagingScrollView.delegate = self;

   [_collectionView addSubview:_pagingScrollView];

   [_collectionView addGestureRecognizer:_pagingScrollView.panGestureRecognizer];
   _collectionView.panGestureRecognizer.enabled = NO;
   _collectionView.scrollEnabled = NO;
}

- (void)renderAds:(NSArray<BABAd *> *)ads {
  _ads = ads;

  [_pageControl setNumberOfPages:_ads.count];

  _pagingScrollView.bounds = CGRectMake(CarouselItemSpacing * 2, 0, [self cellWidth] + CarouselItemSpacing, _collectionView.frame.size.height);
  _pagingScrollView.contentSize = CGSizeMake([self collectionView:_collectionView numberOfItemsInSection:0] * ([self cellWidth] + CarouselItemSpacing), _pagingScrollView.frame.size.height);

  [_collectionView reloadData];
}

- (CGFloat)cellWidth {
  return _collectionView.frame.size.width - 4 * CarouselItemSpacing;
}

- (NSInteger)collectionView:(UICollectionView *)collectionView numberOfItemsInSection:(NSInteger)section {
  return _ads.count;
}

- (CGFloat)collectionView:(UICollectionView *)collectionView
                   layout:(UICollectionViewLayout *)collectionViewLayout
minimumLineSpacingForSectionAtIndex:(NSInteger)section {
  return CarouselItemSpacing;
}

- (CGSize)collectionView:(UICollectionView *)collectionView
                  layout:(UICollectionViewLayout *)collectionViewLayout
  sizeForItemAtIndexPath:(NSIndexPath *)indexPath {
  return CGSizeMake([self cellWidth], collectionView.frame.size.height);
}

- (UIEdgeInsets)collectionView:(UICollectionView *)collectionView
                        layout:(UICollectionViewLayout *)collectionViewLayout
        insetForSectionAtIndex:(NSInteger)section {
  return UIEdgeInsetsMake(0, CarouselItemSpacing * 2, 0, CarouselItemSpacing * 2);
}

- (UICollectionViewCell *)collectionView:(UICollectionView *)collectionView cellForItemAtIndexPath:(NSIndexPath *)indexPath {
  UICollectionViewCell *cell = [collectionView dequeueReusableCellWithReuseIdentifier:CarouselItemReuseIdentifier forIndexPath:indexPath];
  BABDefaultAdViewHolder *adView = [cell viewWithTag:CarouselItemAdViewTag];
  if (!adView) {
    adView = [[BABDefaultAdViewHolder alloc] initWithFrame:cell.bounds];
    adView.tag = CarouselItemAdViewTag;
    adView.mediaViewCornerRadius = 8;
    adView.detailViewPadding = 4;

    [cell addSubview:adView];
  }

  [adView renderAd:[_ads objectAtIndex:indexPath.row]];

  return cell;
}

- (void)scrollViewDidScroll:(UIScrollView *)scrollView {
  if (_pagingScrollView) {
    _collectionView.contentOffset = _pagingScrollView.contentOffset;
    _pageControl.currentPage = (int)(_pagingScrollView.contentOffset.x + (_pagingScrollView.bounds.size.width * 0.5f)) / (int)_pagingScrollView.bounds.size.width;
  }
}
@end
