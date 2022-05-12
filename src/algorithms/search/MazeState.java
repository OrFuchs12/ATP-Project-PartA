package algorithms.search;

public class MazeState extends AState{

    public MazeState(String state) {
        this.State = state;
    }

    @Override
    public AState getCopy() {
        AState copy = new MazeState(this.getState());
        return copy;
    }

    @Override
    public boolean compare_states(AState other) {
        return (this.State.equals(other.State));
    }
}
