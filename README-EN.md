# BuzzScreen Migration SDK Integration Guide
- This guide is aimed to migrate lockscreen users of an app integrated with the existing BuzzScreen SDK (https://github.com/Buzzvil/buzzscreen-sdk-publisher) to the new lockscreen-exclusive application, in order to be in compliance with the new Google Play policy regarding lockscreen monetization.
	> "M app" indicates the existing application integrated with BuzzScreen, and "L app" indicates the new lockscreen-exclusive application hereinafter.
- Through the integration process of this SDK, all processes except the installation of L app can be automated, seamlessly migrating M app BuzzScreen users to L app BuzzScreen.
    > Installation of L app (download & installation of the APK file from Google Play) can only be done with the user's action, thus cannot be automated.
- As M app and L app exchange data for migration, the two APKs should be **signed with an identical signature** to block access from other apps.
    > Using [protectionLevel="signature"](https://developer.android.com/guide/topics/manifest/permission-element.html#plevel) is recommended.
- **Caution when proceeding with the migration** : Before initiating the migration process, **consultation with Buzzvil BD Team in advance** is mandatory. Also, apk files of both M app and L app fully integrated following the guide should be passed on to Buzzvil BD team so they can be **reviewed BEFORE uploading on the market**.

## Migration Implementation
Both M app and L app require additional integration work and this is provided in 2 different ways. 
- [**FULL MIGRATION**](FULL-MIGRATION-EN.md) : Recommended when an independent L app (L app that has its own login system and custom features based on user login info) can be developed. 
    > After migration, L app will have NO dependency on M app.
- [**LIGHT MIGRATION**](LIGHT-MIGRATION-EN.md) : Recommended when an independent L app with its own login system cannot be provided - in this case L app may log in its users via M app login system.
    > SDK provides communication methods from M app to L app that is used to transfer user info(user id, age, gender) and lockscreen activation status.

In order to create L app with minimal cost of migration, **LIGHT MIGRATION** method is recommended. 

### [FULL MIGRATION IMPLEMENTATION](FULL-MIGRATION-EN.md)
### [LIGHT MIGRATION IMPLEMENTATION](LIGHT-MIGRATION-EN.md)



