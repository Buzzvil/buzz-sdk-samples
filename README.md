# BuzzScreen Migration SDK Integration Guide
- 구글 정책 대응을 위해, 기존 [버즈스크린을 연동](https://github.com/Buzzvil/buzzscreen-sdk-publisher)한 어플리케이션의 잠금화면을 새로운 잠금화면 전용 어플리케이션의 잠금화면으로 마이그레이션을 수행하기 위한 가이드입니다.
    > 이하 기존 버즈스크린을 연동한 어플리케이션을 M(Main) 앱으로 지칭하고, 새로운 잠금화면 전용 어플리케이션을 L(LockScreen) 앱으로 지칭 
- 이 SDK의 연동작업을 통해, L앱의 설치과정을 제외한 모든 과정을 자동화하여 M앱의 버즈스크린 사용자를 L앱의 버즈스크린 사용자로 자연스럽게 전환 가능합니다.
    > L앱의 설치(APK 파일 다운로드 & 설치)는 유저의 액션없이 진행할 수 없기에 자동화 불가능
- 마이그레이션 동작을 위해 M앱과 L앱은 서로 정보를 주고 받는데, 다른 앱에서의 접근을 막기 위해 반드시 **동일한 서명으로 APK 생성**해야 합니다.
    > 다른 앱에서의 접근을 막기 위해 안드로이드의 [protectionLevel="signature"](https://developer.android.com/guide/topics/manifest/permission-element.html#plevel) 권한 사용을 위함
- **L앱 마이그레이션 주의** : L앱은 마이그레이션 SDK 연동전에 미리 버즈스크린 연동 작업이 M앱과 동일한 형태로 되어있어야 합니다.
    > 마이그레이션 SDK는 M앱에서 L앱으로의 자연스러운(최소한의 유저 액션) 잠금화면 전환 작업만을 포함
- **마이그레이션 진행시 주의** : 마이그레이션을 진행하기 위해서는 **반드시 미리 버즈빌 BD 팀과 협의 후 진행** 을 해야하며, 가이드 모두 반영한 M앱과 L앱의 APK 파일들은 마켓에 업로드하기전에 버즈빌 BD 팀에 전달하여 **반드시 리뷰 후 마켓에 업로드**해야 합니다.
- 마이그레이션 가이드의 모든 작업은 `sample_main` 및 `sample_lock` 에서 확인할 수 있습니다.
    > 샘플앱의 동작을 위해서는 샘플앱내의 `my_app_key`(`build.gradle`, `BuzzScreen.init` 확인), `AndroidManifest.xml`의 `<app_license>`와 `<plist>`를 발급받은 값으로 교체해주세요. 
- M앱과 L앱 모두 마이그레이션을 위한 추가 연동작업이 있으며 다음 링크들을 통해 확인할 수 있습니다. 

### [M앱 마이그레이션 구현](MIGRATION-M.md)
### [L앱 마이그레이션 구현](MIGRATION-L.md)

