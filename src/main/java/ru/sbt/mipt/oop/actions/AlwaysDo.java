package ru.sbt.mipt.oop.actions;

import ru.sbt.mipt.oop.homeStructure.HomeComponent;

public class AlwaysDo implements HomeComponentAction {
    @Override
    public boolean execute(HomeComponent homeComponent) {
        System.out.println(homeComponent);
        return true;
    }
}
