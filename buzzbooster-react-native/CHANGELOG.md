## [3.1.10] - 2023-11-23
> * [UPDATE] 출석체크 캠페인 달력에 캠페인 시작일, 종료일 표시
> * [FIX] Android 긁는 복권 캠페인에서 이미지가 깨지는 현상 수정
> * [FIX] iOS 룰렛 캠페인 UX 개선

## [3.1.2] - 2023-11-09
> * [NEW] 출석체크 캠페인 상세페이지에 띠배너, 공유 버튼 추가
> * [NEW] 출석체크 캠페인 달력에 예상 달성 미션 표시
> * [UPDATE] Android 홈, 인앱팝업에서 딥링크를 사용할 수 있도록 개선
> * [UPDATE] 네이버페이 브랜드 로고 변경
> * [UPDATE] BottomSheet UI/UX 개선
> * [UPDATE] 일부 아이콘 수정
> * [UPDATE] `showHome`이 플러그인 유무에 상관 없이 항상 Home으로 이동되게 수정
> * [UPDATE] 스탬프 캠페인 시작하기 버튼 추가
> * [FIX] 캘린더 디자인 수정
> * [FIX] Android 특정 Event가 너무 많이 처리되던 문제 수정
> * [FIX] Android SDK 예외 추적 로직 개선
> * [FIX] Android 유저 로그인 호출 이전에 캠페인 상세페이지 실행시 발생하는 크래시 수정
> * [FIX] Android 절전모드에서 액티비티가 실행되지 않는 현상 수정
> * [FIX] iOS 긁는 복권 참여 시 간헐적으로 한번 시도에 여러번 참여되는 문제 수정
> * [FIX] iOS UINavigationBar 다크 테마 문제 수정
> * [FIX] iOS 캠페인에서 에러 발생 시 에러뷰만 보이게 수정
> * [FIX] Xcode15에서 발생하는 warning 제거
> * [FIX] iOS에서 간헐적으로 Buzz-UUID가 의도치 않게 여러번 생성되는 문제 수정

## [3.1.1] - 2023-09-15
> * [NEW] 출석체크 캠페인 과거 기록 조회 기능 추가
> * [NEW] iOS Swift Documentation 추가
> * [UPDATE] 40자 이상으로 길거나, 이메일로 된 User ID를 사용할 수 없도록 변경
> * [UPDATE] Android Notification 권한 획득 로직 제거
> * [FIX] iOS 바텀시트 페이지가 이동시에도 계속 나타나는 버그 수정
> * [FIX] iOS Floating action button이 깜빡거리는 현상 수정
> * [FIX] iOS 캠페인 페이지 로딩이 첫 번째 진입시 되지 않는 현상 수정
> * [FIX] Android 캠페인 상세페이지 이미지의 비율이 유지되지 않는 문제 수정
> * [FIX] Android Picasso 라이브러리의 충돌 문제 해결
> * [FIX] Android 캠페인 상세페이지에서 발생하는 크래시 수정
> * [FIX] Android 잘못된 딥링크를 사용할 경우 발생하는 크래시 수정

## [3.1.0] - 2023-08-17
> * [NEW] UI/UX가 변경된 친구초대 캠페인 추가
> * [UPDATE] iOS bitcode 옵션 제거
> * [UPDATE] iOS SDWebImage 관련 종속성을 제거
> * [FIX] Android의 포인트 히스토리 UI 개선
> * [FIX] Android의 InAppMessageActivity의 launchMode를 singleTask로 수정

## [3.0.0] - 2023-07-05
> * [UPDATE] `sendEvent(name, value)` 및 `UserBuilder.addProperty(key, value)`에서 value 타입으로 number, string, bool이 가능하게 변경
> * [UPDATE] `showCampaign()` 메소드를 `showHome()`으로 변경
> * [UPDATE] `showSpecificCampaign(type:)` 메소드를 `showCampaignWithType(:type)`으로 변경
> * [UPDATE] 캠페인 이동에 룰렛과 긁는 복권 지원
> * [UPDATE] Custom Campaign 제거
> * [UPDATE] iOS SDK가 BuzzBooster, BuzzBoosterSwift, BuzzMutator에서 BuzzBoosterSDK로 간소화
> * [FIX] 플러그인 이미지 해상도 개선

## [2.3.1] - 2023-06-22
> * [NEW] Web Campaign 지원
> * [FIX] 친구초대 인증 버튼 UserEvent 오탈자 수정
> * [UPDATE] 룰렛에 더 많은 정보를 포함할 수 있게 UI 수정
> * [FIX] 간헐적으로 iOS Roulette Campaign에서 발생하는 크래시 해결
> * [FIX] 간헐적으로 iOS 친구초대 코드 입력 버튼 반응이 느린 문제 해결
> * [FIX] Android에서 일부 UI가 다크 테마에서 잘 안보이던 문제 수정

## [2.3.0] - 2023-05-22
> * [NEW] Dash에서 지정한 PageId로 이동하는 기능 추가
> * [FIX] iOS에서 로그인되지 않은 상태에서 캠페인으로 이동할 경우 안내 메시지가 나오지 않던 문제 해결
> * [FIX] Android에서 Home을 Pull to Refresh할 경우 리워드도 갱신되게 수정
> * [FIX] 간헐적으로 발생하는 InAppMessage 타이밍 이슈 해결

## [2.2.0] - 2023-05-03
> * [NEW] SDK에서 발생하는 유저 행동 이벤트를 확인할 수 있는 리스너 추가
> * [UPDATE] 포인트 목록 조회 개선
> * [FIX] 인 앱 메시지 및 네이버페이 포인트 화면 렌더링 시 발생하는 타이밍 이슈 해결.

