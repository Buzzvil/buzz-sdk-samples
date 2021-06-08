
# BuzzAdBenefit SDK for Android

* [개발 가이드](https://buzzvil.atlassian.net/wiki/spaces/BDG/pages/1987346963)

# 2.23.0
* [NEW] 네이버페이와 연동된 Feed 및 Pop에서 네이버페이 적립 페이지로 이동할 수 있는 snackbar 제공
* [UPDATE] Pop opt-out 지원
* [UPDATE] igawork mediation을 사용하는 경우 버전 2.4.4로 업데이트 - click event 수집 목적
* [UPDATE] Interstitial, Push, Pop지면에서도 다크테마를 지원
* [UPDATE] Feed에서 Pop Opt-in 버튼을 숨기는 옵션 제공
* [FIX] 2.19.6, 2.21.4 까지 적용
* NOTICE: Repository 에서 삭제
> ## 2.23.1
> * [FIX] Pop/Feed unitId가 같을 경우 Pop 비정상 동작 수정
> ## 2.23.2
> * [FIX] Feed를 Fragment로 연동할 경우, Feed fragment가 속한 Activity가 시스템에 의해 재시작될 경우 크래시 발생하는 버그 수정 

# 2.21.0
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
> ## 2.21.1
> * [FIX] Pop 에서 광고가 갱신 안되는 버그 픽스
> ## 2.21.2
> * [FIX] 브라우저 기본앱이 지정되지 않았을 경우 포인트가 우선 지급되는 문제 수정
> * [FIX] 네이버포인트 연동의 경우, 네이버 로그인을 요구하는 빈도 줄이기
> ## 2.21.3
> * [NEW] BuzzAppHelper를 통해 특정 액티비티의 Lifecycle 이벤트를 모니터링 할 수 있는 기능 추가
> ## 2.21.4
> * [FIX] Pop을 사용하면서 Feed를 별도로 사용하는 경우 비정상 종료되는 버그 수정
> ## 2.21.5
> * [FIX] Feed를 Fragment로 연동할 경우, Feed fragment가 속한 Activity가 시스템에 의해 재시작될 경우 크래시 발생하는 버그 수정 

# 2.19.0
* [NEW] Feed 지면과 Pop 지면에 배너 타입 광고 서빙
* [NEW] Feed 지면과 Pop 지면에 연동 가능한 ADN SDK 추가
* [UPDATE] 피드 지면의 UI를 Bottomsheet로 변경
* [UPDATE] 피드 지면의 오토로딩 로직 개선
* [NEW] Feed 지면에 Pop 활성화 유도 버튼 추가
* 자세한 사항은 [링크](https://buzzvil.atlassian.net/wiki/spaces/BDG/pages/1752629414/BuzzAd+2.19.x+BuzzScreen+3.23.x+WIP) 참조
> ## 2.19.1
> * [FIX] CtaView customization 관련 마이너 버그 수정
> * [FIX] 업데이트 시 Pop 활성화 관련 버그 수정
> * [FIX] Universal Image Loadner 에서 가끔 IOException 크래시 발생하는 > 버그 수정
> * [FIX] Pop 닫그 버튼(X 버튼) 애니메이션 수정
> * [UPDATE] dark theme 지원
> * [NEW] Naverpay 지원
> ## 2.19.2
> * [FIX] resource ID가 앱에서 사용하는 ID와 겹치지 않도록 prefix 추가
> ## 2.19.3
> * [FIX] Pop 에서 광고가 갱신 안되는 버그 픽스
> ## 2.19.4
> * [FIX] 브라우저 기본앱이 지정되지 않았을 경우 포인트가 우선 지급되는 문제 수정
> * [FIX] 네이버포인트 연동의 경우, 네이버 로그인을 요구하는 빈도 줄이기
> ## 2.19.5
> * [FIX] Pop을 사용하면서 Feed를 별도로 사용하는 경우 비정상 종료되는 버그 수정
> ## 2.19.6
> * [FIX] Feed에서 Toolbar를 없앴던 것 revert
> ## 2.19.7
> * [FIX] Feed를 Fragment로 연동할 경우, Feed fragment가 속한 Activity가 시스템에 의해 재시작될 경우 크래시 발생하는 버그 수정 

# 2.17.0
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
> ## 2.17.1
> * [FIX] setPreviewInterval 미동작 오류 수정
> * [FIX] 만보기 걸음 수 UI가 업데이트 되지 않는 버그 픽스
> ## 2.17.2
> * [FIX] CtaView customization 관련 마이너 버그 수정
> * [FIX] 업데이트 시 Pop 활성화 관련 버그 수정
> * [FIX] Universal Image Loadner 에서 가끔 IOException 크래시 발생하는 버그 수정
> * [FIX] Pop 닫그 버튼(X 버튼) 애니메이션 수정
> * [FIX] 특정 비디오 광고에서 minimumTime 관련 NPE 오류 수정
> ## 2.17.3
> * [FIX] resource ID가 앱에서 사용하는 ID와 겹치지 않도록 prefix 추가
> ## 2.17.4
> * [FIX] 브라우저 기본앱이 지정되지 않았을 경우 포인트가 우선 지급되는 문제 수정

# 2.15.1
* [NEW] 액션형 광고의 브릿지 페이지(Bottom-sheet)에 애드네트워크 광고 배너를 노출(unit_id를 각각 발급받아 사용해주세요)
* [NEW] 피드를 종료할 때 인터스티셜 광고 노출하고 종료 여부를 재확인
* [NEW] 피드 진입 시 기본포인트 지급 기능
* [NEW] 팝 프리뷰에서 만보기 정보 제공
* 자세한 사항은 [링크](https://buzzvil.atlassian.net/wiki/spaces/BDG/pages/1577680970/BuzzAd+2.15.x+BuzzScreen+3.19.x+2021+2) 참조
> ## 2.15.2
> * [FIX] setImageDrawable 적용이 안되는 문제 수정
> ## 2.15.3
> * [FIX] Feed를 여는 Activity가 tackAffinity가 명시적으로 지정된 경우, VideoLandingActivity의 Z-order 문제로 Landing event가 전달되지 않는 문제 수정
> ## 2.15.4
> * [FIX] CtaView customization 관련 마이너 버그 수정
> * [FIX] 업데이트 시 Pop 활성화 관련 버그 수정

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
