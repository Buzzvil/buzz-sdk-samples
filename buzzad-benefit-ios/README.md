
# BuzzAdBenefit SDK for iOS

* [개발 가이드](https://docs.buzzvil.com/)

### 오픈 소스 라이센스 고지
- 이 소프트웨어가 사용하는 오픈 소스 소프트웨어의 라이센스는 "오픈 소스 라이센스 고지 페이지 ([원본 파일](/3rd_party_licenses.html)|[렌더링 버전](https://htmlpreview.github.io/?https://github.com/Buzzvil/buzz-sdk-samples/blob/master/3rd_party_licenses.html))"에서 확인할 수 있다.

## [3.45.0] - 2024-03-21
* [NEW] 피드와 브릿지 페이지에 새로운 광고 배너 버즈배너(BuzzBanner) 추가 
* 자세한 사항은 [링크](https://docs.buzzvil.com/docs/release-news/ios/buzzvil5.9) 참조
> ### [3.45.2] - 2024-04-29
> * [UPDATE] iOS deployment target 12.0으로 변경
> * [UPDATE] 임시적으로 GoogleAds-IMA-iOS-SDK 라이브러리 제거
> * [UPDATE] AvatyeAdCash 2.1.5으로 업데이트
> ### [3.45.3] - 2024-05-10
> * [UPDATE] AvatyeAdCash 2.1.7으로 업데이트
> ### [3.45.4] - 2024-05-14
> * [UPDATE] 임시적으로 AvatyeAdCash 라이브러리 제거
> ### [3.45.13] - 2024-05-14
> * [UPDATE] 임시적으로 제거되었던 AvatyeAdCash 라이브러리 추가
> * [UPDATE] AvatyeAdCash 3.0.6으로 업데이트

## [3.41.0] - 2023-11-02
* [FIX] 버그 수정
* 자세한 사항은 [링크](https://docs.buzzvil.com/docs/release-news/ios/buzzad3.41/) 참조

## [3.39.1] - 2023-10-05
* [FIX] 서드파티 라이브러리의 모듈명과 충돌하는 베이스 이미지(BABImage) 이름 변경
* 자세한 사항은 [링크](https://docs.buzzvil.com/docs/release-news/ios/buzzad3.39/) 참조

## [3.37.1] - 2023-09-07
* [NEW] 애드허브 유저 리텐션 증대를 위한 출석체크 기능 “데일리 리워드” 출시
* 자세한 사항은 [링크](https://docs.buzzvil.com/docs/release-news/ios/buzzad3.37/) 참조

## [3.35.5] - 2023-08-17
* [UPDATE] 애드허브 광고 미할당 UX & UI 개선​
* 자세한 사항은 [링크](https://docs.buzzvil.com/docs/release-news/ios/buzzad3.35/) 참조

## [3.33.1] - 2023-07-13
* [FIX] 동영상 광고 재생이 완료된 후 나타나는 “더보기“ 버튼의 URL이 호출되지 않는 문제 해결
* 자세한 사항은 [링크](https://docs.buzzvil.com/docs/release-news/ios/buzzad3.33/) 참조

## [3.31.1] - 2023-06-15
* [NEW] 네이티브 캐러셀에서 애드허브(피드)로의 진입점을 주목도 높은 카드로 제공
* [UPDATE] 애드허브 이용 비율을 대폭 끌어올릴 수 있도록 개인정보 제3자 제공 동의 UX 개선
* 자세한 사항은 [링크](https://docs.buzzvil.com/docs/release-news/ios/buzzad3.31/) 참조
> ### [3.31.2] - 2023-06-21
> * [FIX] VAST 광고 더보기 버튼 클릭 시 잘못된 페이지로 랜딩하는 버그 수정

## [3.29.1] - 2023-05-18
* [UPDATE] 문의하기 아이콘 업데이트
* [FIX] 리워드 지급형 뉴스 콘텐츠 참여 완료 후 리워드 적립 요청 시 간헐적으로 발생하던 크래시 문제 해결
* 자세한 사항은 [링크](https://docs.buzzvil.com/docs/release-news/ios/buzzad3.29/) 참조

## [3.27.4] - 2023-04-20
* [NEW] 캐러셀(Carousel) 형태로 네이티브 2.0 지면을 구현할 수 있는 가이드 제공
* [NEW] 새로운 리워드 지급형 뉴스 콘텐츠 시스템 탑재
* [FIX] 리워드 지급형 뉴스 콘텐츠의 CTA 버튼이 간헐적으로 참여완료로 변경되지 않는 문제 해결
* 자세한 사항은 [링크](https://docs.buzzvil.com/docs/release-news/ios/buzzad3.27/) 참조
> ### [3.27.5] - 2023-06-21
> * [FIX] VAST 광고 더보기 버튼 클릭 시 잘못된 페이지로 랜딩하는 버그 수정

## [3.25.0] - 2023-03-23
* [FIX] 피드의 개별 광고 사이 영역의 높이를 0으로 설정해도 여전히 표시되는 문제 해결
* [FIX] 3.17.x 이상부터 발생했던 하위 뷰 컨트롤러로 피드 연동 시 피드 최상단의 툴바가 보여지는 문제 해결
* [FIX] 피드에서 간헐적으로 발생하는 UIPageViewController 관련 충돌 문제 해결
* 자세한 사항은 [링크](https://docs.buzzvil.com/docs/release-news/ios/buzzad3.25/) 참조

## [3.23.3] - 2023-02-28
* [NEW] 피드에 캐러셀 배너 영역 추가
* [UPDATE] 활동 추적 권한 허용을 유도하는 배너 디자인 개선
* [FIX] 대시에서 피드 광고 분류 탭을 비활성화한 상태에서 광고 분류 필터만 활성화하면 발생하는 문제 해결
  * 필터가 제대로 나타나지 않아 유저가 참여하고 싶은 광고의 세부 유형을 선택할 수 없는 문제 해결
  * 광고 프리로드를 실패하는 문제 해결
* [FIX] 광고 뷰를 자체 구현한 경우 발생하는 UI 오류 해결 (예: 유저의 광고 참여 상태에 따라 CTA 뷰가 업데이트되지 않은 문제)
* [FIX] CTA 버튼 커스터마이징이 잘 동작하지 않는 버그 수정
* 자세한 사항은 [링크](https://docs.buzzvil.com/docs/release-news/ios/buzzad3.23/) 참조

## [3.21.2] - 2023-01-26
* [CHANGE] Xcode 14에서 BuzzAd iOS SDK를 사용할 때 Warning이 발생하지 않도록 Minimum deployment target을 11로 상향
* [NEW] 커스텀 인앱 브라우저 사용 시 딥링크 광고 여부를 확인하는 인터페이스 추가
* [NEW] 네이티브, 피드, 인터스티셜 다크 모드 지원
* [NEW] revenueType에 새로운 액션형 광고 유형 추가
* [NEW] 피드 배경 색상을 커스터마이징하는 인터페이스 추가
* [UPDATE] 광고 분류 탭과 필터 비활성화 기능 추가
> ### [3.21.3] - 2023-02-15
> * [FIX] 피드 탭 클릭 시 간헐적으로 발생하는 크래시 수정
> * [FIX] 피드 광고 뷰가 재사용 될 때 광고 이벤트 대리자가 해제되지 않는 버그 수정
> ### [3.21.4] - 2023-02-22
> * [FIX] 하위 뷰 컨트롤러로 피드를 연동했을 때 상단 바가 노출되는 버그 수정

## [3.19.0] - 2022-12-28
* [NEW] 피드 상단으로 바로 이동할 수 있는 맨위로가기 버튼 추가
* [UPDATE] 광고 프리로드 중복 요청에 대한 오류 코드 추가
* [UPDATE] 기본 헤더 영역을 높이가 0인 더미 뷰로 변경
* [UPDATE] 피드 광고 분류 필터를 다시 탭하면 전체 광고 필터로 이동하는 동작 수정
* [FIX] 피드를 아래로 스크롤한 후 다른 광고 분류 탭을 선택하면 스크롤 위치가 이전과 같이 유지되어 이동한 탭 목록 상단으로 이동하지 않는 문제 해결

## [3.17.8] - 2022-12-01
* [NEW] 네이티브 2.0에 광고 참여 이벤트를 수신할 수 있는 리스너 추가
* [NEW] 피드 필터 스크롤 뷰의 좌우로 움직일 때 부드럽게 동작하는 페이딩 엣지(fading edge) 적용
* [NEW] 유저가 이미 선택한 탭을 다시 선택해서 전체 탭이 선택될 때, 피드 필터 스크롤 뷰가 왼쪽으로 자동으로 스크롤되는 기능 추가
* [UPDATE] 광고를 오토 로딩하는 시점을 앞으로 당겨서 스크롤 성능 개선
* [UPDATE] 피드 상단에 보여지는 앱 추적 허용 권한 헤더 뷰를 누르면 같이 앱 외부가 아닌 인앱에서 페이지를 열도록 개선
* [UPDATE] 광고 소재 사이즈에 따라 피드의 광고별 구분선 위치가 다이나믹하게 적용되도록 개선
* [UPDATE] 광고 및 아티클 뷰 기본 디자인을 안드로이드와 일관되게 변경
* [FIX] 가로 화면에서 에러 문구 위치가 변경되는 버그 수정
* [FIX] 피드의 scrollToTop 메소드 호출 시 최상단까지 스크롤되지 않는 버그 수정

## [3.15.4] - 2022-11-03
* [NEW] 네이티브 2.0, 자동으로 다음 광고를 불러오는 진화한 네이티브 지면
* [FIX] 뷰 생명주기에 따라 사라져야 하는 인스턴스가 메모리에 잔존하여 발생하는 불필요한 메모리 누수 현상 해결

## [3.13.1] - 2022-10-06
* [NEW] iOS 16을 위한 대응
* [NEW] revenueType에 새로운 액션형 광고 유형 추가
* [NEW] 다양한 기획을 지원하기 위한 광고 유형 확인 함수 추가
* [FIX] 피드 지면에서 진입한 광고 페이지 웹뷰와 브라우저 상단의 내비게이션이 겹쳐서 보여지는 문제 해결
* [FIX] 유저가 개인정보 수집에 동의한 다음 앱을 삭제 후 재설치 시 간헐적으로 발생하는 크래시 오류 해결
> ### [3.13.2] - 2022-10-20
> * [FIX] SDK에서 발생하는 메모리 누수 제거

## [3.11.3] - 2022-09-08
* [UPDATE] Feed 지면에서 광고가 할당되지 않을 때 표시되는 UI 개선
* [UPDATE] Feed 광고 분류 탭의 인디케이터 색상의 커스터마이징 기능 추가
* [FIX] 광고 페이지(랜딩 페이지) 방문 후 다시 지면으로 돌아오는 과정을 이례적으로 수차례 반복할 때 낮은 확률로 발생하는 메모리 누수 현상 해결

## [3.9.1] - 2022-08-11
* [UPDATE] Feed 지면의 필터 UI를 가로 스크롤 뷰로 변경
* [UPDATE] Feed 지면의 하단에 개인정보 처리 방침 배너 추가
* [FIX] 비디오 광고를 전체 화면으로 전환할 때 간헐적으로 발생하는 크래시 수정
* [FIX] 광고 페이지(랜딩 페이지) 방문 후 다시 지면으로 돌아오는 과정을 이례적으로 수차례 반복할 때 낮은 확률로 발생하는 메모리 누수 현상 해결

## [3.7.6] - 2022-07-14
* [NEW] 개인정보 수집 동의 팝업을 띄우는 기능을 추가
> ### [3.7.7] - 2022-08-11
> * [CHANGE] Feed 지면에 진입할 때 개인정보 수집 동의 팝업을 띄우지 않도록 변경
> * [FIX] 광고 페이지(랜딩 페이지) 방문 후 다시 지면으로 돌아오는 과정을 이례적으로 수차례 반복할 때 낮은 확률로 발생하는 메모리 누수 현상 해결

## [3.5.1] - 2022-06-16
* [FIX] Feed 지면의 광고 미할당 안내 이미지 개선
* [FIX] 광고의 impression 발생 여부 확인 로직 개선

## [3.3.0] - 2022-05-19
* [NEW] 편리한 CTA 버튼 커스터마이징을 위해 기본 CTA 뷰 클래스 제공

## [3.2.1] - 2022-05-06
* [NEW] Feed 기본 적립 포인트 알림 팝업 자체 구현 기능 추가
* [FIX] Feed 지면 진입 시 미리 불러온 광고가 존재하는 경우 광고를 다시 요청하지 않도록 수정

## [3.1.2] - 2022-03-31
* [NEW] Feed 엔트리뷰에 이름을 설정하여 사용자 반응을 추적하는 기능 추가
* [CHANGE] 프리로드 시에 모든 탭에 대한 광고를 로드하게 끔 수정
* [FIX] 광고 타이틀이 길어지면 CTA 버튼의 너비가 줄어들어 CTA 텍스트가 보이지 않는 문제 해결
* [FIX] Feed 지면에서 광고 분류 필터가 광고 소재의 좌우 너비를 벗어난 영역에 정렬되는 문제 해결
* [FIX] BuzzAd Web iOS용 SDK를 사용할 경우 액션형 광고가 할당되지 않는 문제 해결

## [3.0.5] - 2022-02-21
* [CHANGE] 더 쉽고 빠른 연동을 위해 SDK interface를 더 사용하기 쉬운 형태로 개편
* [NEW] 기존에 SDK로 제어하던 기능을 포함한 몇가지 피드 설정을 서버에서 동적으로 설정 (탭의 개수, 탭의 이름, 무한 스크롤 기능, 탭에서 노출할 광고 종류)
> ### [3.0.6] - 2022-03-21
> * [CHANGE] 프리로드 시에 모든 탭에 대한 광고를 로드하게 끔 수정
> * [FIX] 광고 타이틀이 길어지면 CTA 버튼의 너비가 줄어들어 CTA 텍스트가 보이지 않는 문제 해결

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
> ### [2.4.6] - 2022-03-24
> * [FIX] Web iOS SDK에서 액션형 광고가 할당되지 않는 버그 수정
> ### [2.4.7] - 2022-06-10
> * [FIX] 광고의 impression 발생 여부 확인 로직 개선
> ### [2.4.8] - 2023-01-03
> * [NEW] iOS 16을 위한 대응
> * [FIX] 광고 페이지(랜딩 페이지) 방문 후 다시 지면으로 돌아오는 과정을 이례적으로 수차례 반복할 때 낮은 확률로 발생하는 메모리 누수 현상 해결
> * [FIX] 뷰 생명주기에 따라 사라져야 하는 인스턴스가 메모리에 잔존하여 발생하는 불필요한 메모리 누수 현상 해결
