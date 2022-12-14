# Communication Utils Guide
- BuzzScreen Extension SDK 에서는 M앱과 L앱 사이의 통신을 위한 유틸리티를 제공합니다.
> `buzzscreen-host`, `buzzscreen-client` SDK 버전 1.8.0 이상부터 지원됩니다.

- 이 유틸리티를 사용하면 M앱과 L앱은 서로를 통신의 엔드포인트로 등록을 하며, [protectionLevel="signature" 인 커스텀 퍼미션](https://developer.android.com/guide/topics/manifest/permission-element.html#plevel)을 사용하여 보안상 안전한 통신이 가능합니다.
- 총 3종류의 상황에 적합한 유틸리티를 제공합니다.

    - 공유되는 데이터 저장소가 필요한 경우 : DataStorage
    - 단방향 이벤트 전달이 필요한 경우 : EventHandler
    - 완전한 서버-클라이언트 구조가 필요한 경우 : RequestHandler

자세한 내용은 Buzzvil Developer Guides의 [Communication Utils](https://buzzvil.atlassian.net/wiki/spaces/BDG/pages/388202703/ver+1.8.x+BuzzScreen+Extension+SDK#Communication-Utils-Guide)항목을 참고해주세요.
