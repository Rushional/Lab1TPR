public class StatesArray {
    private State[][][] states;

    public StatesArray() {
// I will create all these States on the go
        states = new State[21][30][42];
//        for (int i = 0; i <= 20; i++) {
//            for (int j = 0; j <= 29; j++) {
//                for (int k = 0; k <= 41; k++) {
//                        states[i][j][k] = new State();
//                }
//            }
//        }
    }

    public void setState(int ware0, int ware1, int ware2, State state) {
        states[ware0][ware1][ware2] = state;
    }

    public State getState(int ware0, int ware1, int ware2) {
        return states[ware0][ware1][ware2];
    }
}
