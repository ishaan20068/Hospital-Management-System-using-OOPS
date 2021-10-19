package com.company;
import java.util.*;
public class quiz implements assessment {
    private HashMap<student, String> submissions;
    private HashMap<student, Integer> grade;
    private HashMap<student,instructor> grader;
    private boolean closed;
    private final String type;
    private final String question;
    public quiz(String question1){
        this.question=question1;
        this.closed=false;
        this.type="Question";
        this.submissions=new HashMap<>();
        this.grade=new HashMap<>();
        Main.assessmentlist.add(this);
        for(int i=0;i<Main.studentlist.size();i++) {
            this.submissions.put(Main.studentlist.get(i), "");
            this.grade.put(Main.studentlist.get(i), -1);
        }
    }
    @Override
    public int getMax_marks(){return 1;}
    @Override
    public String get_type(){return this.type;}
    @Override
    public int get_marks(student s1){
        if(grade.containsKey(s1)){
        return grade.get(s1);}
        else{System.out.println("invalid student!!!");}
        return 0;
    }
    @Override
    public boolean ispending(student s1){
        return submissions.get(s1).equals("");
    }
    @Override
    public boolean isgraded(student s1){
        if(this.closed==true){return true;}
        else return this.grade.get(s1) != -1;
    }
    @Override
    public void close(){this.closed=true;}
    @Override
    public String get_question(){
        return this.question;
    }@Override
    public boolean isclosed(){
        return this.closed;
    }
    @Override
    public String getsubmission(student s1){return this.submissions.get(s1);}
    @Override
    public void setmarks(student s1,int x){if(x<=1){this.grade.put(s1,x);}else{System.out.println("invalid marks!!!record was not updated");}
    }
    @Override
    public void submit(student s1,String x1){this.submissions.put(s1,x1);}
    @Override
    public void setgrader(instructor i1,student s1){this.grader.put(s1,i1);}
    @Override
    public instructor getgrader(student s1){return this.grader.get(s1);}
}