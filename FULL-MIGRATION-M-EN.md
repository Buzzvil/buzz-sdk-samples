## M App Migration Guide

Reference : **`sample_main`**

### 1. Do NOT change the existing integration of Buzzscreen SDK.

### 2. Check for Buzzscreen SDK updates
Migration is supported in SDK version 1.6.3 or higher. If your current Buzzscreen version is `1.+` in `build.gradle`, no further work is required as SDK will be automatically updated. However, in case Buzzscreen version is specified in `build.gradle`, you need to explicitly use version 1.6.3 or higher.

Check if Buzzscreen library code is as below:
```groovy
dependencies {
    compile 'com.buzzvil:buzzscreen:1.+'
}

```

### 3. Modify `build.gradle`

#### Add `manifestPlaceholders`

```groovy
android {
    defaultConfig {
        // Please replace my_app_key with the app key given for BuzzScreen integration process
        manifestPlaceholders = [buzzScreenAppKey:"my_app_key"]
    }
}
```

#### Add the following to `dependencies`
**Please take extra caution of the library name as it's different from the L App**

```groovy
dependencies {
    compile "com.buzzvil.buzzscreen.ext:migration-from:$buzzscreenExtentionVersionName"
}
```

### 4. Add necessary codes to Application Class
- `MigrationFrom.init(Context context, String lockScreenPackageName)`

    M App initiation code for migration

    **Parameters**
    - `context` : pass the Application context with `this`.
    - `lockScreenPackageName` : the L App Package name

- `MigrationFrom.bind(OnMigrationListener onMigrationListener)`

    This method is for migration process. M App can receive migration process and/or send any user-related information from M app to L app using `onMigrationListener`. **This method MUST be called after `MigrationFrom.init`**

    **Parameters**
    - `onMigrationListener` : Migration listner.
        - `void onAlreadyMigrated()` : This method is called if migration is already proccessed.
        - `Bundle onMigrationStarted()` : This method is called once migration starts. Return value can be used to send user data to L App, such as user authentication information which can be used for automatic login on L app.
        - `void onMigrationCompleted()` : This method is called when migration is completed. Upon this method call, Buzzscreen on M App will be turned off by the SDK. In case you track the lockscreen on/off status separately, it has to be changed to off here.

    > Refer to [Migration Flow](MIGRATION-L-EN.md#Normal-Migration-Flow) for onMigrationListner process.

**Example**

```java
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Existing code to initialize Buzzscreen
        BuzzScreen.init("app_key", this, CustomLockerActivity.class, R.drawable.image_on_fail);

        // Initialization for migration
        // the example code uses com.buzzvil.buzzscreen.sample_lock for the L app package name
        MigrationFrom.init(this, "com.buzzvil.buzzscreen.sample_lock");
        MigrationFrom.bind(new MigrationFrom.OnMigrationListener() {
            @Override
            public void onAlreadyMigrated() {
            }

            @Override
            public Bundle onMigrationStarted() {
                // From M app, Buzzscreen UserProfile data and lockscreen activation status will be automatically transferred to L app.
                // All other information included in returned Bundle will be sent to M app during migration.
                // Sending user authentication information is recommended in order to implement automatic login process on L app. 
                // In this example, user_id is treated as user authentication information and used to implement automatic login on L App.
                if (isLoggedIn()) {
                    Bundle bundle = new Bundle();
                    bundle.putString("user_id", PreferenceHelper.getString(Constants.PREF_KEY_USER_ID, ""));
                    return bundle;
                } else {
                    return null;
                }
            }

            @Override
            public void onMigrationCompleted() {
                // example of storing migration completion status
                PreferenceHelper.putBoolean(Constants.PREF_KEY_MIGRATION_COMPLETED, true);
            }
        });
    }
}

```

### [L app migration guide](FULL-MIGRATION-L-EN.md)