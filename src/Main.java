import engine.MidiByte;
import gui.LoopButton;
import gui.LoopsScrollPane;
import gui.Window;

import java.io.File;

/**
 * Created by WHOmagoo on 4/2/2018.
 */
public class Main {
    public static void main(String[] args) throws Exception{
        Window window = Window.getWindow();
        LoopsScrollPane scrollPane = LoopsScrollPane.getPane();

        //Make a new engine.MidiByte and have it load the midi from the given file location
        MidiByte c = new MidiByte("music" + File.separator + "scale_chords_small" + File.separator + "midi" + File.separator + "scale_c_major.mid");
        MidiByte e = new MidiByte("music" + File.separator + "scale_chords_small" + File.separator + "midi" + File.separator + "scale_e_major.mid");
        MidiByte g = new MidiByte("music" + File.separator + "scale_chords_small" + File.separator + "midi" + File.separator + "scale_g_major.mid");

        LoopButton MusicButtons[] = new LoopButton[100];

        MusicButtons[0] = new LoopButton("C major", c);
        MusicButtons[1] = new LoopButton("E major", e);
        MusicButtons[2] = new LoopButton("G major", g);

        for(LoopButton button : MusicButtons){
            if(button == null){
                break;
            } else {
                System.out.println("Adding something");
                scrollPane.addLoopButton(button);
            }
        }

        window.add(scrollPane);
        window.revalidate();
    }
}
