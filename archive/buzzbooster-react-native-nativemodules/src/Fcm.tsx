import messaging from '@react-native-firebase/messaging';
import { BuzzBoosterReact } from './BuzzBooster/BuzzBoosterReact';
import { requestNotifications, RESULTS } from 'react-native-permissions';

export async function requestUserPermission() {
    const response = await requestNotifications(['alert', 'sound'])
    switch (response.status) {
        case RESULTS.UNAVAILABLE:
            console.log('This feature is not available (on this device / in this context)');
            break;
        case RESULTS.DENIED:
            console.log('The permission has not been requested / is denied but requestable');
            break;
        case RESULTS.LIMITED:
            console.log('The permission is limited: some actions are possible');
            break;
        case RESULTS.GRANTED:
            console.log('The permission is granted');
            break;
        case RESULTS.BLOCKED:
            console.log('The permission is denied and not requestable anymore');
            break;
    }
}

export async function getToken(): Promise<string | null> {
    try {
        const token = await messaging().getToken()
        console.log("getToken:" + token)
        return token
    } catch (error) {
        console.error(error)
        return null
    }
}

export function setFcm() {
    messaging().onMessage(async remoteMessage => {
        console.log("onMessage");
        const data = remoteMessage.data
        if (BuzzBoosterReact.isBuzzBoosterNotification(data)) {
            BuzzBoosterReact.handleForegroundNotification(data)
        }
    });

    /**
     * 앱이 종료된 상태에서 시작될 때 발생, 종료된 동안 받았던 Noti가 있으면 콜백 호출
     */
    messaging().getInitialNotification().then(async remoteMessage => {
        if (remoteMessage == null)
            return
        console.log("getInitialNotification")
        const data = remoteMessage.data
        if (BuzzBoosterReact.isBuzzBoosterNotification(data)) {
            BuzzBoosterReact.handleInitialNotification(data)
        }
    })

    /**
     * 앱이 Background인 상태에서 Notification 클릭으로 시작될 때 발생
     */
    messaging().onNotificationOpenedApp(async remoteMessage => {
        console.log("onNotificationOpenedApp")
        const data = remoteMessage.data
        if (BuzzBoosterReact.isBuzzBoosterNotification(data)) {
            BuzzBoosterReact.onNotificationOpenedApp(data)
        }
    })

    messaging().onTokenRefresh(async token => {
        console.log(`token refreshed: ${token}`)
        BuzzBoosterReact.setPushToken(token)
    })
}
