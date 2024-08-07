package com.anandabs.anandabs;

public class TestMarksAddModule {
    String id_num;
    String marks;
    String test_num;

    public TestMarksAddModule(){

    }

    public TestMarksAddModule(String id_num, String marks, String test_num) {
        this.id_num = id_num;
        this.marks = marks;
        this.test_num = test_num;
    }

    public String getId_num() {
        return id_num;
    }

    public void setId_num(String id_num) {
        this.id_num = id_num;
    }

    public String getMarks() {
        return marks;
    }

    public void setMarks(String marks) {
        this.marks = marks;
    }

    public String getTest_num() {
        return test_num;
    }

    public void setTest_num(String test_num) {
        this.test_num = test_num;
    }
}


