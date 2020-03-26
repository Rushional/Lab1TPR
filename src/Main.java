public class Main {

    public static void main(String[] args) {
        StatesArray statesArray = new StatesArray();
//        These were supposed to be test variables that I didn't use, but I still might, I guess
//        int currentTotal = 60;
//        int currentWarehouse1 = 20;
//        int currentWarehouse2 = 29;
//        int currentWarehouse3 = 11; //so 1 and 2 are full, expecting 10 more to get total 70, warehouse3 has 30 more space
        for (int total = 0; total <= 70; total++) {
            for (int warehouse1 = 0; warehouse1 <= 20; warehouse1++) {
                for (int warehouse2 = 0; warehouse2 <= 29; warehouse2++) {
                    for (int warehouse3 = 0; warehouse3 <= 41; warehouse3++) {
                        State state = statesArray.getState(total, warehouse1, warehouse2, warehouse3);
//                        state decision making for mine5
                        if (total < 70) {
                            int totalShipmentLeft = 70 - total;
                            int mine5warehouse1 = 0;
                            int mine5warehouse2 = 0;
                            int mine5warehouse3 = 0;
                            int shipmentCost = 0;
//                            warehouses from cheapest to most expensive for mine5 are 2,3,1
//                            So these are my priorities
                            if (warehouse2 < 29) {
                                mine5warehouse2 = Math.min(totalShipmentLeft, 29 - warehouse2);
                                total += mine5warehouse2;
                                totalShipmentLeft -= mine5warehouse2;
                                shipmentCost += mine5warehouse2 * 28.3;
                            }
                            if (warehouse3 < 41) { //This if is kinda redundant
                                mine5warehouse3 = Math.min(totalShipmentLeft, 41 - warehouse3);
                                total += mine5warehouse3;
                                totalShipmentLeft -= mine5warehouse3;
                                shipmentCost += mine5warehouse3 * 38.2;
                            }
                            if (warehouse1 < 20) { //This if is kinda redundant
                                mine5warehouse1 = Math.min(totalShipmentLeft, 20 - warehouse1);
                                total += mine5warehouse1;
                                totalShipmentLeft -= mine5warehouse1;
                                shipmentCost += mine5warehouse1 * 38.2;
                            }
                            state.addShipment(5, 1, mine5warehouse1);
                            state.addShipment(5, 2, mine5warehouse2);
                            state.addShipment(5, 3, mine5warehouse3);
                            state.increaseCost(shipmentCost);
                        }
                    }
                }
            }
        }
    }
}
