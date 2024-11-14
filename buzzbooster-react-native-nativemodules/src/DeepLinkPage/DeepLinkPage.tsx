import * as React from 'react';
import { StyleSheet, ScrollView, Button, Text } from 'react-native';

export default function DeepLinkPage({ route, navigation }) {
    const onPress = () => {
        navigation.navigate("Home")
    }
    return (
        <ScrollView style={styles.container}>
            <Text>{route.params?.url}</Text>
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