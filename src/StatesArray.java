public class StatesArray {
    private State[][][] states;

    public StatesArray() {
        states = new State[21][30][42]; // I will create all these States on the go
    }

    public void setState(int ware0, int ware1, int ware2, State state) {
        states[ware0][ware1][ware2] = state;
    }

    public State getState(int ware0, int ware1, int ware2) {
        return states[ware0][ware1][ware2];
    }
}
