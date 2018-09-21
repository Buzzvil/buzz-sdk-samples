# BuzzStore Integration Guide v1.0

버즈스토어를 연동하기 위한 통합 가이드. 연동 과정은 전체적으로 다음의 세 단계로 구성되어 있다.

1. UserToken API Implementation
2. SDK Integration
3. 기타 API Implementation

## 1. UserToken API Implementation
- 퍼블리셔의 유저가 버즈스토어를 이용할 때 주고받는 정보에 대한 보안을 강화하기 위해 버즈스토어는 User Token 을 이용한다.
- User Token 은 퍼블리셔 User Id 와 1:1 매칭되는 값으로, 버즈스토어 서버에서 생성하고 관리한다. 
- 버즈스토어 호출 시 전달되는 User Token 이 없거나 무효하면 버즈스토어 서버는 이를 비정상적 상황으로 인식하고 해당 호출을 차단한다.
- 퍼블리셔는 User Token을 요청하는 API(`UserToken API`)를 다음의 인터페이스에 맞게 구현해야 한다.
- `UserToken API` 는 크게 퍼블리셔 앱과 퍼블리셔 서버끼리의 통신과, 퍼블리셔 서버와 버즈스토어 서버끼리의 통신 총 2가지로 구성되어 있다.

#### UserToken API Interface OverView
User Token의 전체적인 전달 흐름을 그림으로 표현하면 다음과 같다.

