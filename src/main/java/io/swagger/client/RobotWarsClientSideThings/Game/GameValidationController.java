package io.swagger.client.RobotWarsClientSideThings.Game;

import io.swagger.client.RobotWarsClientSideThings.Maps.Map;
import io.swagger.client.RobotWarsClientSideThings.Obstacles.Walls;
import io.swagger.client.RobotWarsClientSideThings.Robots.Robot;
import io.swagger.client.RobotWarsClientSideThings.Robots.alignment;

public class GameValidationController {

    public static boolean checkWin(Robot[] robots) {
        int aliveCount = 0;
        for (Robot robot : robots) {
            if (robot.getCurrentHp() > 0) {
                aliveCount++;
            }
        }
        return aliveCount == 1;
    }

    protected static boolean checkWall(int x, int y, Walls[] walls) {
        for (Walls wall : walls) {
            if (y == wall.getY() && x == wall.getX()) {
                return true;
            }
        }
        return false;
    }


    public static boolean checkCordsForRobot(int x, int y, Robot[] robots) {
        for (Robot robot : robots) {
            if (x == robot.getX() && y == robot.getY()) {
                return true;
            }
        }
        return false;
    }



    


    public static boolean validDirection(alignment alignment, Robot turningRobot, Robot[] robots, Walls[] walls, Map map) {
        int wouldBeX;
        int wouldBeY;
        switch (alignment.key) {
            case "Q":
                wouldBeX = turningRobot.getX() - 1;
                wouldBeY = turningRobot.getY() - 1;
                if (checkCordsForRobot(wouldBeX, wouldBeY, robots) || wouldBeX < 1 || wouldBeY < 1 || checkWall(wouldBeX, wouldBeY, walls)) {
                    return false;
                }
                break;

            case "W":
                wouldBeX = turningRobot.getX();
                wouldBeY = turningRobot.getY() - 1;
                if (checkCordsForRobot(wouldBeX, wouldBeY, robots) || wouldBeY < 1 || checkWall(wouldBeX, wouldBeY, walls)) {
                    return false;
                }
                break;

            case "E":
                wouldBeX = turningRobot.getX() + 1;
                wouldBeY = turningRobot.getY() - 1;
                if (checkCordsForRobot(wouldBeX, wouldBeY, robots) || wouldBeX > map.getMaxX() || wouldBeY < (map.getMaxTotal()/map.getMaxX()) || checkWall(wouldBeX, wouldBeY, walls)) {
                    return false;
                }
                break;

            case "A":
                wouldBeX = turningRobot.getX() - 1;
                wouldBeY = turningRobot.getY();
                if (checkCordsForRobot(wouldBeX, wouldBeY, robots) || wouldBeX < 1 || checkWall(wouldBeX, wouldBeY, walls)) {
                    return false;
                }
                break;

            case "D":
                wouldBeX = turningRobot.getX() + 1;
                wouldBeY = turningRobot.getY();
                if (checkCordsForRobot(wouldBeX, wouldBeY, robots) || wouldBeX > map.getMaxX() || checkWall(wouldBeX, wouldBeY, walls)) {
                    return false;
                }
                break;

            case "Y":
                wouldBeX = turningRobot.getX() - 1;
                wouldBeY = turningRobot.getY() + 1;
                if (checkCordsForRobot(wouldBeX, wouldBeY, robots) || wouldBeX < 1 || wouldBeY > (map.getMaxTotal()/map.getMaxX()) || checkWall(wouldBeX, wouldBeY, walls)) {
                    return false;
                }
                break;

            case "X":
                wouldBeX = turningRobot.getX() + 1;
                wouldBeY = turningRobot.getY() + 1;
                if (checkCordsForRobot(wouldBeX, wouldBeY, robots) || wouldBeX > map.getMaxX() || wouldBeY > (map.getMaxTotal()/map.getMaxX()) || checkWall(wouldBeX, wouldBeY, walls)) {
                    return false;
                }
                break;

            case "S":
                wouldBeX = turningRobot.getX();
                wouldBeY = turningRobot.getY() + 1;
                if (checkCordsForRobot(wouldBeX, wouldBeY, robots) || wouldBeY > (map.getMaxTotal()/map.getMaxX()) || checkWall(wouldBeX, wouldBeY, walls)) {
                    return false;
                }
                break;
        }
        return true;
    }


    public static boolean inRange(Robot robot1, Robot robot2) {
        //check ob robot2 x in range von robot1 x ist
        //Rechts und Oben
        if ((robot1.getX() + robot1.getRange() >= robot2.getX() && robot2.getX() >= robot1.getX()) && (robot1.getY() + robot1.getRange() >= robot2.getY() && robot2.getY() >= robot1.getY())
                //Rechts und Unten
                || (robot1.getX() + robot1.getRange() >= robot2.getX() && robot2.getX() >= robot1.getX()) && (robot1.getY() - robot1.getRange() <= robot2.getY() && robot2.getY() <= robot1.getY())
                //Rechts und Oben
                || (robot1.getX() - robot1.getRange() <= robot2.getX() && robot2.getX() <= robot1.getX()) && (robot1.getY() + robot1.getRange() >= robot2.getY() && robot2.getY() >= robot1.getY())
                //Links und Unten
                || (robot1.getX() - robot1.getRange() <= robot2.getX() && robot2.getX() <= robot1.getX()) && (robot1.getY() - robot1.getRange() <= robot2.getY() && robot2.getY() <= robot1.getY())) {
            //Links und Oben
            return true;
        } else {
            return false;
        }
    }


    public static boolean aligned(Robot robot1, Robot[] robots, Walls[] walls) {
        int tempX = robot1.getX();
        int tempY = robot1.getY();
        for (int i = 1; i <= robot1.getRange(); i++) {
            tempX += robot1.getAlignment().x;
            tempY += robot1.getAlignment().y;
            if (checkCordsForRobot(tempX, tempY, robots)) {
                return true;
            } else if (checkWall(tempX, tempY, walls)) {
                GameView.printWallHitMessage();
                return false;
            }
        }
        GameView.printMissHitMessage();
        return false;
    }


}
