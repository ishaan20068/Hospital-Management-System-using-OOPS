package com.company;
import java.util.*;
public class comment{
    private final String body;
    private final String person_type;
    private final String person_name;
    private final Date date_time;
    public comment(String comment1,String person_type1,String person_name1){
        this.body=comment1;
        this.date_time=new Date();
        this.person_name=person_name1;
        this.person_type=person_type1;
        Main.comments.add(this);
    }
    public String get_body(){return this.body;}
    public String get_person_type(){return this.person_type;}
    public String get_person_name(){return this.person_name;}
    public Date get_date_time(){return this.date_time;}
}
