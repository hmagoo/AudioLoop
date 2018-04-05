import javax.sound.midi.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test {
    public static void main(String[] args) throws MidiUnavailableException, InvalidMidiDataException, IOException {
        Sequencer s = MidiSystem.getSequencer();
        Sequence sequence = MidiSystem.getSequence(new File("music" + File.separator + "custom" + File.separator + "Two Track Scale.mid"));
        s.setSequence(sequence);
        s.open();
        s.setTrackMute(0,false);
        s.setTrackMute(1, true);
        s.setTrackMute(2, true);

        s.setLoopStartPoint(0);
        s.setLoopEndPoint(-1);
        s.setLoopCount(-1);
        s.start();

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        while(true){
            in.readLine();
            System.out.println("Stopping or starting a track");
            s.setTrackMute((int) (Math.random() * 3), 1 == (int) (Math.random() * 2));
        }
    }
}
