package smarthouse;

import java.io.File;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class SmartHouseMain {
    public static void main(String[] args) {
        String pathname="src/main/smarthouse/config.txt";
        ConfigManager configFile = new ConfigManager();
        configFile.readFile(pathname);
        configFile.processConfigInfo();

        TimerTask task = new FileObserver( new File(pathname) ) {
            protected void onChange( File file ) {
                // here we code the action on a change
                System.out.println( "Config File have changed" );
                configFile.readFileByLinePosition(pathname);
                configFile.processConfigInfo();
            }
        };

        Timer timer = new Timer();
        timer.schedule( task , new Date(), 1000 );
    }
}
