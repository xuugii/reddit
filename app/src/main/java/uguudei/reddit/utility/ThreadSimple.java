package uguudei.reddit.utility;


import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class ThreadSimple {
    public static ScheduledExecutorService getWorker(){
        return Executors.newSingleThreadScheduledExecutor();
    }
}
