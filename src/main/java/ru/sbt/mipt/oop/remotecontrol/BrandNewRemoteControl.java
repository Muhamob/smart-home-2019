package ru.sbt.mipt.oop.remotecontrol;

import rc.RemoteControl;

import java.util.HashMap;

public class BrandNewRemoteControl implements RemoteControl {
    private HashMap<String, Command> remoteControls;

    public BrandNewRemoteControl() {
        this.remoteControls = new HashMap<>();
    }

    public void addRemoteControl(String button, Command command) {
        remoteControls.put(button, command);
    }

    @Override
    public void onButtonPressed(String buttonCode, String rcId) {
        if (remoteControls.containsKey(buttonCode)) {
            remoteControls.get(buttonCode).execute();
        }
    }
}
