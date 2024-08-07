
## Buzzvil SDK for Android

* [개발 가이드](https://docs.buzzvil.com/docs/buzzbenefit-android/v5/getting-started)

### 오픈 소스 라이센스 고지
- 이 소프트웨어가 사용하는 오픈 소스 소프트웨어의 라이센스는 "오픈 소스 라이센스 고지 페이지 ([원본 파일](/3rd_party_licenses.html)|[렌더링 버전](https://htmlpreview.github.io/?https://github.com/Buzzvil/buzz-sdk-samples/blob/master/3rd_party_licenses.html))"에서 확인할 수 있다.

## [5.17.0] - 2024-07-25
* [NEW] 새로운 유저 인게이지먼트 및 수익화 기능, “럭키박스” 출시!
* [NEW] 오버레이 미노출 광고 유형 설정 기능 추가
* [NEW] CTA 뷰 전역 커스터마이징 기능 추가
* 자세한 사항은 [릴리스 뉴스](https://docs.buzzvil.com/docs/release-news/android/buzzvil5.17) 참조

## [5.13.0] - 2024-05-30
* [UPDATE] Android 14 정책 대응
* [UPDATE] 심리스한 베네핏허브 프래그먼트 경험을 제공하기 위한 광고 프리로드 기능 지원
* [FIX] 네이티브 오버레이가 노출된 상태에서 앱을 다시 시작하는 경우 비정상적인 동작이 발생하는 문제 해결
* 자세한 사항은 [릴리스 뉴스](https://docs.buzzvil.com/docs/release-news/android/buzzvil5.13) 참조

## [5.11.0] - 2024-05-02
* [UPDATE] Kotlin 1.8 지원을 위해 AndroidX lifecycle 2.5.0 , AGP 7.4, Gradle 7.5 로 업데이트
* [FIX] 버즈룰렛에서 간헐적으로 발생하던 크래시 문제 해결
* 자세한 사항은 [릴리스 뉴스](https://docs.buzzvil.com/docs/release-news/android/buzzvil5.11) 참조

## [5.9.0] - 2024-04-04
* [NEW] 네이티브지면에 베네핏허브로의 진입을 유도하는 네이티브 오버레이 추가
* [UPDATE] 로컬 푸시 알림 기능 제거
* [UPDATE] minSDKVersion 을 19로 업데이트
* [UPDATE] ADCash 의 버전을 2.1.0.6 로 업데이트하여 Lifecyle 문제로 발생한 NullPointException 이 발생되지 않도록 개선
* [FIX] 간헐적으로 발생하던 ANR 문제 해결
* [FIX] 간헐적으로 발생하던 버즈스크린 백화현상 문제 해결
* 자세한 사항은 [릴리스 뉴스](https://docs.buzzvil.com/docs/release-news/android/buzzvil5.9-buzzscreen5.9) 참조

## [5.7.0] - 2024-03-07
* [FIX] 다크모드에서 광고 ID 변경 방지 알림 텍스트 식별 불가 문제 해결
* [FIX] 새로고침 버튼 터치를 반복하는 경우 새로고침 버튼 색상이 진해지는 문제 해결
* [FIX] 룰렛 내 노티피케이션 기능에서 특정 유저에게 간헐적으로 발생하던 문제 해결
* 자세한 사항은 [릴리스 뉴스](https://docs.buzzvil.com/docs/release-news/android/buzzvil5.7-buzzscreen5.7) 참조

## [5.5.0] - 2024-02-15
* [NEW] 광고 ID 변경 방지 알림 추가
* [UPDATE] Lottie 내재화작업
* [UPDATE] gradle wrapper 버전을 7.2 이상으로 업데이트
* [FIX] Android 14 에서 POP의 Foregound Service 에서 발생하던 크래시 문제 해결
* 자세한 사항은 [릴리스 뉴스](https://docs.buzzvil.com/docs/release-news/android/buzzvil5.5-buzzad3.52-buzzscreen4.46) 참조

## [5.3.0] - 2024-01-11
* [UPDATE] 브랜드 테마에 맞게 색상을 자유롭게 설정할 수 있도록 버즈룰렛 (BuzzRoulette) 을 개선하였습니다.
* [UPDATE] Android 14 잠금화면 정책 대응을 위하여 Buzzvil SDK 에서 전체화면 인텐트를 제거하였습니다.
* [FIX] Android 14 잠금화면에서 발생하는 락스크린 서비스관련 크래시 문제 해결
* [FIX] 인터스티셜 지면에서 global theme이 적용되지 않는 문제 해결
* 자세한 사항은 [릴리스 뉴스](https://docs.buzzvil.com/docs/release-news/android/buzzvil5.3-buzzad3.51-buzzscreen4.45) 참조

## [5.2.0] - 2023-12-28
* [NEW] 자체 포인트 시스템이 없는 고객을 위한 리워드 시스템 출시
* [FIX] 버그 수정
* 자세한 사항은 [릴리스 뉴스](https://docs.buzzvil.com/docs/release-news/android/buzzvil5.2-buzzad3.50-buzzscreen4.44) 참조

## [5.1.0] - 2023-11-30
* [UPDATE] 인터스티셜 지면의 디자인을 업데이트하여 닫기 버튼의 위치가 변경되었습니다.
* [UPDATE] 연동 과정 중에 문제가 발생하여 서비스 제공에 문제가 생길 때 전달되는 UNKNOWN 오류 코드에 대한 화면을 추가했습니다.
* [FIX] 액티비티가 시스템에 의해 재생성되는 시점에 버즈배너가 노출되지 않는 문제 해결
* [FIX] 삼성 갤럭시 폴드 기종에서 팝 추가 유도 모달의 종료 버튼이 제대로 보이지 않는 문제 해결
* 자세한 사항은 [릴리스 뉴스](https://docs.buzzvil.com/docs/release-news/android/buzzvil5.1-buzzad3.49-buzzscreen4.43) 참조

## [5.0.0] - 2023-11-02
* [NEW] 수익화와 인게이지먼트를 동시에, 버즈베네핏(Buzzvil SDK v5) 출시
* 자세한 사항은 [릴리스 뉴스](https://docs.buzzvil.com/docs/release-news/android/buzzvil5.0-buzzad3.47-buzzscreen4.41) 참조
