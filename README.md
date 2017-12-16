# BuzzScreen Migration Guide
- 기존 [버즈스크린을 연동](https://github.com/Buzzvil/buzzscreen-sdk-publisher)한 어플리케이션을 새로운 어플리케이션으로 마이그레이션을 하기 위한 가이드
    > 이하 기존 버즈스크린을 연동한 어플리케이션을 M(Main) 앱으로 지칭하고, 새로운 어플리케이션을 L(LockScreen) 앱으로 지칭
- 이 가이드를 통해 L앱의 설치과정을 제외한 모든 과정을 자동화하여 M앱의 버즈스크린 사용자를 L앱의 버즈스크린 사용자로 자연스럽게 전환 가능
    > L앱의 설치(APK 파일 다운로드 & 설치)는 유저의 액션없이 진행할 수 없기에 자동화가 불가능. 자동화의 범위 및 과정은 추후 기술
- **L앱 마이그레이션 주의** : L앱은 마이그레이션 가이드 작업전에 미리 버즈스크린 연동 작업이 M앱과 동일한 형태로 되어있어야 합니다. 
    > 마이그레이션 가이드는 M앱에서 L앱으로의 자연스러운(최소한의 유저 액션) 잠금화면 전환 작업만을 포함합니다.
- **마이그레이션 진행시 주의** : 마이그레이션을 진행하기 위해서는 **반드시 미리 버즈빌 BD 팀과 협의 후 진행** 을 해야하며, 가이드 모두 반영한 M앱과 L앱의 APK 파일들은 마켓에 업로드하기전에 버즈빌 BD 팀에 전달하여 **반드시 리뷰 후 마켓에 업로드**해야 합니다.
- 마이그레이션 가이드의 모든 작업은 `sample_main` 및 `sample_lock` 에서 확인할 수 있습니다.

## M앱 마이그레이션 작업
참고 샘플 : **`sample_main`**

### 1. 기존의 버즈스크린 연동은 변경하지 않습니다.

### 2. 버즈스크린 SDK 업데이트
마이그레이션은 버즈스크린 SDK 버전 1.6.0 이상에서 지원합니다. `build.gradle`에서 버즈스크린 버전이 `1.+` 인 경우는 자동으로 업데이트되기 때문에 추가작업이 필요없지만, 특정 버전으로 지정된 경우는 반드시 1.6.0 버전 이상을 사용해야 합니다.

아래와 같이 버즈스크린 라이브러리 추가 코드 확인.
```
dependencies {
    compile 'com.buzzvil:buzzscreen:1.+'
}

```

### 3. 마이그레이션 SDK 추가
모듈 내의 `build.gradle` 에 다음 코드를 추가합니다. **L앱과 설정이 다름에 주의**합니다.
    
```
dependencies {
    compile 'com.buzzvil:buzzscreen-migration-from:1.+'
}
```

### 4. Application Class 에 코드 추가
- `MigrationFrom.init(Context context, String lockScreenPackageName)`

    마이그레이션을 위한 M앱의 초기화 코드

    **Parameters**
    - `context` : Application context 를 `this` 로 전달
    -  `lockScreenPackageName` : L앱의 패키지명
    
- `MigrationFrom.bind(OnMigrationListener onMigrationListener)`

    마이그레이션 진행을 위한 함수입니다. 마이그레이션 과정을 `onMigrationListener`를 통해 수신받을 수 있고, 마이그레이션시에 M앱에서 L앱으로 옮기고 싶은 정보를 전달할 수 있습니다. **반드시 `MigrationFrom.init` 후에 호출**합니다. 

    **Parameters**
    - `onMigrationListener`
        - `onAlreadyMigrated` : 이미 마이그레이션이 진행된경우 호출됩니다.
        - `onMigrationStarted` : 마이그레이션 시작시 호출됩니다. 리턴값을 통해 M앱에서 L앱으로의 정보 전달이 가능합니다. M앱의 유저인증정보를 전달하여 L앱에서 추가적인 유저 액션 없이 자동로그인을 구현할 수 있습니다. 
        - `onMigrationCompleted` : 마이그레이션 종료시 호출됩니다. 마이그레이션 종료시 M앱에서 버즈스크린이 자동으로 off 됩니다. 만약 on/off 정보를 기록하고 있다면 여기서 off 상태로 변경바랍니다.  

**사용 예시**

```Java
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        
        // Do not touch. 기존 버즈스크린 초기화 코드.
        BuzzScreen.init("app_key", this, CustomLockerActivity.class, R.drawable.image_on_fail);
        
        // 마이그레이션을 위한 코드
        // L앱의 패키지명이 com.buzzvil.sample_lock 인 경우 사용 예시
        MigrationFrom.init(this, "\"com.buzzvil.sample_lock\"");
        MigrationFrom.bind(new MigrationFrom.OnMigrationListener() {
            @Override
            public void onAlreadyMigrated() {
            }

            @Override
            public Bundle onMigrationStarted() {
                // M앱에서 이미 설정한 버즈스크린 UserProfile, 잠금화면 on/off 정보는 자동으로 L앱으로 전달됩니다.
                // 그 외의 정보 전달을 리턴해주면 됩니다.
                // 반드시 유저 인증정보를 전달하여 L앱에서 유저의 추가 액션없이 자동 로그인이 되도록 구현바랍니다.
                // 여기서는 user_id 를 유저인증정보로 가정하고 전달하여 L앱에서 자동로그인을 구현하였습니다.
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
                // 마이그레이션 완료상태를 저장하고 싶은 경우 사용 예시
                PreferenceHelper.putBoolean(Constants.PREF_KEY_MIGRATION_COMPLETED, true);
            }
        });
    }
}

```




