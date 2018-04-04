package engine;

import java.io.File;
import java.util.ArrayList;

public class SelectedFiles extends ArrayList<File> {
    private static volatile SelectedFiles selectedFiles = new SelectedFiles();
    private volatile boolean updating;

    private SelectedFiles(){
    }

    public static SelectedFiles getSelectedFiles(){
        return selectedFiles;
    }

    public  boolean add(File f) {
        if (!contains(f)) {
            return super.add(f);
        } else {
            return false;
        }
    }
}
