import { NavigationContainer, } from "@react-navigation/native";
import { createNativeStackNavigator } from "@react-navigation/native-stack";
import React, { useEffect } from "react";
import { setFcm, getToken } from './Fcm';
import { AppRegistry, StatusBar } from 'react-native';
import Repository from "./Repository";
import { BuzzBoosterReact } from './BuzzBooster/BuzzBoosterReact';
import Home from "./HomePage/Home";
import AppKeySelectView from "./AppKeySelectPage/AppKeySelectView";
import DeepLinkPage from "./DeepLinkPage/DeepLinkPage";
import WebViewPage from "./WebViewPage/WebViewPage";
import Toast from 'react-native-toast-message';
import MarketingConsentPage from "./MarketingConsentPage/MarketingConsentPage";
import { NativeModules } from 'react-native'

import * as Sentry from '@sentry/react-native';

Sentry.init({
  dsn: 'https://6b09a055f43b71da613244ff225fa860@o4459.ingest.sentry.io/4505746014994432',
});


const Stack = createNativeStackNavigator()

AppRegistry.registerComponent('app', () => App);

export default function App() {
  let repository: Repository = new Repository()
  const [androidAppKey, setAndroidAppKey] = React.useState<string | null>(null)
  const [iosAppKey, setiOSAppKey] = React.useState<string | null>(null)


  useEffect(() => {
    async function loadAppKey() {
      let androidAppKey = await repository.getAndroidAppKey()
      let iosAppKey = await repository.getiOSAppKey()
      setAndroidAppKey(androidAppKey);
      setiOSAppKey(iosAppKey);
    }

    async function setToken() {
      let token = await getToken()
      // BuzzBoosterReact.setPushToken(token!);
    }

    if (androidAppKey == null || iosAppKey == null) {
      loadAppKey()
      return
    }

    BuzzBoosterReact.init({
      androidAppKey: androidAppKey,
      iosAppKey: iosAppKey
    })
    setFcm()
    setToken()

    const listener = BuzzBoosterReact.addUserEventChannel((eventName, eventValues) => {
      console.log(`eventName: ${eventName}, eventValues: ${JSON.stringify(eventValues)}`)
    });

    return () => {
      listener.remove();
    }
  }, [androidAppKey, iosAppKey])
  return (
    <>
      <NavigationContainer>
        <Stack.Navigator initialRouteName="Home">
          <Stack.Screen name="Home" component={Home} initialParams={{ appKey: null }} />
          <Stack.Screen name="AppKeySelectView" component={AppKeySelectView} />
          <Stack.Screen name="DeepLinkPage" component={DeepLinkPage} />
          <Stack.Screen name="MarketingConsentPage" component={MarketingConsentPage} />
          <Stack.Screen name="WebViewPage" component={WebViewPage} />
        </Stack.Navigator>
      </NavigationContainer>
      <StatusBar />
      <Toast />
    </>
  );
}
