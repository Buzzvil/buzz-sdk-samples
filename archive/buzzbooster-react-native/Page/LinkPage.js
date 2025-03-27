import * as React from 'react';
import { StyleSheet, ScrollView, Button, Text } from 'react-native';

export default function LinkPage({ route, navigation }) {
    const onPress = () => {
        navigation.navigate("HomePage")
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