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
import EventView from './EventView';

export default function HomePage({ route, navigation }) {
    const isDarkMode = useColorScheme() === 'dark';

    const backgroundStyle = {
      backgroundColor: isDarkMode ? Colors.darker : Colors.lighter,
      flex: 1,
    };

    React.useEffect(() => {
      async function setup() {
          BuzzBooster.setOnCustomCampaignActionButtonClickListener((url) => {
              navigation.navigate("LinkPage", { url: url })
          })
      }
      setup()
  }, []);

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
}
