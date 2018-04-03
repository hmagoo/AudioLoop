package gui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

public class Window extends JFrame{
    private static Window window = new Window();


    private Window(){
        super("Audi Loop Creation Tool");
        setSize(1024,720);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        GridLayout headerGrid = new GridLayout(0,6);
        headerGrid.setHgap(25);
        JPanel header = new JPanel(headerGrid);
        header.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));
        header.setBackground(Color.DARK_GRAY);

        JButton addFolder = new JButton("Add folder");

        addFolder.setMaximumSize(new Dimension(50, 50));
        addFolder.setMargin(new Insets(15,0,15,0));
        header.add(addFolder);

        JButton refresh = new JButton("Refresh");


        header.add(refresh);

        JButton play = new JButton("Play");
        header.add(play);

        JButton pause = new JButton("Pause");
        header.add(pause);

        JButton stop = new JButton("Stop");
        header.add(stop);

        JPanel paddingRight = new JPanel();
        paddingRight.setOpaque(false);
        header.add(paddingRight);


        JPanel container = new JPanel();
        GridLayout layout = new GridLayout(0, 7, 30, 25);
        container.setLayout(layout);
        container.setBackground(Color.GRAY);
        container.setBorder(BorderFactory.createEmptyBorder(15,0,30,0));

        JButton buttons[] = new JButton[100];

        for(int i = 0; i < 100; i++) {
            buttons[i]=new JButton("loop" + i + ".mid");
            buttons[i].setMargin(new Insets(50,15,50,15));
            buttons[i].setBackground(Color.LIGHT_GRAY);
//            buttons[i].setBorder(BorderFactory.createBevelBorder);
            container.add(buttons[i]);

        }


//        loops.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
//        loops.add(loop4);
//        loops.add(loop5);

        JScrollPane scrollPane = new JScrollPane(container);
        scrollPane.setColumnHeaderView(header);
        scrollPane.setBackground(Color.LIGHT_GRAY);
        add(scrollPane);




        setVisible(true);
    }

    public static Window getWindow() {
        return window;
    }
}
