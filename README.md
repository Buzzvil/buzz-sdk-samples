# BuzzStore Integration Guide v0.9

버즈스토어를 연동하기 위한 통합 가이드. 연동 과정은 전체적으로 다음의 세 단계로 구성되어 있다.

1. Key Hash Registration
2. UserToken API Implementation
3. SDK Integration


## 1. Key Hash Registration
- 버즈스토어는 보안을 강화하기 위해 퍼블리셔 앱 내에서 SDK를 통해 버즈스토어를 호출할 때 앱의 Key Hash값을 같이 전달받아 요청의 유효성을 판별한다. 
- 버즈스토어 어드민에 아래에 제시된 대로 콘솔을 이용하거나 코드를 이용해서 생성한 Key Hash를 등록한다.
- Key Hash는 디버그 앱과 릴리즈 앱이 서로 다르다. 두 버젼 모두 등록해야 한다.
- 디버그 앱의 Key Hash는 빌드하는 환경(e.g. 컴퓨터)에 따라 달라진다. 다수의 개발자가 디버그용 앱을 각각 다른 환경에서 빌드할 경우, 각각의 환경에서의 Key Hash를 모두 등록해야 한다.
- 릴리즈 앱의 Key Hash는 유일하다.

> **주의** : 기존에 등록된 Key Hash 값이 아닌 다른 값이 요청 시 전달된다면 서버는 비정상 요청으로 판단하여 해당 요청을 차단한다. 따라서 연동을 시작할 때 제일 처음 단계로 모든 Key Hash를 등록하는 것을 권장한다.

#### (1) 터미널을 이용해서 생성하는 법
##### 디버그용 Key Hash
터미널에서 다음과 같은 command를 통해 디버그용 Key Hash를 얻을 수 있다.
> **주의** : 디버그용 키 저장소의 default 비밀번호는 `android` 이다.

```
keytool -exportcert -alias androiddebugkey -keystore ~/.android/debug.keystore | openssl sha1 -binary | openssl base64
```

##### 릴리즈용 Key Hash
터미널에서 다음과 같은 command를 통해 릴리즈용 Key Hash를 얻을 수 있다. 아래의 `<Release key alias>` 에는 릴리즈 키의 alias를, `<Release key path>` 에는 릴리즈 키의 path를 입력한다.
> **주의** : 릴리즈용 키를 얻으려면 기존에 지정해 둔 키 저장소 비밀번호 입력이 필요하다.

```
keytool -exportcert -alias <Release key alias> -keystore <Release key path> | openssl sha1 -binary | openssl base64
```

#### (2) 코드를 이용해서 생성하는 법
- 제공하는 SDK 내의 `BuzzStore.getKeyHash(Context context)` 함수를 이용한다. 이 함수는 앱의 context 를 파라미터로 받아 현재 빌드된 앱의 Key Hash를 String 형태로 리턴한다.


## 2. UserToken API Implementation
- 퍼블리셔의 유저가 버즈스토어를 이용할 때 주고받는 정보에 대한 보안을 강화하기 위해 버즈스토어는 User Token 을 이용한다.
- User Token 은 퍼블리셔 User Id 와 1:1 매칭되는 값으로, 버즈스토어 서버에서 생성하고 관리한다. 
- 버즈스토어 호출 시 전달되는 User Token 이 없거나 무효하면 버즈스토어 서버는 이를 비정상적 상황으로 인식하고 해당 호출을 차단한다.
- 퍼블리셔는 User Token을 요청하는 API(`UserToken API`)를 다음의 인터페이스에 맞게 구현해야 한다.
- `UserToken API` 는 크게 퍼블리셔 앱과 퍼블리셔 서버끼리의 통신과, 퍼블리셔 서버와 버즈스토어 서버끼리의 통신 총 2가지로 구성되어 있다.

