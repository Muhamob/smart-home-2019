package ru.sbt.mipt.oop.homeStructure;

import ru.sbt.mipt.oop.devices.SmartDevice;
import ru.sbt.mipt.oop.homeUtils.HomeCheckers;

import java.util.*;

public class Building implements HomeComponent {
    private Map<String, Room> smartDevices;
    private List<Premise> premises;

    public Building(List<Premise> premises) {
        smartDevices = new HashMap<>();
        this.premises = new ArrayList<>(premises);
    }

    public void putSmartDevice(SmartDevice smartDevice, Room room) {
        room.addDevice(smartDevice);
        smartDevices.put(smartDevice.getId(), room);
    }

    public void putSmartDevices(Collection<SmartDevice> smartDevices, Room room) {
        for (SmartDevice smartDevice : smartDevices) {
            putSmartDevice(smartDevice, room);
        }
    }

    public void removeSmartDevice(String id) {
        Room room = smartDevices.get(id);
        room.removeDevice(id);
    }

    public void removeSmartDevices(List<String> ids) {
        for (String id : ids) {
            removeSmartDevice(id);
        }
    }

    public Room getRoom(String id) {
        return smartDevices.get(id);
    }

    public void clearAllSmartDevices() {
        smartDevices = new HashMap<>();
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
}
