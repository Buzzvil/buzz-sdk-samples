import { StyleSheet } from "react-native";

export const styles = StyleSheet.create({
    button: {
        height: 20,
        margin: 5,
        padding: 10,
        fontSize: 14,
    },
    input: {
        height: 30,
        margin: 12,
        borderWidth: 0.5,
        borderRadius: 3,
        padding: 10,
        textAlign: 'center'
    },
    container: {
        flex: 1,
        margin: 20,
    },
    rowContainer: {
        flexDirection:'row'
    },
    columnContainer: {
        flexDirection:'column'
    },
    title: {
        fontWeight: "bold",
        fontSize: 16
    }
});