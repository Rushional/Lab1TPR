public class State {
    //This is an element of a huge 70x20x29x41 4d array.
//    It holds information about how we got here and what we'll do next - a 2d 5x3 array of shipments between mines and warehouses
//    Should look like that: Shipments[5][3]
//      1 2 3
//    1
//    2
//    3
//    4
//    5
    private int[][] shipmentsArray;
    private double totalCost;

    public State() {
        totalCost = 0;
        shipmentsArray = new int[5][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                shipmentsArray[i][j] = 0;
            }
        }
    }

    public void increaseCost(double costIncrease) {
        totalCost += costIncrease;
    }

    public void addShipment(int fromMine, int toWarehouse, int shipmentAmount) {
        shipmentsArray[fromMine][toWarehouse] += shipmentAmount;
    }
}
