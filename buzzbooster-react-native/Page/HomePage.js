import React from 'react';
import { BuzzBooster, CampaignType, UserBuilder } from 'react-native-buzz-booster';
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
          BuzzBooster.setCustomCampaignActionButtonClickListener((url) => {
              navigation.navigate("LinkPage", { url: url })
          })
          BuzzBooster.setOptInMarketingCampaignMoveButtonClickListener(() => {
              navigation.navigate("LinkPage", { url: "opt-in-marketing" })
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
              const userId = 'Damon1'
              let user = new UserBuilder(userId)
                .setOptInMarketing(true)
                .addProperty("login_type", "sns(Facebook)")
                .build()
              BuzzBooster.setUser(user)
              BuzzBooster.showInAppMessage();
            }} />
            <Button title='Campaign' onPress={() => {
              BuzzBooster.showCampaign();
            }} />
            <Button title='Attendance Campaign' onPress={() => {
              BuzzBooster.showSpecificCampaign(CampaignType.Attendance);
            }} />
            <EventView />
          </ScrollView>
        </SafeAreaView>
      );
}
