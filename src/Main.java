public class Main {

    public static void main(String[] args) {
        StatesArray statesArrayLastStep = new StatesArray();
        Mine[] mines = new Mine[]{
                new Mine(9.2, 14),
                new Mine(12, 13),
                new Mine(10.7, 14),
                new Mine(13.8, 19),
                new Mine(11.8, 29)};
//        int currentWarehouse0 = 20;
//        int currentWarehouse1 = 29;
//        int currentWarehouse2 = 30; //so 0 and 1 are full, expecting 1 more to get total 70, warehouse2 has 11 more space
//        For starters I'll do it manually, but later I'll just create Warehouses[3], Mines[5]
//        And Mine will have either double transferCosts[3] or double transferTo1, transferTo2, transferTo3;

//        getOptimal(StatesArray nextStep) -> outputs StatesArray for current step
//        In the beginning shipmentsArray everywhere is  0 0 0, minCost is probably maxDouble.
        for (int warehouse0 = 0; warehouse0 <= 20; warehouse0++) {
            for (int warehouse1 = 0; warehouse1 <= 29; warehouse1++) {
                for (int warehouse2 = 0; warehouse2 <= 41; warehouse2++) {
//                    State state = statesArray.getState(warehouse0, warehouse1, warehouse2);
                    State state;
//                    check if possible at all
                    if (warehouse0 + warehouse1 + warehouse2 > 68) {
                        state = new State();
//                        if the sum is exactly 70 already, then the problem is solved and we don't need to do anything else
                        if (warehouse0 + warehouse1 + warehouse2 == 69) {
//                            Mines[4].miningCost + ShippingCosts.getShippingCost[4][0]; = 11.8 + 50.8 = 62.6
//                            Mines[4].miningCost + ShippingCosts.getShippingCost[4][1]; = 11.8 + 28.3 = 40.1
//                            Mines[4].miningCost + ShippingCosts.getShippingCost[4][2]; = 11.8 + 38.2 = 50
//                            Shipping to warehouse 1 is the cheapest option, so that's what we'll do.
                            if (warehouse1 < 29) {
                                state.addShipment(4, 1);
                                state.increaseCost(40.1);
                            } else if (warehouse2 < 41) {
                                state.addShipment(4, 2);
                                state.increaseCost(50);
                            } else if (warehouse0 < 20) {
                                state.addShipment(4, 0);
                                state.increaseCost(62.6);
                            }
                        }
                    }
                    else state = new BadState();
                    statesArrayLastStep.setState(warehouse0, warehouse1, warehouse2, state);
                }
            }
        }
//        System.out.println("case 20 29 11, expecting BadState");
//        if (statesArray.getState(20, 29, 11).getClass() == BadState.class) System.out.println("This doesn't really work unfortunately");
//        else statesArray.getState(20, 29, 11).outputShipmentsArray();
//        System.out.println();
//        System.out.println("case 19 29 21, expecting to skip 1 and add to 3 ");
//        if (statesArray.getState(19, 29, 21).getClass() == BadState.class) System.out.println("This doesn't really work unfortunately");
//        else statesArray.getState(19, 29, 21).outputShipmentsArray();
//        System.out.println();
//        System.out.println("case 19 28 22, expecting to skip 1 and add to 2");
//        if (statesArray.getState(19, 28, 22).getClass() == BadState.class) System.out.println("This doesn't really work unfortunately");
//        else statesArray.getState(19, 28, 22).outputShipmentsArray();
//        System.out.println();
//        System.out.println("case 20 29 20, expecting to add to 3");
//        if (statesArray.getState(20, 29, 20).getClass() == BadState.class) System.out.println("This doesn't really work unfortunately");
//        else statesArray.getState(20, 29, 20).outputShipmentsArray();
//        System.out.println();
//        System.out.println("case 20 29 21, expecting not to do anything");
//        if (statesArray.getState(20, 29, 21).getClass() == BadState.class) System.out.println("This doesn't really work unfortunately");
//        else statesArray.getState(20, 29, 21).outputShipmentsArray();
//        System.out.println();
        StatesArray statesArrayPrevious = new StatesArray();
        for (int warehouse0 = 0; warehouse0 <= 20; warehouse0++) {
            for (int warehouse1 = 0; warehouse1 <= 29; warehouse1++) {
                for (int warehouse2 = 0; warehouse2 <= 41; warehouse2++) {
//                    State state = statesArray.getState(warehouse0, warehouse1, warehouse2);
                    State previousState;
                    int total = warehouse0 + warehouse1 + warehouse2;
//                    if it's done already then we don't do anything
                    if (total == 70) {
                        previousState = new State(statesArrayLastStep.getState(warehouse0, warehouse1, warehouse2));
                    }
//                    check if possible at all
                    else if (total >= 68 && total <= 69 ) {
//                        if the sum is exactly 70 already, then the problem is solved and we don't need to do anything else
//                        calculate for ware 0-2
//                        then, if it's more than 68 (and less then 70?),
//                        check if not doing anything is cheaper
//                        and then create a state accordingly
//                        Maybe I should have Mine.SendToWarehouse(Warehouse warehouse) so SendToWarehouse(Warehouses[2])
//                        Would be how the decision is made
//                        Here we have 4 options. Not take at all or send to one of 0-2 warehouses.
//                        We check what solution is cheapest and we save it to this StatesArray element

//                        calculate the costs of these 4 decisions here
//                        These are costs of different options.
//                        By default they are max double because if the value doesn't change this would signify that
//                        This route is implausible
                        double shippingToWare0Cost = Double.MAX_VALUE;
                        double shippingToWare1Cost = Double.MAX_VALUE;
                        double shippingToWare2Cost = Double.MAX_VALUE;
                        if (warehouse0 < 20) {
                            shippingToWare0Cost = statesArrayLastStep.getState(warehouse0 + 1, warehouse1, warehouse2).getTotalCost() + 62.6;
                        }
                        if (warehouse1 < 29) {
                            shippingToWare1Cost = statesArrayLastStep.getState(warehouse0, warehouse1 + 1, warehouse2).getTotalCost() + 40.1;
                        }
                        if (warehouse2 < 41) {
                            shippingToWare2Cost = statesArrayLastStep.getState(warehouse0, warehouse1, warehouse2 + 1).getTotalCost() + 50;
                        }
                        double notShippingCost = Double.MAX_VALUE;
                        if (warehouse0 + warehouse1 + warehouse2 > 68) { //so if it's exactly 69
                            notShippingCost = statesArrayLastStep.getState(warehouse0, warehouse1, warehouse2).getTotalCost();
                        }
//                        Then determine which shipping option is the cheapest here and realise it
//                        <= means that we'll ship as early as we can and we'll prioritise the 1st warehouse if it doesn't matter
                        if (shippingToWare0Cost <= Math.min(shippingToWare1Cost, Math.min(shippingToWare2Cost, notShippingCost))) {
                            previousState = new State(statesArrayLastStep.getState(warehouse0 + 1, warehouse1, warehouse2));
                            previousState.addShipment(4, 0);
                            previousState.increaseCost(shippingToWare0Cost);
                        }
                        else if (shippingToWare1Cost <= Math.min(shippingToWare2Cost,notShippingCost)) {
                            previousState = new State(statesArrayLastStep.getState(warehouse0, warehouse1 + 1, warehouse2));
                            previousState.addShipment(4, 1);
                            previousState.increaseCost(shippingToWare1Cost);
                        }
                        else if (shippingToWare2Cost <= notShippingCost) {
                            previousState = new State(statesArrayLastStep.getState(warehouse0, warehouse1, warehouse2 + 1));
                            previousState.addShipment(4, 2);
                            previousState.increaseCost(shippingToWare2Cost);
                        }
                        else {
                            previousState = new State(statesArrayLastStep.getState(warehouse0, warehouse1, warehouse2));
                        }
                    }
                    else previousState = new BadState(); //not enough to get 70, so that's a fail-state,
//                    or went over 70 and that's a superfluous state
                    statesArrayPrevious.setState(warehouse0, warehouse1, warehouse2, previousState);
                }
            }
        }
        System.out.println();
        System.out.println("case 20 29 11, expecting BadState");
        if (statesArrayPrevious.getState(20, 29, 11).getClass() == BadState.class) System.out.println("This doesn't really work unfortunately");
        else statesArrayPrevious.getState(20, 29, 11).outputShipmentsArray();
        System.out.println();
        System.out.println("case 19 29 21, expecting to skip 1 and add to 3 ");
        if (statesArrayPrevious.getState(19, 29, 21).getClass() == BadState.class) System.out.println("This doesn't really work unfortunately");
        else statesArrayPrevious.getState(19, 29, 21).outputShipmentsArray();
        System.out.println();
        System.out.println("case 19 28 22, expecting to skip 1 and add to 2");
        if (statesArrayPrevious.getState(19, 28, 22).getClass() == BadState.class) System.out.println("This doesn't really work unfortunately");
        else statesArrayPrevious.getState(19, 28, 22).outputShipmentsArray();
        System.out.println();
        System.out.println("case 20 29 20, expecting to add to 3");
        if (statesArrayPrevious.getState(20, 29, 20).getClass() == BadState.class) System.out.println("This doesn't really work unfortunately");
        else statesArrayPrevious.getState(20, 29, 20).outputShipmentsArray();
        System.out.println();
        System.out.println("case 20 29 21, expecting not to do anything");
        if (statesArrayPrevious.getState(20, 29, 21).getClass() == BadState.class) System.out.println("This doesn't really work unfortunately");
        else statesArrayPrevious.getState(20, 29, 21).outputShipmentsArray();
        System.out.println();
        System.out.println("case 19 28 21, expecting to add to 2 and to 3 ");
        if (statesArrayPrevious.getState(19, 28, 21).getClass() == BadState.class) System.out.println("This doesn't really work unfortunately");
        else statesArrayPrevious.getState(19, 28, 21).outputShipmentsArray();
    }
}
