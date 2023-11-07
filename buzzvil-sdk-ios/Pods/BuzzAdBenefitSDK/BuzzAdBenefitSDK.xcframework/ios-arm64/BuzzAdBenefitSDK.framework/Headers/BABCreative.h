#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

typedef enum {
  BABCreativeLandingDefaultBrowser = 1,
  BABCreativeLandingOverlay,
  BABCreativeLandingInApp,
  BABCreativeLandingYoutubePlayer = 10
} BABCreativeLandingType;

typedef enum {
  BABCreativeUnsupported = 0,
  BABCreativeNative,
  BABCreativeVideo,
  BABCreativeVast,
} BABCreativeType;

typedef enum {
  BABCreativeTemplateVertical = 0,
  BABCreativeTemplateLandscape
} BABCreativeTemplate;

typedef enum {
  BABCreativeVideoAutoplayDisabled = 1,
  BABCreativeVideoAutoplayEnabled,
  BABCreativeVideoAutoplayOnWifi
} BABCreativeVideoAutoplayMode;

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
@property (nonatomic, copy, readonly, nullable) NSString *vastTag;
@property (nonatomic, copy, readonly, nullable) NSString *vastClickUrl;
@property (nonatomic, copy, readonly, nullable) NSArray<NSString *> *vastClickTrackings;
@property (nonatomic, copy, readonly, nullable) NSString *adNetworkCampaignId;
@property (nonatomic, assign, readonly) BOOL isDeeplink;
@property (nonatomic, assign, readonly) BABCreativeVideoAutoplayMode autoplay;
@property (nonatomic, assign, readonly) BABCreativeTemplate templateType;
@property (nonatomic, assign, readonly) BABCreativeType type;
@property (nonatomic, assign, readonly) BABCreativeLandingType landingType;

@property (nonatomic, strong, nullable) BABVideoAdMetadata *videoAdMetadata;

- (instancetype)initWithDictionary:(NSDictionary *)dic;

@end

NS_ASSUME_NONNULL_END
