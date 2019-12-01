package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.utils.HomeReader;
import ru.sbt.mipt.oop.utils.JsonHomeReader;
import ru.sbt.mipt.oop.sources.EventManager;
import ru.sbt.mipt.oop.sources.EventSource;
import ru.sbt.mipt.oop.sources.EventListEventManger;
import ru.sbt.mipt.oop.sources.TestSourceHallDoorClosing;

public class Main {
    public static void main(String[] args) {
        HomeReader homeReader = new JsonHomeReader();
        EventSource eventSource = new TestSourceHallDoorClosing();
        EventManager manger = new EventListEventManger(eventSource);

        Application app = new Application(homeReader, "new-home-by-new-project.js", manger);
        app.run();
    }
}
