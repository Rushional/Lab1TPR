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

//    This constructor is only used in the very first step, when creating states for the last tonne of coal
    public State() {
        totalCost = 0;
        shipmentsArray = new int[5][3];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 3; j++) {
                shipmentsArray[i][j] = 0;
            }
        }
    }

//    This constructor is used for possible solutions in all steps but the first one
//    If the solution is obviously impossible in a current state, a BadState will be created instead
    public State(State sourceState) {
        for (int i = 0; i < 5; i++) {
            System.arraycopy(sourceState.shipmentsArray[i], 0, shipmentsArray[i], 0, 3);
        }
        totalCost = sourceState.totalCost;
    }

    public void increaseCost(double costIncrease) {
        totalCost += costIncrease;
    }

    public void addShipment(int fromMine, int toWarehouse) {
        shipmentsArray[fromMine][toWarehouse] += 1;
    }

    public void outputShipmentsArray() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(shipmentsArray[i][j] + " ");
            }
            System.out.println();
        }
    }

    public int[][] getShipmentsArray() {
        return shipmentsArray;
    }

    public double getTotalCost() {
        return totalCost;
    }
}
