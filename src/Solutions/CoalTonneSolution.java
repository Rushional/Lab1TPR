package Solutions;

public class CoalTonneSolution {
    private StateSolution[][][] stateSolutions;

    public CoalTonneSolution() {
        stateSolutions = new StateSolution[21][30][42]; // I will create all these States on the go
    }

    public void setState(int ware0, int ware1, int ware2, StateSolution stateSolution) {
        stateSolutions[ware0][ware1][ware2] = stateSolution;
    }

    public StateSolution getState(int ware0, int ware1, int ware2) {
        return stateSolutions[ware0][ware1][ware2];
    }
}
