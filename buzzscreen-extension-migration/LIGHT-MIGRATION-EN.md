## Light Migration SDK Integration
- This guide explains a migration method with minial cost by utilizing M app's existing features and buzzscreen integration. 
	- **without L app's own login system** : Collect user profile that is needed for BuzzScreen integration on M app, and this is automatically passed to L app via the migration SDK
    - **without L app lockscreen customization** : By using `SimpleLockerActivity`, a simple L app without customized lockscreen features can be created.
- Using the L app sample code(`sample_lock_light`), a market-ready app can be created only with a few simple modifications.
- Every tasks in the migration guide are included in `sample_main_light` and `sample_lock_light`.
	> In order to properly build sample apps, replace `my_app_key` in the sample app code(in both `build.gradle` and `BuzzScreen.init`), `<app_license>` and `<plist>` values in `AndroidManifest.xml` to originally published values.
- Additional migration tasks for M app and L app can be found in the following links respectively.

#### [M app Migration Implementation](LIGHT-MIGRATION-M-EN.md)
#### [L app Migration Implementation](LIGHT-MIGRATION-L-EN.md)
