/**
 * @format
 */

import { AppRegistry } from 'react-native';
import App from './src/App';
import { name as appName } from './app.json';
// import firebase from '@react-native-firebase/app';

// Your web app's Firebase configuration
// const firebaseConfig = {
//     apiKey: "YOUR-API-KEY",
//     authDomain: "booster-sample.firebaseapp.com",
//     projectId: "booster-sample",
//     storageBucket: "booster-sample.firebasestorage.app",
//     messagingSenderId: "105965989179",
//     appId: "1:105965989179:web:6499773d30fd1c329b1b1e"
// };

// // Initialize Firebase
// if (!firebase.apps.length) {
//     console.log("Firebase initialized");
//     firebase.initializeApp(firebaseConfig);
// } else {
//     console.log("Firebase NOT initialized");
// }

AppRegistry.registerComponent(appName, () => App);
