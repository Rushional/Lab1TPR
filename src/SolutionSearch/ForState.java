package SolutionSearch;

import Models.Warehouse;
import Solutions.BadStateSolution;
import Solutions.CoalTonneSolution;
import Models.Mine;
import Solutions.StateSolution;

public class ForState {
    public static StateSolution findSolution(int[] warehouseStates, Mine[] mines, int currentMineIndex, int currentCoalTonneIndex,
                                      CoalTonneSolution nextTonneSolution, double[] mineToWareCosts, Warehouse[] warehouses) {
        StateSolution currentStateSolution;
        int tonnesExpecting = 70 - (warehouseStates[0] + warehouseStates[1] + warehouseStates[2]);
        int nextMinesStepsLeft = 0;
        for (int mineIndex = 0; mineIndex <= 4; mineIndex++) {
            if (mineIndex > currentMineIndex) nextMinesStepsLeft += mines[mineIndex].getMiningLimit();
        }
        int stepsLeft = mines[currentMineIndex].getMiningLimit() - currentCoalTonneIndex + nextMinesStepsLeft; //Steps left including current one
//        CoalTonneSolution nextTonneSolution = mine4Arrays[currentCoalTonneIndex + 1];
//                    if we've got enough coal already then we don't do anything
        if (tonnesExpecting == 0) {
            currentStateSolution = new StateSolution(nextTonneSolution.getState(warehouseStates[0], warehouseStates[1], warehouseStates[2]));
        }
//                    check if possible at all
//                        mines[4].getMiningLimit() - i = steps taken
        else if (stepsLeft >= tonnesExpecting && tonnesExpecting >= 1) {
//                        Now we have 4 options. We can either ship the tonne of coal to one of 0-2 warehouses
//                        or not take it at all.
//                        We check what solution is the cheapest and then we save it to this StatesArray element

//                        Here we calculate the costs of those 4 options.
//                        By default they are max double because if the value doesn't change this would signify that
//                        This route is implausible
            double totalShippingToWare0Cost = Double.MAX_VALUE;
            double totalShippingToWare1Cost = Double.MAX_VALUE;
            double totalShippingToWare2Cost = Double.MAX_VALUE;
            double notShippingCost = Double.MAX_VALUE;

//            I need to replace the constants with Mines[currentMine].miningCost + TerribleHierarchy.ShippingCosts.getShippingCost[currentMine][0/1/2];
//            this verifying option os probably bad
//            But if use it, start with all false and switch to true and not like I do it now
//            because here we don't enter all the options
            if (warehouseStates[0] < warehouses[0].getStorageLimit()) {
                StateSolution nextStateSolution = nextTonneSolution.getState(warehouseStates[0] + 1, warehouseStates[1], warehouseStates[2]);
                if (nextStateSolution.isPossible())
                    //min cost from next step + cost from current step
                    totalShippingToWare0Cost = nextStateSolution.getTotalCost() + mineToWareCosts[0];
            }
            if (warehouseStates[1] < warehouses[1].getStorageLimit()) {
                StateSolution nextStateSolution = nextTonneSolution.getState(warehouseStates[0], warehouseStates[1] + 1, warehouseStates[2]);
                if (nextStateSolution.isPossible())
                    totalShippingToWare1Cost = nextStateSolution.getTotalCost() + mineToWareCosts[1];
            }
            if (warehouseStates[2] < warehouses[2].getStorageLimit()) {
                StateSolution nextStateSolution = nextTonneSolution.getState(warehouseStates[0], warehouseStates[1], warehouseStates[2] + 1);
                if (nextStateSolution.isPossible())
                    totalShippingToWare2Cost = nextStateSolution.getTotalCost() + mineToWareCosts[2];
            }
            if (stepsLeft > tonnesExpecting) {
                notShippingCost = nextTonneSolution.getState(warehouseStates[0], warehouseStates[1], warehouseStates[2]).getTotalCost();
            }
//                        Then determine which shipping option is the cheapest here and realise it
//                        "<=" means that we'll ship as early as we can and we'll prioritise the 1st warehouse if it doesn't matter
            if (totalShippingToWare0Cost <= Math.min(totalShippingToWare1Cost, Math.min(totalShippingToWare2Cost, notShippingCost))) {
                currentStateSolution = new StateSolution(nextTonneSolution.getState(warehouseStates[0] + 1, warehouseStates[1], warehouseStates[2]));
//             we could add int Mine.index to solve this problem and use int mine.getIndex() here
                currentStateSolution.addShipment(currentMineIndex, 0);
                currentStateSolution.increaseCost(mineToWareCosts[0]);
            } else if (totalShippingToWare1Cost <= Math.min(totalShippingToWare2Cost, notShippingCost)) {
                currentStateSolution = new StateSolution(nextTonneSolution.getState(warehouseStates[0], warehouseStates[1] + 1, warehouseStates[2]));
                currentStateSolution.addShipment(currentMineIndex, 1);
                currentStateSolution.increaseCost(mineToWareCosts[1]);
            } else if (totalShippingToWare2Cost <= notShippingCost) {
                currentStateSolution = new StateSolution(nextTonneSolution.getState(warehouseStates[0], warehouseStates[1], warehouseStates[2] + 1));
                currentStateSolution.addShipment(currentMineIndex, 2);
                currentStateSolution.increaseCost(mineToWareCosts[2]);
            } else currentStateSolution = new StateSolution(nextTonneSolution.getState(warehouseStates[0], warehouseStates[1], warehouseStates[2]));
        }
        else currentStateSolution = new BadStateSolution(); //not enough to get 70, so that's a fail-state,
//        or went over 70 and that's a superfluous state
        return currentStateSolution;
    }
}
