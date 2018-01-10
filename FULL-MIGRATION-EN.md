## Full Migration SDK Integration
- By using Full Migration SDK, Buzzscreen on M app can be fully migrated to L app. Afterwards, L app doesn't have any dependencies on M app.
- **Caution on L app Migration** : Prior to the integration of the migration SDK, the followings should be done in advance. 
    1) Basic features as an independent application (eg. user login)
    2) Buzzscreen SDK integration as in M app
    > The Migration SDK ONLY provides a seamless lockscreen migration from M app to L app (with minimal user action)
- All works of the migration guide are included in `sample_main` and `sample_lock` files.
    > Please change `my_app_key` (in `build.gradle`, `BuzzScreen.init`), `<app_license>` and `<plist>` in `AndroidManifest.xml` of the sample app to the values provided by your account manager.
- M app and L app both require the following items to complete the migration implementation.

#### [M App Migration Implementation](FULL-MIGRATION-M-EN.md)
#### [L App Migration Implementation](FULL-MIGRATION-L-EN.md)
