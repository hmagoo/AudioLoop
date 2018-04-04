package gui;

import engine.MidiByte;
import engine.MidiBytesInUse;
import javafx.stage.FileChooser;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import static javax.swing.JOptionPane.showMessageDialog;

public class LoopsScrollPane extends JScrollPane{
    private static LoopsScrollPane pane = new LoopsScrollPane();
    private JPanel scrollPaneContainer;
    private JLabel disabledOverlay;
    private int panelsAdded;
    private int columns = 6;
    private double buttonWeight = .93;
    private double paddingWeight = .07;
    private MidiBytesInUse midiBytes;


    private LoopsScrollPane(){
        super();
        GridLayout headerGrid = new GridLayout(0,6);
        headerGrid.setHgap(25);
        JPanel header = new JPanel(headerGrid);
        header.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));
        header.setBackground(Color.DARK_GRAY);

        getVerticalScrollBar().setUnitIncrement(16);

        midiBytes = MidiBytesInUse.getMidiBytes();

        JButton addFolder = new JButton("Add files");

        addFolder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Window window = Window.getWindow();
                window.setLayout(new BorderLayout());
                window.add(disabledOverlay, 0);
                window.revalidate();
                window.repaint();

                SynchronousJFXFileChooser chooser = new SynchronousJFXFileChooser(new Supplier<FileChooser>() {
                    @Override
                    public FileChooser get() {
                        FileChooser fileChooser = new FileChooser();
                        fileChooser.setTitle("Select Midi Files");
                        fileChooser.setInitialDirectory(new File("./music"));

                        fileChooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("midi", "*.mid"));
                        return fileChooser;
                    }
                });

                Function f = new Function<FileChooser, java.util.List<File>>() {
                    @Override
                    public java.util.List<File> apply(FileChooser fileChooser) {
                        List<File> files = fileChooser.showOpenMultipleDialog(null);
                        return files;
                    }
                };

                List<File> files = (List<File>) chooser.showDialog(f);

                String duplicates = "Trying to add the following files multiple times:\n";
                String successful = "\nNew midi files added:\n";
                String midiUnavailable = "No resources for the following files:\n";
                String fileReadError = "Could not open the following files:\n";
                String notMidi = "The following files were not midi files:\n";

                boolean hasDuplicates = false;
                boolean hasSuccess = false;
                boolean hasMidiUnavailable = false;
                boolean hasFileReadError = false;
                boolean hasNotMidi = false;

                if(files != null) {
                    for (File curFile : files) {
                        try {
                            MidiByte m = new MidiByte(curFile);
                            if(!midiBytes.add(m)){
                                duplicates += curFile.getPath() + "\n";
                                hasDuplicates = true;
                            } else {
                                successful += curFile.getPath() + "\n";
                                addLoopButton(new LoopButton(m));
                                hasSuccess = true;

                            }
                        } catch (MidiUnavailableException e1) {
                            //e1.printStackTrace();
                            midiUnavailable += curFile.getPath() + "\n";
                            hasMidiUnavailable = true;
                        } catch (IOException e1) {
                            //e1.printStackTrace();
                            fileReadError += curFile.getPath() + "\n";
                            hasFileReadError = true;
                        } catch (InvalidMidiDataException e1) {
                            //e1.printStackTrace();
                            notMidi += curFile.getPath() + "\n";
                            hasNotMidi = true;
//                            showMessageDialog(Window.getWindow(), "The file supplied was not a midi file", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }


                String message = "";

                if(hasNotMidi){
                    message += hasNotMidi;
                }

                if(hasFileReadError){
                    message += fileReadError;
                }

                if(hasMidiUnavailable){
                    message += midiUnavailable;
                }

                if(hasDuplicates){
                    message += duplicates;
                }

                if(hasSuccess){
                    message += successful;
                }

                if(hasNotMidi || hasFileReadError || hasMidiUnavailable || hasDuplicates) {
                    showMessageDialog(Window.getWindow(), message, "Error", JOptionPane.ERROR_MESSAGE);
                }

                window.remove(disabledOverlay);
                window.setLayout(new GridLayout());
                window.revalidate();
                window.repaint();

            }
        });


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



        scrollPaneContainer = new JPanel();
        scrollPaneContainer.setLayout(new GridBagLayout());
        scrollPaneContainer.setBackground(Color.GRAY);
        scrollPaneContainer.setBorder(BorderFactory.createEmptyBorder(15,0,30,0));

        disabledOverlay = new JLabel("Please Select Some Files", SwingConstants.CENTER);
        Font f = disabledOverlay.getFont();
        disabledOverlay.setFont(new Font(f.getFontName(), f.getStyle(), 45));
        disabledOverlay.setForeground(Color.WHITE);
        disabledOverlay.setOpaque(true);
        disabledOverlay.setBackground(new Color(0,0,0,130));

        setColumnHeaderView(header);
        setViewportView(scrollPaneContainer);
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
        scrollPaneContainer.add(button,c);

        if(c.gridx == 1){
            c.gridx--;
            c.gridy--;
            c.fill = GridBagConstraints.HORIZONTAL;
            JPanel verticalSeperator = new JPanel();
            verticalSeperator.setOpaque(false);
            c.gridwidth = columns * 2 + 1;
            scrollPaneContainer.add(verticalSeperator, c);

            c.gridy++;
            c.gridwidth = 1;
            c.weightx = paddingWeight;
            c.fill = GridBagConstraints.NONE;
            JPanel empty = new JPanel();
            empty.setOpaque(false);
            scrollPaneContainer.add(empty, c);

            c.gridx++;
        }

        c.fill = GridBagConstraints.NONE;
        c.gridx++;
        c.weightx = paddingWeight;
        JPanel empty = new JPanel();
        empty.setOpaque(false);
        scrollPaneContainer.add(empty, c);



        panelsAdded++;
    }
}
