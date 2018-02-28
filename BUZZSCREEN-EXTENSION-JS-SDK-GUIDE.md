# Buzzscreen Extension JS SDK 가이드
> [English Version Link](BUZZSCREEN-EXTENSION-JS-SDK-GUIDE-EN.md)

* 개인화된 콘텐츠와 광고를 안드로이드 잠금화면에서 보여주는 [버즈스크린 SDK](https://github.com/Buzzvil/buzzscreen-sdk-publisher)의 동작을 돕는 익스텐션 SDK입니다.
* 버즈스크린의 개인화를 위해서는 유저 정보가 필요하며, 퍼블리셔의 웹사이트에서 버즈스크린으로 유저 정보를 전달하기 위해서는 이 SDK를 사용합니다.
* 이 가이드는 퍼블리셔의 사이트에 이 SDK를 어떻게 적용할 수 있는지 설명합니다.


## 로그인 및 포인트 적립 요청 흐름
![Task Flow](buzzscreen-extension-js-sdk-flow.png)

## 이 SDK의 역할

'Buzzscreen Extension JS SDK'는 퍼블리셔의 사이트와 Buzzscreen White Label 안드로이드 어플리케이션 사이에 다리를 놓아주는 역할을 합니다.

## 로그인 체크 페이지

사용자가 로그인한 상태인지 아닌지 체크하기 위해 필요한 페이지입니다. 이 페이지로 접근하면 로그인한 유저와 로그인하지 않은 유저는 다른 UI의 화면을 보아야 합니다. 이 때, 로그인이 되어 있는 경우와 로그인이 되어 있지 않은 경우에 다른 함수를 호출해서 로그인 여부를 구분할 수 있게 합니다.

* 로그인 하지 않은 유저인 경우: 로그인 버튼 표시, '로그인 상태가 아님'을 Buzzscreen에 알림
* 로그인한 유저인 경우: 로그인 후 화면 표시, '로그인 되어 있음'을 Buzzscreen에 알림

## 적용 방법

'로그인 체크 페이지'에 이 SDK를 삽입합니다.
```html
<script src="buzzscreen-extension-0.0.1.js"></script>
```

### '로그인 상태가 아님'을 알리기

다음 자바스크립트 코드를 호출합니다.
```html
<script>
BuzzScreen.notifyNotSignedIn();
</script>
```

### '로그인 되어 있음'을 알리기

다음 자바스크립트 코드를 호출합니다.
```html
<script>
BuzzScreen.notifySignedIn('userId');
</script>
```

또한, 다음과 같이 사용자의 출생년도와 성별을 전달해서 타게팅 정보를 설정할 수 있습니다.
> 주의 : 타게팅 정보를 설정하지 않는 경우 타게팅이 설정된 광고가 보이지 않으므로 유저가 볼 수 있는 전체 광고의 수량이 줄어들게 됩니다.
```html
<script>
BuzzScreen.notifySignedIn('userId', 1990, BuzzScreen.GENDER_MALE);
BuzzScreen.notifySignedIn('userId', 1990, BuzzScreen.GENDER_FEMALE);
</script>
```
각 인자의 타입은 순서대로 `string`, `number`, `string`입니다. 인자를 잘못 설정한 경우 `console` 창에 에러 로그가 남습니다. 인자가 잘못 설정되지 않도록 주의해서 적용해 주시기 바랍니다.

## SDK 설정 편의를 위한 기능
### BuzzScreen Webview에서 페이지를 로드했는지 확인
다음 api는 BuzzScreen Webview에서 페이지를 로드한 경우 `true`, 아니면 `false`를 리턴합니다.
```html
<script>
const result = BuzzScreen.isBuzzScreenWebView();
</script>
```

### 상세한 로그 확인하기
verbose 옵션을 `true`로 설정하면 `console.info` 레벨의 로그도 확인할 수 있습니다.
```html
<script>
BuzzScreen.setVerbose(true);
BuzzScreen.notifySignedIn('userId', 1990, BuzzScreen.GENDER_MALE);
</script>
```
