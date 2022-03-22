
# BuzzAdBenefit SDK for iOS

* [개발 가이드](https://buzzvil.atlassian.net/wiki/spaces/BDG/pages/2686124049/BuzzAd+iOS+SDK+3.0.x)

### 오픈 소스 라이센스 고지
- 이 소프트웨어가 사용하는 오픈 소스 소프트웨어의 라이센스는 "오픈 소스 라이센스 고지 페이지 ([원본 파일](docs/3rd_party_licenses.html)|[렌더링 버전](https://htmlpreview.github.io/?https://github.com/Buzzvil/buzzscreen-sdk-publisher/blob/master/docs/3rd_party_licenses.html))"에서 확인할 수 있다.

## [3.0.5] - 2022-02-21
* [CHANGE] 더 쉽고 빠른 연동을 위해 SDK interface를 더 사용하기 쉬운 형태로 개편
* [NEW] 기존에 SDK로 제어하던 기능을 포함한 몇가지 피드 설정을 서버에서 동적으로 설정 (탭의 개수, 탭의 이름, 무한 스크롤 기능, 탭에서 노출할 광고 종류)
> ### [3.0.6] - 2022-03-21
> * [CHANGE] 프리로드 시에 모든 탭에 대한 광고를 로드하게 끔 수정
> * [FIX] 광고 제목이 길 때 CTA 버튼의 너비가 줄어드는 현상 수정

## [2.4.1] - 2021-10-12
* [NEW] Feed 엔트리뷰 기능 추가
* [MINOR] Feed 진입 시 로딩 중 상태 표시
* [FIX] VAST 동영상 광고를 재생할 때 간헐적으로 발생하는 크래시에 대한 방어로직 추가
* [FIX] VAST 동영상 광고의 Click Tracking Urls 호출 시점 수정
> ### [2.4.2] - 2021-10-27
> * [FIX] 프리로드 시에 캐싱된 광고를 리셋하고 새로운 아이템을 로드하게 끔 수정
> ### [2.4.3] - 2021-11-12
> * [FIX] Feed 엔트리뷰와 프리로드 기능을 같이 사용할 수 없는 현상 수정
> ### [2.4.4] - 2022-01-10
> * [FIX] Xcode 13에서 Manage Version and Build Number 옵션으로 빌드 했을 때 참여형 광고가 할당되지 않는 이슈 수정
> ### [2.4.5] - 2022-03-22
> * [FIX] Feed의 필터 UI가 광고 UI를 덮는 버그 수정
