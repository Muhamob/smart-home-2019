package ru.sbt.mipt.oop.homeStructure;

import ru.sbt.mipt.oop.actions.HomeComponentAction;
import ru.sbt.mipt.oop.devices.SmartDevice;
import ru.sbt.mipt.oop.homeUtils.HomeCheckers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Interior extends Premise implements HomeComponent {

    private Collection<Floor> floors;

    public Interior() {
        super();
    }

    public void setFloors(Collection<Floor> floors) {
        this.floors = floors;
    }

    @Override
    public SmartDevice getSmartDevice(String id) {
        List<SmartDevice> smartDevices = new ArrayList<>();
        for (Floor floor : floors) {
            smartDevices.add(floor.getSmartDevice(id));
        }

        return HomeCheckers.getFirstNotNull(smartDevices);
    }

    @Override
    public List<SmartDevice> getAllSmartDevices() {
        List<SmartDevice> smartDevices = new ArrayList<>();

        for (Floor floor : floors) {
            smartDevices.addAll(floor.getAllSmartDevices());
        }

        return smartDevices;
    }

    @Override
    public boolean execute(HomeComponentAction action) {
        boolean executed = action.execute(this);
        for (Floor floor : floors) {
            executed |= floor.execute(action);
        }
        return executed;
    }

    @Override
    public boolean contains(String id) {
        for (Floor floor : floors) {
            if (floor.contains(id)) return true;
        }
        return false;
    }
}
