package algorithms.search;
import java.util.ArrayList;


/**
 * Abstract class that describes a state using a String, Cost, and the state it came from.
 * Includes getters and setters. Overrides the equal function which is implemented in its successors
 */
public abstract class AState {
    protected String State;
    private int Cost;
    private AState CameFrom;

    public int getCost() {return Cost;}

    public void setCost(int cost) {Cost = cost;}

    public String getState() {return State;}

    public abstract boolean equals(Object o);

    public void setCameFrom(AState cameFrom) {CameFrom = cameFrom;}

    public AState getCameFrom() {return CameFrom;}

    @Override
    public String toString() {
        return "{" +
                 State + '\'' +
                '}';
    }
}
