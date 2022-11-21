## BuzzBooster SDK for Flutter

* [개발 가이드](https://buzzvil.atlassian.net/wiki/spaces/BDG/pages/2885845036/BuzzBooster+Flutter+SDK)

## Quick Start
```sh
flutter run
```

### 오픈 소스 라이센스 고지
- 이 소프트웨어가 사용하는 오픈 소스 소프트웨어의 라이센스는 "오픈 소스 라이센스 고지 페이지 ([원본 파일](docs/3rd_party_licenses.html)|[렌더링 버전](https://htmlpreview.github.io/?https://github.com/Buzzvil/buzzscreen-sdk-publisher/blob/master/docs/3rd_party_licenses.html))"에서 확인할 수 있다.


## 0.0.1
* [NEW] Campaign Floating Action Button으로 사용자를 캠페인으로 유입시킬 수 있습니다.

## 0.0.2
* [NEW] Campaign Entry Point로 하위 위젯을 추가하여 사용자를 캠페인으로 유입시킬 수 있습니다.
* [FIX] Campaign Floating Action Button이 iOS에서 Fullscreen을 차지하는 문제를 해결했습니다.

## 0.0.3
* [NEW] BuzzBooster 서버로 사용자 이벤트를 전송하는 기능을 추가했습니다.
* [NEW] 사용자를 캠페인으로 유입시킬 수 있는 인앱 메시지 기능을 추가했습니다.

## 0.0.4
* [UPDATE] SDK 초기화 시 플랫폼 별 AppKey를 받도록 변경되었습니다.

## 0.1.0
* [FIX] Campaign List 페이지의 배경색 관련 UX를 개선했습니다.
* [NEW] Campaign 페이지로 이동할 수 있는 API를 추가했습니다.

## 0.1.1
* [FIX] Dart 지원 버전을 Flutter SDK 지원과 동기화합니다.

## 0.1.2
* [FIX] dart 2.17 이상에서 사용 가능한 `super`키워드를 제거합니다.

## 0.1.3
* [FIX] Android Device에서 인앱 메시지의 이미지를 가운데로 조정했습니다.
* [FIX] Android Device에서 앱 테마를 투명으로 한 앱에서 캠페인 페이지에 홈 화면이 나타나는 문제를 해결했습니다.
* [FIX] Android Device에서 일부 화면에서 출석체크 캠페인 캘린더의 글자가 전부 표현되지 않는 문제 해결했습니다.
* [NEW] Android Device에서 출석체크 외의 캠페인 참여로 리워드 지급 시 notification을 렌더링합니다.
* [FIX] iOS Device에서 캠페인 페이지의 배경이 늦게 렌더링되는 문제를 해결했습니다.
* [FIX] iOS Device에서 `showCampaign()` 메소드 호출 시 간혹 캠페인 페이지로 이동되지 않는 문제를 해결했습니다.
* [FIX] iOS Device에서 `setUserId(null)` 메소드 호출 시 크래시가 발생하는 문제를 해결했습니다.

## 0.2.0
* [NEW] Point Plugin 기능을 추가했습니다.

## 0.2.1
* [NEW] 특정 캠페인으로 이동하는 `showSpecificCampaign(CampaignType)` 메소드를 추가했습니다.
* [FIX] 인앱 메시지의 아이템을 클릭하고 캠페인을 닫을 경우, 인앱 메시지를 종료합니다.

## 0.2.2
* [FIX] Android Device의 캠페인 리스트 페이지에서 간혹 발생하는 크래시를 해결했습니다.

## 0.3.0
* [NEW] 친구초대 캠페인이 추가됐습니다.

## 1.11.0
* [NEW] 유저를 이벤트 참여 페이지로 이동시킬 수 있는 커스텀 캠페인이 추가됐습니다.
* [UPDATE] 유저 행동 분석 이벤트 처리 성능이 향상됐습니다.

## 1.11.1
* [FIX] 친구초대 버튼이 정상 동작하지 않는 문제를 해결했습니다.

## 1.11.2
* [UPDATE] 캠페인 UI/UX를 개선했습니다.
* [UPDATE] 캠페인 관련 유저 행동 분석 이벤트를 저장합니다.

## 1.11.3
* [FIX] 일부 디바이스에서 친구초대 캠페인을 로딩할 수 없는 문제를 수정했습니다.
* [FIX] 간헐적으로 발생하는 크래시를 해결했습니다.

## 1.11.4
* [NEW] 친구초대 공유 기능을 구현했습니다.
* [FIX] Android에서 친구초대 '인증' 시 키보드를 내리게 수정했습니다.
* [FIX] Android에서 포인트 플러그인을 사용 시 캠페인 리스트로 이동하게 수정했습니다.
* [UPDATE] UI/UX를 개선했습니다.
