//
//  BABCreative.h
//  BAB
//
//  Created by Jaehee Ko on 20/12/2018.
//  Copyright © 2018 Buzzvil. All rights reserved.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

typedef enum {
  BABCreativeUnsupported = 0,
  BABCreativeNative,
  BABCreativeVideo
} BABCreativeType;

typedef enum {
  BABCreativeTemplateVertical = 0,
  BABCreativeTemplateLandscape
} BABCreativeTemplate;

@class BABVideoAdMetadata;

@interface BABCreative : NSObject

@property (nonatomic, assign, readonly) double width;
@property (nonatomic, assign, readonly) double height;
@property (nonatomic, copy, readonly, nullable) NSString *clickUrl;
@property (nonatomic, copy, readonly, nullable) NSString *title;
@property (nonatomic, copy, readonly, nullable) NSString *body;
@property (nonatomic, copy, readonly, nullable) NSString *callToAction;
@property (nonatomic, copy, readonly, nullable) NSString *iconUrl;
@property (nonatomic, copy, readonly, nullable) NSString *imageUrl;
@property (nonatomic, copy, readonly, nullable) NSString *overlayImageUrl;
@property (nonatomic, copy, readonly, nullable) NSString *streamingVideoUrl;
@property (nonatomic, assign, readonly) int autoplay;
@property (nonatomic, assign, readonly) BABCreativeTemplate templateType;
@property (nonatomic, assign, readonly) BABCreativeType type;

@property (nonatomic, strong, nullable) NSString *encodedClickUrl;
@property (nonatomic, strong, nullable) BABVideoAdMetadata *videoAdMetadata;

- (instancetype)initWithDictionary:(NSDictionary *)dic;

@end

NS_ASSUME_NONNULL_END
