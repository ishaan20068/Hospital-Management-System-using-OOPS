package com.company;

public interface assessment {
    void close();
    String get_question();
    boolean isclosed();
    String getsubmission(student s1);
    void setmarks(student s1,int x);
    void submit(student s1,String x1);
    int get_marks(student s1);
    boolean ispending(student s1);
    String get_type();
    int getMax_marks();
    boolean isgraded(student s1);
    void setgrader(instructor i1,student s1);
    instructor getgrader(student s1);
}