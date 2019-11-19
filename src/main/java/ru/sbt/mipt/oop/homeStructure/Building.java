package ru.sbt.mipt.oop.homeStructure;

import ru.sbt.mipt.oop.actions.HomeComponentAction;

import java.util.*;

public class Building implements HomeComponent {
    private List<HomeComponent> rooms;

    public Building(List<HomeComponent> rooms) {
        this.rooms = new ArrayList<>(rooms);
        System.out.println(rooms);
    }

    @Override
    public boolean execute(HomeComponentAction action) {
        boolean executed = action.execute(this);
        for (HomeComponent premise : rooms) {
            executed |= premise.execute(action);
        }

        return executed;
    }

    @Override
    public boolean contains(String id) {
        for (HomeComponent homeComponent : rooms) {
            if(homeComponent.contains(id)) {
                return true;
            }
        }
        return false;
    }
}
