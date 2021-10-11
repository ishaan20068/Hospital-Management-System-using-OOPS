package a1;
import java.util.*;
public class citizen {
  public String name;
  public int age;
  public String userid;
  public String vaccinationstatus;
  public vaccine vaccineadm;
  public int dosesleft=0;
  public int vaccineday;
  public citizen(String name,int age,String userid){
    this.name=name;
    this.age=age;
    this.userid=userid;
    this.vaccinationstatus="REGISTERED";
    System.out.println("The user was successfully registered.");
    System.out.println("Citizen Name: "+this.name+", Age: "+this.age+", Unique ID: "+this.userid);
  }
  public String getcitizenname(){return this.name;}
  public int getage(){return this.age;}
  public String getuserid(){return this.userid;}
  public String getstatus(){return this.vaccinationstatus;}
}
