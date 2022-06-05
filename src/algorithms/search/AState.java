package algorithms.search;
import java.io.Serializable;
import java.util.ArrayList;


/**
 * Abstract class that describes a state using a String, Cost, and the state it came from.
 * Includes getters and setters. Overrides the equal function which is implemented in its successors
 */
public abstract class AState implements Serializable {
    protected String State;
    private int Cost;
    private AState CameFrom;

    /**
     *
     * @return the cost of the step to this Astate
     */
    public int getCost() {return Cost;}

    /**
     * sets the state cost to the cost the function gets
     * @param cost
     */
    public void setCost(int cost) {Cost = cost;}

    /**
     *
     * @return the string which represent the state
     */
    public String getState() {return State;}

    /**
     *
     * @param o
     * @return true if the state equals the accepted object
     */
    public abstract boolean equals(Object o);

    /**
     * set the state father to camefrom
     * @param cameFrom state that represent the father of this state
     */
    public void setCameFrom(AState cameFrom) {CameFrom = cameFrom;}

    /**
     *
     * @return the father of this state
     */
    public AState getCameFrom() {return CameFrom;}

    /**
     *
     * @return the string that represent the state with the printing format
     */
    @Override
    public String toString() {
        return "{" +
                 State + '\'' +
                '}';
    }
}
