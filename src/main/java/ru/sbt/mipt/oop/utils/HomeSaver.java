package ru.sbt.mipt.oop.utils;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class HomeSaver {

    private final Path path;

    public HomeSaver(String path) {
        this.path = Paths.get(path);
    }

    public void write(String string) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write(string);
        }
    }
}
