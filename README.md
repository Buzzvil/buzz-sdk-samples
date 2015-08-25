# BuzzAd SDK for Android
- 버즈애드를 안드로이드 어플리케이션에 연동하기 위한 라이브러리
- 안드로이드 버전 지원 : Android 2.3(API Level 9) 이상
- SDK 연동 및 샘플 어플리케이션 실행을 위해서는 app_key(버즈애드 어드민에서 확인 가능) 필요
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
- BuzzAd.showOfferWall : 오퍼월 호출.

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
         * app_key : get from publisher page
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
            	 * media_user_id : unique user id in publisher
            	 */
                BuzzAd.showOfferWall(MainActivity.this, "Get Points", "media_user_id");
            }
        });
    }
}
```
