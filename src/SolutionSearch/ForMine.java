package SolutionSearch;

import Models.Mine;
import Models.Warehouse;
import Solutions.CoalTonneSolution;

public class ForMine {
    public static CoalTonneSolution[] findSolution(boolean isLastMine, Mine[] mines, int currentMineIndex, double[] mineToWareCosts,
                                                   CoalTonneSolution coalTonneSolutionNextStep, Warehouse[] warehouses) {
        CoalTonneSolution[] currentMineSolutionArray = new CoalTonneSolution[mines[currentMineIndex].getMiningLimit()];
//        The very last step is manually added to the array if current mine is the last
        if (isLastMine) currentMineSolutionArray[currentMineSolutionArray.length - 1] = coalTonneSolutionNextStep;
        else {
//            If the current tonne isn't last, the last tonne is solved separately for easier access to next step solution
            CoalTonneSolution lastCoalTonneSolution = SolutionSearch.ForCoalTonne.findSolution(coalTonneSolutionNextStep,
                    mines, currentMineIndex, mines[currentMineIndex].getMiningLimit() - 1, mineToWareCosts, warehouses);
            currentMineSolutionArray[currentMineSolutionArray.length - 1] = lastCoalTonneSolution;
        }
        for (int currentCoalTonne = mines[currentMineIndex].getMiningLimit() - 2; currentCoalTonne >= 0; currentCoalTonne--) {
            CoalTonneSolution nextTonneSolution = currentMineSolutionArray[currentCoalTonne + 1];
            CoalTonneSolution currentCoalTonneSolution = SolutionSearch.ForCoalTonne.findSolution(
                    nextTonneSolution, mines, currentMineIndex, currentCoalTonne, mineToWareCosts, warehouses);
            currentMineSolutionArray[currentCoalTonne] = currentCoalTonneSolution;
        }
        return currentMineSolutionArray;
    }
}
