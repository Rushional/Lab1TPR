public class Mine {
    private double miningCost;
    private int miningLimit;
    //Also 4 fields for substances

    public Mine(double miningCost, int miningLimit) {
        this.miningCost = miningCost;
        this.miningLimit = miningLimit;
    }

    public int getMiningLimit() {
        return miningLimit;
    }

    public double getMiningCost() {
        return miningCost;
    }
}
