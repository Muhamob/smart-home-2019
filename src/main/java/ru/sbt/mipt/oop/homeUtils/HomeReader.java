package ru.sbt.mipt.oop.homeUtils;

import ru.sbt.mipt.oop.SmartHome;

import java.io.IOException;

public interface HomeReader {
    public SmartHome readHome(String path) throws IOException;
}
