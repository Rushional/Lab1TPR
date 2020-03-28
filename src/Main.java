public class Main {

    public static void main(String[] args) {
        StatesArray statesArray = new StatesArray();
//        These were supposed to be test variables that I didn't use, but I still might, I guess
//        int currentTotal = 60;
//        int currentWarehouse1 = 20;
//        int currentWarehouse2 = 29;
//        int currentWarehouse3 = 11; //so 1 and 2 are full, expecting 10 more to get total 70, warehouse3 has 30 more space
        for (int total = 0; total <= 70; total++) {
            for (int warehouse0 = 0; warehouse0 <= 20; warehouse0++) {
                for (int warehouse1 = 0; warehouse1 <= 29; warehouse1++) {
                    for (int warehouse2 = 0; warehouse2 <= 41; warehouse2++) {
                        State state = statesArray.getState(total, warehouse0, warehouse1, warehouse2);
//                        state decision making for mine5
                        if (total < 70) {
                            int totalShipmentLeft = 70 - total;
                            int mine4warehouse0 = 0;
                            int mine4warehouse1 = 0;
                            int mine4warehouse2 = 0;
                            int shipmentCost = 0;
//                            warehouses from cheapest to most expensive for mine5 are 1,2,0
//                            So these are my priorities
                            if (warehouse1 < 29) {
                                mine4warehouse1 = Math.min(totalShipmentLeft, 29 - warehouse1);
                                total += mine4warehouse1;
                                totalShipmentLeft -= mine4warehouse1;
                                shipmentCost += mine4warehouse1 * 28.3;
                            }
                            if (warehouse2 < 41) { //This if is kinda redundant
                                mine4warehouse2 = Math.min(totalShipmentLeft, 41 - warehouse2);
                                total += mine4warehouse2;
                                totalShipmentLeft -= mine4warehouse2;
                                shipmentCost += mine4warehouse2 * 38.2;
                            }
                            if (warehouse0 < 20) { //This if is kinda redundant
                                mine4warehouse0 = Math.min(totalShipmentLeft, 20 - warehouse0);
                                total += mine4warehouse0;
                                totalShipmentLeft -= mine4warehouse0;
                                shipmentCost += mine4warehouse0 * 38.2;
                            }
                            state.addShipment(4, 0, mine4warehouse0);
                            state.addShipment(4, 1, mine4warehouse1);
                            state.addShipment(4, 2, mine4warehouse2);
                            state.increaseCost(shipmentCost);
                        }
                    }
                }
            }
        }
    }
}
