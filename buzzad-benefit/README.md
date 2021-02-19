
# BuzzAdBenefit SDK for Android

* [개발 가이드](https://buzzvil.atlassian.net/wiki/spaces/BDG/pages/721256746/BuzzAd+Benefit+2.0+Android+SDK)

# 2.15.1
* [NEW] 액션형 광고의 브릿지 페이지(Bottom-sheet)에 애드네트워크 광고 배너를 노출(unit_id를 각각 발급받아 사용해주세요)
* [NEW] 피드를 종료할 때 인터스티셜 광고 노출하고 종료 여부를 재확인
* [NEW] 피드 진입 시 기본포인트 지급 기능
* [NEW] 팝 프리뷰에서 만보기 정보 제공
* 자세한 사항은 [링크](https://buzzvil.atlassian.net/wiki/spaces/BDG/pages/1577680970/BuzzAd+2.15.x+BuzzScreen+3.19.x+2021+2) 참조
> ## 2.15.2
> * [FIX] setImageDrawable 적용이 안되는 문제 수정

# 2.13.0
* 푸시 알림창에 만보기 기능 제공 (Pop 연동 필요)
* Exoplayer2 업데이트 - 드물게 동영상 광고 재생시 멈추는 현상 수정
* 자세한 사항은 [링크](https://buzzvil.atlassian.net/wiki/spaces/BDG/pages/1456177338/BuzzAd+2.13.x+BuzzScreen+3.17.x+2021+1) 참조
> ## 2.13.1
> * [FIX] 불필요한 error log 삭제
> * [FIX] 드물게 Feed CPS 탭에서 category 로드가 늦게 될 경우 NPE가 발생할 수 있는 버그 수정 
> * [UPDATE] InputMethodService context 에서도 BuzzAd-native 동작 지원
> ## 2.13.2
> * [FIX] Feed의 각 placement를 개별적으로 관리하도록 수정.
> ## 2.13.3
> * [FIX] 2.13.1 이후, ContextThemeWrapper(Dialog) 에 NativeAd 를 붙인 경우 Visibility check가 오동작하는 문제 수정
> ## 2.13.4
> * [FIX] buzzad-browser 에서 외부로그인이 필요한 경우(특정 광고 상품) 오동작 수정

# 2.11.0
* BuzzAdPop에 preloadAndShowFeed interface 추가
* Interstitial 에서 Feed 로 진입하는 entry point 추가
* IN-APP browser에서 다른 도메인으로 이동하는 경우 External Browser로 내보내는 flow 제거
* Interstitial 종료 콜백 추가.
> ## 2.11.1
> * [FIX] System.exit(n)으로 앱을 종료시키는 경우 Pop 동작의 안정성 향상
> ## 2.11.2
> * [FIX] Android Studio 에서 빌드는 되지만 IDE에서 특정 class 찾지 못하는 문제 수정.
> * [UPDATE] 앱 실행 직후 불필요한 error log 제거
> * [FIX] AOS11에서, Feed 열린 후 Home으로 나갔다가 앱 재실행 시, 앱이 처음부터 다시 실행되는 문제 수정.
> ## 2.11.3
> * [FIX] buzzad-browser 에서 외부로그인이 필요한 경우(특정 광고 상품) 오동작 수정

# 2.9.0
* [UPDATE] Feed category UI 개선
* [CHANGE] 이미지 로딩 라이브러리를 UIL 에서 Picasso(v2.71828) 로 변경.
* [CHANGE] Feed에서 Action형 광고를 클릭시 BottomSheet형태의 WebView로 진입하도록 UI 변경
* [NEW] 개인정보처리방침 UI 추가 - 이미 앱에서 동의를 받고 있다면, BuzzAdBenefit.getPrivacyPolicyManager().grantConsent(); 호출함으로써, UI가 나타나지 않게 할 수 있음.
> ## 2.9.1
> * [FIX] Feed에서, 랜딩 후 "system-initiated process death"가 발생하면 빈 웹화면이 나오는 문제 수정
> ## 2.9.2
> * [FIX] 드물게 NPE 발생 - LandingRewardManager$x.onItem
> ## 2.9.3
> * [FIX] Tab customization 관련 마이너 버그 수정
> ## 2.9.4
> * [NEW] Profile 입력을 위한 UI 를 제거할 수 있는 Method 추가
> ## 2.9.5
> * [FIX] System.exit(n)으로 앱을 종료시키는 경우 Pop 동작의 안정성 향상
> ## 2.9.6
> * [FIX] buzzad-browser 에서 외부로그인이 필요한 경우(특정 광고 상품) 오동작 수정

# 2.7.0
* [NEW] Pop 피드백 뷰 (Snackbar / toast) Customize
* [NEW] Pop 아이콘 customize
* [UPDATE] 체류리워드 UI 개선
* [UPDATE] Bridge 포인트 조회 화면 UI 개선
* [FIX] 5.0 단말기에서 드물게 ArrayStoreException 발생하는 오류 수정
* [FIX] 특정 환경에서 resource 충돌로인해 빌드 실패 오류 수정
 - BuzzScreen SDK 를 사용중이라면 3.11.+ 로 업데이트 필요
> ## 2.7.1
> * [FIX] Pop 기능의 안정성 개선
> * [NEW] Notification 기능의 정식 출시
> ## 2.7.2
> * [NEW] Profile 입력을 위한 UI 를 제거할 수 있는 Method 추가
> ## 2.7.3
> * [FIX] Application 기본 테마가 Old theme 를 사용할 경우, 특정 상황에서 광고 클릭 시 IllegalStateException (You need to use a Theme.AppCompat theme) 발생하는 오류 수정
> ## 2.7.4
> * [FIX] System.exit(n)으로 앱을 종료시키는 경우 Pop 동작의 안정성 향상
> ## 2.7.5
> * [FIX] Resource Not Found Exception 에 대한 Workaround 처리

# 2.5.0 
* [Migration Guide for 2.3.x to 2.5.x](https://buzzvil.atlassian.net/wiki/spaces/BDG/pages/1102708753/BN+AOS+2.3.x+to+2.5.x)
* [UPDATE] Feed UI 개선 (탭, 필터, 정렬 기능)
* [CHANGE] 문의하기 기능에 unitId 입력 필요.
* [CHANGE] Pop init 방법 변경
* [CHANGE] workManager 2.4.0 업데이트
* [FIX] LG, 6.x 에서 Pop이 죽는 문제 수정
> ## 2.5.1
> * [UPDATE] Bridge point dialog UI 개선
> * [FIX] MD's Pick 다양한 해상도 대응
> ## 2.5.5
> * [FIX] 앱에서 meterial library 1.3.0-alpha02 이상을 사용하는 경우 resource 이름 충돌로 빌드가 실패하는 문제 수정
> ## 2.5.6
> * [FIX] 누락된 Analytics를 위한 파라미터 추가.
> ## 2.5.7
> * [FIX] Application 기본 테마가 Old theme 를 사용할 경우, 특정 상황에서 광고 클릭 시 IllegalStateException (You need to use a Theme.AppCompat theme) 발생하는 오류 수정

# 2.3.0
* [NEW] 브리지 포인트 조회 화면
* [NEW] CPYoutube / CPK 타잎 지원
* [CHANGE] 로그 정리
* [FIX] 드물게 Fragment 재생성 시 발생하는 크래시 수정
> ## 2.3.1
> * [FIX] 누락된 Analytics를 위한 파라미터 추가.
> ## 2.3.2
> * [FIX] Application 기본 테마가 Old theme 를 사용할 경우, 특정 상황에서 광고 클릭 시 IllegalStateException (You need to use a Theme.AppCompat theme) 발생하는 오류 수정

# 2.2.0
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
> ## 2.2.1
> * [FIX] FeedFragment.init 하위버전 호환
> ## 2.2.2
> * [FIX] 드물게 특정 광고에서 이미지가 표시 안되는 문제 수정
> ## 2.2.3
> * [CHANGE] CPK 지원
> * [FIX] 드물게 POP이 크래시(잠시 후 재 실행됨) 되는 문제 수정
> ## 2.2.4
> * [FIX] Application 기본 테마가 Old theme 를 사용할 경우, 특정 상황에서 광고 클릭 시 IllegalStateException (You need to use a Theme.AppCompat theme) 발생하는 오류 수정


# 2.0.0
* AndroidX 기반으로 변경
> ## 2.0.2
> * Feed에서 광고 클릭시 가끔 이미지가 다시 로드되면서, 깜빡이는 문제 수정
> * 의도치 않게 POP 서비스가 죽었을 때, 다시 살리는 로직 추가
> * Benefit-Web을 위한 인터페이스 추가
> * AndroidManifest.xml에 android:allowBackup="true" 가 추가되지 않도록 수정
