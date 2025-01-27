package io.swagger.client.RobotWarsClientSideThings.PowerUps;

import io.swagger.client.RobotWarsClientSideThings.RobotPowerUp.RobotPowerUpController;
import io.swagger.client.RobotWarsClientSideThings.Robots.Robot;

import java.util.Random;

public class PowerUpController {

    protected static int dmgVal = 3;
    protected static int rangeVal = 2;
    protected static int movementVal = 1;

    public static int makeRandomBuffValue(int value) {
        Random random = new Random();

        int temp = random.nextInt(101);

        if (temp > 10) {
            return value;
        } else {
            return value * -1;
        }
    }

    public static void update(PowerUp powerUp) {
        if (!powerUp.isOnField()) {
            powerUp.setRoundsToRespawn(powerUp.getRoundsToRespawn() - 1);
            if (powerUp.getRoundsToRespawn() == 0) {
                powerUp.setOnField(true);
            }
        }
    }


    public static String chooseImpactedValue(PowerUp powerUp) {
        Random random = new Random();
        int temp = random.nextInt(100);

        if (temp > 66) {
            return "Dmg";
        } else if (temp > 33) {
            return "Range";
        } else {
            return "Movement";
        }

    }

    public static void pickedUpBy(Robot robot, PowerUp mainPowerUp) {

        String type = chooseImpactedValue(mainPowerUp);

        if (type.equals("Dmg")) {
            RobotPowerUpController.applyDmgPowerUp(robot, mainPowerUp, dmgVal);
        } else if (type.equals("Range")) {
            RobotPowerUpController.applyRangePowerUp(robot, mainPowerUp, rangeVal);
        } else if (type.equals("Movement")) {
            RobotPowerUpController.applyMovementPowerUp(robot, mainPowerUp, movementVal);
        }
    }
}
