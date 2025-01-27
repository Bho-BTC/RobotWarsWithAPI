package io.swagger.client.RobotWarsClientSideThings.Robots;

import io.swagger.client.RobotWarsClientSideThings.Game.GameValidationController;
import io.swagger.client.RobotWarsClientSideThings.Obstacles.Walls;

public class RobotController {
    public static void moveRobot(Robot robot, alignment direction, int maxX) {

        robot.setY(robot.getY() + direction.y, maxX);
        robot.setX(robot.getX() + direction.x, maxX);


    }


    public static Robot getRobotOnXY(int x, int y, Robot[] robots) {
        for (Robot robot : robots) {
            if (robot.getX() == x && robot.getY() == y) {
                return robot;
            }
        }
        return null;
    }


    public static void endTurn(Robot turningRobot) {
        turningRobot.setMovesLeft(0);
    }

    public static void endSelfRobot(Robot turningRobot) {
        turningRobot.setCurrentHp(0);
        endTurn(turningRobot);
    }


    public static void align(Robot turningRobot, alignment alignment) {
        turningRobot.setAlignment(alignment);
    }


    public static Robot getFirstRobotInAlignment(Robot turningRobot, Robot[] robots) {
        int tempX = turningRobot.getX();
        int tempY = turningRobot.getY();
        for (int i = 1; i <= turningRobot.getRange(); i++) {
            tempX += turningRobot.getAlignment().x;
            tempY += turningRobot.getAlignment().y;
            if (GameValidationController.checkCordsForRobot(tempX, tempY, robots)) {
                return getRobotOnXY(tempX, tempY, robots);
            }
        }
        return null;
    }


    public static void attack(Robot turningRobot, Robot[] robots, Walls[] walls) {
        turningRobot.setHasAttackedThisRound(true);
        if (GameValidationController.aligned(turningRobot, robots, walls)) {
            RobotController.hit(turningRobot, getFirstRobotInAlignment(turningRobot, robots));
            System.out.println();
        }
    }


    public static void hit(Robot robot, Robot targetRobot) {
        int tempDmg = robot.getDmg();

        targetRobot.setCurrentHp(targetRobot.getCurrentHp() - tempDmg);


        RobotView.printHitMessage(targetRobot);
    }


    public static void getStats(Robot robot) {
        int in;
        while (robot.getSkillPoints() > 0) {
            do {
                in = RobotView.getSkillpointInput(robot);
            } while (in != 1 && in != 2 && in != 3 && in != 4);
            switch (in) {
                case 1:
                    robot.setMaxLifePoints(robot.getMaxLifePoints() + 1);
                    break;


                case 2:
                    robot.setDmg(robot.getDmg() + 1);
                    break;

                case 3:
                    robot.setRange(robot.getRange() + 1);
                    break;


                case 4:
                    robot.setMovement(robot.getMovement() + 1);
                    break;
            }
            robot.setSkillPoints(robot.getSkillPoints() - 1);
        }
        RobotView.printFinalStats(robot);
    }


}
