import React from "react";
import { SafeAreaView, TextInput, Text, Button } from "react-native";
import { styles } from "../styles";
import { BuzzBoosterReact } from '../BuzzBooster/BuzzBoosterReact';
import Toast from 'react-native-toast-message';

const EventTextInput = ({setEventKey, setEventValue}) => {
    return (
        <SafeAreaView style={styles.rowContainer}>
            <TextInput
                style={styles.input}
                onChangeText={setEventKey}
                placeholder="key"
            />
            <TextInput
                style={styles.input}
                onChangeText={setEventValue}
                placeholder="value"
            />
        </SafeAreaView >
    );
};

const EventView = () => {
    const [sendCount, setSendCount] = React.useState(1)
    const [eventName, setEventName] = React.useState("");
    const [eventKey1, setEventKey1] = React.useState("");
    const [eventValue1, setEventValue1] = React.useState("");
    const [eventKey2, setEventKey2] = React.useState("");
    const [eventValue2, setEventValue2] = React.useState("");
    const [eventKey3, setEventKey3] = React.useState("");
    const [eventValue3, setEventValue3] = React.useState("");

    const showToast = (eventName: string) => {
        Toast.show({
          type: 'success',
          text1: `${sendCount}`,
          text2: eventName,
          autoHide: true,
          position: 'bottom',
        });
    }

    return (
        <SafeAreaView style={styles.columnContainer}>
            <Text style={styles.title}>Event</Text>
            <TextInput
                style={styles.input}
                onChangeText={setEventName}
                placeholder="Input Event Name"
            />
            <EventTextInput setEventKey={setEventKey1} setEventValue={setEventValue1}/>
            <EventTextInput setEventKey={setEventKey2} setEventValue={setEventValue2}/>
            <EventTextInput setEventKey={setEventKey3} setEventValue={setEventValue3}/>
            <Button title="Send" onPress={()=>{
                let eventValues: Map<string, string | number | boolean> = new Map()
                if (eventKey1.length != 0 && eventValue1.length != 0) {
                    eventValues[eventKey1] = eventValue1
                }
                if (eventKey2.length != 0 && eventValue2.length != 0) {
                    eventValues[eventKey2] = eventValue2
                }
                if (eventKey3.length != 0 && eventValue3.length != 0) {
                    eventValues[eventKey3] = eventValue3
                }
                
                setSendCount(sendCount + 1)
                showToast(eventName)
                
                if (Object.keys(eventValues).length == 0) {
                    BuzzBoosterReact.sendEvent(eventName)
                } else {
                    BuzzBoosterReact.sendEvent(eventName, eventValues)
                }
                
            }}/>
        </SafeAreaView >
    );
}

export default EventView;
