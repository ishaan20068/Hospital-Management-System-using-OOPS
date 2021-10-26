package A3;
import java.util.*;
public class ladderFloor extends floor{
    private final String type;
    public ladderFloor(int number1,String type1){
        this.setNumber(number1);
        this.type=type1;
        if(this.type.equals("normal ladder")){this.setPoints(2);}
        else if(this.type.equals("elevator")){this.setPoints(4);}
        Main.floorlist.add(this);
    }
    public String getType() {
        return type;
    }
    @Override
    public void execute(player p){
        if(p.getType().equals("normal snake")){
            p.addscore(2);
        }
        else{p.addscore(4);}
    }
}
