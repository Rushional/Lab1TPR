public class StatesArray {
    private State[][][][] states;

    public StatesArray() {
        states = new State[71][21][30][42];
        for (int i = 0; i <= 70; i++) {
            for (int j = 0; j <= 20; j++) {
                for (int k = 0; k <= 29; k++) {
                    for (int l = 0; l <= 41; l++) {
                        states[i][j][k][l] = new State();
                    }
                }
            }
        }
    }

    public State getState(int totalShipments, int ware0, int ware1, int ware2) {
        return states[totalShipments][ware0][ware1][ware2];
    }
}
