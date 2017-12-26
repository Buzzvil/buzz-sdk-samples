# BuzzScreen Migration SDK Integration Guide
- 구글 정책 대응을 위해, 기존 [버즈스크린을 연동](https://github.com/Buzzvil/buzzscreen-sdk-publisher)한 어플리케이션의 잠금화면을 새로운 잠금화면 전용 어플리케이션의 잠금화면으로 마이그레이션 하기 위한 가이드입니다.
    > 이하 기존 버즈스크린을 연동한 어플리케이션을 M(Main) 앱으로 지칭하고, 새로운 잠금화면 전용 어플리케이션을 L(LockScreen) 앱으로 지칭 
- 이 SDK 의 연동작업을 통해, L앱의 설치과정을 제외한 모든 과정을 자동화하여 M앱의 버즈스크린 사용자를 L앱의 버즈스크린 사용자로 자연스럽게 전환 가능합니다.
    > L앱의 설치(APK 파일 다운로드 & 설치)는 유저의 액션없이 진행할 수 없기에 자동화 불가능
- 마이그레이션 동작을 위해 M앱과 L앱은 서로 정보를 주고 받는데, 다른 앱에서의 접근을 막기 위해 반드시 **동일한 서명으로 APK 생성**해야 합니다.
    > 다른 앱에서의 접근을 막기 위해 안드로이드의 [protectionLevel="signature"](https://developer.android.com/guide/topics/manifest/permission-element.html#plevel) 권한 사용을 위함
- **마이그레이션 진행시 주의** : 마이그레이션을 진행하기 위해서는 **반드시 미리 버즈빌 BD 팀과 협의 후 진행** 을 해야하며, 가이드 모두 반영한 M앱과 L앱의 APK 파일들은 마켓에 업로드하기전에 버즈빌 BD 팀에 전달하여 **반드시 리뷰 후 마켓에 업로드**해야 합니다.
- M앱과 L앱 모두 마이그레이션을 위한 추가 연동작업이 있으며 다음 링크들을 통해 확인할 수 있습니다.
 
## 마이그레이션 구현
M앱과 L앱 모두 마이그레이션을 위한 추가 연동작업이 있으며 두가지 방식을 제공하고 있습니다.
- [**FULL MIGRATION**](FULL-MIGRATION.md) : 독립적인 L앱(L앱 내에 자체 로그인 및 로그인 정보 기반 잠금화면 커스텀 기능 제공) 개발이 가능한 경우
    > 마이그레이션 이후에는 M앱에 대한 의존성이 전혀 없습니다.
- [**LIGHT MIGRATION**](LIGHT-MIGRATION.md) : L앱 내에 자체 로그인 기능 구현이 어렵거나 M앱의 로그인 관리를 그대로 L앱에서 사용하고 싶은 경우
    > 버즈스크린 활성화에 필요한 유저 정보(유저아이디, 나이, 성별 등) 및 잠금화면 활성화 동의 여부를 앱 간의 통신을 통해 M앱에서 L앱으로 가져올 수 있도록 SDK에서 지원합니다. 

최소한의 연동 코스트로 L앱을 만들기 위해서는 **LIGHT MIGRATION** 방식을 추천합니다. 

### [FULL MIGRATION 구현하러 가기](FULL-MIGRATION.md)
### [LIGHT MIGRATION 구현하러 가기](LIGHT-MIGRATION.md)
