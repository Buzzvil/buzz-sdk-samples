import React from 'react';
import { NavigationContainer } from "@react-navigation/native";
import { createNativeStackNavigator } from "@react-navigation/native-stack";
import { BuzzBooster } from 'react-native-buzz-booster';
import { AppRegistry, StatusBar } from 'react-native';
import LinkPage from './Page/LinkPage';
import HomePage from './Page/HomePage';
import OptInMarketingPage from './Page/OptInMarketingPage';

const Stack = createNativeStackNavigator();

AppRegistry.registerComponent('app', () => App);

export default function App() {
  React.useEffect(() => {
    BuzzBooster.init({
      androidAppKey: '307117684877774', // TODO: Replace with your Android App Key
      iosAppKey: '279753136766115', // TODO: Replace with your iOS App Key
    });
    BuzzBooster.setUserEventChannel((eventName, eventValues) => {
      console.log(
        `eventName: ${eventName}, eventValues: ${JSON.stringify(eventValues)}`,
      );
    });
  }, []);

  return (
    <>
      <NavigationContainer>
        <Stack.Navigator initialRouteName="HomePage">
          <Stack.Screen name="HomePage" component={HomePage} />
          <Stack.Screen name="LinkPage" component={LinkPage} />
          <Stack.Screen name="OptInMarketingPage" component={OptInMarketingPage} />
        </Stack.Navigator>
      </NavigationContainer>
      <StatusBar />
    </>
  );
}
