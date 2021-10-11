package a1;
import java.util.*;
public class vaccine {
  public String name;
  public int numdoses;
  public int gap;
  public static int numvaccines=0;
  public ArrayList<hospital> presentin=new ArrayList<>();
  public vaccine(String name, int doses,int gap){
    this.name=name;
    this.numdoses=doses;
    this.gap=gap;
    numvaccines++;
  }
  public void addhospital(hospital h){presentin.add(h);}
  public vaccine(String name, int doses){
    this.name=name;
    this.numdoses=doses;
    this.gap=0;
    numvaccines++;
  }
  public void displaydetails(){
    System.out.println("Vaccine Name: "+this.name+", Number of Doses: "+this.numdoses+", Gap Between Doses: "+this.gap);
  }
  public String getname(){
    return name;
  }
  public int getnumdoses(){
    return numdoses;
  }
  public int getgap(){
    return gap;
  }
}
