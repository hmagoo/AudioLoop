package engine;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import javax.sound.midi.*;

public class PlayMidiAudio {
    public static void main(String[] args) throws Exception {

        // Obtains the default Sequencer connected to a default device.
        Sequencer cMajor = MidiSystem.getSequencer();

        // Opens the device, indicating that it should now acquire any
        // system resources it requires and become operational.
        cMajor.open();

        // create a stream from a file
        InputStream is = new BufferedInputStream(new FileInputStream(new File("music" + File.separator + "scale_chords_small" + File.separator + "midi" + File.separator + "scale_c_major.mid")));
        // Sets the current sequence on which the sequencer operates.
        // The stream must point to MIDI file data.
        // Starts playback of the MIDI data in the currently loaded sequence.
//        sequencer.setTempoInBPM();

        long oneQuarterNote = 960;



        cMajor.setSequence(is);
        cMajor.setTickPosition(0);
        cMajor.setLoopStartPoint(0);
        cMajor.setLoopEndPoint(oneQuarterNote*5);
        cMajor.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);

        InputStream eMajorStream = new BufferedInputStream(new FileInputStream(new File("music" + File.separator + "scale_chords_small" + File.separator + "midi" + File.separator + "scale_e_major.mid")));
        Sequencer eMajor = MidiSystem.getSequencer();
        //eMajor.open();
        eMajor.setSequence(eMajorStream);
        eMajor.setLoopStartPoint(0);
        eMajor.setLoopEndPoint(oneQuarterNote*5);
        eMajor.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);

        InputStream gMajorStream = new BufferedInputStream(new FileInputStream(new File("music" + File.separator + "scale_chords_small" + File.separator + "midi" + File.separator + "scale_g_major.mid")));
        Sequencer gMajor = MidiSystem.getSequencer();
        gMajor.open();
        gMajor.setSequence(gMajorStream);
        gMajor.setLoopStartPoint(0);
        gMajor.setLoopEndPoint(oneQuarterNote*5);
        gMajor.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);



        System.out.println("Started Sequencer");

        Sequencer first = eMajor;
        Sequencer second = cMajor;
        Sequencer third = gMajor;

        System.out.println(first.getTempoFactor());
        System.out.println(first.getTempoInBPM());
        System.out.println(first.getTempoInMPQ());
//        System.out.println(first.);
//        System.out.println(first.);
//        System.out.println(first.);
//        System.out.println(first.);
//        System.out.println(first.);
        first.open();
        first.start();

        int loops = 0;


        long prevPos = 0;
        while(loops < 3){
            long curPosition = first.getTickPosition();
            if(prevPos > curPosition) {
                loops++;
                prevPos = curPosition;
                System.out.println(loops);
                if(true)
                    if(loops == 1){
                        second.start();
                    } else if (loops == 2){
                        third.start();
                    }
            } else {
                prevPos = curPosition;
            }

        }

        //continue playing to the end of the file
        first.setLoopCount(0);
        second.setLoopCount(0);
        third.setLoopCount(0);
        first.setTickPosition(first.getLoopEndPoint());
        second.setTickPosition(second.getLoopEndPoint());
        third.setTickPosition(third.getLoopEndPoint());

        while(first.getTickPosition() < oneQuarterNote * 8){
            //while less than 8 eight notes played, continue playing
        }




        //stop playback
        //can change to stop
        System.out.println("Stopping the midi");
//        cMajor.close();
//        eMajor.close();
//        gMajor.close();

        cMajor.stop();
        eMajor.stop();
        gMajor.stop();
    }
}
