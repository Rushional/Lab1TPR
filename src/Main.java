public class Main {

    public static void main(String[] args) {
	// write your code here
        StatesArray statesArray = new StatesArray();
        int currentTotal = 60;
        int currentWarehouse1 = 20;
        int currentWarehouse2 = 29;
        int currentWarehouse3 = 11; //so 1 and 2 are full, expecting 10 more to get total 70, warehouse3 has 30 more space
        for (int total = 0; total < 70; total++) {
            for (int warehouse1 = 0; warehouse1 < 20; warehouse1++) {
                for (int warehouse2 = 0; warehouse2 < 29; warehouse2++) {
                    for (int warehouse3 = 0; warehouse3 < 41; warehouse3++) {
//                        state decision making for mine5
                        if (total + 1 < 70) {
                            int mine5warehouse1 = 0;
                            int mine5warehouse2 = 0;
                            int mine5warehouse3 = 0;
                            int shipmentCost = 0;
//                            warehouses from cheapest to most expensive for mine5 are 2,3,1
//                            So these are my priorities
                            if (warehouse2 + 1 < 29) {
                                int mine5warehouse2 = 29 - (warehouse2 + 1);
                            }
                        }
                        statesArray.states[total][warehouse1][warehouse2][warehouse3].
                    }
                }
            }
        }
    }
}
