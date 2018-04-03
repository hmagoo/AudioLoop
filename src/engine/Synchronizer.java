package engine;

import java.util.ArrayList;

public class Synchronizer implements Runnable {
    private static Synchronizer synchronizer;
    private volatile ArrayList<MidiByte> toStart;
    private volatile ArrayList<MidiByte> toStop;
    private volatile ArrayList<MidiByte> playing;
    private Thread t;

    private Synchronizer() {
        if (synchronizer == null) {
            toStart = new ArrayList<>();
            toStop = new ArrayList<>();
            playing = new ArrayList<>();
            t = new Thread(this);
        }
    }

    public static Synchronizer getSynchronizer() {
        if(synchronizer == null){
            synchronizer = new Synchronizer();
        }
        return synchronizer;
    }

    public void start(MidiByte m){
        if(toStop.contains(m)){
            toStop.remove(m);
        } else if(playing.contains(m)) {
            System.out.println("Warning: trying to start " + m + ", it is already playing");
        } else{
            toStart.add(m);
        }

        if(playing.isEmpty() && (t.getState() == Thread.State.NEW || t.getState() == Thread.State.TERMINATED)){
            System.out.println();
            System.out.println("Starting thread. " + m + " was added.");
            t.interrupt();
            t = new Thread(this);
            t.start();
        }
    }

    public void stop(MidiByte m){
        if(toStart.contains(m)) {
            toStart.remove(m);
        } else if (playing.contains(m)) {
            toStop.add(m);
        } else {
            System.out.println("Warning: trying to stop " + m + ", it wasn't playing");
        }
    }

    private void stopAll(){
        for (MidiByte m: toStop) {
            m.stopLoop();
            playing.remove(m);
        }

        toStop.clear();
        if(playing.isEmpty()){
            System.out.println("Stopping thread");
            t.interrupt();
        }
    }

    private void startAll(){
        for (MidiByte m : toStart) {
            m.startLoop();
            playing.add(m);
        }

        toStart.clear();
    }

    @Override
    public void run() {
        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        startAll();

        long prev = 0;
        while(playing.size() > 0){
            long cur = playing.get(0).getTickPosition();

            if(prev > cur){
                startAll();
                stopAll();
            }

            prev = cur;
        }

        System.out.println("Finished");
    }
}
