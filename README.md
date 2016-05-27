# BuzzAd SDK for Android
- 버즈애드를 안드로이드 어플리케이션에 연동하기 위한 라이브러리
- 안드로이드 버전 지원 : Android 2.3(API Level 9) 이상
- SDK 연동 및 샘플 어플리케이션 실행을 위해서는 `app_key`(버즈애드 퍼블리셔 어드민에서 확인 가능) 필요

## 안드로이드 SDK 연동가이드
### 설정
- [SDK 다운로드](https://github.com/Buzzvil/buzzad-sdk-publisher/archive/master.zip) 후 압축 해제
- 압축 해제한 폴더 내의 buzzad-sdk/buzzad.jar를 개발중인 안드로이드 어플리케이션에 포함
- 구글 플레이 서비스 라이브러리 설정 필요. [구글 플레이 서비스 라이브러리 설정방법](https://developers.google.com/android/guides/setup)을 참고하여 직접 추가하면 된다.

    > 개발 환경이 안드로이드 스튜디오인 경우는 **build.gradle > dependencies**에 `compile 'com.google.android.gms:play-services-ads:7.5.0'`만 추가하면 된다.

- AndroidManifest.xml : 아래와 같이 권한, 액티비티 설정
```Xml
<manifest>
    ...
    <!-- Permission for BuzzAd -->
    <uses-permission android:name="android.permission.INTERNET" />

    <application>
        ...
        <!-- Setting for Google Play Services -->
        <meta-data
            android:name="com.google.android.gms.version"
            android:value="@integer/google_play_services_version" />

        <!-- Activity for BuzzAd -->
        <activity
            android:name="com.buzzvil.buzzad.sdk.OfferWallActivity"
            android:theme="@android:style/Theme.Translucent.NoTitleBar" />
    </application>
</manifest>
```

##### Proguard 설정
Proguard 사용시에 다음 라인들을 Proguard 설정에 추가한다.

```
-keep class com.buzzvil.buzzad.sdk.** {*;}
-keep interface com.buzzvil.buzzad.sdk.** {*;}

-keep class com.google.android.gms.common.GooglePlayServicesUtil {*;}
-keep class com.google.android.gms.ads.identifier.AdvertisingIdClient {*;}
-keep class com.google.android.gms.ads.identifier.AdvertisingIdClient$Info {*;}
```


### 오퍼월 호출
- `BuzzAd.init(String appKey, Context context)` : 오퍼월을 보여주려는 액티비티의 onCreate에서 호출.

    > `app_key`는 퍼블리셔 어드민 페이지에 로그인하여 확인할 수 있다.

- `BuzzAd.showOfferWall(Activity activity, String title, String userId)` : 오퍼월 호출

    > 이 때 전달되는 `userId`(매체사 유저 아이디)는 포인트 적립 요청시([포인트 적립 요청 가이드](https://github.com/Buzzvil/buzzad-sdk-publisher#포인트-적립-요청포스트백----서버-연동) 참고)에 같이 전달된다. 이 값을 통해 매체사가 유저를 구분하여 포인트 지급 처리를 할 수 있다.
    
    > **주의** : 반드시 `BuzzAd.init()` 을 호출한 이후에만 오퍼월 호출이 가능하다.

### 타게팅 정보 추가(선택사항)
성별, 나이별 타게팅 정보를 가진 광고를 유저에게 보여주려 할 때 다음의 메소드를 통해 유저의 정보를 입력할 수 있다.

- `BuzzAd.getUserProfile()` : `UserProfile` 객체를 리턴한다. 리턴된 객체에 유저의 성별, 나이 정보를 아래에 기술된 UserProfile 클래스의 메소드를 이용하여 설정할 수 있다.

    > **주의** : 반드시 `BuzzAd.init()` 을 호출한 이후에만 이 메소드를 이용해서 UserProfile 객체를 리턴할 수 있다.

##### UserProfile 클래스 내의 메소드 목록
- `setGender(String gender)` : 성별을 설정한다. 다음과 같은 미리 정의된 String을 통해 형식에 맞춰 성별을 적용해야 한다.
    - `UserProfile.USER_GENDER_MALE` : 남성인 경우
    - `UserProfile.USER_GENDER_FEMALE` : 여성인 경우

- `setBirthYear(int birthYear)` : 유저의 출생 년도를 4자리의 숫자로 입력하여 나이를 설정한다.

### 사용 예제

```Java
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * Initialize BuzzAd.
         * BuzzAd.init have to be called prior to other methods.
         * app_key : Unique key value for publisher. Please find it on your BuzzAd dashboard.
         * this : Context
         */
        BuzzAd.init("app_key", this);

        /**
         * Set User's profile(Optional)
         * BuzzAd.getUserProfile have to be called after BuzzAd.init is called.
         */
        UserProfile userProfile = BuzzAd.getUserProfile();

        userProfile.setBirthYear(1993);
        userProfile.setGender(UserProfile.USER_GENDER_FEMALE);

        findViewById(R.id.open_offerwall).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * Show offer wall.
                 * MainActivity.this : Current activity
                 * Get Points : Header title on offer wall
                 * publisher_user_id : Unique user id for publisher
                 */
                BuzzAd.showOfferWall(MainActivity.this, "Get Points", "publisher_user_id");
            }
        });
    }
}
```

## 포인트 적립 요청(포스트백) - 서버 연동
- 버즈애드에서 포인트 적립이 발생했을 때 버즈애드에서 직접 매체사 유저들에게 포인트를 지급하는 것이 아니다. 버즈애드 서버에서 매체사 서버로 포인트 적립 요청을 보낼 뿐이고, 실제 지급은 매체사 서버에서 처리한다.
- 포인트 적립 요청을 받을 매체사 주소는 버즈애드 매체사 어드민 페이지에서 등록하면 해당 주소로 포인트 적립시에 요청이 이루어진다.

### 포인트 적립 요청 스펙
- HTTP METHOD : POST
- 요청 방향 : 버즈애드 서버 -> 매체사 서버
- 파라미터

    | Name  | Type | Description |
    | ------ | ----------| ----------- |
    | app_key  | Integer | SDK 연동시 사용한 **app_key**  |
    | campaign_id | Integer | 참여한 광고 아이디  |
    | title | String | 참여한 광고명  |
    | user_id  | String | 매체사 유저 아이디로 SDK의 BuzzAd.showOfferWall 호출시 전달했던  `userId`|
    | point | Integer | 유저에게 지급해야 할 포인트 |
    | transaction_id | String | 포인트 중복 적립을 막기 위한 **id**.<br>같은 **transaction_id**로 요청이 온 경우에는 반드시 포인트 중복 적립이 안되도록 처리해주어야 한다.<br>**최대 64자까지 전달 될 수 있으므로, 연동 시 확인이 필요하다.**|
    | event_at | Long | 포인트 지급 시점. **timestamp**값이다.<br>대부분 API 호출시점과 동일하지만 API 호출이 재시도인 경우 다를 수 있다.|
    | extra | String | 매체사별 자체 정의한 캠페인 데이터의 json serilaize된 스트링.<br>라이브중에 캠페인 extra 정보가 바뀐 경우, 실제 포인트 적립 api에서 바뀐 정보가 적용되는데에 최대 10분이 걸릴 수 있다.<br>eg) `{"sub_type": "A", "source":"external"}`|
    | is_media | Integer |0: 버즈빌측 캠페인<br>1: 매체사측 캠페인|
    | action_type | String | 포인트를 지급 받기 위해 유저가 취한 액션 타입.<br>추후 새로운 타입이 추가될 수 있으므로 연동시 이를 고려해야 한다. <ul><li><b>u</b>: 잠금해제 </li><li><b>l</b>: 랜딩</li><li><b>a</b>: 액션</li></ul>|

- 포인트 적립 요청에 매체사 서버는 정상 처리된 경우는 `HTTP STATUS 200` 응답을 보내야 하며, 그 외의 경우 특정 시간동안 포인트 적립 요청은 재시도가 된다.
    > **주의** : 만약 중복된 `transaction_id`가 포스트백을 통해 전달되면, `HTTP STATUS 200` 응답을 보내야 한다. 그렇지 않으면, 해당 요청에 대해 재시도가 이루어진다.
