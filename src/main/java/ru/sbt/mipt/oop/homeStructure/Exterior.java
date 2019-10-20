package ru.sbt.mipt.oop.homeStructure;

import ru.sbt.mipt.oop.actions.ActionableHomeComponent;
import ru.sbt.mipt.oop.actions.HomeComponentAction;
import ru.sbt.mipt.oop.devices.SmartDevice;

import java.util.ArrayList;
import java.util.List;

/*
Not implemented yet
 */
public class Exterior extends Premise implements HomeComponent {

    private List<HomeComponent> homeComponents;

    public Exterior() {
        homeComponents = new ArrayList<>();
    }

    @Override
    public SmartDevice getSmartDevice(String id) {
        return null;
    }

    @Override
    public List<SmartDevice> getAllSmartDevices() {
        return null;
    }

    @Override
    public void execute(HomeComponentAction action) {
        for (HomeComponent homeComponent : homeComponents) {
            homeComponent.execute(action);
        }
    }
}
