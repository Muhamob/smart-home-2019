package ru.sbt.mipt.oop.homeUtils;

import ru.sbt.mipt.oop.devices.Door;
import ru.sbt.mipt.oop.devices.Light;
import ru.sbt.mipt.oop.devices.SmartDevice;
import ru.sbt.mipt.oop.homeStructure.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class HomeBuilder {

    public static void main(String[] args) throws IOException {

        StringRepresentation representation = new JsonRepresentation();
        HomeSaver homeSaver = new HomeSaver("new-home-by-new-project.js");

        Interior interior = new Interior();
        List<Premise> premises = Collections.singletonList(interior);

        Floor floor = new Floor();

        List<SmartDevice> kitchenDevices = Arrays.asList(
                new Light("1", false),
                new Light("2", true),
                new Door("3", false)
        );
        Room kitchen = new Room("kitchen", kitchenDevices);

        List<SmartDevice> bathroomDevices = Arrays.asList(
                new Light("4", true),
                new Door("5", false)
        );
        Room bathroom = new Room("bathroom", bathroomDevices);

        List<SmartDevice> bedroomDevices = Arrays.asList(
                new Light("6", false),
                new Light("7", false),
                new Light("8", false),
                new Door("9", true)
        );
        Room bedroom = new Room("bedroom", bedroomDevices);

        List<SmartDevice> hallDevices = Arrays.asList(
                new Light("10", false),
                new Light("11", false),
                new Light("12", false),
                new Door("13", false)
        );
        Room hall = new Room("hall", hallDevices);

        floor.setRooms(0, Arrays.asList(kitchen, bathroom, bedroom, hall));
        interior.setFloors(Collections.singletonList(floor));

        SmartHome smartHome = new SmartHome(premises);

        String jsonString = representation.toString(smartHome);
        System.out.println(jsonString);
        homeSaver.write(jsonString);
    }

}
