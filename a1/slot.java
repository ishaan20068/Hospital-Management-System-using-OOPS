package a1;
import java.util.*;
public class slot{
  public int day;
  public int quantity;
  public vaccine vaccinename;
  public slot(int day,int quantity,vaccine vaccinename){
    this.day=day;
    this.quantity=quantity;
    this.vaccinename=vaccinename;
  }
  public int getday(){return this.day;}
  public int getquantity(){return this.quantity;}
  public vaccine getvaccinename(){return this.vaccinename;}
  
}
