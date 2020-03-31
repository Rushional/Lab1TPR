public class Main {
    public static void main(String[] args) {
        StatesArray statesArrayLastStep = new StatesArray();
        State goodState = new State();
        BadState badState = new BadState();
        Mine[] mines = new Mine[]{
                new Mine(9.2, 14),
                new Mine(12, 13),
                new Mine(10.7, 14),
                new Mine(13.8, 19),
                new Mine(11.8, 29)};
//        getOptimal(StatesArray nextStep) -> outputs StatesArray for current step
//        In the beginning shipmentsArray everywhere is  0 0 0, minCost is probably maxDouble.
        for (int warehouse0 = 0; warehouse0 <= 20; warehouse0++) {
            for (int warehouse1 = 0; warehouse1 <= 29; warehouse1++) {
                for (int warehouse2 = 0; warehouse2 <= 41; warehouse2++) {
                    State state;
                    int total = warehouse0 + warehouse1 + warehouse2;
//                    check if possible at all
                    if (total == 69 || total == 70) {
                        state = new State();
//                        if the sum is exactly 70 already, then the problem is solved and we don't need to do anything else
                        if (total == 69) {
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

        int currentMine = 4;
        StatesArray[] mine4Arrays = new StatesArray[mines[currentMine].getMiningLimit()];
        mine4Arrays[mine4Arrays.length - 1] = statesArrayLastStep; //The very last step is manually added to the array
        for (int currentCoalTonne = mines[currentMine].getMiningLimit() - 2; currentCoalTonne >= 0; currentCoalTonne--) { //"-2" because we start with the second to last tonne
            StatesArray currentStatesArray = new StatesArray();
            for (int warehouse0 = 0; warehouse0 <= 20; warehouse0++) {
                for (int warehouse1 = 0; warehouse1 <= 29; warehouse1++) {
                    for (int warehouse2 = 0; warehouse2 <= 41; warehouse2++) {
                        State currentState;
                        int tonnesExpecting = 70 - (warehouse0 + warehouse1 + warehouse2);
                        int stepsLeft = mines[currentMine].getMiningLimit() - currentCoalTonne; //Steps left including current one
                        StatesArray nextStepStates = mine4Arrays[currentCoalTonne + 1];
//                    if we've got enough coal already then we don't do anything
                        if (tonnesExpecting == 0) {
                            currentState = new State(nextStepStates.getState(warehouse0, warehouse1, warehouse2));
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

                            double shipToWare0Cost = mines[currentMine].getMiningCost() + ShippingCosts.getShippingCost(currentMine, 0);
                            double shipToWare1Cost = mines[currentMine].getMiningCost() + ShippingCosts.getShippingCost(currentMine, 1);
                            double shipToWare2Cost = mines[currentMine].getMiningCost() + ShippingCosts.getShippingCost(currentMine, 2);

//                        I need to replace the constants with Mines[currentMine].miningCost + ShippingCosts.getShippingCost[currentMine][0/1/2];
                            if (warehouse0 < 20) {
                                State nextState = nextStepStates.getState(warehouse0 + 1, warehouse1, warehouse2);
                                if (nextState.isPossible())
                                    //min cost from next step + cost from current step
                                    totalShippingToWare0Cost = nextState.getTotalCost() + shipToWare0Cost;
                            }
                            if (warehouse1 < 29) {
                                State nextState = nextStepStates.getState(warehouse0, warehouse1 + 1, warehouse2);
                                if (nextState.isPossible())
                                    totalShippingToWare1Cost = nextState.getTotalCost() + shipToWare1Cost;
                            }
                            if (warehouse2 < 41) {
                                State nextState = nextStepStates.getState(warehouse0, warehouse1, warehouse2 + 1);
                                if (nextState.isPossible())
                                    totalShippingToWare2Cost = nextState.getTotalCost() + shipToWare2Cost;
                            }
                            if (stepsLeft > tonnesExpecting) {
                                notShippingCost = nextStepStates.getState(warehouse0, warehouse1, warehouse2).getTotalCost();
                            }
//                        Then determine which shipping option is the cheapest here and realise it
//                        "<=" means that we'll ship as early as we can and we'll prioritise the 1st warehouse if it doesn't matter
                            if (totalShippingToWare0Cost <= Math.min(totalShippingToWare1Cost, Math.min(totalShippingToWare2Cost, notShippingCost))) {
                                currentState = new State(nextStepStates.getState(warehouse0 + 1, warehouse1, warehouse2));
                                currentState.addShipment(currentMine, 0);
                                currentState.increaseCost(shipToWare0Cost);
                            }
                            else if (totalShippingToWare1Cost <= Math.min(totalShippingToWare2Cost,notShippingCost)) {
                                currentState = new State(nextStepStates.getState(warehouse0, warehouse1 + 1, warehouse2));
                                currentState.addShipment(currentMine, 1);
                                currentState.increaseCost(shipToWare1Cost);
                            }
                            else if (totalShippingToWare2Cost <= notShippingCost) {
                                currentState = new State(nextStepStates.getState(warehouse0, warehouse1, warehouse2 + 1));
                                currentState.addShipment(currentMine, 2);
                                currentState.increaseCost(shipToWare2Cost);
                            }
                            else {
                                currentState = new State(nextStepStates.getState(warehouse0, warehouse1, warehouse2));
                            }
                        }
                        else currentState = new BadState(); //not enough to get 70, so that's a fail-state,
//                    or went over 70 and that's a superfluous state
                        currentStatesArray.setState(warehouse0, warehouse1, warehouse2, currentState);
                    }
                }
            }
            mine4Arrays[currentCoalTonne] = currentStatesArray;
        }
        System.out.println("Path from the 1st tonne when warehouses are empty");
        mine4Arrays[0].getState(0, 0, 0).outputResult();
        System.out.println();
        System.out.println("Path from the 1st tonne when warehouses have 29 space left - 0 0 41");
        mine4Arrays[0].getState(0, 0, 41).outputResult();
        System.out.println();
        System.out.println("Path from 1st tonne when warehouses have 29 space left, but the 2nd warehouse hasn't enough space - 4 12 25");
        mine4Arrays[0].getState(4, 12, 25).outputResult();
    }
}
