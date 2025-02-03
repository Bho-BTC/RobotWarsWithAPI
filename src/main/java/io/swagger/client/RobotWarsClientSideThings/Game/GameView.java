package io.swagger.client.RobotWarsClientSideThings.Game;


import io.swagger.client.RobotWarsClientSideThings.Maps.Map;
import io.swagger.client.RobotWarsClientSideThings.Maps.MapView;
import io.swagger.client.RobotWarsClientSideThings.Obstacles.Walls;
import io.swagger.client.RobotWarsClientSideThings.Robots.Robot;
import io.swagger.client.RobotWarsClientSideThings.Robots.alignment;
import io.swagger.client.model.MovementType;

import java.util.Scanner;

public class GameView {


    public static MovementType getActionType(String playerName, Robot robot, Robot[] robots, Walls[] walls, Map map) {
        Scanner scanner = new Scanner(System.in);
        String input;

        do {
            System.out.println("Was wollen sie tun, " + playerName + "?");
            System.out.println("(1) Bewegen");
            System.out.println("(2) Angreifen");
            System.out.println("(3) Manuell Drehen");
            System.out.println("(4) Zug beenden");

            input = scanner.nextLine();
        } while (!input.equals("1") && !input.equals("2") && !input.equals("3") && !input.equals("4") || input.equals("2") && robot.isHasAttackedThisRound());

        MovementType actionType = null;
        switch (input) {
            //Bewegen
            case "1":
                actionType = MovementType.MOVE;
                if( !GameValidationController.validDirection(robot.getAlignment(), robot, robots, walls, map)){
                    actionType = getActionType(playerName, robot, robots, walls, map);
                }
            break;

            //Angreifen
            case "2":
                actionType =  MovementType.ATTACK;
            break;

            //Manuell Ausrichten
            case "3":
                actionType =  MovementType.ALIGN;
            break;

            case "4":
                actionType = MovementType.END;
            break;
        }
        return actionType;
    }

    public static void intro(String username, String username2) {
        //------------------------------------------Intro---------------------------------------------------
        System.out.println("Willkommen bei Robot Wars");
        System.out.println("          __");
        System.out.println("         |==|");
        System.out.println("       __|__|__");
        System.out.println("      |  O O  |");
        System.out.println("      |_______|");
        System.out.println("      /       \\");
        System.out.println("     /         \\");
        System.out.println("    /___________\\");
        System.out.println("   |             |");
        System.out.println("   |  |       |  |");
        System.out.println("   |  |       |  |");
        System.out.println("   |  |       |  |");
        System.out.println("   |__|       |__|");
        System.out.println(username + "      " + username2);
        System.out.println();
    }


    public static boolean askWantAttack(String playerName) {
        String temp;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println(playerName + ", willst du den Gegner angreifen? (Y/N)");
            temp = sc.nextLine();
        } while (!(temp.equalsIgnoreCase("y")) && !(temp.equalsIgnoreCase("n")));

        if (temp.equalsIgnoreCase("y")) {
            return true;
        } else {
            return false;
        }
    }



    public static alignment getAlignmentForDirection(String direction) {
        for (alignment alignment : alignment.values()) {
            if (direction.equals(alignment.key)) {
                return alignment;
            }
        }
        return alignment.NORTH;
    }


    public static alignment getAlignDirection(String playerName) {
        Scanner scanner = new Scanner(System.in);
        String direction;
        do {
            System.out.println();
            System.out.println(playerName + ", in welche Richtung willst du dich drehen? (Q, W, E, A, D, Y, S, X)");
            System.out.println("    Q    W    E");
            System.out.println("    A   You   D");
            System.out.println("    Y    S    X");
            direction = scanner.nextLine();
        } while (!direction.equalsIgnoreCase("Q") && !direction.equalsIgnoreCase("W") && !direction.equalsIgnoreCase("E")
                && !direction.equalsIgnoreCase("A") && !direction.equalsIgnoreCase("S") && !direction.equalsIgnoreCase("D")
                && !direction.equalsIgnoreCase("Y") && !direction.equalsIgnoreCase("X") && !direction.equalsIgnoreCase("P"));

        return getAlignmentForDirection(direction);
    }


    public static void printMissHitMessage() {
        System.out.println("Du hast nichts getroffen");
    }

    public static void printWallHitMessage() {
        System.out.println("Du hast eine wand getroffen");
    }

    public static void printWinMessage(String winner) {
        if (winner.equals("unentschieden")) {
            System.out.println("Es gibt keinen Gewinner, es ist ein Unentschieden");
        } else {
            System.out.println("Der Gewinner ist: " + winner + "!");
        }
    }


}
