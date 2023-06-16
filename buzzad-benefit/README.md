
## BuzzAdBenefit SDK for Android

* [개발 가이드](https://buzzvil.atlassian.net/wiki/spaces/BDG/pages/718569580/BuzzAd+Benefit)

### 오픈 소스 라이센스 고지
- 이 소프트웨어가 사용하는 오픈 소스 소프트웨어의 라이센스는 "오픈 소스 라이센스 고지 페이지 ([원본 파일](docs/3rd_party_licenses.html)|[렌더링 버전](https://htmlpreview.github.io/?https://github.com/Buzzvil/buzzscreen-sdk-publisher/blob/master/docs/3rd_party_licenses.html))"에서 확인할 수 있다.

## [3.37.0] - 2023-06-15
* [UPDATE] 애드허브 이용 비율을 대폭 끌어올릴 수 있도록 개인정보 제3자 제공 동의 UX & UI 개선
* [UPDATE] 애드허브 광고 미할당 UX & UI 개선
* [UPDATE] 절전 모드(Power save mode)로 인한 광고 적립 실패 이유 제공
* [FIX] 간헐적으로 버즈배너(BuzzBanner)가 노출되지 않는 문제 해결
* 자세한 사항은 [링크](https://docs.buzzvil.com/docs/release-news/android/buzzad3.37-buzzscreen4.31/) 참조

## [3.35.0] - 2023-05-18
* [UPDATE] 문의하기 아이콘 업데이트
  * 사용자가 버즈빌의 광고 인벤토리 참여 중 발생하는 궁금증을 해결하기 위해 제공하는 문의하기 아이콘을 직관적인 디자인으로 업데이트했습니다.
* [UPDATE] 메모리 관리 방식 개선
  * BuzzAd 애드허브(피드)의 메모리를 더 효율적으로 관리하도록 개선했습니다.
* [FIX] 캐러셀에서 네이티브 오버레이를 사용할 때 네이티브 오버레이 아이콘이 원래 자리가 아닌 다른 자리로 이동하는 문제 해결
* [FIX] BuzzAd 초기화 로직을 개선하여 간헐적으로 발생하던 ANR 문제 해결
* 자세한 사항은 [링크](https://docs.buzzvil.com/docs/release-news/android/buzzad3.35-buzzscreen4.29/) 참조

## [3.33.0] - 2023-04-20
* [NEW] 새로운 리워드 지급형 뉴스 콘텐츠 시스템 탑재
  * 기존에는 인앱 브라우저만을 지원했던 시스템을 개선하여 외부 브라우저 및 인앱 브라우저 모두 지원하게 되었습니다. 또한 리워드 지급 조건인 유저 페이지 체류 시간을 대폭 줄임으로써 더 많은 유저의 참여를 유도해 매출 증대 효과도 기대할 수 있습니다.
* [UPDATE] 동영상 광고의 사용성 개선
  * 화면에서 동영상 광고의 일부만 노출된 상태에서 유저가 동영상 광고를 클릭해 재생하면 광고를 잘 볼 수 있도록 화면을 자동으로 스크롤하도록 개선했습니다.
  * 동영상 광고를 재생하고 3초 후에 유저가 광고주 페이지로 이동할 수 있는 `더 알아보기` 버튼을 추가했습니다.
* [UPDATE] 다크모드 CTA 색상 개선
  * 다크모드에서 CTA 버튼의 색상을 눈에 더 잘 띄게 개선했습니다.
* [FIX] CPI(앱 설치형) 광고 참여 후 참여 확인을 체크하는 과정에서 디바이스 내 메모리 부족이 발생하면 리워드 적립이 실패하는 문제 해결
* [FIX] 팝 애니메이션이 끝나기 전에 다른 액티비티가 동시에 열리면, 팝이 사라졌다가 다시 나타났을 때 피드를 닫는 버튼이 동작하지 않는 문제 해결
* 자세한 사항은 [링크](https://docs.buzzvil.com/docs/release-news/android/buzzad3.33-buzzscreen4.27/) 참조

## [3.31.0] - 2023-03-23
* [FIX] 버즈배너를 연동한 팝 피드에서 발생하는 문제 해결
  * 팝 피드 개인정보처리방침 영역 노출 이후 버즈배너 광고가 나타나지 않는 문제 해결
  * 팝 피드 지면에 광고를 할당할 수 없는 경우 버즈배너 자리가 빈 영역으로 남아있는 문제 해결
* [FIX] 버즈배너에 이미지 용량이 100MB 이상인 광고가 할당되는 경우 크래시가 발생하여 100MB 이상인 광고를 할당하지 않도록 수정
* [FIX] Pangle SDK 버전을 4.7.1.4로 업데이트하여 Android 13 기기에서 발생하는 충돌 해결
* [FIX] 네이티브 2.0에서 발생하는 문제 해결
  * 광고 참여 후 나타나는 피드 진입점인 네이티브 오버레이가 정상적으로 동작하나 아이콘은 표시되지 않는 문제 해결
  * 캐러셀의 피드 진입 슬라이드 활성화 시 광고 소재가 노출되지 않는 문제 해결
* [FIX] 동영상 광고의 일부 영역만 노출될 때에도 유저의 재생을 허용하는 문제 해결
* [FIX] 팝 피드와 다른 액티비티가 동시에 열리면 팝 피드를 닫는 버튼이 동작하지 않는 문제 해결
* [FIX] 피드에서 다른 광고 분류 탭으로 이동하면 버즈배너가 나타나지 않는 문제 해결
* [FIX] 동영상 광고에 참여하여 리워드 지급 후 다시 동영상 광고를 재생하면 스크롤을 움직일 때 CTA 버튼의 상태가 참여 가능 상태로 다시 표시되는 문제 해결
* [FIX] Android 기기에서 절전 모드 또는 다크 모드로 변경 시 개인정보 수집동의 UI가 중복으로 나타나는 문제 해결
* [FIX] 재생이 완료된 동영상 광고 위에 일시정지 버튼이 나타나는 문제 해결
* 자세한 사항은 [링크](https://docs.buzzvil.com/docs/release-news/android/buzzad3.31-buzzscreen4.25/) 참조

## [3.29.0] - 2023-02-28
* [IMPORTANT] Android 13(targetsdkversion 33)을 대응하기 위한 검토와 조치 완료
* [UPDATE] 동영상 광고 서빙 시스템 업데이트
* [FIX] BuzzAd Push가 간헐적으로 비활성화되는 문제 해결
* [FIX] BuzzvilBottomSheet 사용 시 다크모드가 실시간으로 반영되지 않는 문제 해결
* [FIX] 버즈배너(BuzzBanner) 초기화 시점에 간헐적으로 오류가 발생하는 현상 해결
* [FIX] 개인정보 수집 동의 UI 표시를 위한 서버 통신을 실패해도 피드에서 광고를 할당하도록 수정
* 자세한 사항은 [링크](https://docs.buzzvil.com/docs/release-news/android/buzzad3.29-buzzscreen4.23/) 참조

## [3.27.0] - 2023-01-26
* [NEW] 개인정보 수집 동의 UI 표시 여부를 서버로 제어하는 기능 추가
* [NEW] revenueType에 새로운 액션형 광고 유형 추가
* [NEW] 피드 배경 색상을 커스터마이징하는 인터페이스 추가
* [NEW] 피드 광고 분류 탭을 좌우로 스와이프하여 전환하는 기능 추가
* [UPDATE] 유저 프로필 입력 UI 개선
* [UPDATE] 개인정보 수집 동의 UI를 웹뷰로 변경하여 다크 모드가 적용되도록 개선
* [FIX] 버즈배너가 노출되지 않는 동작 오류, 미세한 UI 색상 등의 문제 해결
* [FIX] 다크 모드에서 피드 광고 분류 탭을 다른 탭으로 전환하면 잠시 흰색으로 탭 색상이 노출되는 문제 해결
* 자세한 사항은 [링크](https://docs.buzzvil.com/docs/release-news/android/buzzad3.27-buzzscreen4.21/) 참조

## [3.25.0] - 2022-12-29
* [NEW] 피드와 브릿지 페이지에 새로운 광고 배너 버즈배너(BuzzBanner) 추가
* [NEW] 캐러셀(Carousel) 형태로 네이티브 2.0 지면을 구현할 수 있는 가이드 제공
* [UPDATE] 피드 광고 분류 필터를 다시 탭하면 전체 광고 필터로 이동하는 동작 수정
* [UPDATE] 개인정보 수집 동의 창에서 뒤로가기 버튼으로 창을 종료할 수 있도록 개선
* [UPDATE] 팝 버튼의 좌우 여백을 커스터마이징할 수 있도록 개선
* [FIX] WebView를 연동하지 않는 경우 간헐적으로 발생하는 크래시 문제 해결
* [FIX] 팝 피드를 열면 기기 상단의 알림바가 가려지는 문제 해결
* [FIX] 팝 버튼을 누르면 팝 피드가 나타나기 전 화면 하단 중앙에 닫기 아이콘(ExitView)가 나타나는 현상 해결
* [FIX] 동영상 광고 재생 시 ExoPlayer 버전 충돌로 인해 발생하는 앱 빌드 오류를 방지하기 위해 ExoPlayer 버전 체크를 할 수 있도록 ExoPlayerLibraryInfo를 난독화에서 제외
* [FIX] 버즈빌 광고 참여 문의 페이지의 프로토콜을 보안상 더 안전한 HTTPS로 변경
* 자세한 사항은 [링크](https://docs.buzzvil.com/docs/release-news/android/buzzad3.25-buzzscreen4.19/) 참조

## [3.23.0] - 2022-12-01
* [NEW] 네이티브 2.0에 광고 참여 이벤트를 수신할 수 있는 리스너 추가
* [UPDATE] Kotlin으로 구현한 네이티브 2.0의 Java 지원을 위해 인터페이스 개선
* [FIX] 네이티브 2.0에서 새로 호출되어 유저가 아직 참여하지 않은 광고의 CTA 버튼이 “참여 완료“로 표시되는 문제 해결
* 자세한 사항은 [링크](https://docs.buzzvil.com/docs/release-news/android/buzzad3.23-buzzscreen4.17/) 참조

## [3.21.0] - 2022-11-03
* [NEW] 네이티브 2.0, 자동으로 다음 광고를 불러오는 진화한 네이티브 지면
* 자세한 사항은 [링크](https://docs.buzzvil.com/docs/release-news/android/buzzad3.21-buzzscreen4.15/) 참조

## [3.19.0] - 2022-10-06
* [NEW] 룰렛(BuzzRoulette) 푸시 알림의 동작을 커스터마이징할 수 있는 API 추가
* [FIX] Android 7.0에서 유저에게 룰렛 티켓을 제공한 후 앱이 종료되는 문제 해결
* [FIX] BuzzScreen Extension SDK를 연동한 앱의 간헐적인 크래시 오류 해결
* [FIX] BuzzScreen의 간헐적인 크래시 오류 해결
* [FIX] 사용자의 유튜브 좋아요 + 구독하기 광고 참여 상태가 CTA 버튼에 업데이트되지 않는 문제 해결
* 자세한 사항은 [링크](https://docs.buzzvil.com/docs/release-news/android/buzzad3.19-buzzscreen4.13/) 참조

## [3.17.0] - 2022-09-15
* [NEW] 룰렛(BuzzRoulette) 푸시 알림을 커스터마이징하여 제공할 수 있는 API 추가
* [NEW] revenueType에 새로운 액션형 광고 유형 추가
* [NEW] 다양한 기획을 지원하기 위한 광고 유형 확인 함수 추가
* [UPDATE] 광고가 할당되지 않을 때 표시되는 UI 개선
* [UPDATE] 피드 광고 분류 탭의 인디케이터 커스터마이징 기능 추가
* [FIX] 간헐적으로 룰렛 아이콘이 나타나지 않는 문제 해결
* [FIX] 화면을 가로 모드에서 세로 모드로 전환하면 브릿지 페이지가 정상적으로 나타나지 않는 문제 해결
* 자세한 사항은 [링크](https://docs.buzzvil.com/docs/release-news/android/buzzad3.17-buzzscreen4.11/) 참조

## [3.15.0] - 2022-08-11
* [UPDATE] 코드 추가가 필요없는 BuzzRoulette 탑재로 더 쉬워진 수익화
* [UPDATE] 광고 식별자 수집 공지 추가
* [FIX] BuzzRoulette 실행 시 나타나던 불필요한 광고 식별자 수집 동의 UI 제거
* 자세한 사항은 [링크](https://docs.buzzvil.com/docs/release-news/android/buzzad3.15-buzzscreen4.9/) 참조

## [3.13.0] - 2022-07-14
* [UPDATE] 개인정보 처리에 대한 사용자 주권 강화
* [UPDATE] 만보기 기능 제거
* [FIX] BuzzRoulette에서 사용하지 않는 주요 이벤트 알림용 기기의 진동 기능 권한 제거
* 자세한 사항은 [링크](https://docs.buzzvil.com/docs/release-news/android/buzzad3.13-buzzscreen4.7/) 참조

## [3.11.0] - 2022-06-16
* [UPDATE] 피드에서 원하는 영역 어디에서나 버즈룰렛을 연동할 수 있도록 개선
* [UPDATE] 팝 지면 툴바 구현을 위한 퍼블릭 메서드 추가 (핫픽스: 3.0.x 이상)
* [UPDATE] 포또 당첨 확률 공시
* [FIX] BuzzAd 피드 지면을 전체 화면(`FeedFullscreenActivity`)으로 띄울 때 노티피케이션 바를 투명하게 만드는 동작으로 인해 상태 아이콘이 보이지 않는 문제 해결
* [FIX] BuzzAd의 피드 액티비티 전환 애니메이션을 제거하여 피드 지면을 열고 닫을 때 노티피케이션 바 쪽에 나타나던 어색한 시각 효과 문제 해결
* 자세한 사항은 [링크](https://docs.buzzvil.com/docs/release-news/android/buzzad3.11-buzzscreen4.5/) 참조

## [3.9.0] - 2022-05-19
* [UPDATE] 피드에 직접 구현한 툴바를 사용할 때에도 버즈룰렛을 연동할 수 있도록 개선
* [UPDATE] 피드 지면의 사용자 개인 정보 수집 동의 UI 개선
* [FIX] 사용자가 개인 정보 수집에 동의하지 않아 룰렛 참여를 할 수 없음에도 피드 지면의 툴바 영역에 룰렛 아이콘이 노출되는 문제 해결
* 자세한 사항은 [링크](https://care.buzzvil.com/ko/support/solutions/articles/66000504677) 참조

## [3.7.0] - 2022-04-28
* [NEW] 인앱 브라우저의 수명 주기에 맞춘 동작이 실행되도록 개선
* [UPDATE] BuzzRoulette의 광고 라이브러리를 최신 버전으로 업데이트
* [FIX] 기기 화면의 전체 영역을 차지하던 반투명 팝(Pop) 버튼의 투명도를 제거하고 View 크기를 버튼의 실제 크기만을 차지하도록 수정
* [FIX] 프래그먼트로 피드 지면을 연동 시 일부 경우에 과도하게 넓은 상단 여백이 발생하는 문제 해결
* 자세한 사항은 [링크](https://care.buzzvil.com/ko/support/solutions/articles/66000504042) 참조

## [3.5.0] - 2022-03-17
* [UPDATE] targetSdkVersion 31 지원
* [NEW] 캐러셀(Carousel) 형태로 네이티브 지면을 구현할 수 있는 가이드 제공
* [FIX] 동영상 광고의 썸네일을 그리는 과정에서 간헐적으로 앱이 비정상적으로 종료되는 문제 해결
* [FIX] Custom WebView를 반복적으로 실행하면 앱이 비정상적으로 종료되는 문제 해결
* [FIX] ExternalAuth 사용 중 액티비티를 실행할 수 없는 경우 앱이 비정상적으로 종료되는 문제 해결
* 자세한 사항은 [링크](https://care.buzzvil.com/ko/support/solutions/articles/66000501796) 참조

## [3.3.0] - 2022-02-03
* [CHANGE] 피드 기본 적립 포인트 알림 팝업에서 포인트 값을 눈에 띄게 개선
* [CHANGE] CTA 버튼의 리워드 값을 원하는 대로 변경할 수 있도록 개선
* [FIX] Android 12에서 팝 & 푸시 알림의 포그라운드 서비스 알림의 표시 속도 개선
* [FIX] BuzzRoulette 룰렛 아이콘나 티켓 수가 나타나지 않는 등의 동작 오류와 연동 후 발생하는 앱 크래시 등 사용성 관련 문제 해결 
* [FIX] 인터스티셜에서 제공하는 피드 엔트리 포인트의 레이아웃이 틀어져 CTA 버튼 아래에 정렬되지 않는 현상 개선
* [FIX] 피드 확장 기능인 팝 또는 푸시 알림을 재시작하는 broadcast receiver로 인해 팝 또는 푸시 알림을 연동하지 않은 앱이 업데이트되거나 사용자 기기를 다시 켜면 앱 크래시가 발생하는 문제 해결
* 자세한 사항은 [링크](https://care.buzzvil.com/ko/support/solutions/articles/66000500483) 참조

## [3.1.0] - 2021-12-27
* [NEW] Feed 지면에 BuzzRoulette 기능 추가
* [DELETE] Feed 내 exit interstitial 지면 제거 
* [FIX] 피드 광고에 참여해 적립 가능한 총 포인트가 피드의 첫번째 탭에 해당하는 금액만 프리로드되어 적게 표시되는 문제
* 자세한 사항은 [링크](https://care.buzzvil.com/ko/support/solutions/articles/66000498658) 참조
> ### [3.1.1] - 2022-01-18
> * [FIX] 인터스티셜에서 제공하는 피드 엔트리 포인트의 레이아웃이 틀어져 CTA 버튼 아래에 정렬되지 않는 현상 개선
> * [FIX] Pop을 사용할 때 크래시가 발생할 수 있는 경우를 수정
> * [FIX] 앱 재시작 시 Push 기능에서 사용하는 broadcast receiver에서 크래시가 발생할 수 있는 문제 수정
> ### [3.1.2] - 2022-02-03
> * [CHANGE] CTA 버튼의 리워드 값을 원하는 대로 변경할 수 있도록 개선
> * [FIX] 피드 확장 기능인 팝 또는 푸시 알림을 재시작하는 broadcast receiver로 인해 팝 또는 푸시 알림을 연동하지 않은 앱이 업데이트되거나 사용자 기기를 다시 켜면 앱 크래시가 발생하는 문제 해결

## [3.0.0] - 2021-12-02
* [CHANGE] 더 쉽고 빠른 연동을 위해 SDK interface를 더 사용하기 쉬운 형태로 개편
* [CHANGE] Kotlin 1.5 버전 사용
* [NEW] 기존에 SDK로 제어하던 기능을 포함한 몇가지 피드 설정을 서버에서 동적으로 설정 (탭의 개수, 탭의 이름, 무한 스크롤 기능, 탭에서 노출할 광고 종류) 
* [NEW] 앱 충돌 정보를 수집하는 Sentry 연동 인터페이스 추가
* [FIX] Android 12에서 Pop 아이콘이 표시되었을 때 다른 영역이 터치되지 않는 현상 수정 - Pop 아이콘을 불투명하게 변경
* [FIX] 개인정보수집 약관 화면에서 사용자가 동의 여부를 선택하지 않고 뒤로가기 버튼을 누르는 경우에 대한 동의 로직 수정
* [FIX] 광고 동영상에서 자체 구현한 재생 버튼과 SDK의 기본 재생 버튼이 겹쳐 보이는 문제
* [FIX] setVideoPlayerOverlayView 적용 시 동영상 광고가 올바르게 표시되지 않는 문제
> ### [3.0.1] - 2022-01-07
> * [FIX] 인터스티셜에서 제공하는 피드 엔트리 포인트의 레이아웃이 틀어져 CTA 버튼 아래에 정렬되지 않는 현상 개선
> * [FIX] Pop을 사용할 때 크래시가 발생할 수 있는 경우를 수정
> * [FIX] 앱 재시작 시 Push 기능에서 사용하는 broadcast receiver에서 크래시가 발생할 수 있는 문제 수정
> ### [3.0.2] - 2022-02-03
> * [CHANGE] CTA 버튼의 리워드 값을 원하는 대로 변경할 수 있도록 개선
> * [FIX] 피드 확장 기능인 팝 또는 푸시 알림을 재시작하는 broadcast receiver로 인해 팝 또는 푸시 알림을 연동하지 않은 앱이 업데이트되거나 사용자 기기를 다시 켜면 앱 크래시가 발생하는 문제 해결

## [2.31.0] - 2021-10-05
* [NEW] Feed 엔트리뷰 기능 추가
* [CHANGE] Feed 홈탭 기능 제거
* [FIX] exoplayer 버전 호환이 안될 경우에, 비디오 광고 타잎을 무시하는 방어로직 추가
* [FIX] Feed/Pop의 유닛아이디가 같을 경우에, FeedToolbarHolder를 Pop에서도 사용하도록 수정
> ### [2.31.1] - 2021-10-20
> * [MINOR] 릴리즈 빌드 시 OkHttp의 로그를 남기지 않도록 수정
> ### [2.31.2] - 2021-11-04
> * [FIX] 비디오에서 재생 버튼 겹치는 현상 수정
> * [FIX] 비디오에서 오버레이 뷰를 설정했을 때, 화면이 하얗게 보여지는 현상 수정
> ### [2.31.4] - 2021-11-05
> * [FIX] 피드에서 유저 동의 받는 화면의 콜백이 특정 상황에서 중복(누적)되는 오류 수정
> * [FIX] 피드에서 orientation 을 변경할 경우 발생하는 오류 수정
> * [MINOR] '기기 식별자' 관련 구글에서 오진할 가능성이 있는 부분 제거
> ### [2.31.5] - 2021-11-16
> * [FIX] Feed 엔트리뷰를 예상 포인트 메시지를 포함하는 방식으로 구현하는 경우, 실제 피드를 열었을 때 보이는 포인트가 일치하지 않는 오류 수정
> ### [2.31.6] - 2021-11-19
> * [FIX] Android 12에서 Pop 아이콘이 표시되었을 때 다른 영역이 터치되지 않는 현상 수정 - Pop 아이콘을 불투명하게 변경

## [2.29.0] - 2021-08-26
* [NEW] 다음에서 설명하는 광고 ID 관련 정책 변경에 대한 대응
> 시행일: 2021년 10월 4일 
> Android 광고 ID 사용에 관한 [변경사항](https://support.google.com/googleplay/android-developer/answer/9857753)의 설명을 위해 광고 정책을 업데이트합니다. 새로운 기기에서 사용자가 Android 광고 ID를 삭제하면 광고 식별자는 삭제되고 0으로 된 문자열로 대체됩니다. 
* 자세한 사항은 [링크](https://care.buzzvil.com/ko/support/solutions/66000211176)에서 해당 버전을 참조하세요.
> ### [2.29.1] - 2021-09-08
> * [NEW] BuzzAdBenefit.isInitialized() 메소드 추가
> ### [2.29.2] - 2021-09-16
> * [FIX] 경우에 따라, 불필요한 http request 가 중복되는 문제 수정, SessionReadyBroadcast가 반복되는 문제 수정
> ### [2.29.3] - 2021-09-17
> * [FIX] FeedFragment.init 을 반복해서 호출해도 동작에 문제 없도록 수정 (파트너 요청)
> ### [2.29.4] - 2021-09-18
> * [FIX] exoplayer 버전이 호환되지 않을 경우, 동영상 광고가 동작되지 않도록 방어코드 추가
> ### [2.29.5] - 2021-10-20
> * [MINOR] 릴리즈 빌드 시 OkHttp의 로그를 남기지 않도록 수정
> * [MINOR] 1Feed4All 관련 컨피그(FeedConfig/PopConfig)의 상속 구조 변경
> ### [2.29.7] - 2021-11-04
> * [FIX] 비디오에서 재생 버튼 겹치는 현상 수정
> * [FIX] 비디오에서 오버레이 뷰를 설정했을 때, 화면이 하얗게 보여지는 현상 수정
> ### [2.29.9] - 2021-11-05
> * [FIX] 피드에서 유저 동의 받는 화면의 콜백이 특정 상황에서 중복(누적)되는 오류 수정
> * [FIX] 피드에서 orientation 을 변경할 경우 발생하는 오류 수정
> * [MINOR] '기기 식별자' 관련 구글에서 오진할 가능성이 있는 부분 제거
> ### [2.29.10] - 2021-11-18
> * [FIX] Android 12에서 Pop 아이콘이 표시되었을 때 다른 영역이 터치되지 않는 현상 수정 - Pop 아이콘을 불투명하게 변경

## [2.27.0] - 2021-08-16
* [NEW] Feed에 Home Tab 적용
* [FIX] Pop 에서 베이스 리워드 지급시 뜨고있는 snackbar 커스텀이 불가능했던 버그를 수정하였습니다.
* [FIX] Theme이 중복되는 일이 없도록 Prefix 추가
* [FIX] Deep link 에 App 마다 독립적으로 동작되도록 host 이름을 package 이름으로 변경
* [FIX] Feed/Pop 의 Unit ID가 같을 경우, 사용 순서에 따라 크래시 발생할 수 있는 경우에 대해 방어 처리
* [FIX] InAppPop에 의해 AOS6 일부 기기에서 앱이 종료되는 현상 수정
* [FIX] SSL checkServerTrusted 미구현 취약점 수정
* [FIX] Feed 툴바 높이를 변경할 수 없는 버그 수정
* [CHANGE] InAppPop Interface 변경
* [FIX] 2.25.1 까지 적용
* 자세한 사항은 [링크](https://care.buzzvil.com/ko/support/solutions/articles/66000492856-buzzad-2-27-x-buzzscreen-3-31-x-2021%EB%85%84-8%EC%9B%94-) 참조
> ### [2.27.1] - 2021-11-04
> * [FIX] 비디오에서 재생 버튼 겹치는 현상 수정
> * [FIX] 비디오에서 오버레이 뷰를 설정했을 때, 화면이 하얗게 보여지는 현상 수정
> ### [2.27.4] - 2021-11-18
> * [FIX] 경우에 따라, 불필요한 http request 가 중복되는 문제 수정, SessionReadyBroadcast가 반복되는 문제 수정
> * [MINOR] 1Feed4All 관련 컨피그(FeedConfig/PopConfig)의 상속 구조 변경
화면이 하얗게 보여지는 현상 수정
> * [FIX] Android 12에서 Pop 아이콘이 표시되었을 때 다른 영역이 터치되지 않는 현상 수정 - Pop 아이콘을 불투명하게 변경
> * [MINOR] '기기 식별자' 관련 구글에서 오진할 가능성이 있는 부분 제거

## [2.25.0] - 2021-07-05
* [UPDATE] Pop과 InAppPop의 UnitId 분리 지원
* [UPDATE] FeedToolbarHolder 에 문의하기 기능
* [FIX] 기본 설정 중에서 feedFeedbackHandlerClass/htmlEnabled 가 Pop으로 전달되지 않는 버그 수정
* [FIX] VideoLandingActivity 가 드물게 시스템에 의해 재시작될 경우 크래시 발생하는 버그 수정 
* [FIX] 2.23.3 까지 적용
> ### [2.25.1] - 2021-07-14
> * [FIX] 특정 랜딩 페이지에서 TransactionTooLargeException 발생하는 문제 수
* 자세한 사항은 [링크](https://buzzvil.atlassian.net/wiki/spaces/BDG/pages/2211218452/BuzzAd+2.25.x+BuzzScreen+3.29.x) 참조
> ### [2.25.2] - 2021-11-04
> * [FIX] 비디오에서 재생 버튼 겹치는 현상 수정
> * [FIX] 비디오에서 오버레이 뷰를 설정했을 때, 화면이 하얗게 보여지는 현상 수정
> ### [2.25.5] - 2021-11-18
> * [FIX] 경우에 따라, 불필요한 http request 가 중복되는 문제 수정, SessionReadyBroadcast가 반복되는 문제 수정
> * [MINOR] 1Feed4All 관련 컨피그(FeedConfig/PopConfig)의 상속 구조 변경
화면이 하얗게 보여지는 현상 수정
> * [FIX] Android 12에서 Pop 아이콘이 표시되었을 때 다른 영역이 터치되지 않는 현상 수정 - Pop 아이콘을 불투명하게 변경
> * [MINOR] '기기 식별자' 관련 구글에서 오진할 가능성이 있는 부분 제거

## [2.23.0] - 2021-06-02
* [NEW] 네이버페이와 연동된 Feed 및 Pop에서 네이버페이 적립 페이지로 이동할 수 있는 snackbar 제공
* [UPDATE] Pop opt-out 지원
* [UPDATE] igawork mediation을 사용하는 경우 버전 2.4.4로 업데이트 - click event 수집 목적
* [UPDATE] Interstitial, Push, Pop지면에서도 다크테마를 지원
* [UPDATE] Feed에서 Pop Opt-in 버튼을 숨기는 옵션 제공
* [FIX] 2.19.6, 2.21.4 까지 적용
* NOTICE: Repository 에서 삭제
> ### [2.23.1] - 2021-06-04
> * [FIX] Pop/Feed unitId가 같을 경우 Pop 비정상 동작 수정
> ### [2.23.2] - 2021-06-08
> * [FIX] Feed를 Fragment로 연동할 경우, Feed fragment가 속한 Activity가 시스템에 의해 재시작될 경우 크래시 발생하는 버그 수정 
> ### [2.23.3] - 2021-06-18
> * [FIX] Feed bottomsheet에서 커스터마이즈된 toolbar를 사용할 경우, 포인트 총액을 알 수 있는 콜백이 호출되지 않는 버그 수정
> ### [2.23.4] - 2021-07-14
> * [FIX] 특정 랜딩 페이지에서 TransactionTooLargeException 발생하는 문제 수
* 자세한 사항은 [링크](https://buzzvil.atlassian.net/wiki/spaces/BDG/pages/2101805057/BuzzAd+2.23.x+BuzzScreen+3.27.x) 참조
> ### [2.23.5] - 2021-11-04
> * [FIX] 비디오에서 재생 버튼 겹치는 현상 수정
> * [FIX] 비디오에서 오버레이 뷰를 설정했을 때, 화면이 하얗게 보여지는 현상 수정

## [2.21.0] - 2021-05-07
* [UPDATE] targetSdkVersion 30 지원
* [NEW] Feed 에서 Pop 으로 유도하는 기능 추가.
  > ``` BuzzAdBenefitConfig 에서, optInFeatureList(List(OpInFeature.Pop)) 를 호출한다. ```
* [NEW] Native 광고 랜딩 후에, Feed로 유도하는 기능 추가.
  > ``` NativeAdView 에서, enableNativeToFeedOverlay(); 를 호출한다. ```
* [UPDATE] Pop 종료 버튼의 에니메이션 개선
* [UPDATE] Feed 종료/만보기 시작에서 나오는 광고의 UI 개선
* [UPDATE] 단독으로 BuzzAd Push를 사용 가능
* [CHANGE] Feed 에서 다음 기능들이 디폴트로 활성화 됨. ([링크](https://buzzvil.atlassian.net/wiki/spaces/BDG/pages/2048852069/BuzzAd+SDK+2.21.x) 참조)
  > 상단 탭 UI
  > 상당 필터 UI
  > 스크롤 다운 시에 광고 추가 로딩
* [CHANGE] BuzzAdPush와 NotificationConfig.Builder에서 Unit ID를 받지 않도록 변경
* 자세한 사항은 [링크](https://buzzvil.atlassian.net/wiki/spaces/BDG/pages/2014478375/BuzzAd+2.21.x+BuzzScreen+3.24.x) 참조
> ### [2.21.1] - 2021-05-09
> * [FIX] Pop 에서 광고가 갱신 안되는 버그 픽스
> ### [2.21.2] - 2021-05-11
> * [FIX] 브라우저 기본앱이 지정되지 않았을 경우 포인트가 우선 지급되는 문제 수정
> * [FIX] 네이버포인트 연동의 경우, 네이버 로그인을 요구하는 빈도 줄이기
> ### [2.21.3] - 2021-05-13
> * [NEW] BuzzAppHelper를 통해 특정 액티비티의 Lifecycle 이벤트를 모니터링 할 수 있는 기능 추가
> ### [2.21.4] - 2021-05-17
> * [FIX] Pop을 사용하면서 Feed를 별도로 사용하는 경우 비정상 종료되는 버그 수정
> ### [2.21.5] - 2021-06-08
> * [FIX] Feed를 Fragment로 연동할 경우, Feed fragment가 속한 Activity가 시스템에 의해 재시작될 경우 크래시 발생하는 버그 수정 
> ### [2.21.6] - 2021-06-18
> * [FIX] Feed bottomsheet에서 커스터마이즈된 toolbar를 사용할 경우, 포인트 총액을 알 수 있는 콜백이 호출되지 않는 버그 수정
> ### [2.21.7] - 2021-07-14
> * [FIX] 특정 랜딩 페이지에서 TransactionTooLargeException 발생하는 문제 수
> ### [2.21.8] - 2021-11-04
> * [FIX] 비디오에서 재생 버튼 겹치는 현상 수정
> * [FIX] 비디오에서 오버레이 뷰를 설정했을 때, 화면이 하얗게 보여지는 현상 수정

## [2.19.0] - 2021-04-01
* [NEW] Feed 지면과 Pop 지면에 배너 타입 광고 서빙
* [NEW] Feed 지면과 Pop 지면에 연동 가능한 ADN SDK 추가
* [UPDATE] 피드 지면의 UI를 Bottomsheet로 변경
* [UPDATE] 피드 지면의 오토로딩 로직 개선
* [NEW] Feed 지면에 Pop 활성화 유도 버튼 추가
* 자세한 사항은 [링크](https://buzzvil.atlassian.net/wiki/spaces/BDG/pages/1752629414/BuzzAd+2.19.x+BuzzScreen+3.23.x+WIP) 참조
> ### [2.19.1] - 2021-04-19
> * [FIX] CtaView customization 관련 마이너 버그 수정
> * [FIX] 업데이트 시 Pop 활성화 관련 버그 수정
> * [FIX] Universal Image Loadner 에서 가끔 IOException 크래시 발생하는 > 버그 수정
> * [FIX] Pop 닫그 버튼(X 버튼) 애니메이션 수정
> * [UPDATE] dark theme 지원
> * [NEW] Naverpay 지원
> ### [2.19.2] - 2021-05-02
> * [FIX] resource ID가 앱에서 사용하는 ID와 겹치지 않도록 prefix 추가
> ### [2.19.3] - 2021-05-09
> * [FIX] Pop 에서 광고가 갱신 안되는 버그 픽스
> ### [2.19.4] - 2021-05-11
> * [FIX] 브라우저 기본앱이 지정되지 않았을 경우 포인트가 우선 지급되는 문제 수정
> * [FIX] 네이버포인트 연동의 경우, 네이버 로그인을 요구하는 빈도 줄이기
> ### [2.19.5] - 2021-05-17
> * [FIX] Pop을 사용하면서 Feed를 별도로 사용하는 경우 비정상 종료되는 버그 수정
> ### [2.19.6] - 2021-05-21
> * [FIX] Feed에서 Toolbar를 없앴던 것 revert
> ### [2.19.7] - 2021-06-08
> * [FIX] Feed를 Fragment로 연동할 경우, Feed fragment가 속한 Activity가 시스템에 의해 재시작될 경우 크래시 발생하는 버그 수정 
> ### [2.19.8] - 2021-06-18
> * [FIX] Feed bottomsheet에서 커스터마이즈된 toolbar를 사용할 경우, 포인트 총액을 알 수 있는 콜백이 호출되지 않는 버그 수정
> ### [2.19.9] - 2021-07-14
> * [FIX] 특정 랜딩 페이지에서 TransactionTooLargeException 발생하는 문제 수
> ### [2.19.10] - 2021-11-04
> * [FIX] 비디오에서 재생 버튼 겹치는 현상 수정
> * [FIX] 비디오에서 오버레이 뷰를 설정했을 때, 화면이 하얗게 보여지는 현상 수정

## [2.17.0] - 2021-03-04
* [NEW] 쇼핑 적립 광고 최저가 조회 기능 추가
* [NEW] 베이스 리워드 지급 관련 UI 커스텀 기능 추가
* [NEW] 브릿지포인트를 사용하는 퍼블리셔의 지면에서 베이스 리워드 지급시 노출하는 다이얼로그 UI
* [NEW] Feed 지면에 Pop 활성화 유도 버튼 추가
* [NEW] Potto - Tutorial 영역의 landing URL 커스텀 기능 추가
* [UPDATE] 쇼핑 적립 탭의 필터들을 Slider 형태로 변경
* [FIX] Pop 활성화 토글 관련 버그 픽스
* [FIX] 이미지 라이브러리를 Picasso로 사용할 경우 이미지 로딩시에 딜레이가 발생하여 UIL을 사용하도록 변경
* [FIX] androidx.fragment:fragment:1.3.0 을 사용할 경우, feed 에서 간헐적으로 크래시 발생하는 문제 수정
* 자세한 사항은 [링크](https://buzzvil.atlassian.net/wiki/spaces/BDG/pages/1739850216/BuzzAd+2.17.x+BuzzScreen+3.21.x) 참조
> ### [2.17.1] - 2021-03-19
> * [FIX] setPreviewInterval 미동작 오류 수정
> * [FIX] 만보기 걸음 수 UI가 업데이트 되지 않는 버그 픽스
> ### [2.17.2] - 2021-04-19
> * [FIX] CtaView customization 관련 마이너 버그 수정
> * [FIX] 업데이트 시 Pop 활성화 관련 버그 수정
> * [FIX] Universal Image Loadner 에서 가끔 IOException 크래시 발생하는 버그 수정
> * [FIX] Pop 닫그 버튼(X 버튼) 애니메이션 수정
> * [FIX] 특정 비디오 광고에서 minimumTime 관련 NPE 오류 수정
> ### [2.17.3] - 2021-04-30
> * [FIX] resource ID가 앱에서 사용하는 ID와 겹치지 않도록 prefix 추가
> ### [2.17.4] - 2021-06-02
> * [FIX] 브라우저 기본앱이 지정되지 않았을 경우 포인트가 우선 지급되는 문제 수정
> ### [2.17.5] - 2021-07-14
> * [FIX] 특정 랜딩 페이지에서 TransactionTooLargeException 발생하는 문제 수
> ### [2.17.6] - 2021-08-31
> * [NEW] 다음에서 설명하는 광고 ID 관련 정책 변경에 대한 대응
>>  시행일: 2021년 10월 4일 
>> Android 광고 ID 사용에 관한 [변경사항](https://support.google.com/googleplay/android-developer/answer/9857753)의 설명을 위해 광고 정책을 업데이트합니다. 새로운 기기에서 사용자가 Android 광고 ID를 삭제하면 광고 식별자는 삭제되고 0으로 된 문자열로 대체됩니다. 
> ### [2.17.7] - 2021-09-30
> * [FIX] 업데이트 상황에서 FeedActivity에서 'incompatible types for field ...' RuntimeException 발생하는 문제에 대해 방어코드 적용
> ### [2.17.8] - 2021-11-04
> * [FIX] 비디오에서 재생 버튼 겹치는 현상 수정
> * [FIX] 비디오에서 오버레이 뷰를 설정했을 때, 화면이 하얗게 보여지는 현상 수정
> * [FIX] 비디오 WIFI only 등의 설정을 앱에서 했을 때 적용이 안 되는 버그 수정
> ### [2.17.9] - 2021-11-24
> * [FIX] 동영상 재생중 전화가 왔을 때, 앱 크래시 발생하는 버그 수정
> ### [2.17.10] - 2021-12-01
> * [MINOR] 퍼미션 유무와 관련 없이 Build.SERIAL 정보를 수집하지 않도록 수정.
> * [MINOR] '기기 식별자' 관련 구글에서 오진할 가능성이 있는 부분 제거

## [2.15.1] - 2021-02-10
* [NEW] 액션형 광고의 브릿지 페이지(Bottom-sheet)에 애드네트워크 광고 배너를 노출(unit_id를 각각 발급받아 사용해주세요)
* [NEW] 피드를 종료할 때 인터스티셜 광고 노출하고 종료 여부를 재확인
* [NEW] 피드 진입 시 기본포인트 지급 기능
* [NEW] 팝 프리뷰에서 만보기 정보 제공
* 자세한 사항은 [링크](https://buzzvil.atlassian.net/wiki/spaces/BDG/pages/1577680970/BuzzAd+2.15.x+BuzzScreen+3.19.x+2021+2) 참조
> ### [2.15.2] - 2021-02-19
> * [FIX] setImageDrawable 적용이 안되는 문제 수정
> ### [2.15.3] - 2021-02-28
> * [FIX] Feed를 여는 Activity가 tackAffinity가 명시적으로 지정된 경우, VideoLandingActivity의 Z-order 문제로 Landing event가 전달되지 않는 문제 수정
> ### [2.15.4] - 2021-04-19
> * [FIX] CtaView customization 관련 마이너 버그 수정
> * [FIX] 업데이트 시 Pop 활성화 관련 버그 수정
> ### [2.15.5] - 2021-07-14
> * [FIX] 특정 랜딩 페이지에서 TransactionTooLargeException 발생하는 문제 수

## [2.13.0] - 2021-01-14
* 푸시 알림창에 만보기 기능 제공 (Pop 연동 필요)
* Exoplayer2 업데이트 - 드물게 동영상 광고 재생시 멈추는 현상 수정
* 자세한 사항은 [링크](https://buzzvil.atlassian.net/wiki/spaces/BDG/pages/1456177338/BuzzAd+2.13.x+BuzzScreen+3.17.x+2021+1) 참조
> ### [2.13.1] - 2021-01-21
> * [FIX] 불필요한 error log 삭제
> * [FIX] 드물게 Feed CPS 탭에서 category 로드가 늦게 될 경우 NPE가 발생할 수 있는 버그 수정 
> * [UPDATE] InputMethodService context 에서도 BuzzAd-native 동작 지원
> ### [2.13.2] - 2021-01-21
> * [FIX] Feed의 각 placement를 개별적으로 관리하도록 수정.
> ### [2.13.3] - 2021-01-26
> * [FIX] 2.13.1 이후, ContextThemeWrapper(Dialog) 에 NativeAd 를 붙인 경우 Visibility check가 오동작하는 문제 수정
> ### [2.13.4] - 2021-02-02
> * [FIX] buzzad-browser 에서 외부로그인이 필요한 경우(특정 광고 상품) 오동작 수정
> ### [2.13.5] - 2021-07-14
> * [FIX] 특정 랜딩 페이지에서 TransactionTooLargeException 발생하는 문제 수

## [2.11.0] - 2020-12-03
* BuzzAdPop에 preloadAndShowFeed interface 추가
* Interstitial 에서 Feed 로 진입하는 entry point 추가
* IN-APP browser에서 다른 도메인으로 이동하는 경우 External Browser로 내보내는 flow 제거
* Interstitial 종료 콜백 추가.
> ### [2.11.1] - 2020-12-08
> * [FIX] System.exit(n)으로 앱을 종료시키는 경우 Pop 동작의 안정성 향상
> ### [2.11.2] - 2020-12-22
> * [FIX] Android Studio 에서 빌드는 되지만 IDE에서 특정 class 찾지 못하는 문제 수정.
> * [UPDATE] 앱 실행 직후 불필요한 error log 제거
> * [FIX] AOS11에서, Feed 열린 후 Home으로 나갔다가 앱 재실행 시, 앱이 처음부터 다시 실행되는 문제 수정.
> ### [2.11.3] - 2021-02-02
> * [FIX] buzzad-browser 에서 외부로그인이 필요한 경우(특정 광고 상품) 오동작 수정
> ### [2.11.4] - 2021-07-14
> * [FIX] 특정 랜딩 페이지에서 TransactionTooLargeException 발생하는 문제 수

## [2.9.0] - 2020-11-05
* [UPDATE] Feed category UI 개선
* [CHANGE] 이미지 로딩 라이브러리를 UIL 에서 Picasso(v2.71828) 로 변경.
* [CHANGE] Feed에서 Action형 광고를 클릭시 BottomSheet형태의 WebView로 진입하도록 UI 변경
* [NEW] 개인정보처리방침 UI 추가 - 이미 앱에서 동의를 받고 있다면, BuzzAdBenefit.getPrivacyPolicyManager().grantConsent(); 호출함으로써, UI가 나타나지 않게 할 수 있음.
> ### [2.9.1] - 2020-11-06
> * [FIX] Feed에서, 랜딩 후 "system-initiated process death"가 발생하면 빈 웹화면이 나오는 문제 수정
> ### [2.9.2] - 2020-11-09
> * [FIX] 드물게 NPE 발생 - LandingRewardManager$x.onItem
> ### [2.9.3] - 2020-11-12
> * [FIX] Tab customization 관련 마이너 버그 수정
> ### [2.9.4] - 2020-11-29
> * [NEW] Profile 입력을 위한 UI 를 제거할 수 있는 Method 추가
> ### [2.9.5] - 2020-12-08
> * [FIX] System.exit(n)으로 앱을 종료시키는 경우 Pop 동작의 안정성 향상
> ### [2.9.6] - 2021-02-02
> * [FIX] buzzad-browser 에서 외부로그인이 필요한 경우(특정 광고 상품) 오동작 수정
> ### [2.9.8] - 2021-07-14
> * [FIX] 특정 랜딩 페이지에서 TransactionTooLargeException 발생하는 문제 수

## [2.7.0] - 2020-09-25
* [NEW] Pop 피드백 뷰 (Snackbar / toast) Customize
* [NEW] Pop 아이콘 customize
* [UPDATE] 체류리워드 UI 개선
* [UPDATE] Bridge 포인트 조회 화면 UI 개선
* [FIX] 5.0 단말기에서 드물게 ArrayStoreException 발생하는 오류 수정
* [FIX] 특정 환경에서 resource 충돌로인해 빌드 실패 오류 수정
 - BuzzScreen SDK 를 사용중이라면 3.11.+ 로 업데이트 필요
> ### [2.7.1] - 2020-11-06
> * [FIX] Pop 기능의 안정성 개선
> * [NEW] Notification 기능의 정식 출시
> ### [2.7.2] - 2020-11-26
> * [NEW] Profile 입력을 위한 UI 를 제거할 수 있는 Method 추가
> ### [2.7.3] - 2020-12-02
> * [FIX] Application 기본 테마가 Old theme 를 사용할 경우, 특정 상황에서 광고 클릭 시 IllegalStateException (You need to use a Theme.AppCompat theme) 발생하는 오류 수정
> ### [2.7.4] - 2020-12-08
> * [FIX] System.exit(n)으로 앱을 종료시키는 경우 Pop 동작의 안정성 향상
> ### [2.7.5] - 2020-12-14
> * [FIX] Resource Not Found Exception 에 대한 Workaround 처리
> ### [2.7.6] - 2021-07-14
> * [FIX] 특정 랜딩 페이지에서 TransactionTooLargeException 발생하는 문제 수

## [2.5.0] - 2020-09-04
* [Migration Guide for 2.3.x to 2.5.x](https://buzzvil.atlassian.net/wiki/spaces/BDG/pages/1102708753/BN+AOS+2.3.x+to+2.5.x)
* [UPDATE] Feed UI 개선 (탭, 필터, 정렬 기능)
* [CHANGE] 문의하기 기능에 unitId 입력 필요.
* [CHANGE] Pop init 방법 변경
* [CHANGE] workManager 2.4.0 업데이트
* [FIX] LG, 6.x 에서 Pop이 죽는 문제 수정
> ### [2.5.1] - 2020-09-11
> * [UPDATE] Bridge point dialog UI 개선
> * [FIX] MD's Pick 다양한 해상도 대응
> ### [2.5.5] - 2020-09-16
> * [FIX] 앱에서 meterial library 1.3.0-alpha02 이상을 사용하는 경우 resource 이름 충돌로 빌드가 실패하는 문제 수정
> ### [2.5.6] - 2020-09-18
> * [FIX] 누락된 Analytics를 위한 파라미터 추가.
> ### [2.5.7] - 2020-12-02
> * [FIX] Application 기본 테마가 Old theme 를 사용할 경우, 특정 상황에서 광고 클릭 시 IllegalStateException (You need to use a Theme.AppCompat theme) 발생하는 오류 수정
> ### [2.5.8] - 2021-07-14
> * [FIX] 특정 랜딩 페이지에서 TransactionTooLargeException 발생하는 문제 수정

## [2.3.0] - 2020-08-18
* [NEW] 브리지 포인트 조회 화면
* [NEW] CPYoutube / CPK 타잎 지원
* [CHANGE] 로그 정리
* [FIX] 드물게 Fragment 재생성 시 발생하는 크래시 수정
> ### [2.3.1] - 2020-09-18
> * [FIX] 누락된 Analytics를 위한 파라미터 추가.
> ### [2.3.2] - 2020-12-02
> * [FIX] Application 기본 테마가 Old theme 를 사용할 경우, 특정 상황에서 광고 클릭 시 IllegalStateException (You need to use a Theme.AppCompat theme) 발생하는 오류 수정
> ### [2.3.3] - 2021-07-14
> * [FIX] 특정 랜딩 페이지에서 TransactionTooLargeException 발생하는 문제 수정

## [2.2.0] - 2020-06-25
* [NEW] 인터스티셜에 문의하기 버튼 추가
* [NEW] Pop feed의 툴바 커스텀하는 기능 추가
* [NEW] Pop 사라지는 시간을 커스텀하는 기능 추가
* [NEW] 인앱랜딩을 위한 액티비티 커스텀하는 기능 추가
* [CHANGE] 액션형 광고의 참여여부에 따른 CTA 변경
* [UPDATE] Video에 Open Measurement 스펙 적용
* [UPDATE] Pop 베이스 리워드와 광고 리워드를 통합해서 Preview에 노출
* [FIX] 인앱랜딩을 위한 액티비티의 테마가 App 테마를 따르도록 변경.
* [FIX] 컨텐츠 랜딩 시 Feed 내의 카드뷰로 랜딩되도록 변경.
* [FIX] 특정 단말에서 Pop 종료 버튼(X버튼) 에니메이션 개선
> ### [2.2.1] - 2020-09-04
> * [FIX] FeedFragment.init 하위버전 호환
> ### [2.2.2] - 2020-09-04
> * [FIX] 드물게 특정 광고에서 이미지가 표시 안되는 문제 수정
> ### [2.2.3] - 2020-09-04
> * [CHANGE] CPK 지원
> * [FIX] 드물게 POP이 크래시(잠시 후 재 실행됨) 되는 문제 수정
> ### [2.2.4] - 2020-12-02
> * [FIX] Application 기본 테마가 Old theme 를 사용할 경우, 특정 상황에서 광고 클릭 시 IllegalStateException (You need to use a Theme.AppCompat theme) 발생하는 오류 수정


## [2.0.0] - 2020-04-27
* AndroidX 기반으로 변경
> ### [2.0.2] - 2020-09-04  
> * Feed에서 광고 클릭시 가끔 이미지가 다시 로드되면서, 깜빡이는 문제 수정
> * 의도치 않게 POP 서비스가 죽었을 때, 다시 살리는 로직 추가
> * Benefit-Web을 위한 인터페이스 추가
> * AndroidManifest.xml에 android:allowBackup="true" 가 추가되지 않도록 수정
