package com.buzzvil.buzzad.unity.sample;

import com.unity3d.player.UnityPlayer;

public class UnityInterface {
    private static UnityInterface instance = null;

    public static UnityInterface getInstance() {
        if(instance == null) {
            instance = new UnityInterface();
        }
        return instance;
    }

    public void Hello() {
        UnityPlayer.UnitySendMessage("PluginGameObject", "OnMessageReceived", "Why so serious?");
    }
}
