package ru.sbt.mipt.oop.homeStructure;

import ru.sbt.mipt.oop.actions.HomeComponentAction;
import ru.sbt.mipt.oop.devices.SmartDevice;
import ru.sbt.mipt.oop.homeUtils.HomeCheckers;

import java.util.*;

public class Building implements HomeComponent {
    private List<HomeComponent> premises;

    public Building(List<HomeComponent> premises) {
        this.premises = new ArrayList<>(premises);
        System.out.println(premises);
    }

    @Override
    public boolean execute(HomeComponentAction action) {
        boolean executed = action.execute(this);
        for (HomeComponent premise : premises) {
            executed |= premise.execute(action);
        }

        return executed;
    }

    @Override
    public boolean contains(String id) {
        for (HomeComponent homeComponent : premises) {
            if(homeComponent.contains(id)) {
                return true;
            }
        }
        return false;
    }
}
