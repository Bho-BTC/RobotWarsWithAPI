package io.swagger.client.Client;

import io.swagger.client.ApiException;
import io.swagger.client.RobotWarsClientSideThings.User.User;
import io.swagger.client.api.DefaultApi;
import io.swagger.client.model.Robot;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RobotService {

    public static io.swagger.client.RobotWarsClientSideThings.Robots.Robot[] createRobotsForRobotWars(DefaultApi apiInstance, User[] users) throws ApiException {
        Robot robot1Model = apiInstance.apiRobotsRobotIdGet(users[0].getRobotId());
        io.swagger.client.RobotWarsClientSideThings.Robots.Robot robot1 = new io.swagger.client.RobotWarsClientSideThings.Robots.Robot(robot1Model, '1');

        Robot robot2Model = apiInstance.apiRobotsRobotIdGet(users[1].getRobotId());
        io.swagger.client.RobotWarsClientSideThings.Robots.Robot robot2 = new io.swagger.client.RobotWarsClientSideThings.Robots.Robot(robot2Model, '2');

        return new io.swagger.client.RobotWarsClientSideThings.Robots.Robot[]{robot1, robot2};
    }



    private static void printAllRobots(DefaultApi apiInstance) {
        try {
            System.out.println(apiInstance.apiRobotsGet());
        } catch (ApiException e) {
            System.out.println(e.getCode());
            System.out.println(e.getMessage());
        }
    }


    public static Robot chooseRobot(Scanner scanner, DefaultApi apiInstance) throws ApiException {
        try {
            //Roboter Liste laden
            List<Robot> robotList = apiInstance.apiRobotsGet();
            List<String> idList = new ArrayList<>();
            String input;
            //Roboter Ids speichern
            for (Robot robot : robotList) {
                System.out.println(robot.getName());
                System.out.println(robot.getId());
                System.out.println();
                idList.add(robot.getId());
            }
            //Id auswählen lassen
            do {
                System.out.println("Mit welchen Roboter willst du beitreten?");
                input = scanner.nextLine();
            } while (!idList.contains(input));

            return getRobotByIdNoPrint(apiInstance, input);

        } catch (ApiException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


    //--------------------------------------------------------Bestimmen Roboter abrufen----------------------------------------------------------
    public static Robot getRobotByIdNoPrint(DefaultApi apiInstance, String Id) throws ApiException {
        try {
            return apiInstance.apiRobotsRobotIdGet(Id);
        } catch (ApiException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


    //-----------------------------------------------------Robotermodel erstellen--------------------------------------------------------------
    public static io.swagger.client.model.NewRobot makeRobot(Scanner scanner) {
        io.swagger.client.model.NewRobot robot = new io.swagger.client.model.NewRobot();
        UserInputView.getRobotName(scanner, robot);
        UserInputView.getStatsForRobot(scanner, robot);
        return robot;
    }







}
