## Light Migration SDK Integration
- M앱에서 기존에 구현되어 있는 기능과 기존 버즈스크린 연동을 최대한 활용하여, 최소한의 연동 코스트로 L앱을 제작할 수 있는 마이그레이션 방식입니다.
    - **L앱 자체 로그인 시스템 구축 없이** : 버즈스크린 연동에 필요한 유저 프로필 정보는 기존처럼 M앱에서 수집하고, 마이그레이션 SDK를 통해 L앱으로 자동으로 전달됩니다.
    - **L앱 잠금화면 커스터마이징 없이** : `SimpleLockerActivity`를 사용하여 잠금화면 커스터마이징 없이 L앱 제작이 가능합니다.
- L앱 샘플(`sample_lock_light`)을 다운받고, 약간의 설정 변경만으로도 마켓에 업로드 가능한 앱을 제작할 수 있습니다. 
- 마이그레이션 가이드의 모든 작업은 `sample_main_light` 및 `sample_lock_light` 에서 확인할 수 있습니다.
    > 샘플앱의 동작을 위해서는 샘플앱 내의 `my_app_key`(`build.gradle`, `BuzzScreen.init` 확인), `AndroidManifest.xml`의 `<app_license>`와 `<plist>`를 발급받은 값으로 교체해주세요. 
- M앱과 L앱 각각 마이그레이션을 위한 추가 연동작업은 다음 링크들을 통해 확인할 수 있습니다. 

#### [M앱 마이그레이션 구현](LIGHT-MIGRATION-M.md)
#### [L앱 마이그레이션 구현](LIGHT-MIGRATION-L.md)
