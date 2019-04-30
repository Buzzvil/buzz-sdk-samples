//
//  BABArticleViewHolder.h
//  BABNative
//
//  Created by Jaehee Ko on 25/03/2019.
//  Copyright © 2019 Buzzvil. All rights reserved.
//

#import <UIKit/UIKit.h>

@class BABArticle;

NS_ASSUME_NONNULL_BEGIN

@interface BABArticleViewHolder : UIView

@property (nonatomic, readonly) BABArticle *article;

- (void)renderArticle:(BABArticle *)article;

@end

NS_ASSUME_NONNULL_END
