## BuzzBooster SDK for Android

* [개발 가이드](https://buzzvil.atlassian.net/wiki/spaces/BDG/pages/2733572224/BuzzBooster+Android+SDK)

### 오픈 소스 라이센스 고지
- 이 소프트웨어가 사용하는 오픈 소스 소프트웨어의 라이센스는 "오픈 소스 라이센스 고지 페이지 ([원본 파일](docs/3rd_party_licenses.html)|[렌더링 버전](https://htmlpreview.github.io/?https://github.com/Buzzvil/buzzscreen-sdk-publisher/blob/master/docs/3rd_party_licenses.html))"에서 확인할 수 있다.

## [1.0.0] - 2022-03-28
> ### [1.0.1] - 2022-03-31
> * [FIX] BuzzAdBenefit과 동시에 사용하는 경우 하위 모듈 버전 충돌 문제 해결
> ### [1.0.2] - 2022-03-31
> * [FIX] Target Sdk Version 30 지원
> ### [1.0.3] - 2022-04-05
> * [UPDATE] 이벤트 발생 이후 리워드 적립 메시지 수신까지의 시간이 단축 
> ### [1.0.4] - 2022-04-07
> * [FIX] 네트워크가 끊긴 상황에서 네트워크 요청 실패 시 무한히 재시도하던 문제 해결
## [1.3.0] - 2022-06-20
> * [NEW] 출석체크 캠페인 지원
> * [NEW] Floating Action Button으로 캠페인 진입 지점 구현 가능
> * [NEW] Custom Entry View로 캠페인 진입 지점 구현 가능
> ### [1.3.1] - 2022-06-23
> * [UPDATE] 진행 중인 캠페인이 없을 때 이를 안내하기 위한 메시지 추가
## [1.4.0] - 2022-06-29
> * [NEW] 캠페인 홍보를 위한 인앱 메시지 기능 추가
> ### [1.4.1] - 2022-07-06
> * [UPDATE] 캠페인 진입을 위한 method 추가
> > ### [1.4.2] - 2022-07-13
> * [FIX] 인앱 메시지의 이미지를 가운데로 조정
> * [FIX] Android SDK 19 이하의 앱 테마를 투명으로 설정한 앱에서 일부 화면의 배경이 홈 화면으로 나타나는 문제 해결 
> > ### [1.4.3] - 2022-07-15
> * [FIX] 일부 화면에서 출석체크 캠페인 캘린더의 글자가 전부 표현되지 않는 문제 해결
> > ### [1.4.4] - 2022-07-18
> * [UPDATE] 초기화 함수 호출 치 `notificationPendingActivityClass`를 설정하지 않을 경우에도 notification 렌더링