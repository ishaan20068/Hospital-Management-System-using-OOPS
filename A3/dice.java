package A3;
import java.util.*;
public class dice{
    private final int numFaces=2;
    private int faceValue;
    private Random rand=new Random();
    public void roll(){
        this.faceValue = 1 + rand.nextInt(numFaces);
    }
    public int getFaceValue() {
        return faceValue;
    }
    public String toString() {
        return "Dice gave " + faceValue;
    }
}