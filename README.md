# BuzzScreen Extension JS SDK Guide
> [Korean Version Link](BUZZSCREEN-EXTENSION-JS-SDK-GUIDE-KR.md)

* This extension SDK helps publishers to work with [BuzzScreen](https://github.com/Buzzvil/buzzscreen-sdk-publisher/wiki/README_EN) which shows personalized contents and ads on Android lockscreen.
* In order to use personalization features in BuzzScreen, user information is required. Using this SDK, the information can be passed from publisher's website to BuzzScreen app with simple integration.
* This guide explains how to apply JS SDK to publisher's website.

## Sign in and point accumulation request flow
![Task Flow](buzzscreen-extension-js-sdk-flow.png)

1. When a user tries to login by clicking a sign-in button on the BuzzScreen Android client, publisher's web frontend opens via [WebView](https://developer.android.com/reference/android/webkit/WebView.html) and is shown to the user.
2. The user attempts to sign in from the web frontend on the [WebView](https://developer.android.com/reference/android/webkit/WebView.html).
3. The publisher's web frontend receives the user information and passes it to the publisher's server.
4. When the user is successfully signed-in, publisher's server forwards the session information to the web frontend.
5. (Publisher's task) The publisher's web frontend confirms that the user has signed in and calls the BuzzScreen Extension JS SDK function to pass user information.
6. BuzzScreen Extension JS SDK passes user information to Android client through [Javascript Interface](https://developer.android.com/guide/webapps/webview.html#BindingJavaScript) provided by Android. This information is then passed to the BuzzScreen server, which allows the Android client to serve contents and ads.
7. The Android client uses the BuzzScreen SDK to communicate with the BuzzScreen server upon user action such as landing or unlock.
8. The BuzzScreen server passes the userId and point information to the publisher's server to indicate how many points the particular user should receive.
9. (Publisher's task) The publisher's server receives the above information and gives points to the user.

## Publisher's task
What the publisher needs to do is marked in blue in the diagram above.

* Sign-in integration: If a user is successfully signed in from the publisher's website, this sdk should be used to pass user information to BuzzScreen SDK. This is explained in detail in the rest of this guide.
* Postback integration: Publisher's server needs to be integrated with Buzzscreen server in order to give points to users for valid user action. Please refer to [this guide for postback APIs](https://github.com/Buzzvil/buzzscreen-sdk-publisher/blob/master/docs/POSTBACK_EN.md).

## Sign-in integration
For the sign-in integration, the publisher should inform Buzzvil of the **sign-in verification URL**. Also  **SDK integration** is required on result pages for both users who has signed in successfully and who hasn't.

### 1. Sign-in verification URL

![Sign-in verification URL](buzzscreen-extension-js-sdk-sign-in-verification-url.png)

The sign-in verification URL is required to check whether the user has signed in or not. Signed-in users and non-signed-in users must see different result pages at the same URL depending on their sign-in status. Different functions of this SDK should be called on each page to distinguish whether the user has signed in or not.

For example, suppose that `https://www.buzzvil.com/profile` is a sign-in verification URL. When a user who already signed in accesses this address, a page with the account information of the user will be shown (indicated as **personal page** in the above diagram). On this page, SDK should be loaded and `Buzzscreen.notifySignedIn()` should be called in order to pass the user information to BuzzScreen.

On the other hand, when a user who hasn't signed in accesses this address, a login page asking for Id and password is shown (indiacted as **login page**). On this page, SDK should be loaded and `Buzzscreen.notifyNotSignedIn()` should be called in order to notify BuzzScreen that user is not signed in.

To summarize the role of each page,
* For user who has signed in: show result page and notify "signed in" to BuzzScreen
* For user who has not signed in: show login page and notify "not signed in" to BuzzScreen


## How to integrate JS SDK

Insert this SDK on each of the two pages accessible by the "Sign-in verification URL".
```html
<script src="buzzscreen-extension-0.0.1.js"></script>
```

### Notify "signed in"

On **personal page** from the above diagram (the page shown when the user has signed in), call the following JavaScript code to pass the user information (user ID, year of birth and gender) and to notify Buzzscreen.
```html
<script>
BuzzScreen.notifySignedIn('userId', 1990, BuzzScreen.GENDER_MALE);
BuzzScreen.notifySignedIn('userId', 1990, BuzzScreen.GENDER_FEMALE);
</script>
```
The type of each argument is `string`, `number`, `string` in that order. Please take extra caution not to use wrong argument types. If the arguments are set incorrectly, error logs are left in the console window.

In case where users' birthYear and gender information is not available, the following code can be called instead.
> Note: If this information is not set, targeted ads will not be allocated to lockscreen, which will reduce the total number of ads users can see.
```html
<script>
BuzzScreen.notifySignedIn('userId');
</script>
```

### Notify "not signed in"

On **login page** (the page shwon when not-signed-in user access the verification url), call the following JavaScript code:
```html
<script>
BuzzScreen.notifyNotSignedIn();
</script>
```

## Functions for SDK configuration convenience

### Check if the page is loaded in BuzzScreen [Webview](https://developer.android.com/reference/android/webkit/WebView.html)
The following API returns `true` if a page loaded from BuzzScreen [Webview](https://developer.android.com/reference/android/webkit/WebView.html), or `false`.
```html
<script>
const result = BuzzScreen.isBuzzScreenWebView();
</script>
```

### See detailed logs
If you set the verbose option to `true`, you can also view logs at the `console.info` level.
```html
<script>
BuzzScreen.setVerbose(true);
BuzzScreen.notifySignedIn('userId', 1990, BuzzScreen.GENDER_MALE);
</script>
```
