package ru.sbt.mipt.oop.homeStructure;

import ru.sbt.mipt.oop.actions.HomeComponentAction;

public interface Actionable {
    boolean execute(HomeComponentAction action);
}
