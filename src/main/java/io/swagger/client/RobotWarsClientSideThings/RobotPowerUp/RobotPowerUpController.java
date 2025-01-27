package io.swagger.client.RobotWarsClientSideThings.RobotPowerUp;

import io.swagger.client.RobotWarsClientSideThings.PowerUps.PowerUp;
import io.swagger.client.RobotWarsClientSideThings.PowerUps.PowerUpController;
import io.swagger.client.RobotWarsClientSideThings.Robots.Robot;

public class RobotPowerUpController {


    public static void updateBuffs(Robot robot) {
        if (robot.isDmgBuffActive()) {
            robot.setDmgBuffDuration(robot.getDmgBuffDuration() - 1);
        }

        if (robot.isRangeBuffActive()) {
            robot.setRangeBuffDuration(robot.getRangeBuffDuration() - 1);
        }

        if (robot.isMovementBuffActive()) {
            robot.setMovementBuffDuration(robot.getMovementBuffDuration() - 1);
        }


        if (robot.getDmgBuffDuration() < 1){
            robot.setDmgBuffDuration(0);
            removeDmgPowerupFrom(robot);
        }

        if(robot.getRangeBuffDuration() < 1){
            robot.setRangeBuffDuration(0);
            removeRangePowerupFrom(robot);
        }

        if(robot.getMovementBuffDuration() < 1){
            robot.setMovementBuffDuration(0);
            removeMovementPowerupFrom(robot);
        }
    }


    public static void removeDmgPowerupFrom(Robot robot) {
        if (robot.isDmgBuffActive()) {
            robot.setDmgBuffActive(false);
            robot.setDmg(robot.getDmg() - robot.getGainedDmg());
        }
    }

    public static void removeRangePowerupFrom(Robot robot) {
        if (robot.isRangeBuffActive()) {
            robot.setRangeBuffActive(false);
            robot.setRange(robot.getRange() - robot.getGainedRange());
        }
    }

    public static void removeMovementPowerupFrom(Robot robot) {
        if (robot.isMovementBuffActive()) {
            robot.setMovementBuffActive(false);
            robot.setMovement(robot.getMovement() - robot.getGainedMovement());
        }
    }

    //removal in RobotController.hit
    public static void applyDmgPowerUp(Robot robot, PowerUp powerUp, int dmg) {
        if (!robot.isDmgBuffActive()) {
            dmg = PowerUpController.makeRandomBuffValue(dmg);
            robot.setDmgBuffActive(true);
            robot.setDmg(robot.getDmg() + dmg);
            robot.setGainedDmg(dmg);
            robot.setDmgBuffDuration(2);
            powerUp.setOnField(false);
            System.out.println();
            System.out.println("Der Roboter " + robot.getAvatar() + " hat ein Damage PowerUp aufgehoben.");
        } else {
            System.out.println();
            System.out.println("Der Roboter " + robot.getAvatar() + " hat bereits ein Damage PowerUp.");
            powerUp.setOnField(false);
        }
    }


    public static void applyRangePowerUp(Robot robot, PowerUp powerUp, int range) {
        if (!robot.isRangeBuffActive()) {
            range = PowerUpController.makeRandomBuffValue(range);
            robot.setRangeBuffActive(true);
            robot.setRange(robot.getRange() + range);
            robot.setGainedRange(range);
            robot.setRangeBuffDuration(2);
            powerUp.setOnField(false);
            System.out.println();
            System.out.println("Der Roboter " + robot.getAvatar() + " hat ein Range PowerUp aufgehoben.");
        } else {
            System.out.println();
            System.out.println("Der Roboter " + robot.getAvatar() + " hat bereits ein Range PowerUp.");
            powerUp.setOnField(false);
        }
    }

    public static void applyMovementPowerUp(Robot robot, PowerUp powerUp, int movement) {
        if (!robot.isMovementBuffActive()) {
            movement = PowerUpController.makeRandomBuffValue(movement);
            robot.setMovementBuffActive(true);
            robot.setMovesLeft(robot.getMovesLeft() + movement);
            robot.setMovement(robot.getMovement() + movement);
            robot.setGainedMovement(movement);
            robot.setMovementBuffDuration(2);
            powerUp.setOnField(false);
            System.out.println();
            System.out.println("Der Roboter " + robot.getAvatar() + " hat ein Movement PowerUp aufgehoben.");
        } else {
            System.out.println();
            System.out.println("Der Roboter " + robot.getAvatar() + " hat bereits ein Movement PowerUp.");
            powerUp.setOnField(false);
        }
    }
}
