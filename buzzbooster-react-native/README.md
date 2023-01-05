## BuzzBooster SDK for React Native

* [개발 가이드](https://buzzvil.atlassian.net/wiki/spaces/BDG/pages/2953019933/BuzzBooster+React+Native+SDK)

## Quick Start
```sh
npm install .
cd ios
pod install

npx react-native run-android
npx react-native run-ios
```

### 오픈 소스 라이센스 고지
- 이 소프트웨어가 사용하는 오픈 소스 소프트웨어의 라이센스는 "오픈 소스 라이센스 고지 페이지 ([원본 파일](docs/3rd_party_licenses.html)|[렌더링 버전](https://htmlpreview.github.io/?https://github.com/Buzzvil/buzzscreen-sdk-publisher/blob/master/docs/3rd_party_licenses.html))"에서 확인할 수 있다.

## [0.2.0] - 2022-09-01
> * [NEW] BuzzBooster React Native SDK 릴리즈
> ### [0.2.1] - 2022-09-02
> * [FIX] BuzzBooster 초기화 시, iOS에서 notification 권한 요청
> ### [0.2.2] - 2022-09-13
> * [FIX] Android Device의 캠페인 리스트 페이지에서 간혹 발생하는 크래시를 해결했습니다.
## [0.3.0] - 2022-09-21
> * [NEW] 친구초대 캠페인이 추가됐습니다.
> ### [0.3.1] - 2022-09-26
> * [FIX] 진행 중인 캠페인이 없을 때 문구 오류를 수정했습니다.
## [1.11.0] - 2022-10-05
> * [NEW] 유저를 이벤트 참여 페이지로 이동시킬 수 있는 커스텀 캠페인이 추가됐습니다.
> * [UPDATE] 유저 행동 분석 이벤트 처리 성능이 향상됐습니다.
> ### [1.11.1] - 2022-10-05
> * [UPDATE] 출석체크, 친구초대 캠페인 상단 마진을 조정했습니다.
> * [FIX] iOS에서 친구초대 페이지 UI 컴포넌트가 클릭되지 않던 문제를 수정했습니다.
> ### [1.11.2] - 2022-10-07
> * [UPDATE] iOS에서 캠페인과 상태바 사이의 구분선 UI를 개선했습니다.
> ### [1.11.3] - 2022-10-30
> * [UPDATE] 포인트 관련 UI/UX를 개선했습니다.
> * [UPDATE] 캠페인 관련 유저 행동 분석 이벤트를 저장합니다.
> ### [1.11.4] - 2022-10-27
> * [FIX] 일부 디바이스에서 친구초대 캠페인을 로딩할 수 없는 문제를 수정했습니다.
> * [FIX] 간헐적으로 발생하는 크래시를 해결했습니다.
> ### [1.11.5] - 2022-11-16
> * [NEW] 친구초대 공유 기능을 구현했습니다.
> * [FIX] Android에서 친구초대 '인증' 시 키보드를 내리게 수정했습니다.
> * [FIX] Android에서 포인트 플러그인을 사용 시 캠페인 리스트로 이동하게 수정했습니다.
> * [UPDATE] UI/UX를 개선했습니다.
> ### [1.11.6] - 2022-11-30
> * [FIX] iOS에서 캠페인에 설정된 타임존에 따라 시간을 올바르게 렌더링하지 않던 문제를 해결했습니다.
> * [FIX] 신규 캠페인을 접근할 경우 업데이트를 안내합니다.
## [2.0.0] - 2022-01-02
> * [NEW] 스탬프 캠페인이 추가됐습니다.
> * [NEW] 마케팅 수신 동의 유도 캠페인이 추가됐습니다.
> * [NEW] 네이버 포인트 전환 플러그인이 추가됐습니다.
> * [UPDATE] 일부 SDK API를 더 명확하게 수정했습니다.