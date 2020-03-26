public class StatesArray {
    public State[][][][] states;

    public StatesArray() {
        states = new State[70][20][29][41];
        for (int i = 0; i < 70; i++) {
            for (int j = 0; j < 20; j++) {
                for (int k = 0; k < 29; k++) {
                    for (int l = 0; l < 41; l++) {
                        states[i][j][k][l] = new State();
                    }
                }
            }
        }
    }
}
