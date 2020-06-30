package smarthouse;

import java.io.File;
import java.util.TimerTask;

public abstract class FileObserver extends TimerTask {
    private long timeStamp;
    private File file;

    public FileObserver( File file ) {
        this.file = file;
        this.timeStamp = file.lastModified();
    }

    public final void run() {
        long timeStamp = file.lastModified();

        if( this.timeStamp != timeStamp ) {
            this.timeStamp = timeStamp;
            onChange(file);
        }
    }

    protected abstract void onChange( File file );
}
