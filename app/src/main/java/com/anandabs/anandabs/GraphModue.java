package com.anandabs.anandabs;

public class GraphModue {
    private String PaperNum;
    private int Marks;

    public GraphModue(String paperNum, int marks) {
        PaperNum = paperNum;
        Marks = marks;
    }

    public String getPaperNum() {
        return PaperNum;
    }

    public void setPaperNum(String paperNum) {
        PaperNum = paperNum;
    }

    public int getMarks() {
        return Marks;
    }

    public void setMarks(int marks) {
        Marks = marks;
    }
}
