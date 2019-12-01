package ru.sbt.mipt.oop.homeStructure;

import ru.sbt.mipt.oop.actions.HomeComponentAction;

public interface Actionable {
    public boolean execute(HomeComponentAction action);
}
