import { NativeModules, Platform, NativeEventEmitter, EmitterSubscription } from 'react-native';
import { LogBox } from 'react-native';
import type { BuzzBoosterUser } from './BuzzBoosterUser';
import type { BuzzBoosterCampaignType } from './BuzzBoosterCampaignType';
import type { BuzzBoosterTheme } from './BuzzBoosterTheme';

LogBox.ignoreLogs(['new NativeEventEmitter']);

const LINKING_ERROR =
  `The package 'react-native-buzz-booster' doesn't seem to be linked. Make sure: \n\n` +
  Platform.select({ ios: "- You have run 'pod install'\n", default: '' }) +
  '- You rebuilt the app after installing the package\n' +
  '- You are not using Expo managed workflow\n';
const BuzzBooster = NativeModules.BuzzBooster ? NativeModules.BuzzBooster : new Proxy(
  {},
  {
    get() { throw new Error(LINKING_ERROR); },
  }
);

const myModuleEvt = new NativeEventEmitter(BuzzBooster)

interface AppKey {
  androidAppKey: string
  iosAppKey: string
}

export function init({ androidAppKey, iosAppKey }: AppKey) {
  console.log(NativeModules)
  console.log(BuzzBooster)
  if (Platform.OS === 'android') {
    BuzzBooster.initAndroidApp(androidAppKey)
  } else if (Platform.OS === 'ios') {
    BuzzBooster.initIosApp(iosAppKey)
  } else {
    throw new Error("unsupported platform");
  }
}

export function setUser(user: BuzzBoosterUser | null) {
  if (user != null) {
    let userObject = {
      "userId": user.userId,
      "optInMarketing": user.optInMarketing,
      "properties": user.properties,
    }
    BuzzBooster.setUser(userObject)
  } else {
    BuzzBooster.setUser(null)
  }
}

export function setPushToken(pushToken: String) {
  BuzzBooster.setPushToken(pushToken)
}

export function addUserEventChannel(listener: (eventName: string, eventValues: Object | undefined) => void): EmitterSubscription {
  return myModuleEvt.addListener('onUserEventDidOccur', (data) => listener(data['userEventName'], data['userEventValues']))
}

export function addOptInMarketingCampaignMoveButtonClickListener(listener: () => void): EmitterSubscription {
  return myModuleEvt.addListener('OptInMarketingCampaignMoveButtonClicked', (_) => listener())
}

export function sendEvent(eventName: string, eventValues?: Map<string, string | number | boolean> | null) {
  if (eventValues == null || typeof eventValues == 'undefined') {
    BuzzBooster.sendEvent(eventName, null)
  } else {
    BuzzBooster.sendEvent(eventName, eventValues)
  }
}

export function showInAppMessage() {
  BuzzBooster.showInAppMessage()
}

export function showHome() {
  BuzzBooster.showHome()
}

export function showNaverPayExchange() {
  BuzzBooster.showNaverPayExchange()
}

export function showCampaignWithId(campaignId: string) {
  BuzzBooster.showCampaignWithId(campaignId)
}

export function showPage(pageId: string) {
  BuzzBooster.showPage(pageId)
}

export function setTheme(theme: BuzzBoosterTheme) {
  let themeString = theme.valueOf()
  // BuzzBooster.setTheme(themeString)
}

export function showCampaignWithType(campaignType: BuzzBoosterCampaignType) {
  let campaignTypeString = campaignType.valueOf()
  BuzzBooster.showCampaignWithType(campaignTypeString)
}

export function isBuzzBoosterNotification(data: { [key: string]: string } | undefined): Boolean {
  if (data != undefined && data['BuzzBooster'] != undefined) {
    return true
  }
  return false
}

export function handleForegroundNotification(data: { [key: string]: string } | undefined) {
  if (Platform.OS === 'android') {
    handleNotification(data) // android use sample handler
  } else if (Platform.OS === 'ios') {
    BuzzBooster.handleForegroundNotification(data)
  }
}

export function handleInitialNotification(data: { [key: string]: string } | undefined) {
  if (Platform.OS === 'ios') {
    BuzzBooster.handleInitialNotification(data)
  } else {
    BuzzBooster.hasUnhandledNotificationClick()
      .then((exists) => {
        if (exists) {
          handleNotificationClick()
        }
      })
  }
}

export function postJavaScriptMessage(message: string) {
  BuzzBooster.postJavaScriptMessage(message)
}

// Only For iOS
export function onNotificationOpenedApp(data: { [key: string]: string } | undefined) {
  if (Platform.OS === 'ios') {
    BuzzBooster.onNotificationOpenedApp(data)
  }
}

function handleNotificationClick() {
  if (Platform.OS === 'android') {
    BuzzBooster.handleNotificationClick()
  }
}

function handleNotification(data: { [key: string]: string } | undefined) {
  if (Platform.OS === 'android') {
    BuzzBooster.handleNotification(data)
  }
}

// 객체로 내보내기
export const BuzzBoosterReact = {
  init,
  setUser,
  setPushToken,
  addUserEventChannel,
  addOptInMarketingCampaignMoveButtonClickListener,
  sendEvent,
  showInAppMessage,
  showHome,
  showNaverPayExchange,
  showCampaignWithId,
  showPage,
  setTheme,
  showCampaignWithType,
  isBuzzBoosterNotification,
  handleForegroundNotification,
  handleInitialNotification,
  postJavaScriptMessage,
  onNotificationOpenedApp,
};
