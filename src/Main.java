import Solutions.CoalTonneSolution;
import Models.*;
import Solutions.StateSolution;

public class Main {
    public static void main(String[] args) {
        Mine[] mines = new Mine[]{
                new Mine(9.2, 14),
                new Mine(12, 13),
                new Mine(10.7, 14),
                new Mine(13.8, 19),
                new Mine(11.8, 29)};
        Warehouse[] warehouses = new Warehouse[]{
                new Warehouse(20),
                new Warehouse(29),
                new Warehouse(41)};
        ShippingCosts shippingCosts = new ShippingCosts(mines);
        CoalTonneSolution coalTonneSolutionLastStep = SolutionSearch.ForCoalTonne.lastTonneSolution(warehouses);
//        double[] mine4ToWareCosts = new double[]{
//                shippingCosts.getShippingCost(4, 0),
//                shippingCosts.getShippingCost(4, 1),
//                shippingCosts.getShippingCost(4, 2)};
//        CoalTonneSolution[] mine4Arrays = SolutionSearch.ForMine.findSolution(
//                true, mines[4], mine4ToWareCosts, coalTonneSolutionLastStep, warehouses);
//        System.out.println("mine 4 0th tonne state 4 12 25");
//        mine4Arrays[0].getState(4, 12, 25).outputResult();
//
//        CoalTonneSolution mine4SecondToLastTonne = SolutionSearch.ForCoalTonne.findSolution(
//                coalTonneSolutionLastStep, mines[4], mines[4].getMiningLimit() - 2, mine4ToWareCosts, warehouses
//        );
//        System.out.println("mine 4 tonne 28 - 20 29 19 expecting add 2 to 2");
//        mine4SecondToLastTonne.getState(20, 29, 19).outputResult();
//
//
//
//        double[] mine3ToWareCosts = new double[]{
//                shippingCosts.getShippingCost(3, 0),
//                shippingCosts.getShippingCost(3, 1),
//                shippingCosts.getShippingCost(3, 2)};
//        CoalTonneSolution mine3LastCoalTonneSolution = SolutionSearch.ForCoalTonne.findSolution(mine4Arrays[0],
//                mines[3], mines[3].getMiningLimit() - 1, mine3ToWareCosts, warehouses);
//        System.out.println("mine3 last coal state 4 12 25");
//        mine3LastCoalTonneSolution.getState(4, 12, 25).outputResult();
//
//        CoalTonneSolution[] mine3Arrays = SolutionSearch.ForMine.findSolution(
//                false, mines[3], mine3ToWareCosts, mine4Arrays[0], warehouses);
//        System.out.println("mine 3 0th tonne state 0 0 25");
//        mine3Arrays[0].getState(0, 0, 25).outputResult();

        StateSolution taskSolution = SolutionSearch.ForTask.findSolution(mines, shippingCosts, coalTonneSolutionLastStep, warehouses);
        taskSolution.outputResult();
    }
}
