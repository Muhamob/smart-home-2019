package ru.sbt.mipt.oop.actions;

import ru.sbt.mipt.oop.homeStructure.HomeComponent;

/*
Интерфейс для действий применимых к homeComponents
 */
public interface HomeComponentAction {
    public void execute(HomeComponent homeComponent);
}
