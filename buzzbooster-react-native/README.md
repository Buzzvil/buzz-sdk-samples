## BuzzBooster SDK for React Native

* [연동 가이드](https://buzzvilwiki.notion.site/React-Native-SDK-afb7be1b3dd149898f983d5ef1a5dcc6)

## Sample App
Sample App에서는 버즈부스터의 다양한 기능을 체험하고, 개발 예제를 확인할 수 있습니다.
진행 중인 캠페인으로는 출석체크, 친구초대, 마케팅 정보 수신동의, 좋아요/댓글/게시글 스탬프 캠페인이 있습니다.
`HomePage.js`에서 로그인, 캠페인 목록 진입, 출석체크 캠페인 진입, 인앱 메세지 노출을 확인할 수 있습니다.
`EventView.js`에서 좋아요, 댓글, 게시글 작성시 이벤트를 전송하고, 캠페인 상세 페이지에서 스탬프가 쌓이는 것을 확인할 수 있습니다.
`App.js` 파일을 열어서 `androidAppKey`와 `iosAppKey`를 원하는 app key로 변경할 수 있습니다.

## Quick Start
```sh
npm install .
cd ios
pod install

npx react-native run-android
npx react-native run-ios
```

### 오픈 소스 라이센스 고지
- 이 소프트웨어가 사용하는 오픈 소스 소프트웨어의 라이센스는 "오픈 소스 라이센스 고지 페이지 ([원본 파일](../3rd_party_licenses.html)|[렌더링 버전](https://htmlpreview.github.io/?https://github.com/Buzzvil/buzzscreen-sdk-publisher/blob/master/docs/3rd_party_licenses.html))"에서 확인할 수 있다.
