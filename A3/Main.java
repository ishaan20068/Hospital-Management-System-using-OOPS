package A3;
import java.util.*;
public class Main {
    public static ArrayList<floor> floorlist=new ArrayList<>();
    public static HashMap<player,Integer> scorelist=new HashMap<>();
    public static void main(String[] args) {
        floor zero=new emptyFloor(0);
        floor one=new emptyFloor(1);
        floor two=new ladderFloor(2,"elevator");
        floor three=new emptyFloor(3);
        floor four=new emptyFloor(4);
        floor five=new snakeFloor(5,"normal snake");
        floor six=new emptyFloor(6);
        floor seven=new emptyFloor(7);
        floor eight=new ladderFloor(8,"normal ladder");
        floor nine=new emptyFloor(9);
        floor ten=new emptyFloor(10);
        floor eleven=new snakeFloor(11,"king cobra");
        floor twelve=new emptyFloor(12);
        floor thirteen=new emptyFloor(13);
        Scanner sc=new Scanner(System.in);
        Scanner scs=new Scanner(System.in);
        System.out.print("Enter the number of players: ");
        int numplayers=sc.nextInt();
        for(int i=0;i<numplayers;i++) {
            System.out.print("Enter the player"+(i+1)+" name: ");
            String name = scs.nextLine();
            dice d = new dice();
            player p;
            System.out.println("The game setup is ready");
            while (true) {
                System.out.print("Hit enter to roll the dice!!!");
                scs.nextLine();
                d.roll();
                System.out.println(d.toString());
                if (d.getFaceValue() == 2) {
                    System.out.println("Cannot start until you get 1!!!");
                } else {
                    p = new player(name);
                    System.out.println(p.toString());
                    break;
                }
            }
            while (p.getPosition() < 12) {
                System.out.print("Hit enter to roll the dice!!!");
                scs.nextLine();
                p.move(d);
            }
            while (true) {
                System.out.print("Hit enter to roll the dice!!!");
                scs.nextLine();
                d.roll();
                int x = d.getFaceValue();
                System.out.println(d.toString());
                if (x == 2) {
                    System.out.println("player can't move :(");
                } else {
                    p.addscore(1);
                    p.finallevel();
                    System.out.println(p.toString());
                    System.out.println("game over!!!\n" + p.getName() + " accumulated " + p.getScore() + " points");
                    System.out.println("---------------------------------------------------------------");
                    scorelist.put(p,p.getScore());
                    break;
                }
            }
        }
        System.out.println("the scores are as follows:");
        ArrayList<player> winners=new ArrayList<>();
        int max=-100000;
        for(player key1:scorelist.keySet()){
            System.out.println(key1.getName()+" - "+scorelist.get(key1));
            if(scorelist.get(key1)>max){max=scorelist.get(key1);}
        }
        int count=1;
        System.out.println("Winner/Winners");
        for(player key1:scorelist.keySet()){
            if(scorelist.get(key1)==max){
                System.out.println(count+". "+key1.getName());
                count++;
            }
        }
    }
}
