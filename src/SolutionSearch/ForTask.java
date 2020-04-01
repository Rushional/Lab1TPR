package SolutionSearch;

import Models.Mine;
import Models.ShippingCosts;
import Models.Warehouse;
import Solutions.CoalTonneSolution;
import Solutions.StateSolution;

public class ForTask {
    public static StateSolution findSolution(Mine[] mines, ShippingCosts shippingCosts, CoalTonneSolution coalTonneSolutionLastStep,
                                             Warehouse[] warehouses) {
//        The last mine is done separately, because it's last tonne has to be calculated earlier
        CoalTonneSolution[][] minesSolutions = new CoalTonneSolution[5][];
//        int currentMine = 4;
        double[] lastMineToWareCosts = new double[]{
                shippingCosts.getShippingCost(4, 0),
                shippingCosts.getShippingCost(4, 1),
                shippingCosts.getShippingCost(4, 2)};
        CoalTonneSolution[] mine4Arrays = SolutionSearch.ForMine.findSolution(
                true, mines, 4, lastMineToWareCosts, coalTonneSolutionLastStep, warehouses);
        minesSolutions[4] = mine4Arrays;
        CoalTonneSolution FirstTonneOfNextMineSolution = mine4Arrays[0];
        for (int currentMineIndex = 3; currentMineIndex >= 0 ; currentMineIndex--) {
            double[] currentMineToWareCosts = new double[]{
                    shippingCosts.getShippingCost(currentMineIndex, 0),
                    shippingCosts.getShippingCost(currentMineIndex, 1),
                    shippingCosts.getShippingCost(currentMineIndex, 2)};
            CoalTonneSolution[] currentMineArrays = SolutionSearch.ForMine.findSolution(
                    false, mines, currentMineIndex, currentMineToWareCosts, FirstTonneOfNextMineSolution, warehouses);
            minesSolutions[currentMineIndex] = currentMineArrays;
            FirstTonneOfNextMineSolution = currentMineArrays[0];
        }
        return minesSolutions[0][0].getState(0, 0, 0);
    }
}
