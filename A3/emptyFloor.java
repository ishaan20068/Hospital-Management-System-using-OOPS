package A3;
import java.util.*;
public class emptyFloor extends floor{
    public emptyFloor(int number){
    this.setPoints(1);
    this.setNumber(number);
    Main.getlist().add(this);
    }
    @Override
    public void execute(player p){
        p.addscore(1);
    }
}
