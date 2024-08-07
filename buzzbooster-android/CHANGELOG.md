## [4.13.4] - 2024-07-09
> * [UPDATE] Android 14 대응

## [4.11.5] - 2024-06-04
> * [UPDATE] 유저 아이디 길이 제한 해제
> * [FIX] 출석체크 비밀리워드 레이아웃 버그 수정

## [4.9.2] - 2024-05-13
> * [UPDATE] 출석체크 이미지 높이 제한 해제

## [4.7.2] - 2023-03-14
> * [UPDATE] 유저 아이디 길이 제한을 40자에서 65자로 변경

## [4.7.0] - 2023-03-07
> * [UPDATE] 친구초대 캠페인에 최대 추천인 제한을 적용
> * [UPDATE] 출석체크 비밀 리워드 캠페인의 공유하기 버튼을 출석 여부에 따라 변경
> * [UPDATE] 네트워크 요청 최적화
> * [FIX] 특정 상황에서 동일한 캠페인 상세페이지가 백스택에 존재하는 현상 수정
> * [FIX] 일부 캠페인에서 유의사항이 나타나지 않는 현상 수정

## [4.5.0] - 2023-02-16
> * [FIX] Android 14를 target으로 설정할 경우 발생하는 크래시 수정
> * [FIX] 특정 기기에서 인앱 팝업의 글자가 잘려서 나타나는 현상 수정

## [4.3.0] - 2023-12-28
> * [UPDATE] 이벤트 전송 성능 개선
> * [FIX] 최초 설치시 캠페인 타입으로 이동시 상세페이지를 확인할 수 없는 현상 수정

## [4.2.0] - 2023-12-14
> * [UPDATE] 친구초대 코드 입력을 완료한 유저에게는 코드 입력 버튼 숨김
> * [FIX] 동일한 캠페인 상세페이지가 백스택에 존재하지 않도록 수정
> * [FIX] Android API level 25 이하에서 Foreground 헤드업 노티가 나타나지 않는 현상 수정
> * [FIX] MainActivity의 launchMode에 따라 notification click이 동작하지 않는 현상 수정

## [4.1.0] - 2023-11-30
> * [NEW] 다크모드 지원
> * [UPDATE] 리워드 영역 UX 개선
> * [UPDATE] 유저 이벤트 리스너 API 변경
> * [FIX] 출석체크 버튼 연타할 때 올바르지 않은 화면이 보이는 문제 수정
> * [FIX] 포인트 히스토리 페이지에서 로딩시 화면이 깜빡이는 문제 수정

## [3.1.10] - 2023-11-23
> * [UPDATE] 출석체크 캠페인 달력에 캠페인 시작일, 종료일 표시
> * [FIX] 긁는 복권 캠페인에서 이미지가 깨지는 현상 수정

## [3.1.8] - 2023-11-09
> * [NEW] 출석체크 캠페인 상세페이지에 띠배너, 공유 버튼 추가
> * [NEW] 출석체크 캠페인 달력에 예상 달성 미션 표시
> * [UPDATE] 네이버페이 브랜드 로고 변경
> * [FIX] 유저 로그인 호출 이전에 캠페인 상세페이지 실행시 발생하는 크래시 수정
> * [FIX] 절전모드에서 액티비티가 실행되지 않는 현상 수정

## [3.1.7] - 2023-10-26
> * [UPDATE] 홈, 인앱팝업에서 딥링크를 사용할 수 있도록 개선

## [3.1.5] - 2023-10-13
> * [UPDATE] BottomSheet UI/UX 개선
> * [UPDATE] 일부 아이콘 수정
> * [UPDATE] `showHome`이 플러그인 유무에 상관 없이 항상 Home으로 이동되게 수정
> * [UPDATE] 스탬프 캠페인 시작하기 버튼 추가
> * [FIX] 캘린더 디자인 수정
> * [FIX] 특정 Event가 너무 많이 처리되던 문제 수정
> * [FIX] SDK 예외 추적 로직 개선

## [3.1.3] - 2023-09-27
> * [FIX] Glide 라이브러리의 충돌 문제 해결

