# BuzzAd SDK for Android
- 버즈애드를 안드로이드 어플리케이션에 연동하기 위한 라이브러리
- 안드로이드 버전 지원 : Android 2.3(API Level 9) 이상
- SDK 연동 및 샘플 어플리케이션 실행을 위해서는 app_key(버즈애드 퍼블리셔 어드민에서 확인 가능) 필요
- 구글 플레이 서비스 라이브러리 설정 필요. [구글 플레이 서비스 라이브러리 설정방법](https://developers.google.com/android/guides/setup)을 참고하여 직접 추가하면 된다.

## 버즈애드 SDK 연동가이드

### 1. 설정
- [SDK 다운로드](https://github.com/Buzzvil/buzzad-sdk-publisher/archive/master.zip) 후 압축 해제
- 압축 해제한 폴더 내의 buzzad-sdk/buzzad.jar를 개발중인 안드로이드 어플리케이션에 포함
- Android Manifest : 아래와 같이 권한, 액티비티 설정

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

### 2. 오퍼월 호출
- BuzzAd.init : 오퍼월을 보여주려는 액티비티의 onCreate에서 호출.
- BuzzAd.showOfferWall : 오퍼월 호출

    > 이 때 전달되는 매체사 유저 아이디는 포인트 적립시(3. 포인트 적립 요청 참고)에 같이 전달된다. 이 값을 통해 매체사가 유저를 구분하여 포인트 지급 처리를 할 수 있다.

#### 사용 예제

```Java
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        /**
         * Initialize BuzzAd.
         * BuzzAd.init have to be called prior to other methods.
         * app_key : get from publisher admin
         * this : context
         */
        BuzzAd.init("app_key", this);
        
        findViewById(R.id.open_offerwall).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	/**
            	 * Show offer wall.
            	 * MainActivity.this : current activity
            	 * Get Points : header title on offer wall
            	 * publisher_user_id : unique user id for publisher
            	 */
                BuzzAd.showOfferWall(MainActivity.this, "Get Points", "publisher_user_id");
            }
        });
    }
}
```

### 3. 포인트 적립 요청(포스트백)  - 서버 연동
- 버즈애드에서 포인트 적립이 발생했을 때 직접 유저들에게 포인트를 지급하는 것이 아니다. 버즈애드 서버에서 매체사 서버로 포인트 적립 요청을 보낼 뿐이고, 실제 지급은 매체사 서버에서 처리한다.
- 포인트 적립 요청을 받을 매체사 주소는 버즈애드 매체사 페이지에서 등록한다.
- 포인트 적립 요청시 보내는 파라미터는 다음과 같다.

| Name  | Description |
| ------------- | ------------- |
| app_key  | SDK 연동시 사용한 app_key  |
| campaign_id  | 참여한 광고 아이디  |
| title | 참여한 광고명  |
| user_id  | 매체사 유저 아이디  |
| point | 유저에게 지급해야 할 포인트 |
| transaction_id | 포인트 중복 적립을 막기 위한 id. 같은 transaction_id로 요청이 온 경우에는 반드시 포인트 중복 적립이 안되도록 처리해주어야 한다.|
