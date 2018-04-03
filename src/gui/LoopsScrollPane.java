package gui;

import javax.swing.*;
import java.awt.*;

public class LoopsScrollPane extends JScrollPane{
    private static LoopsScrollPane pane = new LoopsScrollPane();
    private JPanel container;
    private int panelsAdded;
    private int columns = 6;
    private double buttonWeight = .93;
    private double paddingWeight = .07;


    private LoopsScrollPane(){
        super();
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


        container = new JPanel();
        container.setLayout(new GridBagLayout());
        container.setBackground(Color.GRAY);
        container.setBorder(BorderFactory.createEmptyBorder(15,0,30,0));

        setColumnHeaderView(header);
        setViewportView(container);
        setBackground(Color.LIGHT_GRAY);
    }

    public static LoopsScrollPane getPane(){
        return pane;
    }

    public void addLoopButton(LoopButton button){
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = panelsAdded % columns * 2 + 1;
        c.gridy = panelsAdded / columns * 2 + 1;
        c.weightx = buttonWeight;
        c.fill = GridBagConstraints.HORIZONTAL;
        container.add(button,c);

        if(c.gridx == 1){
            c.gridx--;
            c.gridy--;
            c.fill = GridBagConstraints.HORIZONTAL;
            JPanel verticalSeperator = new JPanel();
            verticalSeperator.setOpaque(false);
            c.gridwidth = columns * 2 + 1;
            container.add(verticalSeperator, c);

            c.gridy++;
            c.gridwidth = 1;
            c.weightx = paddingWeight;
            c.fill = GridBagConstraints.NONE;
            JPanel empty = new JPanel();
            empty.setOpaque(false);
            container.add(empty, c);

            c.gridx++;
        }

        c.fill = GridBagConstraints.NONE;
        c.gridx++;
        c.weightx = paddingWeight;
        JPanel empty = new JPanel();
        empty.setOpaque(false);
        container.add(empty, c);



        panelsAdded++;
    }
}
