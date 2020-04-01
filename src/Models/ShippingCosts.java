package Models;

public class ShippingCosts {
    private static double[][] transportCosts = new double[][]{
            {12, 7.2, 38.2},
            {47.4, 25.3, 50},
            {18.2, 18.8, 25.1},
            {40.9, 12.4, 26.8},
            {50.8, 28.3, 38.2}};
    private Mine[] mines;

    public ShippingCosts(Mine[] mines) {
        this.mines = mines;
    }

    public double getShippingCost(int fromMine, int toWarehouse) {
        return mines[fromMine].getMiningCost() + transportCosts[fromMine][toWarehouse];
    }
}
