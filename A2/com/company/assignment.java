package com.company;
import java.util.*;
public class assignment implements assessment{
    private HashMap<student, String> submissions;
    private HashMap<student, Integer> grade;
    private boolean closed;
    private HashMap<student,instructor> grader;
    private final int max_marks;
    private final String question;
    private final String type;
    public assignment(String question1,int marks){
        this.question=question1;
        this.max_marks=marks;
        this.grader=new HashMap<>();
        this.closed=false;
        this.type="Assignment";
        this.submissions=new HashMap<>();
        this.grade=new HashMap<>();
        Main.assessmentlist.add(this);
        for(int i=0;i<Main.studentlist.size();i++) {
            this.submissions.put(Main.studentlist.get(i), "");
            this.grade.put(Main.studentlist.get(i), -1);
        }
    }
    @Override
    public boolean isgraded(student s1){

        if(this.grade.get(s1)!=-1){return true;}
        else{return false;}
    }
    @Override
    public int getMax_marks(){return this.max_marks;}
    @Override
    public int get_marks(student s1){
        if(grade.containsKey(s1)){
            return grade.get(s1);}
        else{System.out.println("invalid student!!!");}
        return 0;
    }
    @Override
    public String get_type(){return this.type;}
    @Override
    public boolean ispending(student s1){
        if(submissions.get(s1).equals("")){return true;}
        else{return false;}
    }
    @Override
    public String get_question(){
        return this.question;
    }
    @Override
    public void close(){this.closed=true;}
    @Override
    public boolean isclosed(){
        return this.closed;
    }
    @Override
    public String getsubmission(student s1){return this.submissions.get(s1);}
    @Override
    public void setmarks(student s1,int x){
        if(x<=this.max_marks){this.grade.put(s1,x);}else{System.out.println("invalid marks!!!record was not updated");}
    }
    @Override
    public void submit(student s1,String x1){
        if(x1.length()<5){
            System.out.println("invalid file!!!");return;}
        else if(x1.substring(x1.length() - 4).equals(".zip")){
        this.submissions.put(s1,x1);}
        else{System.out.println("Invalid file type!!! the records were not updated!!!");}
    }
    @Override
    public void setgrader(instructor i1,student s1){this.grader.put(s1,i1);}
    @Override
    public instructor getgrader(student s1){return this.grader.get(s1);}
}
