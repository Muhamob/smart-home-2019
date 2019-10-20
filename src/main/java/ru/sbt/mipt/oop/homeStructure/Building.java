package ru.sbt.mipt.oop.homeStructure;

import ru.sbt.mipt.oop.actions.ActionableHomeComponent;
import ru.sbt.mipt.oop.actions.HomeComponentAction;
import ru.sbt.mipt.oop.devices.SmartDevice;
import ru.sbt.mipt.oop.homeUtils.HomeCheckers;

import java.util.*;

public class Building implements HomeComponent {
    private List<Premise> premises;

    public Building(List<Premise> premises) {
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
        for (Premise premise : premises) {
            smartDevices.add(premise.getSmartDevice(id));
        }

        return HomeCheckers.getFirstNotNull(smartDevices);
    }

    @Override
    public List<SmartDevice> getAllSmartDevices() {
        List<SmartDevice> smartDevices = new ArrayList<>();

        for (Premise premise : premises) {
            smartDevices.addAll(premise.getAllSmartDevices());
        }

        return smartDevices;
    }

    @Override
    public void execute(HomeComponentAction action) {
        action.execute(this);
        for (Premise premise : premises) {
            premise.execute(action);
        }
    }
}
