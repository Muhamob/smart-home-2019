package ru.sbt.mipt.oop.eventHandlers;

/*
Конфигурационный код
Чтобы добавить ещё одно событие нужно дописать EVENTSMTH(new EventSmth());
 */
public enum EventHandlerEnum {
    EVENTDOOR(new EventDoor()),
    EVENTLIGHT(new EventLight());

    private EventHandler eventHandler;

    EventHandlerEnum(EventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }

    public EventHandler getEventHandler() {
        return eventHandler;
    }
}
