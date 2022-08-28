package a1;
import java.util.*;
public class Main {
  public static HashMap<String,citizen> citizenlist=new HashMap<>();
  public static HashMap<String,vaccine> vaccinespecs=new HashMap<>();
  public static HashMap<Integer,hospital> hospitallist=new HashMap<>();
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
        if(citizenlist.containsKey(uid)){
          System.out.println("User ID is already present, enter a unique user ID");
        }
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
          (vtemp.presentin).add(hospitallist.get(hid));
          slot stemp=new slot(daynum,quantity,vtemp);
          hospital htemp=hospitallist.get(hid);
          htemp.addslot(stemp);
          vtemp.addhospital(htemp);
          System.out.println("Slot added by hospital "+hid+" for day: "+daynum+", Available quantity: "+quantity+" of vaccine "+vaccinelist.get(vaccinechoice));
        }
        System.out.println("---------------------------------\n{Menu Options}");
      }
      else if(choice==5){
        System.out.print("Enter patient unique ID: ");
        String uid=scs.nextLine();
        citizen patient=citizenlist.get(uid);
        if((patient.vaccinationstatus).equals("FULLY VACCINATED")){
          System.out.println("You are already fully vaccinated and overdose can cause unnecessary damage!!!");
          continue;
        }
        System.out.print("1. Search by area\n2. Search by Vaccine\nEnter option: ");
        int option =sc.nextInt();
        if(option==1){
          System.out.print("Enter pincode: ");
          int pincode1=sc.nextInt();
          Set<Integer> ids=hospitallist.keySet();
          for(int id:ids){
            hospital htemp1=hospitallist.get(id);
            if(htemp1.gethospitalpincode()==pincode1){
              System.out.println(htemp1.gethospitalid()+htemp1.gethospitalname());
            }
          }
          System.out.print("Enter hospital ID: ");
          int hidtemp1=sc.nextInt();
          if(!hospitallist.containsKey(hidtemp1)){if((hospitallist.get(hidtemp1)).pincode!=pincode1){System.out.println("Wrong ID"); continue;}}
          hospital htemp2=hospitallist.get(hidtemp1);
          ArrayList<slot> slots1=htemp2.slotlist;
          for(int i=0;i<slots1.size();i++){
            slot slottemp=slots1.get(i);
            System.out.println(i+"-> Day:"+slottemp.getday()+", Available Qty:"+slottemp.getquantity()+", Vaccine:"+(slottemp.getvaccinename()).name);
          }
          System.out.print("Choose slot: ");
          int chooseslot=sc.nextInt();
          if((patient.vaccinationstatus).equals("PARTIALLY VACCINATED")){
            if(slots1.get(chooseslot).day<patient.vaccineday){
              System.out.println("You cannot choose a slot before the due date!!!\nTry booking again from the main menu");
              continue;}}
          if((patient.vaccinationstatus).equals("REGISTERED")){
            patient.dosesleft=((slots1.get(chooseslot)).vaccinename).numdoses;
          }
          System.out.println(patient.name+" vaccinated with "+((slots1.get(chooseslot)).vaccinename).name);
          (slots1.get(chooseslot)).quantity--;
          patient.vaccineadm=(slots1.get(chooseslot)).vaccinename;
          patient.dosesleft--;
          patient.vaccinationstatus="PARTIALLY VACCINATED";
          if(patient.dosesleft==0){patient.vaccinationstatus="FULLY VACCINATED";}
          if(patient.vaccinationstatus=="PARTIALLY VACCINATED"){patient.vaccineday=(slots1.get(chooseslot)).day+patient.vaccineadm.getgap();}
          if((slots1.get(chooseslot)).quantity==0){
            ArrayList<hospital> tempdes=(((slots1.get(chooseslot)).vaccinename).presentin);
            int i=tempdes.indexOf(htemp2);
            tempdes.remove(i);
            slots1.remove(chooseslot);
          }
          
        }
        if(option==2){
          System.out.println("Enter Vaccine Name: ");
          String vname=scs.nextLine();
          vaccine vtemp=vaccinespecs.get(vname);
          ArrayList<hospital> hlist1=vtemp.presentin;
          for(int i=0;i<hlist1.size();i++){
            hospital x1=hlist1.get(i);
            System.out.println(x1.gethospitalid()+" "+x1.gethospitalname());
          }
          System.out.print("Enter hospital ID: ");
          int hid1=sc.nextInt();
          hospital h1=hospitallist.get(hid1);
          ArrayList<slot> l1=h1.slotlist;
          for(int i=0;i<h1.slotlist.size();i++){
            if((h1.slotlist.get(i)).vaccinename==vtemp){
              System.out.println(i+"-> Day: "+(h1.slotlist.get(i)).day+", Available Qty: "+(h1.slotlist.get(i)).getquantity()+", vaccine: "+ (h1.slotlist.get(i)).getvaccinename());
            }
          }
          System.out.print("Choose slot: ");
          int slotchoice=sc.nextInt();
          slot chosenslot=h1.slotlist.get(slotchoice);
          if((patient.vaccinationstatus).equals("PARTIALLY VACCINATED")){
            if(chosenslot.day<patient.vaccineday){
              System.out.println("You cannot choose a slot before the due date!!!\nTry booking again from the main menu");
              continue;}}
           if((patient.vaccinationstatus).equals("REGISTERED")){
            patient.dosesleft=(chosenslot.vaccinename).numdoses;
          }
          System.out.println(patient.name+" vaccinated with "+(chosenslot.vaccinename).name);
          (chosenslot).quantity--;
          patient.vaccineadm=(chosenslot).vaccinename;
          patient.dosesleft--;
          patient.vaccinationstatus="PARTIALLY VACCINATED";
          if(patient.dosesleft==0){patient.vaccinationstatus="FULLY VACCINATED";}
          if(patient.vaccinationstatus=="PARTIALLY VACCINATED"){patient.vaccineday=(chosenslot).day+patient.vaccineadm.getgap();}
          if(chosenslot.quantity==0){
            ArrayList<hospital> tempdes=((chosenslot).vaccinename).presentin;
            int i=tempdes.indexOf(h1);
            tempdes.remove(i);
            (h1.slotlist).remove(chosenslot);
          }
        }
        System.out.println("---------------------------------\n{Menu Options}");
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
          else if(vaccination.equals("FULLY VACCINATED")){
            System.out.println("FULLY VACCINATED");
            System.out.println("Vaccine given: "+(x.vaccineadm).name);
            System.out.println("Number of doses given: "+(x.vaccineadm).numdoses);
          }
          else{
            System.out.println("PARTIALLY VACCINATED");
            System.out.println("Vaccine given: "+(x.vaccineadm).name);
            System.out.println("Number of doses given: "+((x.vaccineadm).numdoses-x.dosesleft));
            System.out.println("Next dose due date: "+x.vaccineday);
          }
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
