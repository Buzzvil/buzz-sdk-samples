import * as React from 'react';
import { StyleSheet, ScrollView, Text, Switch, Button } from 'react-native';
import { BuzzBoosterReact } from '../BuzzBooster/BuzzBoosterReact';
import Toast from 'react-native-toast-message';
import Repository from '../Repository';

const repository: Repository = new Repository();

export default function MarketingConsentPage() {
    const [isOptInMarketing, setIsOptInMarketing] = React.useState<boolean>(false);

    React.useEffect(() => {
        repository.getIsOptInMarketing().then((value) => {
            setIsOptInMarketing(value);
        });
    }, []);

    const onPressSave = () => {
        repository.setIsOptInMarketing(isOptInMarketing);
        if (isOptInMarketing) {
            BuzzBoosterReact.sendEvent("bb_opt_in_marketing");
        } else {
            BuzzBoosterReact.sendEvent("bb_opt_out_marketing");
        }
        showToast("수신동의: " + isOptInMarketing);
    }

    return (
        <ScrollView style={styles.container}>
            <Text>수신동의</Text>
            <Switch value={isOptInMarketing} onValueChange={(isOptIn) => setIsOptInMarketing(isOptIn)} />
            <Button title="저장" onPress={onPressSave} />
        </ScrollView>
    );
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        margin: 20,
    },
});

const showToast = (eventName: string) => {
    Toast.show({
        type: 'success',
        text1: `${eventName}`,
        autoHide: true,
        position: 'bottom',
    });
}
