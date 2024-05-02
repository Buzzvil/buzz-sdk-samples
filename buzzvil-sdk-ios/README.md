
# BuzzvilSDK for iOS

* [개발 가이드](https://docs.buzzvil.com/)

### 오픈 소스 라이센스 고지
- 이 소프트웨어가 사용하는 오픈 소스 소프트웨어의 라이센스는 "오픈 소스 라이센스 고지 페이지 ([원본 파일](/3rd_party_licenses.html)|[렌더링 버전](https://htmlpreview.github.io/?https://github.com/Buzzvil/buzz-sdk-samples/blob/master/3rd_party_licenses.html))"에서 확인할 수 있다.

## [5.11.0] - 2024-05-02
* [NEW] Swift Package Manager 지원
* [UPDATE] Interstitial 광고에 Feed 진입점 추가
* 자세한 사항은 [링크](https://docs.buzzvil.com/docs/release-news/ios/buzzvil5.11) 참조

## [5.9.0] - 2024-04-04
* [NEW] 네이티브지면에 베네핏허브로의 진입을 유도하는 네이티브 오버레이 추가
* [UPDATE] iOS deployment target 12.0으로 변경
* [UPDATE] AFNetworking, ReactiveObjc 라이브러리 제거
* [UPDATE] GoogleAds-IMA-iOS-SDK 3.18.5으로 업데이트
* [UPDATE] SDWebImage 5.19.0으로 업데이트
* 자세한 사항은 [링크](https://docs.buzzvil.com/docs/release-news/ios/buzzvil5.9) 참조
> ### [5.9.1] - 2024-04-08
> * [UPDATE] SDWebImage, SDWebImageWebPCoder 라이브러리 제거
> ### [5.9.2] - 2024-04-09
> * [FIX] BuzzBoosterSDK 유저 행동 분석 이벤트가 서버로 정상적으로 전달되지 않는 문제 수정
> ### [5.9.3] - 2024-04-11
> * [FIX] BuzzBoosterSDK CampaignViewController에서 발생하는 메모리 릭 수정
> ### [5.9.4] - 2024-04-19
> * [FIX] Feed collectionView에서 간헐적으로 발생하는 index out of bounds 문제 수정
> * [FIX] BuzzBoosterSDK iOS 14 기기에서 Attendance 캠페인 진입 시 App freesing 문제 수정
> ### [5.9.5] - 2024-04-24
> * [UPDATE] GoogleAds-IMA-iOS-SDK iOS 17 대응된 3.22.1으로 업데이트
> ### [5.9.6] - 2024-04-24
> * [UPDATE] GoogleAds-IMA-iOS-SDK 라이브러리 제거
> ### [5.9.7] - 2024-05-02
> * [FIX] Feed CTAButton reward icon empty UIImage를 넣을 경우 좌우 여백이 동일하도록 수정

## [5.7.0] - 2024-03-07
* [FIX] 다른 화면으로 이동 후 베네핏허브로 다시 돌아올 때, 버즈부스터 활성화 시 나타나는 스크롤 유도 버튼의 애니메이션이 멈추는 문제 해결
* [FIX] 네트워트 에러 발생한 상태에서 새로고침 버튼을 터치하면 광고 필터가 노출되는 문제 해결
* 자세한 사항은 [링크](https://docs.buzzvil.com/docs/release-news/ios/buzzvil5.7) 참조
> ### [5.7.1] - 2024-03-14
> * [FIX] BuzzBoosterSDK userID 길이 제한 65자로 확장

## [5.5.0] - 2024-02-15
* [NEW] iOS 17 정책 대응을 위해 Buzzvil SDK 내에서 수집하는 개인정보들과 유저 추적 등의 내용들을 SDK 내의 정해진 파일에 공시 
* [UPDATE] Lottie 내재화
* [UPDATE] 베네핏허브의 스크롤 유도 버튼에 애니메이션 효과를 추가
* [UPDATE] ATT 유도가이드 화면을 통해 iPhone 시스템 설정의 앱 영역으로 이동할 수 있도록 개선
* [FIX] 네이티브 캐러셀에서 비디오 광고가 자동 재생 하지 않는 문제 해결
* 자세한 사항은 [링크](https://docs.buzzvil.com/docs/release-news/ios/buzzvil5.5) 참경
> ### [5.5.3] - 2024-03-06
> * [UPDATE] 문의하기 버튼 위치 베네핏 허브 하단에서 헤더로 변경

## [5.3.0] - 2024-01-11
* [FIX] 인터스티셜 지면에서 global theme이 적용되지 않는 문제 해결 
* 자세한 사항은 [링크](https://docs.buzzvil.com/docs/release-news/ios/buzzvil5.3-buzzad3.45) 참조
> ### [5.3.1] - 2024-01-11
> * [FIX] BuzzBenefitMarketingStatus Objective-C 호환성 문제 해결

## [5.2.0] - 2023-12-28
* [NEW] 자체 포인트 시스템이 없는 고객을 위한 리워드 시스템 출시
* [FIX] 버그 수정
* 자세한 사항은 [링크](https://docs.buzzvil.com/docs/release-news/ios/buzzvil5.2-buzzad3.44) 참조

## [5.1.0] - 2023-11-30
* [UPDATE] 인터스티셜 디자인 업데이트
* [UPDATE] UNKNOWN 오류 코드 대응 고도화를 위한 개선
* 자세한 사항은 [링크](https://docs.buzzvil.com/docs/release-news/ios/buzzvil5.1-buzzad3.43) 참조
> ### [5.1.1] - 2023-12-01
> * [FIX] 프리로드 과정에서 발생하는 개인정보 수집동의 관련 버그 수정

## [5.0.0] - 2023-11-10
* [NEW] 수익화와 인게이지먼트를 동시에, 버즈베네핏(Buzzvil SDK v5) 출시
* 자세한 사항은 [링크](https://docs.buzzvil.com/docs/release-news/ios/buzzvil5.0-buzzad3.41) 참조
> ### [5.0.1] - 2023-11-15
> * [FIX] 앱 아카이브 과정에서 발생하는 nested framework 문제 해결
