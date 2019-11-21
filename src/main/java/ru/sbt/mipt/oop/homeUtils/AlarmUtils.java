package ru.sbt.mipt.oop.homeUtils;

import ru.sbt.mipt.oop.devices.Alarm;
import ru.sbt.mipt.oop.homeStructure.Actionable;

public class AlarmUtils {
    public static Alarm getAlarm(Actionable actionable) {
        final Alarm[] alarm_tmp = {null};

        actionable.execute(x -> {
            if (x instanceof Alarm) {
                alarm_tmp[0]= (Alarm) x;
                return true;
            }
            return false;
        });

        return alarm_tmp[0];
    }
}
