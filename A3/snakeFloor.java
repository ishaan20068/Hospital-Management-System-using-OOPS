package A3;
import java.util.*;
public class snakeFloor extends floor{
    private final String type;
    public snakeFloor(int number1, String type1){
        this.setNumber(number1);
        this.type=type1;
        if(this.type.equals("normal snake")){this.setPoints(-2);}
        else if(this.type.equals("king cobra")){this.setPoints(-4);}
        Main.floorlist.add(this);
    }
    public String getType() {
        return type;
    }
    @Override
    public void execute(player p){
        if(p.getType().equals("normal snake")){
            p.addscore(-2);
        }
        else{p.addscore(-4);}
    }
}
