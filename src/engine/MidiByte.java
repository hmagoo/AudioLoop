package engine;

import javax.sound.midi.*;
import java.io.*;

public class MidiByte  {
    private Sequencer s;
    private int beatLength;

    public MidiByte(String filePath) throws MidiUnavailableException, IOException, InvalidMidiDataException {

        s = MidiSystem.getSequencer();
        Sequence sequence = MidiSystem.getSequence(new File(filePath));
        s.setSequence(sequence);
        s.setLoopStartPoint(0);
        beatLength = sequence.getResolution();
        s.setLoopEndPoint(beatLength * 8);
        s.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
        s.open();
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
}
