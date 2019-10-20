package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.homeUtils.HomeReader;
import ru.sbt.mipt.oop.homeUtils.JsonHomeReader;
import ru.sbt.mipt.oop.sources.EventSource;
import ru.sbt.mipt.oop.sources.TestSource1;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        HomeReader homeReader = new JsonHomeReader();
        EventSource eventSource = new TestSource1();

        try {
            Application app = new Application(
                    homeReader, "new-home-by-new-project.js", eventSource);
            app.run();
        } catch (IOException e) {
            System.out.println("Something wrong with reading file");
        }
    }
}
