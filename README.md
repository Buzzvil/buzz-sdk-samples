# Buzzscreen Extension JS SDK Guide

* An extension SDK that helps you to work with the [BuzzScreen](https://github.com/Buzzvil/buzzscreen-sdk-publisher) which shows personalized content and ads on Android lockscreen.
* You need user information to use the personalization feature in BuzzScreen, and you need to pass user information to the Buzzscreen app via this SDK. 
* This guide explains how you can apply this SDK to publisher's web site.

## Sign in and point accumulation request flow
![Task Flow](buzzscreen-extension-js-sdk-flow.png)

## What publishers should do
What the publisher needs to do is shown in blue in the figure above.

* Sign in integration: If the user is successfully signed in from the publisher's website, this sdk should be used to pass user information to BuzzScreen. This is explained in detail in the rest of this guide.
* Postback integration: If the user gets points to earn by doing something like landing on an ad, the publisher's server needs to be integrated to receive the notification and take appropriate action. Please refer to [this guide for postback APIs](https://github.com/Buzzvil/buzzscreen-sdk-publisher/blob/master/docs/POSTBACK_EN.md).

## Sign in integration
For the integration of the login, the publisher should notify Buzzvil of the 'sign in verification URL' and the 'sign out URL' and set up 'sign in verification URL' for SDK integration according to the following.

### 1. Sign in verification URL

The sign in verification URL is necessary to check whether the user has signed in or not. To do this, the signed-in user and the non-signed-in user must see different pages at the same URL. In this case, you can call the different functions of this SDK on each page to distinguish whether the user has signed in or not.
For example, suppose here is an address `https://www.buzzvil.com/profile`. This address is for accessing the page with the account information of the user logged into the site. Then, you can notify that the user has signed in by loading this SDK from the user account information page, calling API and passing the user information to Buzzscreen.
If a user who doesn't signed in accesses the above address, a login page asking for ID and password is shown. Then, you can notify that the user doesn't signed in by loading this SDK from the login page and calling API.

The approximate role of each page is summarized as follows.
* For user not signed in: show sign in button and notify "not signed in" to BuzzScreen
* For user signed in: show signed in page and notify "signed in" to BuzzScreen

### 2. Sign out URL
If the user signed out the lock screen, an URL is needed that allows her to sign out also from the publisher's website normally. This address is used by the Android application. For example, if an user can signed out by accessing `https://www.buzzvil.com/logout`, the Android application will access the above address and sign out when the user wants to do. 

## How to apply

Insert this SDK on each of the two pages accessible by the "Sign in verification URL".
```html
<script src="buzzscreen-extension-0.0.1.js"></script>
```

### Notify "not signed in"

On the page that is accessed when the user doesn't sign in, call the following JavaScript code:
```html
<script>
BuzzScreen.notifyNotSignedIn();
</script>
```

### Notify "signed in"

On the page that is accessed when the user has signed in, call the following JavaScript code to pass the user information and to notify that the user has signed in.
```html
<script>
BuzzScreen.notifySignedIn('userId', 1990, BuzzScreen.GENDER_MALE);
BuzzScreen.notifySignedIn('userId', 1990, BuzzScreen.GENDER_FEMALE);
</script>
```
The type of each argument is `string`, `number`, `string` in that order. If the arguments are set incorrectly, error logs are left in the console window. Be careful not to set the arguments incorrectly.

You may want to work with Buzzscreen instead of passing the user's birthYear and gender as follows.
> Note: If you do not set targeting information such as year of birth, gender, etc, targeted ads will not be allocated to lockscreen, which will reduce the total number of ads users can see. 
```html
<script>
BuzzScreen.notifySignedIn('userId');
</script>
```

## Functions for SDK configuration convenience

### Check the page is loaded in BuzzScreen Webview
The following API returns `true` if loaded from BuzzScreen Webview, or `false`.
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
