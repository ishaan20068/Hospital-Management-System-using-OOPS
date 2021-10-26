package A3;
import java.util.*;
public class player {
    private final String name;
    private int position;
    private int score;
    player(String given_name){
        this.name=given_name;
        this.position=0;
        this.score=1;
    }
    public int getPosition() {return position;}
    public String getName() {return name;}
    public int getScore() {return score;}
    public floor getFloor(){return Main.floorlist.get(this.position);}
    public  String getType(){
        if(this.getFloor() instanceof emptyFloor){
            return "Empty Floor";
        }
        else if(this.getFloor() instanceof snakeFloor){
            return ((snakeFloor) this.getFloor()).getType();
        }
        else if(this.getFloor() instanceof ladderFloor){
            return ((ladderFloor) this.getFloor()).getType();
        }
        else{return "no floor!!!";}
    }
    public  void addscore(int x){
        this.score+=x;
    }
    @Override
    public String toString(){return "Player position floor-"+this.getFloor().getNumber()+"\n"+this.name+" has reached "+this.getType()+"\ntotal points : "+this.getScore();}
    public void finallevel(){this.position=13;}
    public void move(dice d){
        d.roll();
        int x=d.getFaceValue();
        System.out.println(d.toString());
        this.position+=x;
        floor currfloor=this.getFloor();
        currfloor.execute(this);
        System.out.println(this.toString());
        if(this.getType().equals("Empty Floor")){return;}
        else if(this.getType().equals("normal snake")){
            this.position-=4;
            currfloor=this.getFloor();
            currfloor.execute(this);
            System.out.println(this.toString());
        }
        else if(this.getType().equals("king cobra")){
            this.position-=8;
            currfloor=this.getFloor();
            currfloor.execute(this);
            System.out.println(this.toString());
        }
        else if(this.getType().equals("elevator")){
            this.position+=8;
            currfloor=this.getFloor();
            currfloor.execute(this);
            System.out.println(this.toString());
        }
        else if(this.getType().equals("normal ladder")){
            this.position+=4;
            currfloor=this.getFloor();
            currfloor.execute(this);
            System.out.println(this.toString());
        }
    }
}