## M앱 마이그레이션 구현

참고 샘플 : **`sample_main_light`**

- M앱은 마이그레이션 이후에도 L앱에서 잠금화면 활성화에 필요한 정보들을 제공해주는 역할을 합니다.
    > M앱에서 정보를 가져올 수 없는 경우 L앱에서는 잠금화면을 사용할 수 없고, 이 경우 유저에게 M앱의 잠금화면 활성화 화면을 통해 활성화하도록 유도합니다. 마이그레이션 SDK를 통해 제공하는 [새로운 활성화 함수](LIGHT-MIGRATION-M.md#3-잠금화면-활성화-화면-변경)를 통해 이 과정이 진행됩니다.  
- L앱은 항상 실행 시에 M앱의 상태를 체크하여 L앱의 잠금화면 활성화 가능 여부를 판단합니다.
    > M앱이 제거되면 자동으로 L앱에서의 잠금화면이 비활성화됩니다.

### 1. `build.gradle` 설정

#### `manifestPlaceholders` 추가

```groovy
android {
    defaultConfig {
        // my_app_key 에는 버즈스크린 연동 시 발급받은 앱키를 입력합니다.
        manifestPlaceholders = [buzzScreenAppKey:"my_app_key"]
    }
}
```

#### `dependencies` 에 추가
M앱을 위한 마이그레이션 라이브러리를 추가하고, 버즈스크린 버전 1.6.3이상으로 업데이트해야 합니다.
> 버즈스크린 버전이 `1.+` 인 경우는 자동으로 업데이트되기 때문에 추가작업이 필요없지만, 특정 버전으로 지정된 경우는 반드시 1.6.3 버전 이상으로 지정 필요.

```groovy
dependencies {
    
    // 기존 버즈스크린 연동
    compile 'com.buzzvil:buzzscreen:1.+'
    
    // M앱을 위한 마이그레이션 라이브러리. L앱과 다름에 주의!
    compile 'com.buzzvil.buzzscreen.ext:migration-host:0.9.6'
}
```


### 2. Application Class 에 코드 추가
기존 버즈스크린 연동을 위해 추가한 `BuzzScreen.init` 다음에 `MigrationHost.init`를 호출합니다.

- `MigrationHost.init(Context context, String lockScreenPackageName)`

    마이그레이션을 위한 M앱의 초기화 코드

    **Parameters**
    - `context` : Application context 를 `this` 로 전달
    - `lockScreenPackageName` : L앱의 패키지명
    
**사용 예시**

```java
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // 기존 버즈스크린 초기화 코드.
        BuzzScreen.init("app_key", this, SimpleLockerActivity.class, R.drawable.image_on_fail);

        // 마이그레이션을 위한 초기화
        // L앱의 패키지명이 com.buzzvil.buzzscreen.sample_lock_light 인 경우 사용 예시
        MigrationHost.init(this, "com.buzzvil.buzzscreen.sample_lock_light");
        
        // L앱에서 버즈스크린이 활성화되어 M앱에서 잠금화면이 비활성화 되는 경우 호출되는 리스너 등록 예시
        MigrationHost.setOnDeactivatedByLockScreenAppListener(new MigrationHost.OnDeactivatedByLockScreenAppListener() {
            @Override
            public void onDeactivated() {
                Log.i("MainApp", "LockScreen is deactivated by LockScreen App");
            }
        });
    }
}

```


### 3. 잠금화면 활성화 화면 변경
L앱은 M앱의 유저 정보로 잠금화면을 활성화 할 뿐 직접 유저에게 정보나 동의를 받는 과정이 없습니다. 이 때문에 L앱에서는 기존 M앱의 잠금화면 활성화 화면(유저 프로필, 사용동의, 활성화/비활성화 설정 화면)을 변경하여 사용하게 됩니다.
- 잠금화면 활성화 화면 액티비티로 이동하는 [딥링크](https://developer.android.com/training/app-links/deep-linking.html)를 설정합니다. 
    > L앱에서 잠금화면 활성화에 필요한 정보나 유저 사용 동의가 필요한 경우 M앱의 잠금화면 활성화 화면으로 이동시켜 이 과정을 진행합니다. 만약 해당 딥링크가 없으면 M앱의 초기화면을 실행합니다. 
- 앱 내의 잠금화면 활성화 화면에서 활성화/비활성화를 설정하는 스위치를 배너 형태로 변경합니다. 해당 배너 클릭시 혹은 잠금화면 사용 동의를 받고 잠금화면을 활성화 하는 곳(기존 `BuzzScreen.getInstance().activate()` 호출하는 지점과 동일)에 다음 함수를 호출합니다.
    > 마이그레이션을 지원하는 M앱 버전에선 이 배너가 L앱으로 마이그레이션을 수행하는 하나의 채널이 될 뿐만 아니라, M앱에서 잠금화면을 처음으로 사용하려는 유저를 L앱으로 유도하는 채널이 되기도 합니다.

    - `MigrationHost.requestActivationWithLaunch()`

        L앱을 통해 잠금화면을 활성화합니다. L앱이 설치 안된 경우 마켓을 통해 설치 후 자동으로 활성화되고, L앱이 설치된 경우 L앱을 실행하면서 자동으로 잠금화면이 활성화됩니다.
        > `MigrationHost.requestActivationWithLaunch()` 을 호출하기 전에도 이전 버즈스크린 연동처럼 `BuzzScreen.getInstance().getUserProfile()`를 통해 [유저 정보 설정](https://github.com/Buzzvil/buzzscreen-sdk-publisher#2-%EC%9C%A0%EC%A0%80-%EC%A0%95%EB%B3%B4-%EC%84%A4%EC%A0%95)하는 코드는 미리 호출되어야 합니다. 이 정보를 그대로 L앱에서 활용하여 잠금화면이 활성화됩니다.

#### L앱 잠금화면 활성화 과정
![Light Activation Flow From M](light_activation_flow_from_m.jpg)


### 4. 로그아웃 처리
M앱에서 로그아웃이 일어나는 경우 `BuzzScreen.getInstance().logout()`, `MigrationHost.requestDeactivation()` 를 호출합니다.
> L앱에서의 잠금화면을 비활성화하고, 다음 활성화 시에 L앱에서 새로운 유저 정보를 M앱으로부터 제공받기 위해서입니다.

- `BuzzScreen.getInstance().logout()`

    M앱에서 잠금화면을 비활성화하고, 유저정보를 초기화합니다.
 
- `MigrationHost.requestDeactivation()`
 
    L앱에서 잠금화면이 활성화되어 있는 경우 해당 잠금화면을 비활성화합니다.


### 5. 버즈스크린 활성화/비활성화 관련 코드 제거
잠금화면 활성화/비활성화를 위해 삽입했던 `BuzzScreen.getInstance().activate()`, `BuzzScreen.getInstance().deactivate()`를 모두 제거합니다. 단, 잠금화면에서 바로 잠금화면 비활성화를 제공하는 경우에는 해당 `BuzzScreen.getInstance().deactivate()` 호출만 제거하지 않습니다.
> 마이그레이션 SDK 연동 이후에는 마이그레이션 SDK에서 M앱의 버즈스크린 라이프사이클이 자동으로 관리되고, **유저경험을 위해 잠금화면에서만 잠금화면 비활성화 버튼을 제공**하게 됩니다.


### 그외 유용한 함수들

- `MigrationHost.isLockScreenAppActivated()`
 
    L앱에서 잠금화면이 활성화되어 있으면 `true`, 비활성화되어 있으면 `false`를 리턴합니다. 

- `MigrationHost.requestUserProfileSync()`

    기존 M앱에서 주기적으로 유저 아이디가 변경되는 경우에는, `setUserId(String string)` 호출 이후에 이 함수를 호출하여 L앱에도 변경된 유저 정보값으로 동기화를 합니다. 이 함수를 호출하지 않으면 L앱에서는 다음 `checkAvailability` 호출 전까지는 변경된 유저 정보가 반영되지 않습니다.

- `MigrationHost.setLockScreenAppMarketLink(String link)`

    `requestActivationWithLaunch()` 통해 L앱 설치하는 경우, 패키지명으로 생성된 기본 마켓 uri 가 아닌 유입 경로 분석을 위해 커스텀 링크를 적용하고 싶으면 이 함수를 사용합니다. `requestActivationWithLaunch()` 전에 설정해야 적용됩니다.
    
    **Parameters**
        - `link` : 커스텀 마켓 링크
        
- `MigrationHost.requestActivation(OnRequestActivateResponseListener listener)`

    L앱의 잠금화면을 활성화 합니다. `MigrationHost.requestActivationWithLaunch()` 와 다른 점은 L앱의 실행없이 L앱의 잠금화면이 활성화 된다는 점입니다. 잠금화면을 활성화 할때는 M앱에서 설정된 유저 정보를 사용하게 됩니다.
    
    **주의 : M앱의 MigrationHost 0.9.4 이상에서 호출되어야 하며, L앱 역시 MigrationClient 0.9.4 이상이 적용되어야 합니다.**
     
    **Parameters**
    - `OnRequestActivateResponseListener`
        - `onAlreadyActivated()` : L앱에서 버즈스크린이 이미 활성화가 되어있는 경우 호출됩니다.
        - `onActivated()` : L앱에서 버즈스크린이 활성화가 된 경우 호출됩니다.
        - `onError(RequestActivationError error)` : L앱의 버즈스크린 활성화에 실패한 경우 호출됩니다.
            - `LOCKSCREEN_APP_NOT_INSTALLED` : L앱이 설치되지 않아 활성화에 실패한 경우
            - `LOCKSCREEN_APP_MIGRATION_NOT_SUPPORTED` : L앱에서 마이그레이션 연동이 되지 않아 활성화에 실패한 경우
            - `UNKNOWN_ERROR` : 잘못된 연동 혹은 일시적인 에러로 발생한 경우
    

### [L앱 마이그레이션 구현하러 가기](LIGHT-MIGRATION-L.md)
