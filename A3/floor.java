package A3;
import java.util.*;
abstract class floor {
    private int number;
    private int points;
    public int getPoints(){return this.points;}
    public int getNumber(){return this.number;}
    public void setNumber(int number) {
        this.number = number;
    }
    public void setPoints(int numpoints){this.points=numpoints;}
    abstract void execute(player p);
}
