package algorithms.search;

public abstract class AState {
    protected String State;
    private int Cost;
    private AState CameFrom;
    //private boolean isVisited;


    public int getCost() {
        return Cost;
    }

    public void setCost(int cost) {
        Cost = cost;
    }

    public String getState() {
        return State;
    }

    public abstract AState getCopy();
//    public void setVisited(){
//        isVisited = true;
//    }

    //public boolean isVisited() {
    //    return isVisited;
    //}


//    public boolean equals(Object o){
//        if (this == o)
//            return true;
//        if (o == null || getClass() != o.getClass())
//            return false;
//        AState state1 = (AState) o;
//        if (state1 == null){
//            return false;}
//        else{
//            return state1.equals(state1);}
//    }

//    public boolean compare_state(AState other)
//    {
//        return this.compare_state(other);
//    }

    public abstract boolean equals(Object o);

    public void setCameFrom(AState cameFrom) {
        CameFrom = cameFrom;
    }

    public AState getCameFrom() {
        return CameFrom;
    }

    //public abstract boolean compare_states(AState other);

    @Override
    public String toString() {
        return "{" +
                 State + '\'' +
                '}';
    }
}
