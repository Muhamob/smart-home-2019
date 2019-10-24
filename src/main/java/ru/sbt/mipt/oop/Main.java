package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.homeUtils.HomeReader;
import ru.sbt.mipt.oop.homeUtils.JsonHomeReader;
import ru.sbt.mipt.oop.sources.EventManager;
import ru.sbt.mipt.oop.sources.EventSource;
import ru.sbt.mipt.oop.sources.EventSourceManger;
import ru.sbt.mipt.oop.sources.TestSourceHallDoorClosing;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        HomeReader homeReader = new JsonHomeReader();
        EventSource eventSource = new TestSourceHallDoorClosing();
        EventManager manger = new EventSourceManger(eventSource);

        try {
            Application app = new Application(
                    homeReader, "new-home-by-new-project.js", manger);
            app.run();
        } catch (IOException e) {
            System.out.println("Something wrong with reading file");
        }
    }
}
