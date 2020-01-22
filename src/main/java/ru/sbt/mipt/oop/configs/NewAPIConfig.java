package ru.sbt.mipt.oop.configs;

import com.coolcompany.smarthome.events.EventHandler;
import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rc.RemoteControl;
import rc.RemoteControlRegistry;
import ru.sbt.mipt.oop.adapters.*;
import ru.sbt.mipt.oop.homeStructure.SmartHome;
import ru.sbt.mipt.oop.remotecontrol.*;
import ru.sbt.mipt.oop.utils.HomeReader;
import ru.sbt.mipt.oop.utils.JsonHomeReader;

@Configuration
public class NewAPIConfig {
    private static final String DEFAULT_ALARM_CODE = "1111";
    private static final String DEFAULT_RC_ID = "default_id";

    @Bean
    SensorEventsManager sensorEventsManager(){
        SensorEventsManager sensorEventsManager = new SensorEventsManager();
        sensorEventsManager.registerEventHandler(
                getEventHandlerAdapter(getSmartHome(), getSensorEventAdapter())
        );
        return sensorEventsManager;
    }

    private SensorEventAdapter getSensorEventAdapter() {
        return new EventAdapter();
    }

    private EventHandler getEventHandlerAdapter(SmartHome smartHome, SensorEventAdapter sensorEventAdapter) {
        return new EventHandlerAdapter(smartHome, sensorEventAdapter);
    }

    @Bean
    public SmartHome getSmartHome() {
        return getJsonHomeReader().readHome("new-home-by-new-project.js");
    }

    @Bean
    public HomeReader getJsonHomeReader() {
        return new JsonHomeReader();
    }

    @Bean
    RemoteControl remoteControl(SmartHome smartHome) {
        BrandNewRemoteControl smartHomeRemoteControl = new BrandNewRemoteControl();
        smartHomeRemoteControl.addRemoteControl("A", activateAlarmCommand(smartHome));
        smartHomeRemoteControl.addRemoteControl("B", activateAlertCommand(smartHome));
        smartHomeRemoteControl.addRemoteControl("C", closeMainDoorCommand(smartHome));
        smartHomeRemoteControl.addRemoteControl("D", turnOffAllLightsCommand(smartHome));
        smartHomeRemoteControl.addRemoteControl("1", turnOnAllLightsCommand(smartHome));
        smartHomeRemoteControl.addRemoteControl("2", turnHallLightsOnCommand(smartHome));
        remoteControlRegistry().registerRemoteControl(smartHomeRemoteControl, DEFAULT_RC_ID);
        return smartHomeRemoteControl;
    }

    private RemoteControlRegistry remoteControlRegistry() {
        return new RemoteControlRegistry();
    }

    private Command turnHallLightsOnCommand(SmartHome smartHome) {
        return new TurnHallLightsOnCommand(smartHome);
    }

    private Command turnOnAllLightsCommand(SmartHome smartHome) {
        return new TurnOnAllLightsCommand(smartHome);
    }

    private Command turnOffAllLightsCommand(SmartHome smartHome) {
        return new TurnOffAllLightsCommand(smartHome);
    }

    private Command closeMainDoorCommand(SmartHome smartHome) {
        return new CloseMainDoorCommand(smartHome);
    }

    private Command activateAlertCommand(SmartHome smartHome) {
        return new ActivateAlertCommand(smartHome);
    }

    private Command activateAlarmCommand(SmartHome smartHome) {
        return new ActivateAlarmCommand(smartHome, DEFAULT_ALARM_CODE);
    }
}
