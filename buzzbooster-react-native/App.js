import React from 'react';
import {NavigationContainer} from '@react-navigation/native';
import {createNativeStackNavigator} from '@react-navigation/native-stack';
import {BuzzBooster} from 'react-native-buzz-booster';
import {AppRegistry, StatusBar} from 'react-native';
import LinkPage from './Page/LinkPage';
import HomePage from './Page/HomePage';
import OptInMarketingPage from './Page/OptInMarketingPage';
import messaging from '@react-native-firebase/messaging';

const Stack = createNativeStackNavigator();

AppRegistry.registerComponent('app', () => App);

export default function App() {
  React.useEffect(() => {
    async function setup() {
      BuzzBooster.init({
        androidAppKey: '307117684877774',
        iosAppKey: '279753136766115',
      });

      requestUserPermission();

      const token = await messaging().getToken();
      BuzzBooster.setPushToken(token);

      /**
       * 앱이 실행 중인 상태에서 Notification 처리
       */
      messaging().onMessage(async remoteMessage => {
        const data = remoteMessage.data;
        if (BuzzBooster.isBuzzBoosterNotification(data)) {
          BuzzBooster.handleForegroundNotification(data);
        }
      });

      /**
       * 앱이 종료된 상태에서 시작될 때 발생, 종료된 동안 받았던 Noti가 있으면 콜백 호출
       */
      messaging()
        .getInitialNotification()
        .then(async remoteMessage => {
          if (remoteMessage == null) {
            return;
          }
          const data = remoteMessage.data;
          if (BuzzBooster.isBuzzBoosterNotification(data)) {
            BuzzBooster.handleInitialNotification(data);
          }
        });

      /**
       * 앱이 Background인 상태에서 Notification 클릭으로 시작될 때 발생
       */
      messaging().onNotificationOpenedApp(async remoteMessage => {
        const data = remoteMessage.data;
        if (BuzzBooster.isBuzzBoosterNotification(data)) {
          BuzzBooster.onNotificationOpenedApp(data);
        }
      });
    }
    setup();
  }, []);

  return (
    <>
      <NavigationContainer>
        <Stack.Navigator initialRouteName="HomePage">
          <Stack.Screen name="HomePage" component={HomePage} />
          <Stack.Screen name="LinkPage" component={LinkPage} />
          <Stack.Screen
            name="OptInMarketingPage"
            component={OptInMarketingPage}
          />
        </Stack.Navigator>
      </NavigationContainer>
      <StatusBar />
    </>
  );
}

async function requestUserPermission() {
  const authStatus = await messaging().requestPermission();
  const enabled =
    authStatus === messaging.AuthorizationStatus.AUTHORIZED ||
    authStatus === messaging.AuthorizationStatus.PROVISIONAL;

  if (enabled) {
    console.log('Authorization status:', authStatus);
  }
}
