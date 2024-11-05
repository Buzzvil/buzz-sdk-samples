import * as React from 'react';
import { TextInput, ScrollView, Button, Text, SafeAreaView } from 'react-native';
import { BuzzBoosterReact } from '../BuzzBooster/BuzzBoosterReact';
import { BuzzBoosterCampaignType } from '../BuzzBooster/BuzzBoosterCampaignType';
import EventView from './EventView';
import AppInfoView from './AppInfoView';
import { styles } from "../styles";
import { requestUserPermission } from '../Fcm';
export const isReadyRef = React.createRef();
export const navigationRef = React.createRef();

export default function Home({ route, navigation }) {
    const [pageId, setPageId] = React.useState<string>("")
    const [version, setVersion] = React.useState<string>("")

    React.useEffect(() => {
        async function oneTimeSetup() {
            await requestUserPermission()
        }
        oneTimeSetup()
    }, []);

    React.useEffect(() => {
        const listener = BuzzBoosterReact.addOptInMarketingCampaignMoveButtonClickListener(() => {
            navigation.navigate("MarketingConsentPage", {})
        })

        return () => {
            listener.remove()
        }
    }, [route.params?.appKey])

    return (
        <ScrollView style={styles.container} showsVerticalScrollIndicator={false}>
            <Text>{version}</Text>
            <AppInfoView navigation={navigation} passedAppKey={route.params?.appKey} />
            <EventView />
            <Button title="showHome" onPress={showHome} />
            <SafeAreaView style={styles.rowContainer}>
                <Button title="출첵" onPress={() => showCampaignWithType(BuzzBoosterCampaignType.Attendance)} />
                <Button title="친초" onPress={() => showCampaignWithType(BuzzBoosterCampaignType.Referral)} />
                <Button title="마수동" onPress={() => showCampaignWithType(BuzzBoosterCampaignType.OptInMarketing)} />
                <Button title="긁" onPress={() => showCampaignWithType(BuzzBoosterCampaignType.ScratchLottery)} />
                <Button title="룰" onPress={() => showCampaignWithType(BuzzBoosterCampaignType.Roulette)} />
                <Button title="웹" onPress={() => navigation.navigate("WebViewPage", {})} />
            </SafeAreaView>
            <SafeAreaView style={styles.rowContainer}>
                <TextInput
                    style={styles.input}
                    placeholder='plz input pageId'
                    onChangeText={(text) => setPageId(text)}
                    defaultValue={pageId}
                />
                <Button onPress={() => { BuzzBoosterReact.showPage(pageId) }} title="이동" />
            </SafeAreaView>
        </ScrollView>
    );
}

const showHome = () => {
    BuzzBoosterReact.showHome()
}

const showCampaignWithType = (campaignType: BuzzBoosterCampaignType) => {
    BuzzBoosterReact.showCampaignWithType(campaignType)
}
