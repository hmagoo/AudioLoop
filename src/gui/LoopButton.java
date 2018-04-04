package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import engine.MidiByte;
import engine.Synchronizer;

public class LoopButton extends JButton implements ActionListener{
    MidiByte m;
    Synchronizer s;


    public LoopButton(MidiByte m){
        super(m.toString());
        this.m = m;
        setMargin(new Insets(50,15,50,15));
        setBackground(new Color(0xdb7a7a));
        addActionListener(this);
        s = Synchronizer.getSynchronizer();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(getBackground() == Color.LIGHT_GRAY) {
            setBackground(new Color(0xdb7a7a));
            s.stop(m);
        } else {
            setBackground(Color.LIGHT_GRAY);
            s.start(m);
        }
    }

}
