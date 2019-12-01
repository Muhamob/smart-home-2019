package ru.sbt.mipt.oop.homeStructure;

import ru.sbt.mipt.oop.actions.HomeComponentAction;

import java.util.ArrayList;
import java.util.List;

public class Building implements Actionable {
    private List<Actionable> rooms;

    public Building(List<Actionable> rooms) {
        this.rooms = new ArrayList<>(rooms);
        System.out.println(rooms);
    }

    @Override
    public boolean execute(HomeComponentAction action) {
        boolean executed = action.execute(this);
        for (Actionable premise : rooms) {
            executed |= premise.execute(action);
        }

        return executed;
    }
}
