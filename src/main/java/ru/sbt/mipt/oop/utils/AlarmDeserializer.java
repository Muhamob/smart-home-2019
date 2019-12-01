package ru.sbt.mipt.oop.utils;

import com.google.gson.*;
import ru.sbt.mipt.oop.devices.alarm.Alarm;

import java.lang.reflect.Type;

public class AlarmDeserializer implements JsonDeserializer<Alarm> {
    @Override
    public Alarm deserialize(JsonElement jsonElement, Type type,
                                  JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();

        return new Alarm(
                jsonObject.get("id").getAsString()
        );
    }
}
