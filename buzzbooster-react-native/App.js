import React from 'react';
import * as BuzzBooster from 'react-native-buzz-booster';
import {
  ScrollView,
  StatusBar,
  useColorScheme,
  Button,
} from 'react-native';
import { SafeAreaView } from 'react-native-safe-area-context';
import { Colors, Header } from 'react-native/Libraries/NewAppScreen';
import EventView from './EventView'

const App = () => {
  const isDarkMode = useColorScheme() === 'dark';

  const backgroundStyle = {
    backgroundColor: isDarkMode ? Colors.darker : Colors.lighter,
    flex: 1,
  };

  React.useEffect(() => {
    BuzzBooster.init({
      androidAppKey: '307117684877774',
      iosAppKey: '279753136766115',
    })
  }, [])

  return (
    <SafeAreaView style={backgroundStyle}>
      <StatusBar barStyle={isDarkMode ? 'light-content' : 'dark-content'} />
      <ScrollView
        contentInsetAdjustmentBehavior='automatic'
        style={backgroundStyle}>
        <Header />
        <Button title='Login' onPress={() => {
          const userId='Damon1'
          BuzzBooster.setUserId(userId);
          BuzzBooster.showInAppMessage();
        }} />
        <Button title='Campaign' onPress={() => {
          BuzzBooster.showCampaign();
        }} />
        <Button title='Attendance Campaign' onPress={() => {
          BuzzBooster.showSpecificCampaign(BuzzBooster.CampaignType.Attendance);
        }} />
        <EventView />
      </ScrollView>
    </SafeAreaView>
  );
};

export default App;
