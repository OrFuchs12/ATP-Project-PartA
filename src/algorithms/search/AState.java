package algorithms.search;

public abstract class AState {
    protected String State;
    private int Cost;
    private AState CameFrom;
    private boolean isVisited;



    public String getState() {
        return State;
    }

    public abstract AState getCopy();
    public void setVisited(){
        isVisited = true;
    }

    public boolean isVisited() {
        return isVisited;
    }
    public boolean equals(Object o){
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        AState state1 = (AState) o;
        return state1!=null? state1.State.equals(state1.State):state1.State ==null;
    }

    public void setCameFrom(AState cameFrom) {
        CameFrom = cameFrom;
    }

    public AState getCameFrom() {
        return CameFrom;
    }
}
