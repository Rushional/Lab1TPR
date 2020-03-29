public class Main {

    public static void main(String[] args) {
        StatesArray statesArray = new StatesArray();
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
//                            Maybe I should have Mine.SendToWarehouse(Warehouse warehouse) so SendToWarehouse(Warehouses[2])
//                            Would be how the decision is made
//                            Here we have 4 options. Not take at all or send to one of 0-2 warehouses.
//                            We check what solution is cheapest and we save it to this StatesArray element

//                            calculate the costs of these 4 decisions here
//                            These are costs of different options.
//                            By default they are max int because if the value doesn't change this would signify that
//                            This route is implausible
//                            double shippingToWare0 = Integer.MAX_VALUE;
//                            double shippingToWare1 = Integer.MAX_VALUE;
//                            double shippingToWare2 = Integer.MAX_VALUE;
//                            alternative solution would be to use BadState extends State
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

                            //                        Then determine which one is the cheapest here
                            //                        if (a < Math.min(b,c) send a;
                            //                        else if (b < c) send b;
                            //                        else send c;

                            //                        Then save it to current StatesArray element

                            //                        Clone() shipmentsArray from the correct next step and modify it:
                            //                        +1 one path from mine to a warehouse
                        }
                    }
                    else state = new BadState();
                    statesArray.setState(warehouse0, warehouse1, warehouse2, state);
                }
            }
        }
        System.out.println("case 20 29 11, expecting BadState");
        if (statesArray.getState(20, 29, 11).getClass() == BadState.class) System.out.println("This doesn't really work unfortunately");
        else statesArray.getState(20, 29, 11).outputShipmentsArray();
        System.out.println();
        System.out.println("case 19 29 21, expecting to skip 1 and add to 3 ");
        if (statesArray.getState(19, 29, 21).getClass() == BadState.class) System.out.println("This doesn't really work unfortunately");
        else statesArray.getState(19, 29, 21).outputShipmentsArray();
        System.out.println();
        System.out.println("case 19 28 22, expecting to skip 1 and add to 2");
        if (statesArray.getState(19, 28, 22).getClass() == BadState.class) System.out.println("This doesn't really work unfortunately");
        else statesArray.getState(19, 28, 22).outputShipmentsArray();
        System.out.println();
        System.out.println("case 20 29 20, expecting to add to 3");
        if (statesArray.getState(20, 29, 20).getClass() == BadState.class) System.out.println("This doesn't really work unfortunately");
        else statesArray.getState(20, 29, 20).outputShipmentsArray();
        System.out.println();
        System.out.println("case 20 29 21, expecting not to do anything");
        if (statesArray.getState(20, 29, 21).getClass() == BadState.class) System.out.println("This doesn't really work unfortunately");
        else statesArray.getState(20, 29, 21).outputShipmentsArray();
        System.out.println();
    }
}
