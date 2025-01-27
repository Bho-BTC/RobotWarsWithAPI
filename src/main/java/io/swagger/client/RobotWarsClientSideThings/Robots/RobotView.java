package io.swagger.client.RobotWarsClientSideThings.Robots;

import java.util.Scanner;

public class RobotView {


    //-----------------------------------------Holt sich ein Zeichen langen Input vom Nutzer, benötigt Nachricht zum Auffordern------------------------------------------
    public static void chooseRobotAvatar(Robot robot, String message) {
        //-----------------------------------------------Robot Auswahl-------------------------------------------------,
        Scanner scanner = new Scanner(System.in);
        String input;
        do {
            System.out.println(message);
            input = scanner.nextLine();
        } while (input.length() > 1);
        robot.setAvatar(input.charAt(0));
        System.out.println();
    }

    public static void printHitMessage(Robot targetRobot) {
        System.out.println("Du hast den Roboter " + targetRobot.getAvatar() + " getroffen. ");
    }

    public static int getSkillpointInput(Robot robot) {
        Scanner scanner = new Scanner(System.in);
        int input;

        System.out.println("Geben sie an welchen Stat sie erhöhen wollen. Sie haben noch " + robot.getSkillPoints() + " übrig.");
        System.out.println("(1) Leben: " + robot.getMaxLifePoints());
        System.out.println("(2) Schaden: " + robot.getDmg());
        System.out.println("(3) Reichweite: " + robot.getRange());
        System.out.println("(4) Movement: " + robot.getMovement());
        input = scanner.nextInt();

        return input;
    }

    public static void printFinalStats(Robot robot) {
        System.out.println("Leben: " + robot.getMaxLifePoints());
        System.out.println("Schaden: " + robot.getDmg());
        System.out.println("Reichweite: " + robot.getRange());
        System.out.println("Movement: " + robot.getMovement());
        System.out.println();
    }


    public static void printStats(Robot robot) {
        System.out.println("Stats von " + robot.getAvatar());
        System.out.println("HP: " + robot.getCurrentHp() + "/" + robot.getMaxLifePoints());
        System.out.println("Damage: " + robot.getDmg());
        System.out.println("Range: " + robot.getRange());
        System.out.println("Moves: " + robot.getMovesLeft() + "/" + robot.getMovement());
        System.out.println("Ausrichtung: " + robot.getAlignment().toString());

        System.out.println();
        if (robot.isDmgBuffActive()) {
            System.out.println("Dmg Buff is active for " + robot.getDmgBuffDuration() + " Turns");
        }
        if (robot.isRangeBuffActive()) {
            System.out.println("Range Buff is active for " + robot.getRangeBuffDuration() + " Turns");
        }
        if (robot.isMovementBuffActive()) {
            System.out.println("MovementBuff is active for " + robot.getMovementBuffDuration() + " Turns");
        }
        System.out.println();
    }


}
