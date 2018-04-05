import javax.sound.midi.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.channels.Channel;

public class Test {
    public static void main(String[] args) throws MidiUnavailableException, InvalidMidiDataException, IOException, InterruptedException {
        Sequencer s = MidiSystem.getSequencer();
        Sequence sequence = MidiSystem.getSequence(new File("music" + File.separator + "custom" + File.separator + "t" +
                "" +
                "est.mid"));
        s.setSequence(sequence);
        s.open();
        //s.setTrackMute(0,true);

//        s.setTrackMute(1, true);
//        s.setTrackMute(2, true);
//        s.setTrackSolo(0, true);

        System.out.println();

        s.setLoopStartPoint(0);
        s.setLoopEndPoint(-1);
        s.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);


        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        Track tracks[] = sequence.getTracks();
        for(Track t : tracks){
            System.out.println(t);
        }

//        tracks[0].add(new MidiEvent(new ShortMessage(ShortMessage.PITCH_BEND, 0, 30, 30), 0));
//        tracks[0].add(new MidiEvent(new ShortMessage(ShortMessage.CONTROL_CHANGE, 0, 0, 9), 0));
//        tracks[0].add(new MidiEvent(new ShortMessage(ShortMessage.CONTROL_CHANGE, 0, 32, 0), 0));

        Synthesizer synth = MidiSystem.getSynthesizer();
        Instrument instruments[] = synth.getDefaultSoundbank().getInstruments();
        //plays a synthed note
//        synth.loadInstrument(instruments[55]);
//        MidiChannel mc[] = synth.getChannels();
//        synth.open();
//        mc[0].
//        mc[0].noteOn(60, 1200);


        for(Instrument i : instruments){
            System.out.println(i);
        }

//        for(int i = 120; i < 128; i++) {
//            //Changes the instrument used
//            tracks[0].add(new MidiEvent(new ShortMessage(ShortMessage.PROGRAM_CHANGE, 0, i, 0), 0));
//            System.out.println("Now playing " + instruments[i]);
//            Thread.sleep(3975);
//        }

        //tracks[0].add(new MidiEvent(new ShortMessage(ShortMessage.PROGRAM_CHANGE, 0, 106, 0), 0));
//        tracks[0].add(new MidiEvent(new ShortMessage(ShortMessage.PROGRAM_CHANGE, 1, 75, 0), 0));
//        tracks[0].add(new MidiEvent(new ShortMessage(ShortMessage.PROGRAM_CHANGE, 2, 115, 0), 0));


        s.getTickLength();

//        for(int i = 0; i < 1000; i++) {
//            tracks[0].add(new MidiEvent(new ShortMessage(ShortMessage.CONTROL_CHANGE, 0, 0, 9), i));


        //Changes the instrument of a track's channel to the specified instrument (data1) at the specified tick
        System.out.println(tracks[0].add(new MidiEvent(new ShortMessage(ShortMessage.PROGRAM_CHANGE, 0, 6, 0), 0)));
        System.out.println(tracks[1].add(new MidiEvent(new ShortMessage(ShortMessage.PROGRAM_CHANGE, 0, 6, 0), 0)));
        System.out.println(tracks[2].add(new MidiEvent(new ShortMessage(ShortMessage.PROGRAM_CHANGE, 0, 6, 0), 0)));
        System.out.println(tracks[0].add(new MidiEvent(new ShortMessage(ShortMessage.PROGRAM_CHANGE, 1, 79, 0), 0)));
        System.out.println(tracks[1].add(new MidiEvent(new ShortMessage(ShortMessage.PROGRAM_CHANGE, 1, 79, 0), 0)));
        System.out.println(tracks[2].add(new MidiEvent(new ShortMessage(ShortMessage.PROGRAM_CHANGE, 1, 79, 0), 0)));
        System.out.println(tracks[0].add(new MidiEvent(new ShortMessage(ShortMessage.PROGRAM_CHANGE, 2, 117, 0), 0)));
        System.out.println(tracks[1].add(new MidiEvent(new ShortMessage(ShortMessage.PROGRAM_CHANGE, 2, 117, 0), 0)));
        System.out.println(tracks[2].add(new MidiEvent(new ShortMessage(ShortMessage.PROGRAM_CHANGE, 2, 117, 0), 0)));
        s.start();
        Thread.sleep(8000);
//        System.out.println(tracks[0].add(new MidiEvent(new ShortMessage(ShortMessage.PROGRAM_CHANGE, 1, 57, 0), 0)));
        System.out.println(tracks[1].add(new MidiEvent(new ShortMessage(ShortMessage.PROGRAM_CHANGE, 1, 57, 0), 0)));
//        System.out.println(tracks[2].add(new MidiEvent(new ShortMessage(ShortMessage.PROGRAM_CHANGE, 1, 57, 0), 0)));

//            tracks[1].add(new MidiEvent(new ShortMessage(ShortMessage.CONTROL_CHANGE, 0, 0, 9), i));
//            tracks[1].add(new MidiEvent(new ShortMessage(ShortMessage.PROGRAM_CHANGE, 0, 56, 0), i));
//            tracks[2].add(new MidiEvent(new ShortMessage(ShortMessage.CONTROL_CHANGE, 0, 0, 9), i));
//            tracks[2].add(new MidiEvent(new ShortMessage(ShortMessage.PROGRAM_CHANGE, 0, 80, 0), i));
//            tracks[3].add(new MidiEvent(new ShortMessage(ShortMessage.PROGRAM_CHANGE, 0, 80, 0), i));
//            tracks[3].add(new MidiEvent(new ShortMessage(ShortMessage.PROGRAM_CHANGE, 0, 90, 0), i));
//        }



//        s.setTrackMute(0, true);
//        s.setTrackMute(1, true);
        s.setTrackMute(3, true);
        s.setTrackMute(4, true);





//        for(int i = 0; i < 4; i++){
//            System.out.println("Now playing track " + i);
//            for(int n = 0; n < 16; n++){
//                s.setTrackMute(n, n != i);
//            }
//
//            Thread.sleep(3000);
//        }
//
//        while(true){
//            in.readLine();
//            System.out.println("Stopping or starting a track");
//            s.setTrackMute((int) (Math.random() * 3), 1 == (int) (Math.random() * 2));
//        }
    }
}
