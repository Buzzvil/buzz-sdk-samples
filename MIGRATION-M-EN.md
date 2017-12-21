## M App Migration Guide

Reference : **`sample_main`**

### 1. Do NOT change the original integration of Buzzscreen.

### 2. Check Buzzscreen SDK update
Migration is supported for SDK version 1.6.0 or higher. If your current Buzzscreen version is `1.+` in `build.gradle`, no further work is required as SDK will be automatically updated. However, in case where Buzzscreen version is specified in `build.gradle`, you need to explicitly use version 1.6.0 or higher. 

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
        // use Buzzscreen app key for my_app_key value
        manifestPlaceholders = [buzzScreenAppKey:"my_app_key"]
    }
}
```

#### Add the following to `dependencies`
**Please take extra caution of the library name**

```groovy
dependencies {
    compile 'com.buzzvil.buzzscreen.ext:migration-from:+'
}
```

### 4. Add necessary codes to Application Class
- `MigrationFrom.init(Context context, String lockScreenPackageName)`

    M App initiation code for migration

    **Parameters**
    - `context` : pass `this` for Application context. 
    - `lockScreenPackageName` : L App Package name

- `MigrationFrom.bind(OnMigrationListener onMigrationListener)`

    This method is for migration process. M App can receive migration process and/or send any user information from M app to L app using `onMigrationListener`. **This method MUST be called after `MigrationFrom.init`**

    **Parameters**
    - `onMigrationListener` : Migration listner.
        - `void onAlreadyMigrated()` : This method is called if migration is already proccessed.
        - `Bundle onMigrationStarted()` : This method is called once migration starts. Return value can be used to send user data to L App, such as user authentication information which can be used for automatic login on L app.
     여기다시보기   - `void onMigrationCompleted()` : This method is called when migration is completed. With this method, Buzzscreen on M App will be turned off. In case on/off status is being recorded, it has to be changed to off here. 

    > Refer to [Migration Flow](MIGRATION-L-EN.md#Normal-Migration-Flow) for onMigrationListner process.

**Example**

```java
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // original initiation code for Buzzscreen.
        BuzzScreen.init("app_key", this, CustomLockerActivity.class, R.drawable.image_on_fail);

        // code for migration
        // example code uses com.buzzvil.buzzscreen.sample_lock for L app package name
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

### [L app migration guide](MIGRATION-L.md)