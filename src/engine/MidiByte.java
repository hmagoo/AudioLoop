package engine;

import javax.sound.midi.*;
import java.io.*;
import java.util.StringTokenizer;

public class MidiByte  {
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
        s.setLoopStartPoint(0);
        beatLength = sequence.getResolution();
        s.setLoopEndPoint(beatLength * 8);
        s.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
        s.open();

        path = file.getPath();

        StringTokenizer tok = new StringTokenizer(path, File.separator);
        while(tok.hasMoreTokens()){
            name = tok.nextToken();
        }

//        name.replace(".mid", "");
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

    public void close(){
        s.close();
    }

    public long getTickPosition(){
        return s.getTickPosition();
    }

    public String toString(){
        return name;
    }
}
