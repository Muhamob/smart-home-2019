package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.homeUtils.HomeReader;
import ru.sbt.mipt.oop.homeUtils.JsonHomeReader;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        HomeReader homeReader = new JsonHomeReader();
        try {
            Application app = new Application(homeReader, "smart-home-1.js");
            app.run();
        } catch (IOException e) {
            System.out.println("Something wrong with reading file");
        }
    }
}
