package com.company;
import java.util.*;
public class Main{
    public static ArrayList<assessment> assessmentlist=new ArrayList<>();
    public static ArrayList<comment> comments=new ArrayList<>();
    public static ArrayList<student> studentlist=new ArrayList<>();
    public static ArrayList<lecture> videolist=new ArrayList<>();
    public static ArrayList<slides> slideslist=new ArrayList<>();
    public static ArrayList<instructor> instructorlist=new ArrayList<>();
    public static void main(String[] args) {
        int choicetemp =0;
        student s0=new student("S0");
        student s1=new student("S1");
        student s2=new student("S2");
        instructor i0=new instructor("I0");
        instructor i1=new instructor("I1");
        while(choicetemp>=0){
            Scanner sc=new Scanner(System.in);
            Scanner scs=new Scanner(System.in);
            System.out.println("Welcome to Backpack\n1. Enter as instructor\n2. Enter as student\n3. Exit");
            System.out.print("enter your choice: ");
            int choice=sc.nextInt();
            if(choice==3){sc.close();scs.close();return;}
            else if(choice==1){
                System.out.println("Instructors:\n");
                for(int i=0;i<instructorlist.size();i++){
                    System.out.println(i+" - "+instructorlist.get(i).get_name());
                }
                System.out.print("Choose id: ");
                int id_instructor=sc.nextInt();
                instructor prof=instructorlist.get(id_instructor);
                int choice1=0;
                while(choice1!=9){
                    System.out.println("Welcome "+prof.get_name());
                    System.out.println("INSTRUCTOR MENU\n1. Add class material\n2. Add assessments\n3. View lecture materials\n4. View assessments\n5. Grade assessments\n6. Close assessment\n7. View comments\n8. Add comments\n9. Logout");
                    System.out.print("your choice: ");
                    choice1=sc.nextInt();
                    if(choice1==1){
                        System.out.println("1. Add Lecture Slides\n2. Add Lecture Video");
                        System.out.print("your choice: ");
                        int choice2=sc.nextInt();
                        if(choice2==1){
                            System.out.print("Enter topic of slides: ");
                            String topic=scs.nextLine();
                            System.out.print("Enter number of slides: ");
                            int numslide=sc.nextInt();
                            System.out.println("Enter content of slides");
                            ArrayList<String> content2=new ArrayList<>();
                            for(int i=0;i<numslide;i++){
                                System.out.print("Content of slide "+(i+1)+": ");
                                String contentstring=scs.nextLine();
                                content2.add(contentstring);
                            }
                            prof.addslides(topic, numslide, content2);
                        }
                        else if(choice2==2){
                            System.out.print("Enter topic of video: ");
                            String topic=scs.nextLine();
                            System.out.print("Enter filename of video: ");
                            String file1=scs.nextLine();
                            prof.addlecture(topic,file1);
                        }
                        else{System.out.println("invalid choice!!! try again!!!");
                        }
                    }
                    else if(choice1==2){
                        System.out.println("1. Add Assignment\n2. Add quiz");
                        System.out.print("your choice: ");
                        int choice2=sc.nextInt();
                        if(choice2==1){
                            System.out.print("enter problem statement: ");
                            String ques=scs.nextLine();
                            System.out.print("enter maximum marks: ");
                            int max=sc.nextInt();
                            new assignment(ques,max);
                        }
                        else if(choice2==2){
                            System.out.print("enter quiz question: ");
                            String ques=scs.nextLine();
                            new quiz(ques);
                        }
                        else{System.out.println("invalid choice!!! try again!!!");
                        }
                    }
                    else if(choice1==3){
                        prof.printslides();
                        prof.printvideo();
                    }
                    else if(choice1==4){
                        int num=assessmentlist.size();
                        if(num==0){System.out.println("No assessments");}
                        else{
                            int idcount=0;
                            for(int i=0;i<num;i++){
                                assessment temp=assessmentlist.get(i);
                                if(!temp.isclosed()){
                                    if(temp.get_type().equals("Assignment")){
                                        System.out.println("ID: "+idcount+" "+temp.get_type()+": "+temp.get_question()+" Max marks: "+temp.getMax_marks());}
                                    else{System.out.println("ID: "+idcount+" "+temp.get_type()+": "+temp.get_question());}
                                    System.out.println("----------------");
                                    idcount++;
                                }
                            }
                        }
                    }
                    else if(choice1==5){
                        HashMap<Integer,assessment> templist=new HashMap<>();
                        int idcount=0;
                        for(int i=0;i< assessmentlist.size();i++){
                            assessment temp=assessmentlist.get(i);
                            if(!temp.isclosed()){
                                if(temp.get_type().equals("Assignment")){
                                    System.out.println("ID: "+idcount+" "+temp.get_type()+": "+temp.get_question()+" Max marks: "+temp.getMax_marks());
                                    templist.put(idcount,temp);
                                }
                                else{System.out.println("ID: "+idcount+" "+temp.get_type()+": "+temp.get_question());templist.put(idcount,temp);}
                                System.out.println("----------------");
                                idcount++;
                            }
                        }
                        if(templist.size()==0){
                            System.out.println("no open assignments");
                            continue;
                        }
                        System.out.print("Enter id of assessment to view submission: ");
                        int id=sc.nextInt();
                        assessment ass1=templist.get(id);
                        int c=0;
                        HashMap<Integer,student> templist2=new HashMap<>();
                        for (int i=0;i< studentlist.size();i++){
                            if(!ass1.isgraded(studentlist.get(i))&&!ass1.ispending(studentlist.get(i))){
                                System.out.println(c+" : "+studentlist.get(i).get_name());
                                templist2.put(c, studentlist.get(i));
                                c++;
                            }
                        }
                        if(templist2.isEmpty()){
                            System.out.println("no one has submitted yet!!!");
                            continue;
                        }
                        System.out.print("choose student id to grade: ");
                        int id11=sc.nextInt();
                        student stemp=templist2.get(id11);
                        System.out.println("Submission: "+ass1.getsubmission(stemp)+"\n---------------\nMax marks: "+ass1.getMax_marks());
                        System.out.print("Marks scored : ");
                        int mark=sc.nextInt();
                        ass1.setmarks(stemp,mark);
                        ass1.setgrader(prof,stemp);
                    }
                    else if(choice1==6){
                        System.out.println("List of Open Assignments:");
                        int idcount=0;
                        HashMap<Integer,assessment> templist=new HashMap<>();
                        for(int i=0;i< assessmentlist.size();i++){
                            assessment temp=assessmentlist.get(i);
                            if(!temp.isclosed()){
                                if(temp.get_type().equals("Assignment")){
                                    System.out.println("ID: "+idcount+" "+temp.get_type()+": "+temp.get_question()+" Max marks: "+temp.getMax_marks());
                                templist.put(idcount,temp);
                                }
                                else{System.out.println("ID: "+idcount+" "+temp.get_type()+": "+temp.get_question());templist.put(idcount,temp);}
                                System.out.println("----------------");
                                idcount++;
                            }
                        }
                        System.out.print("Enter id of assignment to close: ");
                        int id1=sc.nextInt();
                        assessment temp4=templist.get(id1);
                        temp4.close();
                    }
                    else if(choice1==7){
                        prof.view_comments();
                    }
                    else if(choice1==8){
                        System.out.print("enter the comment: ");
                        String comment1=scs.nextLine();
                        prof.add_comments(comment1);
                        System.out.println("your comment was added!");
                    }
                    else if(choice1==9){
                        break;
                    }
                    else{System.out.println("invalid choice!!!\nChoose again!!!");choice1=0;}
                }
            }
            else if(choice==2){
                System.out.println("Students:\n");
                for(int i=0;i<studentlist.size();i++){
                    System.out.println(i+" - "+studentlist.get(i).get_name());
                }
                System.out.print("Choose id: ");
                int id_student=sc.nextInt();
                student child=studentlist.get(id_student);
                int choice1=0;
                while(choice1!=9){
                    System.out.println("Welcome "+child.get_name());
                    System.out.println("STUDENT MENU\n1. View lecture materials\n2. View assessments\n3. Submit assessment\n4. View grades\n5. View comments\n6. Add comments\n7. Logout");
                    System.out.print("your choice: ");
                    choice1=sc.nextInt();
                    if(choice1==1){
                        child.printslides();
                        child.printvideo();
                    }
                    else if(choice1==2){
                        int num=assessmentlist.size();
                        if(num==0){System.out.println("No assessments");}
                        else{
                            int idcount=0;
                            for(int i=0;i<num;i++){
                                assessment temp=assessmentlist.get(i);
                                if(temp.ispending(child)&&!temp.isclosed()){
                                    if(temp.get_type().equals("Assignment")){
                                        System.out.println("ID: "+idcount+" "+temp.get_type()+": "+temp.get_question()+" Max marks: "+temp.getMax_marks());}
                                    else{System.out.println("ID: "+idcount+" "+temp.get_type()+": "+temp.get_question());}
                                    System.out.println("----------------");
                                    idcount++;
                                }
                            }
                        }
                    }
                    else if(choice1==3){
                        int num=assessmentlist.size();
                        if(num==0){System.out.println("No pending assessments");}
                        else{
                            int idcount=0;
                            HashMap<Integer,assessment> templist=new HashMap<>();
                            for(int i=0;i<num;i++){
                                assessment temp=assessmentlist.get(i);
                                if(temp.ispending(child)&&!temp.isclosed()){
                                    if(temp.get_type().equals("Assignment")){
                                        System.out.println("ID: "+idcount+" "+temp.get_type()+": "+temp.get_question()+" Max marks: "+temp.getMax_marks());
                                        templist.put(idcount,temp);
                                    }
                                    else{System.out.println("ID: "+idcount+" "+temp.get_type()+": "+temp.get_question());templist.put(idcount,temp);}
                                    idcount++;
                                }
                            }
                            if(templist.size()==0){System.out.println("No pending assessments");continue;}
                            System.out.print("Choose assessment id: ");
                            int idass=sc.nextInt();
                            assessment temp3=templist.get(idass);
                            if(temp3.get_type().equals("Assignment")){
                                System.out.print("enter filename of assignment: ");
                                String file=scs.nextLine();
                                temp3.submit(child,file);
                            }
                            else{
                                System.out.println(temp3.get_question()+" : ");
                                String ans=scs.nextLine();
                                temp3.submit(child,ans);
                            }
                        }
                    }
                    else if(choice1==4){
                        ArrayList<assessment> submitted=new ArrayList<>();
                        for(int i=0;i<assessmentlist.size();i++){
                            assessment temp1=assessmentlist.get(i);
                            if(!temp1.ispending(child)){
                                submitted.add(temp1);
                            }
                        }
                        ArrayList<assessment> graded=new ArrayList<>();
                        ArrayList<assessment> ungraded=new ArrayList<>();
                        if(submitted.size()==0){System.out.println("Graded submissions\n\nUngraded submissions");continue;}
                        for(int i=0;i<submitted.size();i++){
                            assessment temp1=submitted.get(i);
                            if(temp1.isgraded(child)){
                                graded.add(temp1);
                            }
                            else{ungraded.add(temp1);}
                        }
                        System.out.println("Graded submissions\n");
                        for(int i=0;i< graded.size();i++){
                            assessment temp1=graded.get(i);
                            System.out.println("submission: "+temp1.getsubmission(child)+"\nscore: "+temp1.get_marks(child)+"graded by: "+temp1.getgrader(child).get_name());
                            System.out.println("----------------------------");
                        }
                        System.out.println("Ungraded submissions\n");
                        for(int i=0;i<ungraded.size();i++){
                            assessment temp1=ungraded.get(i);
                            System.out.println("submission: "+temp1.getsubmission(child));
                            System.out.println("----------------------------");
                        }
                    }
                    else if(choice1==5){
                        child.view_comments();
                    }
                    else if(choice1==6){
                        System.out.print("enter the comment: ");
                        String comment1=scs.nextLine();
                        child.add_comments(comment1);
                        System.out.println("your comment was added!");
                    }
                    else if(choice1==7){
                        break;
                    }
                    else{System.out.println("invalid choice!!!\nChoose again!!!");choice1=0;}
                }
            }
            else{System.out.println("Invalid choice!!!\nChoose again!!!");choice=0;}
        }
    }
}

