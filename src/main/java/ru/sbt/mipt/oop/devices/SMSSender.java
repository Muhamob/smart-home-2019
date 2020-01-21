package ru.sbt.mipt.oop.devices;

public class SMSSender extends SmartDevice {
    private final String phoneNumber;

    public SMSSender(String id, String phoneNumber) {
        super(id);
        this.phoneNumber = phoneNumber;
    }

    public SMSSender(String id) {
        super(id);
        // default phone
        this.phoneNumber = "112";
    }

    public void sendSms(String message) {
        System.out.println("Sending message " + message + " to " + this.phoneNumber + ".");
        // And do smth
    }
}