## [3.1.2] - 2023-09-15
> * [NEW] 출석체크 캠페인 과거 기록 조회 기능 추가
> * [UPDATE] 40자 이상으로 길거나, 이메일로 된 User ID를 사용할 수 없도록 변경
> * [UPDATE] Notification 권한 획득 로직 제거
> * [FIX] 캠페인 상세페이지 이미지의 비율이 유지되지 않는 문제 수정
> * [FIX] Picasso 라이브러리의 충돌 문제 해결
> * [FIX] 캠페인 상세페이지에서 발생하는 크래시 수정
> * [FIX] 잘못된 딥링크를 사용할 경우 발생하는 크래시 수정

## [3.1.0] - 2023-08-17
> * [NEW] UI/UX가 변경된 친구초대 캠페인 추가
> * [FIX] 포인트 히스토리 UI 개선
> * [FIX] InAppMessageActivity의 launchMode를 singleTask로 수정

## [3.0.0] - 2023-07-05
> * [UPDATE] `sendEvent(name, value)` 및 `BuzzBoosterUser.Builder:addProperty(key, value)`에서 value 타입으로 number, string, bool이 가능하게 변경
> * [UPDATE] `showCampaign` 메소드를 `showHome`으로 변경
> * [UPDATE] 캠페인 이동에 룰렛과 긁는 복권 지원
> * [UPDATE] `BuzzBoosterUser.Builder`의 `setProperty`를 `addProperty`로 변경
> * [UPDATE] Custom Campaign 제거
> * [FIX] 플러그인 이미지 해상도 개선

## [2.3.7] - 2023-06-22
> * [FIX] 친구초대를 여러번 참여하는 문제 수정

## [2.3.6] - 2023-06-15
> * [NEW] Web Campaign 추가

## [2.3.5] - 2023-06-14
> * [UPDATE] 룰렛에 더 많은 정보를 포함할 수 있게 UI 수정

## [2.3.4] - 2023-06-01
> * [FIX] Toolbar 관련 UI 수정

## [2.3.3] - 2023-06-24
> * [UPDATE] Push 페이지 이동 기능 개선

## [2.3.2] - 2023-05-19
> * [FIX] 다크 테마에서 UI가 일부 보이지 않던 문제 해결

## [2.3.1] - 2023-05-18
> * [FIX] Campaign Fragment에서 발생하는 메모리 누수 제거
> * [UPDATE] InAppMessage 전환 애니메이션 수정

## [2.3.0] - 2023-05-28
> * [NEW] Dash에서 지정한 PageId로 이동하는 기능 추가
> * [FIX] Android에서 Home을 Pull to Refresh할 경우 리워드도 갱신되게 수정
> * [FIX] 간헐적으로 발생하는 InAppMessage 타이밍 이슈 해결

## [2.2.0] - 2023-05-03
> * [NEW] SDK에서 발생하는 유저 행동 이벤트를 확인할 수 있는 리스너 추가
> * [UPDATE] 포인트 목록 조회 개선
> * [FIX] 인 앱 메시지 및 네이버페이 포인트 화면 렌더링 시 발생하는 타이밍 이슈 해결.

## [2.1.1] - 2023-04-11
> * [FIX] 기프티콘 플러그인만 활성화된 상태에서 캠페인 리스트 이동 동작 수정

## [2.1.0] - 2023-04-06
> * [NEW] 기프티콘 기능 추가
> * [UPDATE] Event 전송 시 event Value로 지원되는 타입 추가
> * [UPDATE] 캠페인 리스트 UI 개선
> * [FIX] 긁는 복권 이미지 개선

## [2.0.5] - 2023-03-03
> * [NEW] 긁는 복권 캠페인 추가
> * [NEW] 룰렛 캠페인 추가
> * [NEW] 후지급 캠페인 추가
> * [UPDATE] 현재 버전에서 지원하지 않는 캠페인이 있는 경우 앱 마켓으로 이동
> * [UPDATE] Notification 클릭 시 기본으로 앱의 메인 액티비티로 이동
> * [FIX] 캠페인 리스트가 보이지 않는 버그 수정

## [2.0.3] - 2023-02-17
> * [UPDATE] Push 기능 개선
> * [UPDATE] Targert SDK Version을 33으로 조정
> * [UPDATE] 친구초대 누적 현황 UI 추가
> * [FIX] 마케팅 수신 동의 스크롤이 끝까지 되지 않는 현상 수정
> * [FIX] Notification 클릭 시 발생하는 메모리 누수 제거
> * [FIX] FCM으로 받은 Push Message 처리가 중복으로 되던 문제 해결
> * [FIX] FCM Notification 채널 설정