#### UserToken API Interface OverView
User Token의 전체적인 전달 흐름을 그림으로 표현하면 다음과 같다.
![buzzstore user token](https://github.com/Buzzvil/buzzstore-sdk-publisher/blob/guide/buzzstore.png)

- Request 주체 : Publisher App
- Response 주체 : BuzzStore Server
- Linker : Publisher Server
- Request 방향 : `Publisher App -> Publisher Server -> BuzzStore Server`
- Response 방향 : `BuzzStore Server -> Publisher Server -> Publisher App`
- API 호출 주체 : BuzzStore SDK(Token Validate check 실패 시)

User Token은 외부 유출을 원천 차단하기 위해 사전 등록된 퍼블리셔 서버에게만 Server-To-Server 방식으로 전달하게 되어 있다. 따라서 퍼블리셔 앱은 직접 버즈스토어 서버로의 토큰 요청이 불가하고 반드시 퍼블리셔 서버를 거쳐야 한다. 이 때, 퍼블리셔 앱에서 퍼블리셔 서버로의 Request는 곧바로 퍼블리셔 서버에서 버즈스토어 서버로의 Request로 이어져야 한다. 마찬가지로 버즈스토어 서버에서 퍼블리셔 서버로 돌아온 Response는 곧바로 퍼블리셔 서버에서 퍼블리셔 앱으로의 Response로 이어져야 한다.

> **주의** : 퍼블리셔 서버에서 유저 토큰을 별도로 저장하여 관리할 필요는 없다. 유저 토큰 요청의 시작은 언제나 퍼블리셔 앱에서 일어나기 때문에 퍼블리셔 서버는 버즈스토어 서버에서 받아온 유저 토큰을 단순히 퍼블리셔 앱으로 전달해주기만 하면 된다.

> **주의** : 구현된 UserToken API의 앱 내 호출은 BuzzStore SDK에서 제공하는 'UserToken 유효성 체크 인터페이스' 내의 OnNeedAPICall() 메소드 안에서만 일어나면 된다.(그림의 'Token Validate check' 참조) SDK에서 UserToken의 유효성을 체크하기 때문에 퍼블리셔 측에서 이 외에 별도로 API 호출을 위한 로직을 짤 필요는 없다.

#### Publisher App <-> Publisher Server(App 내 API 구현)
- 기존에 퍼블리셔 앱이 서버와 통신하던 방식을 이용해 퍼블리셔 서버에게 현재 유저의 정보에 맞는 User Token을 요청하는 API를 구현한다.
- 이 API는 보안상의 이유로 퍼블리셔쪽 인증이 이뤄진 채널을 통해서 통신해야 한다. e.g. 로그인
- 해당 API는 SDK가 제공하는 interface 인 `UserTokenListener` 의 `OnNeedAPICall()` 안에 호출되도록 구현한다.
- 호출 결과 받아온 User Token은 `UserTokenListener` 의 `OnNeedAPICall()` 의 리턴값으로 전달되도록 구현한다.(3. SDK Integration 내 'UserToken 유효성 체크 interface 구현' 참조)

#### Publisher Server <-> BuzzStore server(Server-To-Server 연동)
- 이 연동을 하기 앞서 퍼블리셔 서버의 아이피 주소를 버즈스토어 서버에 `화이트 리스트`로 등록해야 한다. 화이트 리스트에 등록 될 아이피주소는 별도의 채널(e.g. 이메일)을 통해서 퍼블리셔가 전달한다.

###### 요청
- API 호출 방향 : 퍼블리셔 서버 -> 버즈스토어 서버
- method : `POST`
- url : `https://52.193.111.153/api/users` (테스트 환경)
- Headers : 다음의 파라미터를 담아서 요청한다.
    - `HTTP-X-BUZZVIL-APP-ID` : 사전에 발급한 퍼블리셔 앱에 부여 된 고유한 아이디.
    - `HTTP-X-BUZZVIL-API-KEY` : 사전에 발급한 서버 투 서버 API 사용을 위한 고유한 API 키
- POST 필수 파라미터 : 퍼블리셔의 유저 식별자 `publisher_user_id`

e.g.
```JSON
{
"publisher_user_id": 1270537
}
```

> **주의** : 버즈스토어는 퍼블리셔 유저 식별자를 기준으로 유저를 식별하므로 해당 값은 추후 바꿀 수 없다. 버즈스토어는 두개 이상의 서로 다른 유저 식별자를 서로 다른 유저로 인식한다. 따라서 퍼블리셔가 제공하는 유저 식별자는 절대 업데이트되지 않는 값을 사용해야 한다. 예를들어, 계정에 연동 된 이메일 주소와 같이 추후 변동될 여지가 있는 값 보다는 퍼블리셔 디비 내의 고정 값으로 사용하길 권장한다.

- 성공 시 JSON 포맷으로 `publisher_user_id`, `token` 를 리턴한다. HTTP 응답 상태 코드는 200 이다. 

e.g.
```JSON
{
"publisher_user_id": 1270537,
"token": "yYn05pKNlHpMdmBd2GeXU4tBdQtIENuFmFJ0MhBJkwBIzE2TXffLTA7bfWAmRSOc"
}
```
- 실패 시 JSON 포맷으로 `error_code`, `error_message` 를 리턴한다.

###### 실패 시 재시도에 관하여
버즈스토어 서버에 일시적 장애가 발생하여 이 API 를 통한 토큰 발급에 실패하는 경우 스토어 SDK 이용이 제한된다. 이 때, SDK내에서 제공하는 `UserTokenListener` 의 `OnNeedAPICall()` 의 구현이 완료되어 있으면 SDK가 자동으로 재시도를 한다.(3. SDK Integration 내 'UserToken 유효성 체크 interface 구현' 참조)


## 3. SDK Integration
- 버즈스토어를 안드로이드 어플리케이션에 연동하기 위한 라이브러리
- 안드로이드 버전 지원 : Android 4.0.3(API Level 15) 이상
- SDK는 버즈스토어 UI 호출 기능과 UserToken 유효성 체크 기능을 제공한다.
- 버즈스토어 UI는 모두 웹으로 이루어져 있으며, 필수 파라미터를 전달하여 웹뷰를 통해 호출한다.
- UserToken 유효성 체크는 Java interface로 제공하므로 아래의 가이드에 따라 Publisher 앱 내에서 직접 interface 의 구현이 필요하다.

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
            android:name="com.buzzvil.buzzstore.StoreActivity"
            android:theme="@android:style/Theme.Translucent.NoTitleBar" />
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

- `BuzzStore.setUserTokenListener(UserTokenListener listener)` : 유저 토큰의 유효성을 검사하여 유효하지 않을 때 호출되는 리스너인 UserTokenListener 를 구현하는 함수이다.

- `BuzzStore.loadStore(Activity activity)` : 버즈스토어 모바일 UI를 호출한다.

> **주의** : 반드시 `BuzzStore.init()`, `BuzzStore.setUserTokenListener()` 이 모두 호출된 이후에 호출해야 한다.

#### UserToken 유효성 체크 interface 구현
제공하는 `UserTokenListener` 를 통해 `UserToken API` call이 필요할 때(OnNeedAPICall())와, UserToken 요청의 재시도에도 불구하고 지속적으로 유저 토큰 획득에 실패하여 유저가 결국 BuzzStore를 띄울 수 없을 때(OnFail())의 이벤트 처리를 할 수 있다.

interface의 구성은 다음과 같다.
```Java
public interface UserTokenListener {
    String OnNeedAPICall();
    void OnFail();
}
```

- `String OnNeedAPICall()` : UserToken이 유효하지 않을 때 호출된다. 이 메소드는 퍼블리셔가 구현한 `UserToken API` 를 호출하고 (2. UserToken API Implementation 참조), 이를 통해 전달받은 UserToken을 리턴하도록 구현해야 한다. SDK는 이 전달받은 UserToken 을 가지고 버즈스토어 서버로 다시 UserToken의 유효성을 체크한다.

> **주의** : OnNeedAPICall() 은 재시도만을 위한 것이 아니라 유저가 버즈스토어를 최초로 로드하여 UserToken을 생성하려 할 때에도 불리게 된다. 따라서 필수적으로 구현해야 한다.

- `void OnFail()` : SDK는 OnNeedAPICall() 을 통해 전달받은 UserToken을 가지고 BuzzStore Server로 유효성 체크 시도를 반복하는데, 최대 횟수 만큼 시도했으나 결국 유효한 UserToken을 발급받지 못해 최종 실패한 경우 이 메소드가 호출된다. 이 메소드는 연동 중 디버깅 용이나 유저에게 버즈스토어 접근 불가를 알리는 UI 처리를 위해 사용할 수 있다.

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
         * appId : Unique key value to identify the publisher.
         * userId : user identifier used from publisher
         * this : Context
         */
        BuzzStore.init("appId", "userId", this);

        BuzzStore.setUserTokenListener(new BuzzStore.UserTokenListener() {
            @Override
            public String OnNeedAPICall() {
                /**
                 * 해당 함수가 호출되면 퍼블리셔 서버로의 API 콜을 통해 해당 유저의 유저토큰을 전달받아 리턴해야 한다.
                 * 보안상의 이유로 해당 유저토큰은 Server-To-Server로만 제공되므로 퍼블리셔앱은 반드시 퍼블리셔 서버를 통해서 버즈스토어로 요청해야 한다.
                 */
                return UserToken;
            }

            @Override
            public void OnFail() {
                /**
                 * 최대 횟수 만큼 SDK 내부에서 Validation 시도를 하였으나 최종 실패한 경우 이 함수가 호출된다.
                 * 이 때, 퍼블리셔는 실패 UI 등을 유저에게 보이도록 처리하는 것이 권장된다.
                 * ex. 서버 장애로 인한 접속 불가 메세지 알림
                 */
            }
        });

        findViewById(R.id.showStoreButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * Load BuzzStore.
                 * MainActivity.this : Current activity
                 */
                BuzzStore.loadStore(MainActivity.this);
            }
        });
    }
}
```
