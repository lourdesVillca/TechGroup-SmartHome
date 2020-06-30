package smarthouse;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.stream.Stream;

public class ConfigManager {
    private final HashMap<String, Sensor> sensorList = new HashMap<String, Sensor>();
    private String configData;
    private String sensorKey;
    private int currentPosition;
    private final DeviceFactory deviceFactory;

    public ConfigManager() {
        deviceFactory = new DeviceFactory();
        configData = "";
        currentPosition = 0;
    }

    public void readFile(String filename) {
        try (Stream<String> stream = Files.lines(Paths.get(filename))) {
            stream.forEach(line -> {
                configData = configData.concat("\n" + line);
                currentPosition++;
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readFileByLinePosition(String filename) {
        configData = "";
        try (Stream<String> lines = Files.lines(Paths.get(filename))) {
            lines.skip(currentPosition).forEach(line -> {
                configData = configData.concat("\n" + line);
                currentPosition++;
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void processConfigInfo() {
        for (String line : configData.split("\n")) {
            String[] dataInfo = line.split("=");
            String action = dataInfo[0];
            if (action.contains("create-sensor")) {
                System.out.println("Create new Sensor: " + dataInfo[1]);
                Sensor sensor = new Sensor(dataInfo[1]);
                sensorList.put(dataInfo[1], sensor);
                sensorKey = dataInfo[1];
            }
            if (action.startsWith("create-device")) {
                String[] deviceInfo = dataInfo[1].split(",");
                sensorKey = deviceInfo[0];
                String deviceName = deviceInfo[1];
                String deviceStatus = deviceInfo[2];
                Device device = deviceFactory.createDevice(deviceName, deviceStatus);
                sensorList.get(sensorKey).registerObserver(device);
            }
            if (action.startsWith("update-sensor")) {
                String[] sensorInfo = dataInfo[1].split(",");
                sensorKey = sensorInfo[0];
                if (sensorKey.equals("Motion")) {
                    sensorList.get(sensorKey).notifyMovement(Boolean.valueOf(sensorInfo[1]));
                } else {
                    sensorList.get(sensorKey).notifyTemperature(Integer.parseInt(sensorInfo[1]));
                }
            }
            if (action.startsWith("remove-device")) {
                String[] deviceInfo = dataInfo[1].split(",");
                sensorKey = deviceInfo[0];
                String deviceName = deviceInfo[1];
                Observer observer = sensorList.get(sensorKey).getObserver(deviceName);
                sensorList.get(sensorKey).removeObserver(observer);
            }
        }
    }
}
