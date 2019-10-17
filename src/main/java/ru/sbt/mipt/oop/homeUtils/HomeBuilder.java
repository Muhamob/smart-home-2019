package ru.sbt.mipt.oop.homeUtils;

import ru.sbt.mipt.oop.devices.Door;
import ru.sbt.mipt.oop.devices.Light;
import ru.sbt.mipt.oop.homeStructure.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

public class HomeBuilder {

    public static void main(String[] args) throws IOException {

        StringRepresentation representation = new JsonRepresentation();
        HomeSaver homeSaver = new HomeSaver("output.js");

        Interior interior = new Interior();
        Exterior exterior = new Exterior();

        Floor floor = new Floor();

        Room kitchen = new Room(Arrays.asList(
                new Light("1", false),
                new Light("2", true),
                new Door("3", false)),
                "kitchen");
        Room bathroom = new Room(Arrays.asList(
                new Light("4", true),
                new Door("5", false)),
                "bathroom");
        Room bedroom = new Room(Arrays.asList(
                new Light("6", false),
                new Light("7", false),
                new Light("8", false),
                new Door("9", true)),
                "bedroom");
        Room hall = new Room(Arrays.asList(
                new Light("10", false),
                new Light("11", false),
                new Light("12", false),
                new Door("13", false)),
                "hall");


        floor.setRooms(0, Arrays.asList(kitchen, bathroom, bedroom, hall));
        interior.setFloors(Collections.singletonList(floor));
        SmartHome smartHome = new SmartHome(interior, exterior);

        String jsonString = representation.toString(smartHome);
        System.out.println(jsonString);
        homeSaver.write(jsonString);
    }

}
