package com.company;
import java.util.*;

public class student implements user{
    private final String name;
    private final int id;
    private static int numstudents=0;
    public student(String name1){
        this.name=name1;
        this.id=numstudents;
        numstudents++;
        Main.studentlist.add(this);
    }
    @Override
    public String get_name(){return this.name;}
    public int get_id(){return this.id;}
    @Override
    public void add_comments(String comments1){
        new comment(comments1,"STUDENT",this.name);
    }
    @Override
    public void view_comments(){
        for(int i=0;i<Main.comments.size();i++){
            comment x=Main.comments.get(i);
            String comment_body=x.get_body();
            String student_name=x.get_person_name();
            Date date=x.get_date_time();
            System.out.println(comment_body+" - "+student_name+"\n"+date);
        }
    }
    @Override
    public void printvideo(){
        if(Main.videolist.size()==0){return;}
        for(int i=0;i<Main.videolist.size();i++){
            lecture x=Main.videolist.get(i);
            System.out.println("Title of video: "+x.get_title()+"\nVideo file: "+x.get_file()+"\nDate of upload: "+x.get_uploadtime()+" by: "+x.get_uploader().get_name());
            System.out.println();
        }
    }
    @Override
    public void printslides(){
        if(Main.slideslist.size()==0){return;}
        for(int i=0;i<Main.slideslist.size();i++){
            slides x=Main.slideslist.get(i);
            System.out.println("Title: "+x.get_title()+"\nnumber of slides: "+x.get_num());
            for(int j=0; j<x.get_content().size();j++){
                System.out.println("Slide "+(j+1)+": "+x.get_content().get(j));
            }
            System.out.println("\nDate of upload: "+x.get_uploadtime()+" by: "+x.get_uploader().get_name()+"\n");
        }
    }
}
