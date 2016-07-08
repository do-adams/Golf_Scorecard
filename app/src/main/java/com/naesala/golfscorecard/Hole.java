package com.naesala.golfscorecard;

public class Hole {
    private String mName;
    private int mStrokes;

    public Hole(String name, int strokes) {
        mName = name;
        mStrokes = strokes;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getName() {
        return mName;
    }

    public void setStrokes(int strokes) {
        mStrokes = strokes;
    }

    public int getStrokes() {
        return mStrokes;
    }

    public void incrementStrokes() {
        mStrokes++;
    }

    public void decrementStrokes() {
        if (mStrokes != 0)
            mStrokes--;
    }
}
