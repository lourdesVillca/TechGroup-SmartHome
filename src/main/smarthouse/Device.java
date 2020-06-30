package smarthouse;

public class Device implements Observer {
    protected String status;
    protected String deviceName;

    public Device(String deviceName, String status) {
        this.deviceName = deviceName;
        this.status = status;
    }

    @Override
    public void update(int value) {
    }

    @Override
    public void update(boolean status) {
        this.status = status ? "On" : "Off";
        System.out.println("Device: " + this.deviceName + " Changing status to: " + this.status);
    }

    @Override
    public String getObjectName() {
        return this.deviceName;
    }

}
