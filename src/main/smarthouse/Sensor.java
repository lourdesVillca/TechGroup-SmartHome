package smarthouse;

import java.util.ArrayList;

public class Sensor implements Subject {
    private final ArrayList<Observer> deviceList;
    private final String type;

    public Sensor(String type) {
        this.deviceList = new ArrayList<>();
        this.type = type;
    }

    @Override
    public void registerObserver(Observer o) {
        System.out.println("Registering new Observer: " + o.getObjectName() + " -- Sensor type: " + type);
        deviceList.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        System.out.println("Removing object: " + o.getObjectName());
        deviceList.remove(o);
    }

    @Override
    public void notifyMovement(boolean status) {
        String message = status ? "Sensor detect movement" : "Sensor detect No movement";
        System.out.println(message);
        deviceList.forEach(device -> {
            if (type.equals("Motion")) {
                device.update(status);
            }
        });
    }

    @Override
    public void notifyTemperature(int value) {
        System.out.println("Sensor receive signal to change temperature");
        deviceList.forEach(device -> {
            if (type.equals("Temperature")) {
                device.update(value);
            }
        });
    }

    public Observer getObserver(String deviceName) {
        return deviceList.stream()
                .filter(observer -> deviceName.equals(observer.getObjectName()))
                .findAny().orElse(null);
    }
}
