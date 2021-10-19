package com.company;
import java.util.*;

import static com.company.Main.slideslist;

public class slides implements material{
    private final String title;
    private final int num_slides;
    private final ArrayList<String> content;
    private instructor uploader;
    private final Date upload_time;
    public slides(String slide_title,int number , ArrayList<String> content1){
        this.title=slide_title;
        this.num_slides=number;
        this.content=content1;
        this.upload_time=new Date();
        slideslist.add(this);
    }
    @Override
    public void set_uploader(instructor ix){
        this.uploader=ix;
    }
    @Override
    public String get_title(){return this.title;}
    public int get_num(){return this.num_slides;}
    public ArrayList<String> get_content(){return this.content;}
    @Override
    public instructor get_uploader(){return this.uploader;}
    @Override
    public Date get_uploadtime(){return this.upload_time;}
    // public ArrayList<slides> get_slideslist(){return slideslist;}

}
