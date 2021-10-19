package com.company;
import java.util.*;
public interface material {
    String get_title();
    void set_uploader(instructor i);
    instructor get_uploader();
    Date get_uploadtime();
}