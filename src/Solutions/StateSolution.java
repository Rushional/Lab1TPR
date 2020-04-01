package Solutions;

public class StateSolution {
    //This is an element of a huge 20x29x41 3d array.
//    It holds information about what's the optimal action in this state and what happens after it
//    It's a 2d 5x3 array of shipments between mines and warehouses
//    Should look like that: Shipments[5][3]
//      0 1 2
//    0
//    1
//    2
//    3
//    4
    private int[][] shipmentsArray;
    private double totalCost;

//    This constructor is only used in the very first step, when creating states for the last tonne of coal
    public StateSolution() {
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
    public StateSolution(StateSolution sourceStateSolution) {
        shipmentsArray = new int[5][3];
        for (int i = 0; i < 5; i++) {
            System.arraycopy(sourceStateSolution.shipmentsArray[i], 0, shipmentsArray[i], 0, 3);
        }
        totalCost = sourceStateSolution.totalCost;
    }

    public void increaseCost(double costIncrease) {
        totalCost += costIncrease;
    }

    public void addShipment(int fromMine, int toWarehouse) {
        shipmentsArray[fromMine][toWarehouse] += 1;
    }

    private void outputShipmentsArray() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(shipmentsArray[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void outputResult() {
        if (getClass() == BadStateSolution.class) System.out.println("This state won't get you anywhere");
        else {
            outputShipmentsArray();
            double roundedTotalCost = getTotalCost();
            roundedTotalCost *= 10;
            roundedTotalCost = Math.round(roundedTotalCost);
            roundedTotalCost = roundedTotalCost/10;
            System.out.println("Total cost is: " + roundedTotalCost);
        }
    }

    public int[][] getShipmentsArray() {
        return shipmentsArray;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public boolean isPossible() {
        return !(this.getClass() == BadStateSolution.class);
    }
}
