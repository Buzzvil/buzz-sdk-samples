# Communication Utils Guide
- The Migration SDK provides a utility module for facilitating communication between M App and L App.
> It's available in `migration-client`, `migration-host` SDK ver 0.9.3 or above.

- Through this module, M App and L App will register each other as endpoints of the communication and the data will be passed back and forth in a secure way using [a custom permission - protectionLevel="signature"](https://developer.android.com/guide/topics/manifest/permission-element.html#plevel)
- The module provides three kinds of communication methods tailored to its use case.

    - when a shared data storage is needed : [DataStorage](COMMUNICATION-UTILS.md#datastorage)
    - when a one-way event posting is needed : [EventHandler](COMMUNICATION-UTILS.md#eventhandler)
    - when a complete server-client architecture is needed : [RequestHandler](COMMUNICATION-UTILS.md#requesthandler)

## DataStorage

- DataStorage is useful when a shared data storage between L App and M App is needed.
- Data is stored in a key-value structure

> Use case: The migration SDK uses DataStorage to determine whether the lock screen of the L app is active. The L app updates the value in the DataStorage whenever the lock screen is activated or deactivated, and the M app reads the value from the DataStorage when it wants to check the status of the L app. 

### How to use it

Access a DataStorage instance through `MigrationXXX.getDataStorage()`

#### Saving data
- `void put(String key, String value)` : Stores value mapped to key.

> **For one key, put must be called on one side only**. Each app stores its data in its store when it calls put. It searches its store by calling get, and then searches the store of the other app if there is no value in its own store. If you put different values ​​for puts in both apps for the same key, they will be stored in their own repositories, so the values ​​will not be shared.

#### Data retrieval
- `String get(String key)` : Synchronously retrieves the value mapped to the key and returns it. 
- `void getAsync(String key, DataStorage.AsyncQueryListener listener)` : Asynchronously retrieves the value mapped to key and passes it to AsyncQueryListener.
    > DataStorage uses [ContentProvider] (https://developer.android.com/guide/topics/providers/content-providers.html). Due to the nature of ContentProvider, it may take a long time to process data. Therefore, the asynchronous method `getAsync () Is recommended.

    **Parameters**
    - `AsyncQueryListener`
        - `void onQueryComplete(String value)` : Invoked when the search is complete and value is passed as a parameter (null if none).


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

- This is used when the event is forwarded from the Sender to the Receiver only in one direction.
> The Sender and Receiver roles are not fixed for a specific app but depend on how it is used.
- Since the name of each event is the key, both the Sender and the Receiver must use the same event name for one event.
- Bundle is used to add additional information when delivering events.

> Use case: The Migration SDK uses EventHandler to disable the L app lock screen from the M app (requestDeactivation () of MigrationHost). The L app implements logic that disables the lock screen as a receiver, registers it as an event while the M app sends the event as a Sender.

### How to use

Access an EventHandler instance through `MigrationXXX.getEventHandler()`.

#### Sending events
- `void post(String eventName)` : Send an event to a Receiver.
- `void post(String eventName, Bundle extras)` : Sends an event to the Receiver with additional data in the form of a bundle.

#### Receiving events
**Note: Be sure to call this in Application Class.**
- `void registerEventListener(String eventName, EventHandler.OnEventListener listener)` : The logic for receiving a specific event is implemented in OnEventListener and registered by mapping it to eventName.

    **Parameters**
    - `OnEventListener`
        - `void onEvent(Bundle extras)` : Invoked when an event is received, and additional data sent by the Sender is passed as a parameter (if additional data does not exist, an empty Bundle is passed instead of a null object).

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

- Use when you need to organically process requests and responses between two apps, like a server-client structure.
> The Server and Client roles are not fixed for a specific app but depend on how it is used.
- Because each request-response pair is distinguished by a request code, both the server and the client must use the same request code.
- Bundle is used for data exchanged between request and response.

> Migration SDK uses RequestHandler to retrieve Buzzscreen settings data for M apps from L apps (checkAvailability() of MigrationClient). The L app requests the Buzzscreen settings data to the M app as a client, and the M app responds with the Buzzscreen settings data when it receives this request as a server. Through this response, the L app activates the lockscreen and handles other necessary logic.

### How to use

Get a RequestHandler instance through `MigrationXXX.getRequestHandler()`

#### Server

**Note: Be sure to call this in Application Class.**
- `void registerResponder(int requestCode, MsgRequestHandler.Responder responder)` : Implement a response when a request comes from a client.

    **Parameters**
    - `requestCode` : Unique code to distinguish requests
    - `Responder`
        - `Bundle respond(Bundle parameters)` : Called when a request comes from the client, and the parameters passed in are the additional parameters contained in the request. You must return the response in the Bundle.

#### Client
- `void request(int requestCode, Bundle params, Request.OnResponseListener listener)` : It sends a request to the server and implements a callback that handles the response.
    
    **Parameters**
    - `requestCode` : Unique code to distinguish requests
    - `params` : If you need to pass parameters along with requests, pass it in bundle form.
    - `OnResponseListener`
        - `void onResponse(Bundle response)` : Invoked when communication with the server app is successful, and the server's response data is passed as a parameter (if response data does not exist, an empty Bundle is passed instead of a null object).
        - `void onFail(Request.FailReason failReason)` : Invoked when communication with the server app fails. The cause of the failure is passed as a parameter.

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
