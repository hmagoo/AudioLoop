package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import engine.MidiByte;
import engine.Synchronizer;

public class LoopButton extends JButton implements ActionListener{
    private MidiByte m;
    private Synchronizer s;
    private int track;

    public LoopButton(MidiByte m, int track){
        super(m.toString() + ":" + track);
        this.m = m;
        this.track = track;
        setMargin(new Insets(50,15,50,15));
        setBackground(new Color(0xdb7a7a));
        addActionListener(this);
        s = Synchronizer.getSynchronizer();
        m.setTrackMute(track, true);
        m.startLoop();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(getBackground() == Color.LIGHT_GRAY) {
            setBackground(new Color(0xdb7a7a));
            m.setTrackMute(track, true);
            //s.stop(m);
        } else {
            setBackground(Color.LIGHT_GRAY);
//            s.start(m);
            m.setTrackMute(track, false);
        }
    }

}
