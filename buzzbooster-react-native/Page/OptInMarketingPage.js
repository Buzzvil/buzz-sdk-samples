import React, { useState } from 'react';
import { StyleSheet, ScrollView, Button, Text, Switch } from 'react-native';
import { BuzzBooster } from 'react-native-buzz-booster';

export default function OptInMarketingPage({ route, navigation }) {
    const [isEnabled, setIsEnabled] = useState(false);
    const toggleSwitch = () => {
        setIsEnabled(previousState => !previousState)
        BuzzBooster.sendEvent('bb_opt_in_marketing');
    };
    const onPress = () => {
        navigation.navigate("HomePage")
    }
    return (
        <ScrollView style={styles.container}>
            <Text>{"This is Sample App's Opt In Marketing Page"}</Text>
            <Text>{"수신동의 하시겠습니까?"}</Text>
            <Switch
                trackColor={{ false: '#767577', true: '#81b0ff' }}
                thumbColor={isEnabled ? '#f5dd4b' : '#f4f3f4'}
                ios_backgroundColor="#3e3e3e"
                onValueChange={toggleSwitch}
                value={isEnabled}
            />
            <Button title="back" onPress={onPress} />
        </ScrollView>
    );
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        margin: 20,
    },
});