## [2.1.0] - 2023-04-13
> * [NEW] 기프티콘 기능 추가
> * [UPDATE] Event 전송 시 event Value로 지원되는 타입 추가
> * [UPDATE] 캠페인 리스트 UI 개선
> * [UPDATE] SDK 사이즈 최적화
> * [FIX] Android에서 긁는 복권 이미지 개선
> * [FIX] Android에서 인 앱 메시지 및 네이버페이 포인트 화면 렌더링 시 발생하는 타이밍 이슈 해결.
> * [FIX] iOS에서 네트워크 헤더에 빌드 넘버가 포함되지 않던 문제 해결
> * [FIX] iOS에서 특정 디바이스의 일부 캠페인에서 앱이 멈추던 문제 해결
> * [FIX] iOS에서 출석체크 캠페인 연속출석 일자에 대한 컬러 수정
> * [FIX] iOS에서 마케팅 수신 동의 버튼 오타 수정
> * [FIX] iOS에서 Notification 클릭 시 이동 로직이 일부 ViewController 사용 방식에서 동작하지 않던 문제 해결
> * [FIX] 기프티콘 플러그인만 활성화된 상태에서 캠페인 리스트 이동 동작 수정
> * [FIX] 마케팅 수신 동의 기본 값이 잘못 전송되던 문제 해결

## [2.0.8] - 2023-03-27
> * [NEW] 긁는 복권 캠페인 추가
> * [NEW] 룰렛 캠페인 추가
> * [NEW] 후지급 캠페인 추가
> * [UPDATE] 현재 버전에서 지원하지 않는 캠페인이 있는 경우 앱 마켓으로 이동
> * [UPDATE] Notification 클릭 시 기본으로 앱의 메인 액티비티로 이동
> * [UPDATE] iOS 사이즈 최적화
> * [FIX] 캠페인 리스트가 보이지 않는 현상 수정
> * [FIX] iOS에서 이벤트가 서버에 저장되지 않는 현상 수정

## [2.0.1] - 2023-02-17
> * [UPDATE] Push 기능 개선
> * [UPDATE] Android Targert SDK Version을 33으로 조정
> * [UPDATE] iOS Deployment Target 11로 조정
> * [UPDATE] 친구초대 누적 현황 UI 추가
> * [FIX] Network Crash Log 제거
> * [FIX] iOS에서 출석체크 리워드 지급 메시지 버튼 텍스트 오류 수정
> * [FIX] Android에서 마케팅 수신 동의 스크롤이 끝까지 되지 않는 현상 수정

## [2.0.0] - 2022-01-02
> * [NEW] 스탬프 캠페인이 추가됐습니다.
> * [NEW] 마케팅 수신 동의 유도 캠페인이 추가됐습니다.
> * [NEW] 네이버 포인트 전환 플러그인이 추가됐습니다.
> * [UPDATE] 일부 SDK API를 더 명확하게 수정했습니다.

## [1.11.6] - 2022-11-30
> * [FIX] iOS에서 캠페인에 설정된 타임존에 따라 시간을 올바르게 렌더링하지 않던 문제를 해결했습니다.
> * [FIX] 신규 캠페인을 접근할 경우 업데이트를 안내합니다.

## [1.11.5] - 2022-11-16
> * [NEW] 친구초대 공유 기능을 구현했습니다.
> * [FIX] Android에서 친구초대 '인증' 시 키보드를 내리게 수정했습니다.
> * [FIX] Android에서 포인트 플러그인을 사용 시 캠페인 리스트로 이동하게 수정했습니다.
> * [UPDATE] UI/UX를 개선했습니다.

## [1.11.4] - 2022-10-27
> * [FIX] 일부 디바이스에서 친구초대 캠페인을 로딩할 수 없는 문제를 수정했습니다.
> * [FIX] 간헐적으로 발생하는 크래시를 해결했습니다.

## [1.11.3] - 2022-10-30
> * [UPDATE] 포인트 관련 UI/UX를 개선했습니다.
> * [UPDATE] 캠페인 관련 유저 행동 분석 이벤트를 저장합니다.

## [1.11.2] - 2022-10-07
> * [UPDATE] iOS에서 캠페인과 상태바 사이의 구분선 UI를 개선했습니다.

## [1.11.1] - 2022-10-05
> * [UPDATE] 출석체크, 친구초대 캠페인 상단 마진을 조정했습니다.
> * [FIX] iOS에서 친구초대 페이지 UI 컴포넌트가 클릭되지 않던 문제를 수정했습니다.

## [1.11.0] - 2022-10-05
> * [NEW] 유저를 이벤트 참여 페이지로 이동시킬 수 있는 커스텀 캠페인이 추가됐습니다.
> * [UPDATE] 유저 행동 분석 이벤트 처리 성능이 향상됐습니다.

## [0.3.1] - 2022-09-26
> * [FIX] 진행 중인 캠페인이 없을 때 문구 오류를 수정했습니다.

## [0.3.0] - 2022-09-21
> * [NEW] 친구초대 캠페인이 추가됐습니다.

## [0.2.2] - 2022-09-13
> * [FIX] Android Device의 캠페인 리스트 페이지에서 간혹 발생하는 크래시를 해결했습니다.

## [0.2.1] - 2022-09-02
> * [FIX] BuzzBooster 초기화 시, iOS에서 notification 권한 요청

## [0.2.0] - 2022-09-01
> * [NEW] BuzzBooster React Native SDK 릴리즈
