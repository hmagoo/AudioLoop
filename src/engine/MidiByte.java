package engine;

import javax.sound.midi.*;
import java.io.*;
import java.util.StringTokenizer;

public class MidiByte {
    private Sequencer s;
    private int beatLength;
    String path;
    String name;

    public MidiByte(String filePath) throws MidiUnavailableException, IOException, InvalidMidiDataException {
        this(new File(filePath));
    }

    public MidiByte(File file) throws MidiUnavailableException, IOException, InvalidMidiDataException{
        s = MidiSystem.getSequencer();
        Sequence sequence = MidiSystem.getSequence(file);
        s.setSequence(sequence);
        System.out.println(s.getClass());

        beatLength = sequence.getResolution();
        s.setLoopStartPoint(0);
        s.setLoopEndPoint(-1);
        s.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
        s.open();

        path = file.getPath();

        StringTokenizer tok = new StringTokenizer(path, File.separator);
        while(tok.hasMoreTokens()){
            name = tok.nextToken();
        }

        if(name.regionMatches(true, name.length() - 4, ".mid", 0,4)){
            name = name.substring(0,name.length() - 4);
        }
    }

    public void stopLoop(){
        s.stop();
    }

    public void startLoop(){
        s.start();
    }

    public void setTrackMute(int track, boolean flag){
        s.setTrackMute(track, flag);
    }

    public void close(){
        s.close();
    }

    public long getTickPosition(){
        return s.getTickPosition();
    }

    @Override
    public String toString(){
        return name;
    }

    public void meta(MetaMessage event){
        if(event.getType() == 47){
            System.out.println("Stopping " + name);
        }else {
            System.out.println("Meta event triggered " + event);
        }
    }

    @Override
    public boolean equals(Object object) {
        if (object == null){
            return false;
        } else if(object instanceof MidiByte) {
            return path.equals(((MidiByte) object).path);
        } else {
            System.out.println("Other type");
            System.out.println(object.getClass());
            return false;
        }
    }
}
