# BuzzAd SDK for Android
- Buzzvil`s library for integrating BuzzAd with Android Apps.
- Requires Android version 2.3(API level 9) or newer.
- Please find `app_key` on your BuzzAd dashboard before beginning the SDK  integration or running sample applications.
- The Google Play Services SDK must be configured. Please refer to Google`s [Setting Up Google Play Services](https://developers.google.com/android/guides/setup) guide for more details.

    > Android studio configuration is different from eclipse. If you are using andorid studio, just add `compile 'com.google.android.gms:play-services-ads:7.5.0'` to **build.gradle > dependencies**.

## Integration Guide

### 1. Setup
Download and unzip [BuzzAd SDK](https://github.com/Buzzvil/buzzad-sdk-publisher/archive/master.zip) and include **buzzad-sdk/buzzad.jar** from the unzipped folder in your android application.

Add permission and activity to your Android Manifest as below.

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

##### ProGuard Configuration
To prevent ProGuard from stripping away required classes, add the following lines in your ProGuard configuration file.

```
-keep class com.buzzvil.buzzad.sdk.** { *; }
-keep interface com.buzzvil.buzzad.sdk.** { *; }
```


### 2. Show Offer Wall
- `BuzzAd.init(String appKey, Context context)` : Call this on `onCreate` in your activity to show Offer Wall.

    > You can find `app_key` on your BuzzAd dashboard.

- `BuzzAd.showOfferWall(Activity activity, String title, String userId)` : Shows Offer Wall on your application.

    > `userId`(Publisher User Id) is sent to publisher with points accumulation request(Please refer to 3. Points Accumulation Request for more details). Publishers can identify each user by using `userId`, and give points to the user.

#### Example

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

### 3. Points Accumulation Request(Postback)  - Server to Server Integration
When a point accumulation activity occurs from a user, BuzzAd does not give the user reward points directly. The BuzzAd server will make a point accumulation request to the publisher's server and the publisher's server must process the request and provide points for the user.

You can set the postback URL to get the postback from BuzzAd server in BuzzAd dashboard.

##### Postback Specification
- HTTP METHOD : POST
- Request direction : BuzzAd Server -> Publisher Server
- Parameters:

    | Name  | Description |
    | ------------- | ------------- |
    | app_key  | The value used in BuzzAd.init() of SDK  |
    | campaign_id  | Campaign ID  |
    | title | Campaign Title |
    | user_id  | The value used in BuzzAd.showOfferWall() of SDK |
    | point | The value how many points a user can get |
    | transaction_id | A unique ID to prevent duplicated same postback. If transaction_id is same with previous postback, you have to skip the postback |

- Response : If a postback is precessed successfully, publishers have to respond with `HTTP STATUS 200`. Otherwise, the postback is sent to again in specific peorid. We do not care anything except http status code for retry policy.
