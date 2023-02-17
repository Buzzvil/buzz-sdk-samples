import React from 'react';
import { NavigationContainer } from "@react-navigation/native";
import { createNativeStackNavigator } from "@react-navigation/native-stack";
import * as BuzzBooster from 'react-native-buzz-booster';
import { AppRegistry, StatusBar } from 'react-native';
import LinkPage from './Page/LinkPage';
import HomePage from './Page/HomePage';
import OptInMarketingPage from './Page/OptInMarketingPage';

const Stack = createNativeStackNavigator();

AppRegistry.registerComponent('app', () => App);

export default function App() {
  React.useEffect(() => {
    BuzzBooster.init({
      androidAppKey: '307117684877774',
      iosAppKey: '279753136766115',
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