![buzzstore user token](https://github.com/Buzzvil/buzzstore-sdk-publisher/blob/master/buzzstore.png)

- Request 주체 : Publisher App
- Response 주체 : BuzzStore Server
- Linker : Publisher Server
- Request 방향 : `Publisher App -> Publisher Server -> BuzzStore Server`
- Response 방향 : `BuzzStore Server -> Publisher Server -> Publisher App`
- API 호출 주체 : BuzzStore SDK(Token Validate check 실패 시)

User Token은 외부 유출을 원천 차단하기 위해 사전 등록된 퍼블리셔 서버에게만 Server-To-Server 방식으로 전달하게 되어 있다. 따라서 퍼블리셔 앱은 직접 버즈스토어 서버로의 토큰 요청이 불가하고 반드시 퍼블리셔 서버를 거쳐야 한다. 이 때, 퍼블리셔 앱에서 퍼블리셔 서버로의 Request는 곧바로 퍼블리셔 서버에서 버즈스토어 서버로의 Request로 이어져야 한다. 마찬가지로 버즈스토어 서버에서 퍼블리셔 서버로 돌아온 Response는 곧바로 퍼블리셔 서버에서 퍼블리셔 앱으로의 Response로 이어져야 한다.

> **주의 1** : 퍼블리셔 서버에서 유저 토큰을 별도로 저장하여 관리할 필요는 없다. 유저 토큰 요청의 시작은 언제나 퍼블리셔 앱에서 일어나기 때문에 퍼블리셔 서버는 버즈스토어 서버에서 받아온 유저 토큰을 단순히 퍼블리셔 앱으로 전달해주기만 하면 된다.

> **주의 2** : 구현된 UserToken API의 앱 내 호출은 BuzzStore SDK에서 제공하는 'UserToken 유효성 체크 인터페이스' 내의 OnNeedAPICall() 메소드 안에서만 일어나야 된다.(그림의 'Token Validate check' 참조) SDK에서 UserToken의 유효성을 체크하기 때문에 퍼블리셔 측에서 이 외에 별도로 API 호출을 위한 로직을 짤 필요는 없다.

#### Publisher App <-> Publisher Server(App 내 API 구현)
- 기존에 퍼블리셔 앱이 서버와 통신하던 방식을 이용해 퍼블리셔 서버에게 현재 유저의 정보에 맞는 User Token을 요청하는 API를 구현한다.
- 이 API는 보안상의 이유로 퍼블리셔쪽 인증이 이뤄진 채널을 통해서 통신해야 한다. e.g. 로그인
- 해당 API는 SDK가 제공하는 interface 인 `UserTokenListener` 의 `OnNeedAPICall()` 안에 호출되도록 구현한다.
- 호출 결과 받아온 User Token은 `UserTokenListener` 의 `OnNeedAPICall()` 에서 호출한 API의 Response가 온 시점에서 SDK의 `setUserToken()` 을 호출하여 전달되도록 구현한다.(2. SDK Integration 내 'UserToken 유효성 체크 interface 구현' 참조)

#### Publisher Server <-> BuzzStore server(Server-To-Server 연동)
- 이 연동을 하기 앞서 퍼블리셔 서버의 아이피 주소를 버즈스토어 서버에 `화이트 리스트`로 등록해야 한다. 화이트 리스트에 등록 될 아이피주소는 별도의 채널(e.g. 이메일)을 통해서 퍼블리셔가 전달한다.

###### 요청
- API 호출 방향 : 퍼블리셔 서버 -> 버즈스토어 서버
- method : `POST`
- url : `https://store-api.buzzvil.com/api/v1/users`
- Headers : 다음의 파라미터를 담아서 요청한다.
    - `X-BUZZVIL-APP-ID` : 사전에 발급한 퍼블리셔 앱에 부여 된 고유한 아이디.
    - `X-BUZZVIL-API-TOKEN` : 사전에 발급한 서버 투 서버 API 사용을 위한 고유한 API 토큰
- POST 필수 파라미터 : 퍼블리셔의 유저 식별자 `publisher_user_id`, 퍼블리셔의 유저의 국가코드 (ISO 2글자 포맷, 대문자) `country`
- Content-Type: application/x-www-form-urlencoded (아래의 요청 Body가 이런 포맷으로 전달되어야 함)

e.g.
```Body
{
    "publisher_user_id": 1270537,
    "country": "KR"
}
```

> **주의** : 버즈스토어는 퍼블리셔 유저 식별자를 기준으로 유저를 식별하므로 해당 값은 추후 바꿀 수 없다. 버즈스토어는 두개 이상의 서로 다른 유저 식별자를 서로 다른 유저로 인식한다. 따라서 퍼블리셔가 제공하는 유저 식별자는 절대 업데이트되지 않는 값을 사용해야 한다. 예를들어, 계정에 연동 된 이메일 주소와 같이 추후 변동될 여지가 있는 값 보다는 퍼블리셔 디비 내의 유저 식별자 값(e.g. Users 테이블의 pk 값)으로 사용하길 권장한다.

> **주의** : 유저의 국가코드가 ISO의 2글자 포맷의 코드가 아닌 경우 잘못된 국가라는 에러를 보냄

- 성공 시 JSON 포맷으로 `publisher_user_id`, `token`, `country` 를 리턴한다. HTTP 응답 상태 코드는 200 또는 201 이다. 

e.g.
```JSON
{
    "publisher_user_id": 1270537,
    "country": "KR",
    "token": "yYn05pKNlHpMdmBd2GeXU4tBdQtIENuFmFJ0MhBJkwBIzE2TXffLTA7bfWAmRSOc"
}
```
- 실패 시 JSON 포맷으로 `error_code`, `error_message` 를 리턴한다.

###### 실패 시 재시도에 관하여
버즈스토어 서버에 일시적 장애가 발생하여 이 API 를 통한 토큰 발급에 실패하는 경우 스토어 SDK 이용이 제한된다. 이 때, SDK내에서 제공하는 `UserTokenListener` 의 `OnNeedAPICall()` 의 구현이 완료되어 있으면 SDK가 자동으로 재시도를 한다.(2. SDK Integration 내 'UserToken 유효성 체크 interface 구현' 참조)


## 2. SDK Integration
- 버즈스토어를 안드로이드 어플리케이션에 연동하기 위한 라이브러리
- 안드로이드 버전 지원 : Android 4.0(API Level 14) 이상
- SDK는 버즈스토어 UI 호출 기능과 UserToken 유효성 체크 기능을 제공한다.
- 버즈스토어 UI는 모두 웹으로 이루어져 있으며, 필수 파라미터를 전달하여 웹뷰를 통해 호출한다.
- UserToken 유효성 체크는 Java interface로 제공하므로 아래의 가이드에 따라 Publisher 앱 내에서 직접 interface 의 구현이 필요하다.

#### 업데이트 내역
- 2016.08.08 로딩 UI 변경
    - AndroidManifest.xml 중 activity 설정하는 부분에서 theme 제거

#### 기본 설정

- [SDK 다운로드](https://github.com/Buzzvil/buzzstore-sdk-publisher/archive/master.zip) 후 압축해제하여 buzzstore-sdk/buzzstore.jar를 개발중인 안드로이드 어플리케이션에 라이브러리로 포함시킨다.
- 구글 플레이 서비스 라이브러리 설정 필요. [구글 플레이 서비스 라이브러리 설정 방법](https://developers.google.com/android/guides/setup)을 참고하여 직접 추가한다.

    > 안드로이드 스튜디오인 경우는 **build.gradle > dependencies**에 **compile 'com.google.android.gms:play-services-ads:7.5.0'**만 추가하면 된다.

- AndroidManifest.xml 에 다음과 같이 액티비티와 퍼미션을 추가한다.

```Xml
<manifest>
    ...
    <!-- permission for BuzzStore -->
    <uses-permission android:name="android.permission.INTERNET" />

    <!-- activity for BuzzStore -->
    <activity
            android:name="com.buzzvil.buzzstore.StoreActivity" />
    </application>
    ...
</manifest>
```

- Proguard 설정
Proguard 사용 시 다음 라인들을 Proguard 설정에 추가한다.
```
-keep class com.buzzvil.buzzstore.** {*;}
-keep interface com.buzzvil.buzzstore.** {*;}
```

#### 버즈스토어 UI 호출
- `BuzzStore.init(String appId, String userId, Context context)` : 초기화 함수로, 버즈스토어를 로드하려는 액티비티의 onCreate 에 호출한다.
    - `appId` : 버즈스토어에서 퍼블리셔 앱에게 부여하는 고유한 식별자이다. 연동 시작 시 지급되며 버즈스토어 어드민에서 확인할 수 있다.
    - `userId` : 퍼블리셔에서 관리하는 유저의 고유한 식별자이다. 버즈스토어는 이 정보를 그대로 받아 포인트 관리를 하게 된다.
    - `context` : 앱의 Context 를 파라미터로 전달한다.

- `BuzzStore.setUserTokenListener(UserTokenListener listener)` : 유저 토큰의 유효성을 검사하여 유효하지 않을 때 호출되는 리스너인 UserTokenListener 를 구현하는 함수이다.(구현해야 할 내용은 'UserToken 유효성 체크 interface 구현' 참조)

- `BuzzStore.loadStore(Activity activity)` : 버즈스토어 모바일 UI를 호출한다.

- `BuzzStore.loadStore(Activity activity, StoreType type)` : 버즈스토어 모바일 UI를 호출하면서 명시적으로 특정 타입의 탭을 먼저 띄우려 할 때 사용한다.
    
    **StoreType list**
    - `DEFAULT` : 스토어의 메인 페이지인 상품 페이지로 연결된다.
    - `COUPONS` : 쿠폰함으로 연결된다.
    - `POINTS` : 포인트 내역으로 연결된다.
    
    > **주의 1** : 반드시 `BuzzStore.init()`이 호출된 이후에 호출해야 한다.
    
    > **주의 2** : 위의 메소드처럼 탭을 명시하지 않으면 `StoreType.DEFAULT` 로 자동 설정된다. 즉, `BuzzStore.loadStore(activity)` 는 `BuzzStore.loadStore(activity, BuzzStore.StoreType.DEFAULT)` 와 동일하다.

#### UserToken 유효성 체크 interface 구현
제공하는 `UserTokenListener` 를 통해 `UserToken API` 호출이 필요할 때(OnNeedAPICall)와, UserToken 요청의 재시도에도 불구하고 지속적으로 유저 토큰 획득에 실패하여 유저가 결국 BuzzStore를 띄울 수 없을 때(OnFail)의 이벤트 처리를 할 수 있다.

interface의 구성은 다음과 같다.
```Java
public interface UserTokenListener {
    void OnNeedAPICall();
    void OnFail();
}
```

- `void OnNeedAPICall()` : UserToken이 유효하지 않을 때 호출된다. **이 메소드는 퍼블리셔의 `UserToken API` 를 호출하고 (1. UserToken API Implementation 참조) 이 API의 Response를 통해 전달받은 UserToken을 `setUserToken` 메소드를 통해 등록하도록 구현해야 한다**. SDK는 전달받은 UserToken 을 가지고 다시 버즈스토어 서버에서 UserToken의 유효성을 체크하여 스토어를 로드할 수 있는지 여부를 판단한다.
    - `void setUserToken(String userToken)` : API call이 성공했을 때, Response로 전달받은 UserToken을 String 형태로 전달한다. API call이 실패했을 때는 빈 스트링("")을 인자로 넣어 이 메소드를 호출해야 SDK에서 자동으로 재시도를 하게 된다.

> **주의** : OnNeedAPICall() 은 재시도만을 위한 것이 아니라 유저가 버즈스토어를 최초로 로드하여 UserToken을 생성하려 할 때에도 불리게 된다. 따라서 필수적으로 구현해야 한다.

- `void OnFail()` : SDK는 OnNeedAPICall() 을 통해 전달받은 UserToken을 가지고 BuzzStore Server로 유효성 체크 시도를 반복하는데, 최대 횟수 만큼 시도했으나 결국 유효한 UserToken을 발급받지 못해 최종 실패한 경우 이 메소드가 호출된다. 버즈스토어 서버 혹은 퍼블리셔 서버가 사고로 작동하지 않을 때 이러한 경우가 생길 수 있다. 유저에게 서버 에러로 인한 버즈스토어 접근 불가를 알리는 UI 처리를 구현하는 것이 권장된다.

##### 사용 예제
```Java
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * Initialize BuzzStore.
         * BuzzStore.init have to be called prior to BuzzStore.loadStore
         * @param appId : Unique key value to identify the publisher.
         * @param userId : user identifier used from publisher
         * @param this : Context
         */
        BuzzStore.init("appId", "userId", this);

        BuzzStore.setUserTokenListener(new BuzzStore.UserTokenListener() {
            @Override
            public void OnNeedAPICall() {
                /**
                 * 해당 함수가 호출되면 퍼블리셔 서버로의 API 콜을 통해 해당 유저의 유저토큰을 전달받아 setUserToken 메소드를 통해 등록해야 한다.
                 * 보안상의 이유로 해당 유저토큰은 Server-To-Server로만 제공되므로 퍼블리셔앱은 반드시 퍼블리셔 서버를 통해서 버즈스토어로 요청해야 한다.
                 *
                 * 주의 : 아래의 코드는 참고를 위한 Pseudo-code 로 실제로 이와 같은 메소드가 제공되지는 않는다.
                 */
                Request(
                        new ResponseListener() {
                            OnSuccess(Response response) {
                                String userToken = response.getString("user_token");
                                /**
                                 * 서버로부터 userToken을 전달받은 후에 아래의 메소드를 호출한다.
                                 */
                                BuzzStore.setUserToken(userToken);
                            }
                            OnError() {
                                /**
                                 * 서버 통신 실패 시 아래와 같이 빈 스트링으로 setUserToken을 다시 요청한다.
                                 */
                                BuzzStore.setUserToken("");
                            }
                        }
                );
            }

            @Override
            public void OnFail() {
                /**
                 * 최대 횟수 만큼 SDK 내부에서 Validation 시도를 하였으나 최종 실패한 경우 이 함수가 호출
                 된다.
                 * 버즈스토어 서버 혹은 퍼블리셔 서버가 작동하지 않을 때 이런 경우가 발생한다.
                 * 이 때, 퍼블리셔는 실패 UI 등을 유저에게 보이도록 처리하는 것이 권장된다.
                 * ex. 서버 장애 공지 메세지
                 */
                Toast.makeText(MainActivity.this, "서버 장애 발생. 현재 접근이 제한되어 있습니다.", Toast.LENGTH_LONG).show();
            }
        }
        
        findViewById(R.id.showStoreButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /**
                 * Load BuzzStore.
                 * @param MainActivity.this : Current activity
                 */
                BuzzStore.loadStore(MainActivity.this);
//                BuzzStore.loadStore(MainActivity.this, BuzzStore.StoreType.DEFAULT); // Same as above
            }
        });

        findViewById(R.id.showCouponButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /**
                 * Load BuzzStore with 'coupons' tab shown first.
                 */
                BuzzStore.loadStore(MainActivity.this, BuzzStore.StoreType.COUPONS);
            }
        });

        findViewById(R.id.showPointButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * Load BuzzStore with 'points' tab shown first.
                 */
                BuzzStore.loadStore(MainActivity.this, BuzzStore.StoreType.POINTS);
            }
        });
    }
}
```

## 3. Push Postback
포인트 적립시 어플리케이션의 푸시 노티피케이션을 이용해 사용자에게 푸시 알림 전달이 필요할 경우 사용하는 API. 매체사에서 postback api을 받는 로직을 구현하고 해당 url을 버즈스토어에 등록해야 한다. BuzzStore에서 등록한 postback url로 요청이 오면 전달받은 파라미터를 이용해 직접 사용자에게 푸시를 전송해야한다.

![buzzstore postback api](https://github.com/Buzzvil/buzzstore-sdk-publisher/blob/master/postback.png)

### 1. 요청 방향
* BuzzStore -> 매체사
 
### 2. HTTP Request method

* **POST**
 
### 3. HTTP Request URL

* 매체사에서 정의

### 4. HTTP Request Parameters

| 필드 | 타입 | 설명 |
|-----|----|-----|
| user_id | String | 매체사에서 정의한 user_id |
| campaign_name | String | 포인트가 지급된 캠페인 이름 |
| point | Integer | 유저에게 지급할 포인트 |

## 4. 기타 API Implementation
이 항목에서는 기타 버즈스토어가 지원하는 API에 대한 설명이 기술되어 있다. 기타 API 는 Server-To-Server 를 통한 통신만을 지원한다. 따라서 1. UserToken API Implementation 에 설명된 것 처럼 사전에 화이트리스트 처리 된 아이피를 통한 API 호출만이 허용 된다. 

#### 잔고 조회 API
- 이 연동을 하기 앞서 퍼블리셔 서버의 아이피 주소를 버즈스토어 서버에 `화이트 리스트`로 등록해야 한다. 화이트 리스트에 등록 될 아이피주소는 별도의 채널(e.g. 이메일)을 통해서 퍼블리셔가 전달한다.
- 해당 API 는 특정 유저 A의 잔고를 조회한다.

###### 요청
- API 호출 방향 : 퍼블리셔 서버 -> 버즈스토어 서버
- method : `GET`
- url : `https://store-api.buzzvil.com/api/v1/points`
- Headers : 다음의 파라미터를 담아서 요청한다.
    - `X-BUZZVIL-APP-ID` : 사전에 발급한 퍼블리셔 앱에 부여 된 고유한 아이디.
    - `X-BUZZVIL-API-TOKEN` : 사전에 발급한 서버 투 서버 API 사용을 위한 고유한 API 토큰
    - `X-BUZZVIL-USER-ID`: 퍼블리셔 유저 아이디.

e.g.
```
GET https://store-api.buzzvil.com/api/v1/points
```

> **주의** : 잔고 호출 API 는 절대 클라이언트에서 직접 호출 되서는 안된다. 클라이언트 <-> 퍼블리셔 서버 <-> 버즈스토어 서버를 통한 중계 방식을 이용해야 한다.

- 성공 시 JSON 포맷으로 `balance` 를 리턴한다. HTTP 응답 상태 코드는 200 이다. 

e.g.
```JSON
{
    "balance": 2000
}
```
- 실패 시 JSON 포맷으로 `error_code`, `error_message` 를 리턴한다.

#### 포인트 지급/차감 API
- 이 연동을 하기 앞서 퍼블리셔 서버의 아이피 주소를 버즈스토어 서버에 `화이트 리스트`로 등록해야 한다. 화이트 리스트에 등록 될 아이피주소는 별도의 채널(e.g. 이메일)을 통해서 퍼블리셔가 전달한다.
- 해당 API 는 특정 유저 A에게 포인트를 지급하거나 차감한다.

###### 요청
- API 호출 방향 : 퍼블리셔 서버 -> 버즈스토어 서버
- method : `POST`
- url : `https://store-api.buzzvil.com/api/v1/points`
- Headers : 다음의 파라미터를 담아서 요청한다.
    - `X-BUZZVIL-APP-ID` : 사전에 발급한 퍼블리셔 앱에 부여 된 고유한 아이디
    - `X-BUZZVIL-API-TOKEN` : 사전에 발급한 서버 투 서버 API 사용을 위한 고유한 API 토큰
    - `X-BUZZVIL-USER-ID`: 퍼블리셔 유저 아이디
- POST 필수 파라미터 : 
    - `amount`: 지급/차감 포인트. int이고 금액 단위가 아닌 포인트 단위여야 함. 양수이면 포인트 지급, 음수이면 포인트 차감임
    - `title`: 지급/차감의 이유 (ex. "3월 마케팅 이벤트")
- Content-Type: application/json

e.g.
```
{
    "amount": 500,
    "title": "제비뽑기 이벤트"
}
```

e.g.
```
POST https://store-api.buzzvil.com/api/v1/points
```

> **주의** : 지급/차감 API 는 절대 클라이언트에서 직접 호출 되서는 안된다. 클라이언트 <-> 퍼블리셔 서버 <-> 버즈스토어 서버를 통한 중계 방식을 이용해야 한다.

- 성공시 HTTP 응답 상태 코드는 200 이다. 
- 실패 시 JSON 포맷으로 `error_code`, `error_message` 를 리턴한다.

#### 회원 탈퇴 API
- 이 연동을 하기 앞서 퍼블리셔 서버의 아이피 주소를 버즈스토어 서버에 `화이트 리스트`로 등록해야 한다. 화이트 리스트에 등록 될 아이피주소는 별도의 채널(e.g. 이메일)을 통해서 퍼블리셔가 전달한다.
- 해당 API 는 특정 유저를 버즈스토어에서 탈퇴 시킨다.

###### 요청
- API 호출 방향 : 퍼블리셔 서버 -> 버즈스토어 서버
- method : `POST`
- url : `https://store-api.buzzvil.com/api/v1/users/deactivate`
- Headers : 다음의 파라미터를 담아서 요청한다.
    - `X-BUZZVIL-APP-ID` : 사전에 발급한 퍼블리셔 앱에 부여 된 고유한 아이디.
    - `X-BUZZVIL-API-TOKEN` : 사전에 발급한 서버 투 서버 API 사용을 위한 고유한 API 토큰
    - `X-BUZZVIL-USER-ID`: 퍼블리셔 유저 아이디.
- Content-Type: application/x-www-form-urlencoded
    
###### 응답
- 성공시 HTTP 응답 상태 코드는 200 이다. 
- 실패 시 JSON 포맷으로 `error_code`, `error_message` 를 리턴한다.
