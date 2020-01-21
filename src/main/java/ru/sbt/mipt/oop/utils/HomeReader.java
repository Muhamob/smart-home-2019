package ru.sbt.mipt.oop.utils;

import ru.sbt.mipt.oop.homeStructure.SmartHome;

public interface HomeReader {
    SmartHome readHome(String path);
}
