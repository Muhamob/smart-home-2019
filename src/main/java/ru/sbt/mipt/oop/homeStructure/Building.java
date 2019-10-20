package ru.sbt.mipt.oop.homeStructure;

import ru.sbt.mipt.oop.actions.HomeComponentAction;
import ru.sbt.mipt.oop.devices.SmartDevice;
import ru.sbt.mipt.oop.homeUtils.HomeCheckers;

import java.util.*;

public class Building implements HomeComponent {
    private List<HomeComponent> premises;

    public Building(List<HomeComponent> premises) {
        this.premises = new ArrayList<>(premises);
        System.out.println(premises);
    }

    /*
    Добавить исключение, если найдено несколько устройств с одинаковым ID
     */
    @Override
    public SmartDevice getSmartDevice(String id) {
        List<SmartDevice> smartDevices = new ArrayList<>();
        System.out.println(premises);
        for (HomeComponent premise : premises) {
            smartDevices.add(premise.getSmartDevice(id));
        }

        return HomeCheckers.getFirstNotNull(smartDevices);
    }

    @Override
    public List<SmartDevice> getAllSmartDevices() {
        List<SmartDevice> smartDevices = new ArrayList<>();

        for (HomeComponent premise : premises) {
            smartDevices.addAll(premise.getAllSmartDevices());
        }

        return smartDevices;
    }

    @Override
    public boolean execute(HomeComponentAction action) {
        boolean executed = action.execute(this);
        for (HomeComponent premise : premises) {
            executed |= premise.execute(action);
        }

        return executed;
    }

    @Override
    public boolean contains(String id) {
        for (HomeComponent homeComponent : premises) {
            if(homeComponent.contains(id)) {
                return true;
            }
        }
        return false;
    }
}
