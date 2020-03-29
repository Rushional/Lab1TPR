public class ShippingCosts {
    private static double[][] costs = new double[][]{
            {12, 7.2, 38.2},
            {47.4, 25.3, 50},
            {18.2, 18.8, 25.1},
            {40.9, 12.4, 26.8},
            {50.8, 28.3, 38.2}};

    public static double getShippingCost(int fromMine, int toWarehouse) {
        return costs[fromMine][toWarehouse];
    }
}
