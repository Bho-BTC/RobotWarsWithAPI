package io.swagger.client.RobotWarsClientSideThings.Maps;

public class MapController {

    public static int getAmountOfThese(char these, Map map) {
        int amount = 0;
        for (char space : map.arrayVersion) {
            if (space == these) {
                amount++;
            }
        }
        return amount;
    }


    public static int[] getLocationOfThese(char these, Map map, int amount) {
        int[] locationsOfThese = new int[amount];
        int counter = 0;
        int location = 0;
        for (char space : map.arrayVersion) {
            if (space == these) {
                locationsOfThese[counter] = location;
                counter++;
            }
            location++;
        }
        return locationsOfThese;
    }


}
