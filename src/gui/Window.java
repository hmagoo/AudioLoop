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
        LoopsScrollPane scrollPane = LoopsScrollPane.getPane();

        add(scrollPane);
    }

    public static Window getWindow() {
        return window;
    }
}
