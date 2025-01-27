package io.swagger.client.RobotWarsClientSideThings.Maps;
import io.swagger.client.RobotWarsClientSideThings.Obstacles.Walls;
import io.swagger.client.RobotWarsClientSideThings.PowerUps.PowerUp;
import io.swagger.client.RobotWarsClientSideThings.Robots.Robot;

public class MapValidation {

    protected static boolean isWall(int countX, int countY, Walls[] walls) {
        for (Walls wall : walls) {
            if (countY == wall.getY() && countX == wall.getX()) {
                return true;
            }
        }
        return false;
    }

    protected static boolean isRobot(int countX, int countY, Robot[] robots) {
        for(Robot robot : robots) {
            if (countX == robot.getX() && countY == robot.getY()) {
                return true;
            }
        }
        return false;
    }


    protected static boolean isPowerUp(int countX, int countY, PowerUp[] powerUps) {
        for (PowerUp powerUp : powerUps) {
            if (countY == powerUp.getY() && countX == powerUp.getX() && powerUp.isOnField()) {
                return true;
            }
        }
        return false;
    }



}
