package engine;

import java.util.ArrayList;

public class MidiBytesInUse extends ArrayList<MidiByte> {
    private static volatile MidiBytesInUse midiBytes = new MidiBytesInUse();

    private MidiBytesInUse(){
    }

    public static MidiBytesInUse getMidiBytes(){
        return midiBytes;
    }

    public  boolean add(MidiByte f) {
        if (!contains(f)) {
            return super.add(f);
        } else {
            return false;
        }
    }
}
