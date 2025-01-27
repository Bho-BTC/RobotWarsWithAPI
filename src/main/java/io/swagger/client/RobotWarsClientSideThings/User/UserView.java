package io.swagger.client.RobotWarsClientSideThings.User;

import java.util.Scanner;

public class UserView {

    public static String getName(User user, String message) {
        Scanner scanner = new Scanner(System.in);
        String username;

        do {
            System.out.println(message);
            username = scanner.nextLine();
        } while (username.length() > 16 || username.length() < 3);

        return username;
    }



}
