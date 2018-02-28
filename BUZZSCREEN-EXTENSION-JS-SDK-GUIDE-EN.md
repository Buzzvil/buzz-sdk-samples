# Buzzscreen Extension JS SDK Guide

* An extension SDK that helps you to work with the [BuzzScreen SDK](https://github.com/Buzzvil/buzzscreen-sdk-publisher) which shows personalized content and ads on Android lockscreen.
* You need user information to use the personalization feature in BuzzScreen, and you need to pass user information to the Buzzscreen app via this SDK. 
* This guide explains how you can apply this SDK to publisher's web site.

## Sign in and point accumulation request flow
![Task Flow](buzzscreen-extension-js-sdk-flow.png)

## The role of this SDK

The 'Buzzscreen Extension JS SDK' is a bridge between publisher's site and Buzzscreen White Label Android app.

## Check Sign in Page

**Check sign in page** is necessary to check whether the user is signed in or not. Users who are signed in and users who are not signed in need to be directed to different pages. At this page, you can call different functions to distinguish whether the user is signed in or not.

* For user not signed in: show sign in button and notify "not signed in" to BuzzScreen
* For user signed in: show signed in page and notify "signed in" to BuzzScreen

## How to apply

Implement this SDK into the "Check Sign in page".
```html
<script src="buzzscreen-extension-0.0.1.js"></script>
```

### Notify "not signed in"

Call the following JavaScript code:
```html
<script>
BuzzScreen.notifyNotSignedIn();
</script>
```

### Notify "signed in"

Call the following JavaScript code:
```html
<script>
BuzzScreen.notifySignedIn('userId');
</script>
```

You can also set up targeting information by passing the user's birth year and gender as follows:
> Note: If you do not set targeting information, targeted ads will not be allocated to lockscreen, which will reduce the total number of ads users can see. 
```html
<script>
BuzzScreen.notifySignedIn('userId', 1990, BuzzScreen.GENDER_MALE);
BuzzScreen.notifySignedIn('userId', 1990, BuzzScreen.GENDER_FEMALE);
</script>
```
The type of each argument is `string`, `number`, `string` in that order. If the arguments are set incorrectly, error logs are left in the console window. Be careful not to set the arguments incorrectly.

## Functions for SDK configuration convenience
### Check the page is loaded in BuzzScreen Webview
The following api returns `true` if loaded from BuzzScreen Webview, or `false`.
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
