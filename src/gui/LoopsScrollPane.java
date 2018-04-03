package gui;

import javax.swing.*;
import java.awt.*;

public class LoopsScrollPane extends JScrollPane{
    private static LoopsScrollPane pane = new LoopsScrollPane();

    private LoopsScrollPane(){
        JPanel container = new JPanel();
        GridLayout layout = new GridLayout(0, 7, 30, 25);
        container.setLayout(layout);

        JButton buttons[] = new JButton[100];
        JPanel panels[] = new JPanel[100];

        for(int i = 0; i < 100; i++) {
            panels[i] = new JPanel(new BorderLayout(0,0));
            panels[i].setBackground(Color.ORANGE);

            buttons[i]=new JButton("<html> <br><br><br>loop" + i + ".mid<br><br><br><br>");
            panels[i].add(buttons[i]);
            if((int) (Math.random() * 2) == 1){
                container.add(panels[i]);
            }
        }

        setRowHeaderView(container);
    }

    public static LoopsScrollPane getPane(){
        return pane;
    }

}
