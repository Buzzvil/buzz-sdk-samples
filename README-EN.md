# BuzzScreen Migration SDK Integration Guide

- This guide is aimed to migrate lockscreen users of an app integrated with the existing BuzzScreen SDK (https://github.com/Buzzvil/buzzscreen-sdk-publisher) to the new lockscreen-exclusive application, in order to be in compliance with the new Google Play policy regarding lockscreen monetization.
	> "M app" indicates existing application integrated with BuzzScreen, and "L app" indicates the new lockscreen-exclusive application hereinafter.
- Through the integration process of this SDK, all processes except the installation of L app can be automated, seamlessly migrating M app BuzzScreen users to L app BuzzScreen.
    > Installation of L app (download & installation of APK file) can only be done with the user's action, thus cannot be automated.
- As M app and L app exchange information for migration, the two APKs should be **generated with identical signature** to block access from other apps.
    > Using [protectionLevel="signature"](https://developer.android.com/guide/topics/manifest/permission-element.html#plevel) is recommended.
- **Caution on L app Migration** : Prior to the integration of migration SDK, L app should be integrated with BuzzScreen SDK in the same way M app is integrated. 
    > Migration SDK ONLY includes seamless lockscreen conversion from M app to L app (with minimal user action)
- **Caution when proceeding migration** : Before initiating the migration process, **consultation with Buzzvil BD Team in advance** is mandatory. Also, apk files of both M app and L app fully integrated following the guide should be passed on to Buzzvil BD team so they can be **reviewed BEFORE uploading on the market**.
- All works of the migration guide are included in `sample_main` and `sample_lock` files.
    > Please change `my_app_key` (in `build.gradle`, `BuzzScreen.init`), `<app_license>` and `<plist>` in `AndroidManifest.xml` of the sample app to the key values given.
- M app and L app both have additional integration works to be done for migration, which are guided in the following links.

### [M app migration guide](MIGRATION-M-EN.md)
### [L app migration guide](MIGRATION-L-EN.md)





