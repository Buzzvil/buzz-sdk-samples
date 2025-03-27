import React, { useEffect } from "react";
import { useState, useMemo } from "react";
import { SafeAreaView, Button, TextInput, Text, View, Switch } from "react-native";
import RadioGroup, { RadioButtonProps } from 'react-native-radio-buttons-group';
import { BuzzBoosterReact } from '../BuzzBooster/BuzzBoosterReact';
import { BuzzBoosterUserBuilder } from '../BuzzBooster/BuzzBoosterUser';
import { BuzzBoosterTheme } from '../BuzzBooster/BuzzBoosterTheme';
import { styles } from "../styles";
import DropDownPicker from 'react-native-dropdown-picker';
import RNRestart from 'react-native-restart';
import { NativeModules } from 'react-native'
import Repository from "../Repository";
const repository: Repository = new Repository()

const AppInfoView = ({ navigation, passedAppKey }) => {
    const [userId, setUserId] = useState<string>("")
    const [server, setServer] = useState<string>("Dev")
    const [appKey, setAppKey] = useState<string>(passedAppKey)
    const [theme, setTheme] = useState<string>(BuzzBoosterTheme.system.valueOf());
    const [isOptInMarketing, setIsOptInMarketing] = useState<boolean>(false);
    const radioButtons: RadioButtonProps[] = useMemo(() => ([
        {
            id: BuzzBoosterTheme.light.valueOf(),
            label: BuzzBoosterTheme.light.valueOf(),
            value: BuzzBoosterTheme.light.valueOf()
        },
        {
            id: BuzzBoosterTheme.dark.valueOf(),
            label: BuzzBoosterTheme.dark.valueOf(),
            value: BuzzBoosterTheme.dark.valueOf()
        },
        {
            id: BuzzBoosterTheme.system.valueOf(),
            label: BuzzBoosterTheme.system.valueOf(),
            value: BuzzBoosterTheme.system.valueOf()
        }
    ]), []);

    const onRadioPress = (id: string) => {
        setTheme(id);
    }

    // Watch theme value to BuzzBooster.setTheme
    useEffect(() => {
        const matchedTheme = (Object.values(BuzzBoosterTheme) as string[]).find(
            (value) => value === theme
          ) as BuzzBoosterTheme | undefined;
          
        BuzzBoosterReact.setTheme(matchedTheme ?? BuzzBoosterTheme.system);
    }, [theme])

    useEffect(() => {
        if (passedAppKey == null) {
            repository.getAppKey().then((value) => {
                setAppKey(value)
            })
        } else {
            setAppKey(passedAppKey)
        }
        repository.getTheme().then((value) => {
            setTheme(value)
        })
        repository.getUserId().then((value) => {
            setUserId(value)
        })
        repository.getIsOptInMarketing().then((value) => {
            setIsOptInMarketing(value)
        })
    }, [passedAppKey])

    const applyChanges = async () => {
        await repository.setUserId(userId)
        await repository.setTheme(theme)
        await repository.setAppKey(appKey)
        await repository.setIsOptInMarketing(isOptInMarketing)
        RNRestart.Restart();
    }

    return <SafeAreaView>
        <AppKeyView appKey={appKey} setAppKey={setAppKey}/>
        <LoginoutView userId={userId} setUserId={setUserId} isOptInMarketing={isOptInMarketing} />
        <OptInMarketingView isOptInMarketing={isOptInMarketing} setIsOptInMarketing={setIsOptInMarketing} />
        <RadioGroup
            radioButtons={radioButtons}
            onPress={onRadioPress}
            selectedId={theme}
            layout='row'
        />
        <Button title="Apply Changes" onPress={applyChanges} />
    </SafeAreaView>
}

const OptInMarketingView = ({ isOptInMarketing, setIsOptInMarketing }) => {
    return <SafeAreaView style={styles.columnContainer}>
        <Text style={styles.title}>IsOptInMarketing</Text>
        <Switch value={isOptInMarketing} onValueChange={(isOptIn) => setIsOptInMarketing(isOptIn)} />
    </SafeAreaView>
}

const AppKeyView = ({ appKey, setAppKey }) => {
    return <SafeAreaView style={styles.rowContainer}>
        <TextInput
            style={styles.input}
            placeholder='plz input appKey'
            onChangeText={(text) => setAppKey(text)}
            defaultValue={appKey}
        />
    </SafeAreaView>
}

const LoginoutView = ({ userId, setUserId, isOptInMarketing }) => {
    return <SafeAreaView style={styles.columnContainer}>
        <Text style={styles.title}>UserInfo</Text>
        <SafeAreaView style={styles.rowContainer}>
            <TextInput
                style={styles.input}
                placeholder='plz input userId'
                onChangeText={(text) => setUserId(text)}
                defaultValue={userId}
            />
        </SafeAreaView>
        <SafeAreaView style={styles.rowContainer}>
            <Button title="Login" onPress={() => { login(userId, isOptInMarketing) }} />
            <Button title="Logout" onPress={() => { logout() }} />
        </SafeAreaView>
    </SafeAreaView>
}
const login = (userId, isOptInMarketing) => {
    let user = new BuzzBoosterUserBuilder(userId)
        .addProperty("key1", "value")
        .addProperty("key2", 2)
        .addProperty("key3", true)
        .setOptInMarketing(isOptInMarketing)
        .build()
    BuzzBoosterReact.setUser(user)
    BuzzBoosterReact.showInAppMessage()
}

const logout = () => {
    BuzzBoosterReact.setUser(null)
}

export default AppInfoView;
