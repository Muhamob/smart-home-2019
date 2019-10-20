package ru.sbt.mipt.oop.actions;

import ru.sbt.mipt.oop.homeStructure.HomeComponent;

/*
Интерфейс для действий применимых к homeComponents
 */
public interface HomeComponentAction {
    // true means that action was executed
    public boolean execute(HomeComponent homeComponent);
}
