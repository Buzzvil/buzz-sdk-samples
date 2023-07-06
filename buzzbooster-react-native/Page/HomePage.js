import React from 'react';
import { BuzzBooster, CampaignType, UserBuilder } from 'react-native-buzz-booster';
import {
  ScrollView,
  StatusBar,
  useColorScheme,
  Button,
} from 'react-native';
import { SafeAreaView } from 'react-native-safe-area-context';
import { Colors } from 'react-native/Libraries/NewAppScreen';
import EventView from './EventView';
import uuid from 'react-native-uuid';
import { Toast } from 'react-native-toast-message/lib/src/Toast';

export default function HomePage({ route, navigation }) {
  const isDarkMode = useColorScheme() === 'dark';

  const backgroundStyle = {
    backgroundColor: isDarkMode ? Colors.darker : Colors.lighter,
    flex: 1,
  };

  React.useEffect(() => {
    async function setup() {
      BuzzBooster.setOptInMarketingCampaignMoveButtonClickListener(() => {
        navigation.navigate("OptInMarketingPage")
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
        <Button title='Login' onPress={login} />
        <Button title='InAppMessage' onPress={showInAppMessage} />
        <Button title='Campaign' onPress={showHome} />
        <Button title='Attendance Campaign' onPress={showAttendanceCampaign} />
        <EventView />
      </ScrollView>
      <Toast />
    </SafeAreaView>
  );
}

function login() {
  const userId = uuid.v4();
  let user = new UserBuilder(userId)
    .setOptInMarketing(true)
    .addProperty("login_type", "sns(Facebook)")
    .build()
  BuzzBooster.setUser(user)

  Toast.show({
    type: 'success',
    text1: 'Login Success',
    text2: 'User Id: ' + userId,
    position: 'bottom',
  })
}

function showInAppMessage() {
  BuzzBooster.showInAppMessage();
}

function showHome() {
  BuzzBooster.showHome();
}

function showAttendanceCampaign() {
  BuzzBooster.showCampaignWithType(CampaignType.Attendance);
}
