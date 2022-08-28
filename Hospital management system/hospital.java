package a1;
import java.util.*;

public class hospital {
  public static int numhospitals=0;
  public String name;
  public int pincode;
  public int hospitalid;
  public ArrayList<slot> slotlist=new ArrayList<>();
  public hospital(String name, int pincode){
    this.name=name;
    this.pincode=pincode;
    numhospitals++;
    this.hospitalid=99999+numhospitals;
  }
  public void addslot(slot s){
    slotlist.add(s);
  }
  public String gethospitalname(){return this.name;}
  public int gethospitalpincode(){return this.pincode;}
  public int gethospitalid(){return this.hospitalid;}
  public void displaydetails(){
    System.out.println("Hospital Name: "+this.name+", Pincode: "+this.pincode+", Unique ID: "+this.hospitalid);
  }
  
}
