package ru.sbt.mipt.oop.actions;

import ru.sbt.mipt.oop.homeStructure.Actionable;

/*
Интерфейс для действий применимых к homeComponents
 */
public interface HomeComponentAction {
    // true means that action was executed
    boolean execute(Actionable actionable);
}
