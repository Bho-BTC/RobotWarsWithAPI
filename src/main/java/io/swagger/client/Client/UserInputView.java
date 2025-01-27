package io.swagger.client.Client;

import java.math.BigDecimal;
import java.util.Scanner;

public class UserInputView {

    //----------------------------------------------------------Roboter Stats abfragen---------------------------------------------------------
    public static void getStatsForRobot(Scanner scanner, io.swagger.client.model.NewRobot robot) {
        int health = 1, attackDamage = 1, attackRange = 1, movementRate = 1;
        int in;
        for (int i = 0; i < 15; i++) {
            do {
                System.out.println("Geben sie an welchen Stat sie erhöhen wollen. Sie haben noch " + (15 - i) + " übrig.");
                System.out.println("(1) Leben: " + health);
                System.out.println("(2) Schaden: " + attackDamage);
                System.out.println("(3) Reichweite: " + attackRange);
                System.out.println("(4) Movement: " + movementRate);
                in = scanner.nextInt();
            } while (in != 1 && in != 2 && in != 3 && in != 4);
            switch (in) {
                case 1:
                    health++;
                    break;


                case 2:
                    attackDamage++;
                    break;

                case 3:
                    attackRange++;
                    break;


                case 4:
                    movementRate++;
                    break;
            }
        }
        System.out.println();
        System.out.println("Leben: " + health);
        System.out.println("Schaden: " + attackDamage);
        System.out.println("Reichweite: " + attackRange);
        System.out.println("Movement: " + movementRate);
        System.out.println();

        robot.health(BigDecimal.valueOf(health));
        robot.attackDamage(BigDecimal.valueOf(attackDamage));
        robot.attackRange(BigDecimal.valueOf(attackRange));
        robot.movementRate(BigDecimal.valueOf(movementRate));

    }

    public static int getActionInput(Scanner scanner) {
        int input;
        do {
            System.out.println("Was willst du tun?");
            System.out.println("(1) Einen Roboter erstellen");
            System.out.println("(2) Eine Map laden");
            System.out.println("(3) Ein Spiel erstellen");
            System.out.println("(4) Einem Spiel beitreten");
            System.out.println("(5) Exit");
            input = scanner.nextInt();
        } while (input != 1 && input != 2 && input != 3 && input != 4 && input != 5);
        return input;
    }

    //-------------------------------------------------------Roboter Namen abfragen----------------------------------------------------------------
    public static void getRobotName(Scanner scanner, io.swagger.client.model.NewRobot robot) {
        System.out.println("Wählen sie einen Namen für Ihren Roboter aus:");
        String robotName = scanner.nextLine();
        robot.setName(robotName);
    }
}
