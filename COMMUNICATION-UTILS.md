# Communication Utils Guide
- 마이그레이션 SDK 에서는 M앱과 L앱 사이의 통신을 위한 유틸리티를 제공합니다.
> `migration-client`, `migration-host` SDK 버전 0.9.3 이상부터 지원됩니다.

- 이 유틸리티를 사용하면 M앱과 L앱은 서로를 통신의 엔드포인트로 등록을 하며, [protectionLevel="signature" 인 커스텀 퍼미션](https://developer.android.com/guide/topics/manifest/permission-element.html#plevel)을 사용하여 보안상 안전한 통신이 가능합니다.
- 총 3종류의 상황에 적합한 유틸리티를 제공합니다.

    - 공유되는 데이터 저장소가 필요한 경우 : [DataStorage](COMMUNICATION-UTILS.md#datastorage)
    - 단방향 이벤트 전달이 필요한 경우 : [EventHandler](COMMUNICATION-UTILS.md#eventhandler)
    - 완전한 서버-클라이언트 구조가 필요한 경우 : [RequestHandler](COMMUNICATION-UTILS.md#requesthandler)

## DataStorage

- 두 앱 사이에 공유되는 데이터 저장소가 필요한 경우 사용합니다.
- 저장소에서는 key-value 구조로 데이터를 관리합니다.

> 마이그레이션 SDK에서는 L앱의 잠금화면이 활성화 되어 있는지 여부를 확인할 때 사용하고 있습니다. L앱에서 잠금화면이 활성화 되거나 비활성화 될 때마다 DataStorage 에 값을 업데이트 시키며, M앱에서는 L앱의 상태를 확인하고 싶을 때 DataStorage 로부터 값을 읽습니다.

### 사용 방법

`MigrationXXX.getDataStorage()` 를 통해 DataStorage instance를 가져옵니다.

#### 데이터 저장
- `void put(String key, String value)` : key 에 매핑되는 value 를 저장합니다.

> **하나의 키에 대해서 put 은 반드시 한쪽에서만 호출해야 합니다**. 각 앱은 put 을 호출하면 데이터를 자신의 저장소에 저장하며, get 을 호출하면 자신의 저장소를 검색한 후 값이 없으면 상대편 앱의 저장소를 검색합니다. 만약 같은 키에 대해 두 앱 모두에서 put 을 통해 서로 다른 값을 넣으면 서로 자신의 저장소에 각각 저장하므로 값이 공유가 되지 않습니다. 

#### 데이터 검색
- `String get(String key)` : key 에 매핑된 value 를 Synchronous 하게 검색한 후 리턴합니다. 
- `void getAsync(String key, DataStoage.AsyncQueryListener listener)` : key 에 매핑된 value 를 Asynchronous 하게 검색해서 AsyncQueryListener 에 전달합니다.
    > DataStorage 는 [ContentProvider](https://developer.android.com/guide/topics/providers/content-providers.html) 를 사용하는데 ContentProvider 특성상 데이터 처리시 소요 시간이 길어질 수 있으므로 비동기 방법인 `getAsync()` 를 사용하는 것을 권장합니다.

    **Parameters**
    - `AsyncQueryListener`
        - `void onQueryComplete(String value)` : 검색이 완료되면 호출되며 파라미터로 value 가 전달됩니다(없을 경우 null 전달).


### Code Example
```java
MigrationXXX.getDataStorage().put("SHARED_CONFIG_KEY", "config_value");

// Synchronous
String value = MigrationXXX.getDataStorage().get("SHARED_CONFIG_KEY");
// Asynchronous
MigrationXXX.getDataStorage().getAsync("SHARED_CONFIG_KEY", new DataStorage.AsyncQueryListener() {
    @Override
    public void onQueryComplete(String value) {
    }
});
```


## EventHandler

- Sender 로부터 Receiver 에게로 한쪽 방향으로만 이벤트를 전달하는 경우 사용합니다.
> Sender, Receiver 역할은 특정 앱에 대해 고정된 것이 아니라 사용 방법에 따라 달라집니다.
- 각 이벤트의 이름이 키가 되므로 Sender 와 Receiver 모두 하나의 이벤트에 대해 동일한 이벤트 이름을 사용해야 합니다.
- 이벤트 전달시 추가적으로 넣을 정보는 Bundle 을 이용합니다.

> 마이그레이션 SDK에서는 M앱에서 L앱의 잠금화면을 비활성화 시킬 때 사용하고 있습니다(MigrationHost의 requestDeactivation()). L앱이 Receiver 로서 잠금화면을 비활성화시키는 로직을 구현해서 이벤트로 등록해 두고, M앱이 Sender 로서 해당 이벤트를 전송합니다.

### 사용 방법

`MigrationXXX.getEventHandler()` 를 통해 EventHandler instance를 가져옵니다.

#### 이벤트 전송
- `void post(String eventName)` : 이벤트를 Receiver 에게 보냅니다.
- `void post(String eventName, Bundle extras)` : Bundle 형태의 추가 데이터를 담아서 이벤트를 Receiver 에게 보냅니다.

#### 이벤트 수신
- `void registerEventListener(String eventName, EventHandler.OnEventListener listener)` : 특정 이벤트를 받았을 때의 로직을 OnEventListener를 통해 구현해서 eventName과 매핑해 등록합니다.
    > 반드시 Application Class 에서 호출되어야 합니다.
    
    **Parameters**
    - `OnEventListener`
        - `void onEvent(Bundle extras)` : 이벤트를 받았을 때 호출되며 파라미터로 Sender 에서 보낸 추가 데이터가 전달됩니다(없을 경우 null 이 아닌 빈 Bundle 전달).

### Code Example
```java
// Sender
Bundle eventData = new Bundle();
eventData.putString("extra_info", "extra_value");
MigrationXXX.getEventHandler().post("SAMPLE_EVENT", eventData);

// Receiver
MigrationXXX.getEventHandler().registerEventListener("SAMPLE_EVENT", new EventHandler.OnEventListener() {
    @Override
    public void onEvent(Bundle extras) {
        Log.d(TAG, "onReceive SAMPLE_EVENT");
        String extraInfo = extras.getString("extra_info"); // "extra_value" returned
        ...
    }
});

```


## RequestHandler

- 서버-클라이언트 구조처럼 두 앱간 유기적인 요청과 응답 처리가 필요한 경우 사용합니다.
> 서버, 클라이언트 역할은 특정 앱에 대해 고정된 것이 아니라 사용 방법에 따라 달라집니다.
- 각 요청과 응답 쌍은 request code 를 통해 구별이 되므로 서버와 클라이언트 모두 같은 request code를 사용해야 합니다.
- 요청과 응답 시 주고받는 데이터는 Bundle 을 이용합니다.

> 마이그레이션 SDK에서는 L앱에서 M앱의 버즈스크린 사용 정보를 가지고 올 때 사용하고 있습니다(MigrationClient의 checkAvailability()). L앱이 클라이언트로서 M앱에 버즈스크린 사용 정보를 요청하며, M앱은 서버로서 이 요청을 받으면 버즈스크린 사용 정보를 담아 응답합니다. L앱은 이 응답을 통해 잠금화면을 활성화하는 등의 로직을 처리합니다.

### 사용 방법

`MigrationXXX.getRequestHandler()` 를 통해 RequestHandler instance를 가져옵니다.

#### 서버
- `void registerResponder(int requestCode, MsgRequestHandler.Responder responder)` : 클라이언트로부터 요청이 왔을 때의 응답을 구현합니다.
    > 반드시 Application Class에서 호출되어야 합니다.

    **Parameters**
    - `requestCode` : 요청을 구분하는 고유 코드
    - `Responder`
        - `Bundle respond(Bundle parameters)` : 클라이언트로부터 요청이 왔을 경우 호출되며, 파라미터로는 요청에 담긴 추가 파라미터가 전달됩니다. 클라이언트에 보낼 응답을 Bundle 에 담아서 리턴해야 합니다.

#### 클라이언트
- `void request(int requestCode, Bundle params, Request.OnResponseListener listener)` : 서버에 요청을 보내고, 요청에 대한 응답이 왔을 때의 콜백을 구현합니다.
    
    **Parameters**
    - `requestCode` : 요청을 구분하는 고유 코드
    - `params` : 요청시 보낼 데이터가 필요한 경우 Bundle 형태로 전달합니다.
    - `OnResponseListener`
        - `void onResponse(Bundle response)` : 서버 앱과의 통신에 성공한 경우 호출되며 파라미터로 서버의 응답 데이터가 전달됩니다(없을 경우 null 이 아닌 빈 Bundle 전달).
        - `void onFail(Request.FailReason failReason)` : 서버 앱과의 통신에 실패한 경우 호출됩니다. 실패의 원인이 파라미터로 전달됩니다.

### Code Example
```java
// Server
MigrationXXX.getRequestHandler().registerResponder(1000, new MsgRequestHandler.Responder() {
    @Override
    public Bundle respond(Bundle parameters) {
        Bundle response = new Bundle();
        response.putString("response_key", "response_sample");
        return response;
    }
});

// Client
MigrationXXX.getRequestHandler().request(1000, null, new Request.OnResponseListener() {
    @Override
    public void onResponse(Bundle response) {
        String value = response.getString("response_key"); // "response_sample" returned
        ...
    }

    @Override
    public void onFail(Request.FailReason failReason) {
    }
});
```
