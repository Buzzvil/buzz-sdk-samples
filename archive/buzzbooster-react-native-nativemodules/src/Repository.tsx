import AsyncStorage from '@react-native-async-storage/async-storage';
import { Platform } from 'react-native';
import { BuzzBoosterTheme } from './BuzzBooster/BuzzBoosterTheme';

const ThemeKey = "Theme"
const AndroidAppKeyKey = "AndroidAppKey"
const iOSAppKeyKey = "iOSAppKey"
const UserIdKey = "UserId"
const IsOptInMarketingKey = "IsOptInMarketing"

export default class Repository {
    constructor() { }
    async getTheme(): Promise<string> {
        var theme: string | null = await AsyncStorage.getItem(ThemeKey)
        if (theme == null) {
            theme = BuzzBoosterTheme.system.valueOf()
        }
        return theme;
    }

    async setTheme(theme: string) {
        await AsyncStorage.setItem(ThemeKey, theme)
    }

    async getUserId(): Promise<string> {
        var userId: string | null = await AsyncStorage.getItem(UserIdKey)
        if (userId == null) {
            userId = ""
        }
        return userId
    }

    async setUserId(userId: string) {
        await AsyncStorage.setItem(UserIdKey, userId)
    }

    async getAppKey(): Promise<string> {
        if (Platform.OS === 'ios') {
            return this.getiOSAppKey()
        } else {
            return this.getAndroidAppKey()
        }
    }

    async setAppKey(appKey: string) {
        if (Platform.OS === 'ios') {
            await this.setiOSAppKey(appKey)
        } else {
            await this.setAndroidAppKey(appKey)
        }
    }

    async setIsOptInMarketing(isOptInMarketing: boolean) {
        await AsyncStorage.setItem(IsOptInMarketingKey, isOptInMarketing.toString())
    }

    async getIsOptInMarketing(): Promise<boolean> {
        var isOptInMarketing: string | null = await AsyncStorage.getItem(IsOptInMarketingKey)
        if (isOptInMarketing == null) {
            isOptInMarketing = "false"
        }
        return isOptInMarketing === "true"
    }

    async getAndroidAppKey(): Promise<string> {
        var appKey: string | null = await AsyncStorage.getItem(AndroidAppKeyKey)
        if (appKey == null) {
            appKey = "YOURAPPKEY"
        }
        return appKey
    }

    async setAndroidAppKey(appKey: string) {
        AsyncStorage.setItem(AndroidAppKeyKey, appKey)
    }

    async getiOSAppKey(): Promise<string> {
        var appKey: string | null = await AsyncStorage.getItem(iOSAppKeyKey)
        if (appKey == null) {
            appKey = "YOURAPPKEY"
        }
        return appKey
    }

    async setiOSAppKey(appKey: string) {
        AsyncStorage.setItem(iOSAppKeyKey, appKey)
    }
}
