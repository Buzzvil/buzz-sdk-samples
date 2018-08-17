# BuzzStore Integration Guide v1.0

This is an integrated guide for BuzzStore integration. Integration process consists of the following three steps.

1. UserToken API Implementation
2. SDK Integration
3. Other API Implementation

## 1. UserToken API Implementation
- BuzzStore uses User Token to enhance the security of information exchanged when users use BuzzStore.
- User Token is a value that matches 1:1 with Publisher User Id and is created and managed by BuzzStore server. 
- If there is no User Token delivered when BuzzStore is called, or if it is invalid, BuzzStore server recognizes it as abnormal and blocks the call.
- The publisher must implement the API (`UserToken API`) that requests the User Token according to the following interface.
- The `UserToken API` consists of two main components: 
> Communication between publisher application and publisher server 
Communication between publisher server and BuzzStore server.

#### UserToken API Interface Overview
The overall flow of User Token delivery is illustrated in the figure below. 

![buzzstore user token](https://github.com/Buzzvil/buzzstore-sdk-publisher/blob/master/buzzstore.png)

- Subject of Request : Publisher App
- Subject of Response : BuzzStore Server
- Linker : Publisher Server
- Request Direction : `Publisher App -> Publisher Server -> BuzzStore Server`
- Response Direction : `BuzzStore Server -> Publisher Server -> Publisher App`
- API Caller : BuzzStore SDK(when Token Validate check failed)

The User Token is to be delivered to the pre-registered Publisher server via Server-To-Server integration in order to prevent leakage. Therefore, the publisher app can not directly request a token to the BuzzStore server and must pass through the publisher server. The request from the publisher app to the publisher server should be immediately transferred to the request from the publisher server to BuzzStore server. Similarly, a response from the Buzzstore server to the publisher server should be immediately transferred to the response from the publisher server to the publisher app.

> ** Note 1 ** : There is no need to store and manage User Tokens separately on the publisher server. Since the User Token request always is initiated in the publisher app, the publisher server needs to simply pass the User Token received from the BuzzStore server to the publisher app.

> ** Note 2 ** : In-app calls of implemented UserToken API should be occured only within the OnNeedAPICall() method in the 'UserToken Validity Check Interface' that BuzzStore SDK provides(please refer to 'Token Validate Check' in the diagram above). The publisher does not need to add any additional logic for API calls as validity of the UserToken is checked in the SDK. 

#### Publisher App <-> Publisher Server(API implementation within the app)
- Implement API that requests User Token according to the current user information by using the way that the publisher app communicates with the publisher server. 
- For security reasons, this API must communicate over the channel that the publisher authenticated. e.g. login
- Implement this API to be called in the 'OnNeedAPICall()' of `UserTokenListener` interface provided by SDK. 
- Set the User Token received by calling `setUserToken()` of the SDK when getting response from API called by `OnNeedAPICall()` of `UserTokenListener`. (please refer to 'UserToken Validity Check Interface' in SDK integration)

#### Publisher Server <-> BuzzStore Server(Server-To-Server Integration)
- Prior to the API integration,, a publisher must `whitelist` the IP address of its server on the Buzzstore server. The publisher should share the IP address to be registered through separate channel (eg. e-mail).

###### Request
- API Call Direction : Publisher Server -> BuzzStore Server
- method : `POST`
- url : `https://store-api.buzzvil.com/api/v1/users`
- Headers : Request with the following parameters.
    - `HTTP-X-BUZZVIL-APP-ID` : A unique ID that is issued to publisher application. 
    - `HTTP-X-BUZZVIL-API-TOKEN` : A unique API token issued previously for server-to-server API. 
- Essential POST Parameter : Publisher's user identifier `publisher_user_id`

e.g.
```JSON
{
    "publisher_user_id": 1270537
}
```

> ** Note ** : As BuzzStore identifies the user based on the publisher user identifier, it cannot be changed later. BuzzStore recognizes two or more different user identifiers as different users. Therefore, the user identifier provided by the publisher must be a value that is never updated. For example, it is recommended to use the user identifier value in the publisher database (for example, the pk value in the Users table) rather than a value that may change later, such as an email address linked with an account.

- When successed, `publisher_user_id`, `token` are returned in JSON format. HTTP response code is 200 or 201. 

e.g.
```JSON
{
    "publisher_user_id": 1270537,
    "token": "yYn05pKNlHpMdmBd2GeXU4tBdQtIENuFmFJ0MhBJkwBIzE2TXffLTA7bfWAmRSOc"
}
```
- When failed, `error_code`, `error_message` are returned in JSON format.

###### About retry when failed
When BuzzzStore Server fails to issue tokens through API due to a temporary failure, use of BuzzStore SDK is restricted. At this time, SDK automatically retries when `OnNeedAPICall()` of `UserTokenListener` provided in the SDK is implemented. (please refer to 'UserToken Validity Check Interface' in this doc)


## 2. SDK Integration
- Library to integrate BuzzStore to Android application 
- Android Version Support: Android 4.0 (API Level 14) or higher
- SDK provides BuzzStore UI call function and UserToken validity check function.
- BuzzStore UI is made entirely on the web. SDK passes the required parameters and opens the BuzzStore UI on WebView.
- The UserToken validation check is provided in Java interface. Publisher needs to directly implement the interface in publisher app according to the guide below. 

#### Update History
- 2016.08.08 Loading UI changed 
    - Remove theme from the activity settings in AndroidManifest.xml 

#### Basic Settings

- After [download SDK](https://github.com/Buzzvil/buzzstore-sdk-publisher/archive/master.zip), unpack it and include buzzstore-sdk/buzzstore.jar as a library in the publisher app. 
- Require Google Play Service Library setting. Pleaer refer to [How to set up Google Play Service Library](https://developers.google.com/android/guides/setup) and add it. 

    > For Android Studio, just add **compile 'com.google.android.gms:play-services-ads:7.5.0'** to **build.gradle > dependencies**

- Add activity and permission in AndroidManifest.xml as below. 

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

- Proguard Settings
When using Proguard, please add below lines to Proguard settings. 
```
-keep class com.buzzvil.buzzstore.** {*;}
-keep interface com.buzzvil.buzzstore.** {*;}
```

#### BuzzStore UI Call
- `BuzzStore.init(String appId, String userId, Context context)` : Call this initialization function in OnCreate of the activity to load BuzzStore. 
     - `appId`: A unique identifier given to the publisher app in BuzzStore. It will be sent to publisher upon starting integration. It is also possible to check this value on BuzzStore Admin.
     - `userId`: A unique identifier of the user managed by the publisher. BuzzStore manages point with this information.
     - `context`: Context of the app.


- `BuzzStore.setUserTokenListener(UserTokenListener listener)` : A function to register UserTokenListener which is called when the token is not valid. (Please refer to 'UserToken Validity Check Interface Implementation' for the details.) 

- `BuzzStore.loadStore(Activity activity)` : Call BuzzStore mobile UI. 

- `BuzzStore.loadStore(Activity activity, StoreType type)` : It is used to explicitly populate a tab of a specific type while calling BuzzStore mobile UI.
    
    **StoreType list**
    - `DEFAULT` : Links to store item page, the main page of the store. 
    - `COUPONS` : Links to coupon box. 
    - `POINTS` : Link to point details. 
    
    > **Notice 1** : Must call it after `BuzzStore.init()`is called. 
    
    > **Notice 2** : If not specify a tab like the above, it is automatically set to `StoreType.DEFAULT`. That is, `BuzzStore.loadStore(activity)` is the same as `BuzzStore.loadStore(activity, BuzzStore.StoreType.DEFAULT)`.

#### UserToken Validity Check Interface Implementation 
`UserTokenListener` is used to handle OnFail event(when user has failed to get User Token even after multiple attempts of retry) and OnNeedAPICall event(when `UserToken API` call is needed).

The configuration of the interface is as follows. 
```Java
public interface UserTokenListener {
    void OnNeedAPICall();
    void OnFail();
}
```

- `void OnNeedAPICall()` : Called when UserToken is not valid. **This method should be implemented to call publisher's `UserToken API`(Please refer to 1.UserToken API Implementation) and to register the UserToken received as a response of this API through `setUserToken` method**. SDK checks the validity of UserToken in BuzzStore server with the received UserToken and decides whether or not the store can be loaded.
    - `void setUserToken(String userToken)` : Use this method to pass the UserToken (String type)received as a response when API call is successful. When the API call is failed, this method needs to be called with an empty string ("") to make SDK to retry automatically.

> **Notice** : OnNeedAPICall() must be implemented. This method is not only used for retrying but also called when the UserToken is about to be generated upon user's attempt to load the BuzzStore for the first time. 

- `void OnFail()` : The SDK repeats the validation check through the BuzzStore server with the UserToken passed through OnNeedAPICall(). This method is called if the user fails to get a valid UserToken after the maximum number of attempts. This can happen when the BuzzStore server or publisher server is not functioning normally. It is recommended to implement a UI process that notifies the user that a server error has prevented the access to BuzzStore.

##### Usage Example
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
                 * When the function is called, UserToken received through the API call should be registered through setUserToken method. 
                 * For security reasons, the corresponding user token is provided only through Server-To-Server, so publisher app must make a request to the BuzzStore through the publisher server.
                 *
                 * Note : The following code is a pseudo-code for reference. No such method is provided. 
                 */
                Request(
                        new ResponseListener() {
                            OnSuccess(Response response) {
                                String userToken = response.getString("user_token");
                                /**
                                 * Call below method after receiving UserToken from the server.
                                 */
                                BuzzStore.setUserToken(userToken);
                            }
                            OnError() {
                                /**
                                 * If server communication fails, call setUserToken with empty string like below.
                                 */
                                BuzzStore.setUserToken("");
                            }
                        }
                );
            }

            @Override
            public void OnFail() {
                /**
                 * This function is called when the validation attempt within the SDK failed after the maximum number of retry.
                 * This happens when the BuzzStore server or publisher server is not running.
                 * At this time, it is recommended for the publisher to process failure UI to be visible to the user.
                 * ex. Server failure notice message
                 */
                Toast.makeText(MainActivity.this, "Server error occured. Current access is restricted.", Toast.LENGTH_LONG).show();
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
An API that is used when a push notification is needed upon users earn the point. The publisher must implement the logic to receive the postback api and need to register the url in BuzzStore. When a request arrives from a postback url registered in BuzzStore, the publisher needs to deliver the push message directly to the user using the parameter received.

![buzzstore postback api](https://github.com/Buzzvil/buzzstore-sdk-publisher/blob/master/postback.png)

### 1. Request Direction
* BuzzStore -> Publisher
 
### 2. HTTP Request method

* **POST**
 
### 3. HTTP Request URL

* Defined by publisher

### 4. HTTP Request Parameters

| Field | Type | Explanation |
|-----|----|-----|
| user_id | String | user_id defined by publisher|
| campaign_name | String | Campaign name to which points were given |
| point | Integer | Amount of point to be given to the user |

## 4. Other API Implementation
Here describes other APIs supported by BuzzStore. As only Server-to-Server communication is supported, API calls through pre-whitelisted IPs are allowed (as explained in 1. UserToken API Implementation). 

#### Point Balance Checking API
- Prior to the API integration, IP address of publisher server must be `whitelist`ed in BuzzStore server. The IP address should be sent by the publisher through a separate channel (eg. e-mail).
- The API retrieves the balance of a specific user A


###### Request 
- API Call Direction : Publisher server -> BuzzStore server
- method : `GET`
- url : `https://store-api.buzzvil.com/api/v1/points`
- Headers : Request with parameters below. 
    - `HTTP-X-BUZZVIL-APP-ID` : A unique ID that is issued to publisher application. 
    - `HTTP-X-BUZZVIL-API-TOKEN` : A unique API token issued previously for server-to-server API.
    - `HTTP-X-BUZZVIL-USER-ID`: Publisher user ID. 

e.g.
```
GET https://store-api.buzzvil.com/api/v1/points
```

> **Note** : Point Balance Checking API should NEVER be called directly from the client side. Need to be called by using Client <-> Publisher Server <-> BuzzStore Server communication method. 

- Returns `balance` in JSON format on success. HTTP response status code is 200.

e.g.
```JSON
{
    "balance": 2000
}
```
- Returns `error_code`, `error_message` in JSON format on fail.

#### Account Removal API
- Prior to the API integration, IP address of publisher server must be `whitelist`ed in BuzzStore server. The IP address should be sent by the publisher through a separate channel (eg. e-mail).
- This API removes a specific user account from BuzzStore.

###### Request
- API Call Direction : Publisher server -> BuzzStore server
- method : `POST`
- url : `https://store-api.buzzvil.com/api/v1/users/deactivate`
- Headers : Request with parameters below.
    - `HTTP-X-BUZZVIL-APP-ID` : A unique ID that is issued to publisher application. 
    - `HTTP-X-BUZZVIL-API-TOKEN` : A unique API token issued previously for server-to-server API.
    - `HTTP-X-BUZZVIL-USER-ID`: Publisher user ID.
    
###### Response
- On success, the HTTP response status code is 200.
- On failure, `error_code` and` error_message` are returned in JSON format.
