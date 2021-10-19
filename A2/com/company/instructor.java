package com.company;
import java.util.*;
public class instructor implements user {
    private final String name;
    private final int id;
    private static int numinstructors = 0;

    public instructor(String instructor_name) {
        this.name = instructor_name;
        this.id = numinstructors;
        numinstructors++;
        Main.instructorlist.add(this);
    }

    @Override
    public String get_name() {
        return this.name;
    }

    public int get_id() {
        return this.id;
    }

    @Override
    public void add_comments(String comments1) {
        new comment(comments1, "INSTRUCTOR", this.name);
    }

    @Override
    public void view_comments() {
        for (int i = 0; i < Main.comments.size(); i++) {
            comment x = Main.comments.get(i);
            String comment_body = x.get_body();
            String instructor_name = x.get_person_name();
            Date date = x.get_date_time();
            System.out.println(comment_body + " - " + instructor_name + "\n" + date);
        }
    }

    public void addlecture(String title, String video) {
        if (video.length() < 5) {
            System.out.println("not a valid file!!!");
        } else {
            String format;
            format = video.substring(video.length() - 4);
            if (format.equals(".mp4")) {
                lecture l1 = new lecture(title, video);
                l1.set_uploader(this);
            } else {
                System.out.println("not a valid file!!!");
            }
        }
    }

    public void addslides(String slide_title, int number, ArrayList<String> content1) {
        slides s1=new slides(slide_title, number, content1);
        s1.set_uploader(this);
    }

    @Override
    public void printvideo() {
        if(Main.videolist.size()==0){return;}
        for (int i = 0; i < Main.videolist.size(); i++) {
            lecture x = Main.videolist.get(i);
            System.out.println("Title of video: " + x.get_title() + "\nVideo file: " + x.get_file() + "\nDate of upload: " + x.get_uploadtime() + " by: " + x.get_uploader().get_name());
            System.out.println();
        }
    }

    @Override
    public void printslides() {
        if(Main.slideslist.size()==0){return;}
        for (int i = 0; i < Main.slideslist.size(); i++) {
            slides x = Main.slideslist.get(i);

            System.out.println("Title: " + x.get_title() + "\nnumber of slides: " + x.get_num());
            for (int j = 0; j < x.get_content().size(); j++) {
                System.out.println("Slide " + (j + 1) + ": " + x.get_content().get(j));
            }
            System.out.println("Date of upload: " + x.get_uploadtime() + " by: " + x.get_uploader().get_name() + "\n");
        }
    }

}