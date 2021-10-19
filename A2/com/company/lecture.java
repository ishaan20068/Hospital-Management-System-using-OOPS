package com.company;
import java.util.*;
public class lecture implements material {
    private final String title;
    private final String file;
    private instructor uploader;
    private final Date upload_time;

    public lecture(String slide_title, String filename) {
        this.title = slide_title;
        this.file = filename;
        this.upload_time = new Date();
        Main.videolist.add(this);
    }

    @Override
    public void set_uploader(instructor ix) {
        this.uploader = ix;
    }

    @Override
    public String get_title() {
        return this.title;
    }

    public String get_file() {
        return this.file;
    }

    @Override
    public instructor get_uploader() {
        return this.uploader;
    }

    @Override
    public Date get_uploadtime() {
        return this.upload_time;
    }

}