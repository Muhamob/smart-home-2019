package ru.sbt.mipt.oop.homeStructure;

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
    public boolean execute(HomeComponentAction action) {
        boolean executed = false;
        for (HomeComponent homeComponent : homeComponents) {
            executed |= homeComponent.execute(action);
        }

        return executed;
    }

    @Override
    public boolean contains(String id) {
        for (HomeComponent homeComponent : homeComponents) {
            if (homeComponent.contains(id)) return true;
        }
        return false;
    }
}
