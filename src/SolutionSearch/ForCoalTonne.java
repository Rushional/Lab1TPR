package SolutionSearch;

import Models.Mine;
import Models.Warehouse;
import Solutions.BadStateSolution;
import Solutions.CoalTonneSolution;
import Solutions.StateSolution;

public class ForCoalTonne {
    public static CoalTonneSolution findSolution(CoalTonneSolution nextTonneSolution, Mine[] mines, int currentMineIndex,
                                                 int currentCoalTonneIndex, double[] mineToWareCosts, Warehouse[] warehouses) {
        CoalTonneSolution currentCoalTonneSolution = new CoalTonneSolution();
        for (int warehouse0 = 0; warehouse0 <= warehouses[0].getStorageLimit(); warehouse0++) {
            for (int warehouse1 = 0; warehouse1 <= warehouses[1].getStorageLimit(); warehouse1++) {
                for (int warehouse2 = 0; warehouse2 <= warehouses[2].getStorageLimit(); warehouse2++) {
                    int[] warehouseStates = new int[]{warehouse0, warehouse1, warehouse2};
                    StateSolution currentStateSolution = ForState.findSolution(
                            warehouseStates, mines, currentMineIndex, currentCoalTonneIndex, nextTonneSolution, mineToWareCosts, warehouses);
                    currentCoalTonneSolution.setState(warehouse0, warehouse1, warehouse2, currentStateSolution);
                }
            }
        }
        return currentCoalTonneSolution;
    }

//    This only works with standard values, so I'll have to change it later
    public static CoalTonneSolution lastTonneSolution(Warehouse[] warehouses) {
        CoalTonneSolution coalTonneSolutionLastStep = new CoalTonneSolution();
        for (int warehouse0 = 0; warehouse0 <= warehouses[0].getStorageLimit(); warehouse0++) {
            for (int warehouse1 = 0; warehouse1 <= warehouses[1].getStorageLimit(); warehouse1++) {
                for (int warehouse2 = 0; warehouse2 <= warehouses[2].getStorageLimit(); warehouse2++) {
                    StateSolution stateSolution;
                    int total = warehouse0 + warehouse1 + warehouse2;
//                    check if possible at all
                    if (total == 69 || total == 70) {
                        stateSolution = new StateSolution();
//                        if the sum is exactly 70 already, then the problem is solved and we don't need to do anything else
                        if (total == 69) {
//                            Mines[4].miningCost + Models.ShippingCosts.getShippingCost[4][0]; = 11.8 + 50.8 = 62.6
//                            Mines[4].miningCost + Models.ShippingCosts.getShippingCost[4][1]; = 11.8 + 28.3 = 40.1
//                            Mines[4].miningCost + Models.ShippingCosts.getShippingCost[4][2]; = 11.8 + 38.2 = 50
//                            Shipping to warehouse 1 is the cheapest option, so that's what we'll do.
                            if (warehouse1 < 29) {
                                stateSolution.addShipment(4, 1);
                                stateSolution.increaseCost(40.1);
                            } else if (warehouse2 < 41) {
                                stateSolution.addShipment(4, 2);
                                stateSolution.increaseCost(50);
                            } else if (warehouse0 < 20) {
                                stateSolution.addShipment(4, 0);
                                stateSolution.increaseCost(62.6);
                            }
                        }
                    }
                    else stateSolution = new BadStateSolution();
                    coalTonneSolutionLastStep.setState(warehouse0, warehouse1, warehouse2, stateSolution);
                }
            }
        }
        return coalTonneSolutionLastStep;
    }
}
