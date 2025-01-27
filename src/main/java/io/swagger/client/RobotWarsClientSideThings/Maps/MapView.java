package io.swagger.client.RobotWarsClientSideThings.Maps;

import io.swagger.client.RobotWarsClientSideThings.Obstacles.Walls;
import io.swagger.client.RobotWarsClientSideThings.PowerUps.PowerUp;
import io.swagger.client.RobotWarsClientSideThings.Robots.Robot;
import io.swagger.client.RobotWarsClientSideThings.Robots.RobotController;

public class MapView {
    public static void drawMap(Map map) {

        for (int i = 0; i < map.getMaxTotal(); i++) {
            char space = map.getSpace1D(i);

            if(space != ' '){
                System.out.print("[ " + space + " ] ");
            } else {
                System.out.print("[   ] ");
            }
            if ((i + 1) % map.getMaxX() == 0) {
                System.out.println(); // Neue Zeile am Ende der Reihe
            }
        }
        System.out.println(map.arrayVersion);
        System.out.println(map.arrayVersion.length);

//        while (countY < map.maxY) {
//            System.out.println();
//            while (countX < map.maxX) {
//                if (MapValidation.isWall(countX, countY, walls)) {
//                    out = wallAvatar;
//                } else if (MapValidation.isPowerUp(countX, countY, powerUps)) {
//                    out = powerUpAvatar;
//                } else if (MapValidation.isRobot(countX,countY,robots)) {
//                    out = RobotController.getRobotOnXY(countX,countY,robots).getAvatar();
//
//                } else {
//                    out = ' ';
//                }
//                if (out == wallAvatar) {
//                    System.out.print("[" + out + out + out + "] ");
//                } else {
//                    System.out.print("[ " + out + " ] ");
//                }
//
//                countX++;
//            }
//            countY++;
//            countX = 0;
//        }
//        for (char space : map.arrayVersion){
//            System.out.print(space);
//        }
//        System.out.println();
    }


}
