package smarthouse;

public class AirConditioning extends Device{
    private int temperature;
    public AirConditioning(String deviceName, String status, int temperature){
        super(deviceName, status);
        this.temperature = temperature;

    }
    @Override
    public void update(int temperature){
        System.out.println("Device: "+ super.deviceName+ " Changing temperature to: " + temperature);
        this.temperature = temperature;
    }
}
