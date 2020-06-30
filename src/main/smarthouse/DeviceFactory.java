package smarthouse;

public class DeviceFactory {
    /**
     * Method that given the device name create the Device Object.
     *
     * @param name represents the device.
     * @return a new Ship according the ship type.
     */
    Device createDevice(String name, String status) {
        switch (name) {
            case "Light":
                return new Light(name, status);

            case "Television":
                return new Television(name, status);

            case "Alarm":
                return new Alarm(name, status);

            default:
                return new AirConditioning(name, status, 0);
        }
    }
}
