package a1;
import java.util.*;
public class Main {
  public static HashMap<String,citizen> citizenlist=new HashMap<>();
  public static HashMap<String,vaccine> vaccinespecs=new HashMap<>();
  public static HashMap<Integer,hospital> hospitallist=new HashMap<>();
  public static HashMap<hospital,ArrayList<slot>> hospitalslots=new HashMap<>();
  public static ArrayList<String> vaccinelist=new ArrayList<>();
  public static void main(String[] args){
    Scanner sc=new Scanner(System.in);
    Scanner scs=new Scanner(System.in);
    System.out.println("CoWin Portal initialized....\n---------------------------------\n1. Add Vaccine");
    System.out.println("2. Register Hospital\n3. Register Citizen\n4. Add Slot for Vaccination\n5. Book Slot for Vaccination");
    System.out.println("6. List all slots for a hospital\n7. Check Vaccination Status\n8. Exit\n---------------------------------");
    int choice=0;
    while(choice<9){
      choice=sc.nextInt();
      if(choice==1){
        System.out.print("Vaccine Name: ");
        String namevaccine=scs.nextLine();
        System.out.print("Number of Doses: ");
        int numdose=sc.nextInt();
        int gapdose;
        if(numdose>1){
        System.out.print("Gap between Doses: ");
        gapdose=sc.nextInt();}
        else{gapdose=0;}
        vaccine v1=new vaccine(namevaccine,numdose,gapdose);
        v1.displaydetails();
        vaccinespecs.put(namevaccine,v1);
        vaccinelist.add(namevaccine);
        System.out.println("---------------------------------\n{Menu Options}");
      }
      else if(choice==2){
        System.out.print("Hospital name: ");
        String hospitalname=scs.nextLine();
        System.out.print("pincode: ");
        int pincode=sc.nextInt();
        hospital h1=new hospital(hospitalname,pincode);
        h1.displaydetails();
        int hid=h1.gethospitalid();
        hospitallist.put(hid,h1);
        System.out.println("---------------------------------\n{Menu Options}");
      }
      else if(choice==3){
        String cname;
        int age;
        String uid;
        System.out.print("Citizen Name: ");
        cname=scs.nextLine();
        System.out.print("Age: ");
        age=sc.nextInt();
        System.out.print("Unique ID: ");
        uid=scs.nextLine();
        if(age<18){
          System.out.println("Citizen Name: "+cname+", Age: "+age+", Unique ID: "+uid+"\nOnly above 18 are allowed");
        }
        else{
        citizen c1=new citizen(cname,age,uid);
        citizenlist.put(uid,c1);}
        System.out.println("---------------------------------\n{Menu Options}");
      }
      else if(choice==4){
        System.out.print("Enter Hospital ID: ");
        int hid=sc.nextInt();
        while(true){
          if(hospitallist.containsKey(hid)){break;}
          else{
            System.out.print("Your hospital id was incorrect!!!\nEnter again: ");
            hid=sc.nextInt();
          }
        }
        System.out.print("Enter the number of slots: ");
        int numslots=sc.nextInt();
        for(int i=0;i<numslots;i++){
          System.out.print("Enter Day Number: ");
          int daynum=sc.nextInt();
          System.out.print("Enter Quantity: ");
          int quantity=sc.nextInt();
          System.out.println("Select the vaccine");
          for(int j=0;j<vaccinelist.size();j++){System.out.println(j+". "+vaccinelist.get(j));}
          int vaccinechoice=sc.nextInt();
          vaccine vtemp=vaccinespecs.get(vaccinelist.get(vaccinechoice));
          slot stemp=new slot(daynum,quantity,vtemp);
          hospital htemp=hospitallist.get(hid);
          htemp.addslot(stemp);
          vtemp.addhospital(htemp);
          System.out.println("Slot added by hospital "+hid+" for day: "+daynum+", Available quantity: "+quantity+" of vaccine "+vaccinelist.get(vaccinechoice));
        }
        System.out.println("---------------------------------\n{Menu Options}");
      }
      else if(choice==5){
        
      }
      else if(choice==6){
        System.out.print("Enter Hospital ID: ");
        int hidtemp=sc.nextInt();
        hospital htemp=hospitallist.get(hidtemp);
        ArrayList<slot> slots=htemp.slotlist;
        for(int i=0;i<slots.size();i++){
          slot s1=slots.get(i);
          System.out.println("Day: "+s1.day+" Vaccine: "+(s1.getvaccinename()).name+" Available Qty: "+s1.quantity);
        }
        System.out.println("---------------------------------\n{Menu Options}");
      }
      else if(choice==7){
        System.out.print("Enter Patient ID: ");
        String patientid=scs.nextLine();
        if(citizenlist.containsKey(patientid)){
          citizen x=citizenlist.get(patientid);
          String vaccination=x.getstatus();
          if(vaccination.equals("REGISTERED")){System.out.println("Citizen REGISTERED");}
          
        }
        else{System.out.println("patient id is invalid!!!");}
        System.out.println("---------------------------------\n{Menu Options}");
      }
      else if(choice==8){
        System.out.print("Are you sure you want to exit(yes/no): ");
        String decision=scs.nextLine();
        if(decision.equals("yes")){return;}
        else{choice=0;}
      }
      else{System.out.println("Please enter a valid choice!!!");choice=0;}
    }
  }
}
