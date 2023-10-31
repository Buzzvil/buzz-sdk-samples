//
//  BABRewardResult.h
//  BAB
//
//  Created by Jaehee Ko on 02/05/2019.
//  Copyright © 2019 Buzzvil. All rights reserved.
//

typedef NS_ENUM(NSInteger, BABRewardResult) {
  BABRewardResultSuccess = 0,
  BABRewardResultAlreadyParticipated,
  BABRewardResultBrowserNotLaunched,
  BABRewardResultTooShortToParticipate,
  BABRewardResultNetworkError,
  BABRewardResultClientError,
  BABRewardResultServerError,
  BABRewardResultTimeout,
  BABRewardResultMissingReward,
  BABRewardResultUnknownError,
} ;