## [2.0.0] - 2022-01-02
> * [NEW] 스탬프 캠페인이 추가됐습니다.
> * [NEW] 마케팅 수신 동의 유도 캠페인이 추가됐습니다.
> * [NEW] 네이버 포인트 전환 플러그인이 추가됐습니다.
> * [UPDATE] 일부 SDK API를 더 명확하게 수정했습니다.

## [1.11.5] - 2022-11-22
> * [FIX] 신규 캠페인을 접근할 경우 업데이트를 안내

## [1.11.4] - 2022-11-16
> * [UPDATE] 친구초대 인증 시 에러가 발생할 경우 키보드를 내려 보일 수 있게 수정

## [1.11.3] - 2022-10-27
> * [UPDATE] 친구초대 인증 시 키보드를 내려 인증 완료를 유도하게 개선
> * [FIX] 포인트 플러그인을 사용할 경우 항상 캠페인 리스트 페이지로 이동하게 수정

## [1.11.2] - 2022-10-21
> * [NEW] 친구초대 시스템 공유 기능 추가
> * [UPDATE] 친구초대 캠페인 관련 UI 개선
> * [UPDATE] 포인트 관련 UI 개선
> * [FIX] 로그인하지 않은 상태에서 `showCampaign` 호출 시 로그인이 필요하다는 에러 페이지 렌더링

## [1.11.1] - 2022-10-07
> * [UPDATE] 캠페인 페이지 방문과 관련한 유저 행동 분석 이벤트를 추가했습니다.
> * [UPDATE] 출석체크, 친구초대 캠페인 UI를 개선했습니다.

## [1.11.0] - 2022-10-05
> * [NEW] 캠페인을 참여할 수 있는 페이지로 이동시키는 커스텀 캠페인을 추가했습니다.

## [1.10.1] - 2022-09-26
> * [FIX] 진행 중인 캠페인이 없을 때 문구를 사용자가 이해하기 쉽게 수정합니다.

## [1.10.0] - 2022-09-21
> * [NEW] 친구초대 캠페인 추가

## [1.6.1] - 2022-07-28
> * [FIX] 인앱 메시지를 클릭하여 캠페인을 이동 후 캠페인을 닫았을 때, 인앱 메시지가 닫히도록 수정

## [1.6.0] - 2022-07-26
> * [NEW] 포인트 플러그인 추가

## [1.4.4] - 2022-07-18
> * [UPDATE] 초기화 함수 호출 치 `notificationPendingActivityClass`를 설정하지 않을 경우에도 notification 렌더링

## [1.4.3] - 2022-07-15
> * [FIX] 일부 화면에서 출석체크 캠페인 캘린더의 글자가 전부 표현되지 않는 문제 해결

## [1.4.2] - 2022-07-13
> * [FIX] 인앱 메시지의 이미지를 가운데로 조정
> * [FIX] Android SDK 19 이하의 앱 테마를 투명으로 설정한 앱에서 일부 화면의 배경이 홈 화면으로 나타나는 문제 해결

## [1.4.1] - 2022-07-06
> * [UPDATE] 캠페인 진입을 위한 method 추가

## [1.4.0] - 2022-06-29
> * [NEW] 캠페인 홍보를 위한 인앱 메시지 기능 추가

## [1.3.1] - 2022-06-23
> * [UPDATE] 진행 중인 캠페인이 없을 때 이를 안내하기 위한 메시지 추가

## [1.3.0] - 2022-06-20
> * [NEW] 출석체크 캠페인 지원
> * [NEW] Floating Action Button으로 캠페인 진입 지점 구현 가능
> * [NEW] Custom Entry View로 캠페인 진입 지점 구현 가능

## [1.0.4] - 2022-04-07
> * [FIX] 네트워크가 끊긴 상황에서 네트워크 요청 실패 시 무한히 재시도하던 문제 해결

## [1.0.3] - 2022-04-05
> * [UPDATE] 이벤트 발생 이후 리워드 적립 메시지 수신까지의 시간이 단축

## [1.0.2] - 2022-03-31
> * [FIX] Target Sdk Version 30 지원

## [1.0.1] - 2022-03-31
> * [FIX] BuzzAdBenefit과 동시에 사용하는 경우 하위 모듈 버전 충돌 문제 해결

## [1.0.0] - 2022-03-28
> * Initial Release
