package io.swagger.client;

import io.swagger.client.RobotWarsClientSideThings.Game.RobotWarsGame;
import io.swagger.client.RobotWarsClientSideThings.User.User;
import io.swagger.client.api.DefaultApi;
import io.swagger.client.model.*;

import java.math.BigDecimal;
import java.util.*;

public class RobotWarsClient {

    public static void main(String[] args) throws ApiException, InterruptedException {
        DefaultApi apiInstance = new DefaultApi();
        Scanner scanner = new Scanner(System.in);
        int toDo = 0;

        while (toDo != 5) {
            toDo = getActionInput(scanner);
            scanner.nextLine();
            switch (toDo) {
                //Roboter erstellen
                case 1:
                    io.swagger.client.model.NewRobot newRobot = makeRobot(scanner);
                    apiInstance.apiRobotsRobotPost(newRobot);
                    break;

                //Map laden
                case 2:
                    System.out.println(getMapById(apiInstance, getFirstMapId(apiInstance)));

                    break;

                //Spiel erstellen
                case 3:
                    NewGame newGame = new NewGame();
                    newGame.mapId(getFirstMapId(apiInstance));
                    createNewGameWithPrint(newGame, apiInstance);
                    break;

                //Spiel beitreten
                case 4:
                    Robot chosenRobot = chooseRobot(scanner, apiInstance);
                    JoinGame joinGame = new JoinGame();
                    joinGame.robotId(chosenRobot.getId());
                    //chooseRobot(scanner, apiInstance);

                    //chooseGameId(scanner, apiInstance);
                    System.out.print("Give Game Id:");
                    String gameId= scanner.nextLine();

                    Game game = apiInstance.apiGamesGameIdGet(gameId);
                    try {
                        String userId = (apiInstance.apiGamesGameIdJoinPost(joinGame, gameId).getPlayerId());
                        System.out.println(userId);
                        while (game.getStatus() == Game.StatusEnum.INITIAL) {
                            System.out.println(apiInstance.apiGamesGameIdGet(game.getId()).getStatus());
                            Thread.sleep(2000);
                            game = apiInstance.apiGamesGameIdGet(game.getId());
                        }

                        game = apiInstance.apiGamesGameIdGet(game.getId());
                        User[] users = createUsersForRobotWars(game, userId);
                        io.swagger.client.RobotWarsClientSideThings.Robots.Robot[] robots = createRobotsForRobotWars(apiInstance, users);



                        RobotWarsGame RobotWars = new RobotWarsGame(game, getMapById(apiInstance, game.getMap()),robots, users, chosenRobot.getId(), userId, apiInstance);

                        RobotWars.play();

                    } catch (ApiException e) {
                        System.out.println(e.getMessage());
                        System.out.println("Kein Game heute :(");
                    } catch (InterruptedException e) {
                        System.out.println(e.getMessage());
                        System.out.println("Zu Lange gebraucht sadge");
                    }

                    break;

                case 5:
                    break;
            }
        }
    }


    public static io.swagger.client.RobotWarsClientSideThings.Robots.Robot[] createRobotsForRobotWars(DefaultApi apiInstance, User[] users) throws ApiException {
        Robot robot1Model = apiInstance.apiRobotsRobotIdGet(users[0].getRobotId());
        io.swagger.client.RobotWarsClientSideThings.Robots.Robot robot1 = new io.swagger.client.RobotWarsClientSideThings.Robots.Robot(robot1Model, '1');

        Robot robot2Model = apiInstance.apiRobotsRobotIdGet(users[1].getRobotId());
        io.swagger.client.RobotWarsClientSideThings.Robots.Robot robot2 = new io.swagger.client.RobotWarsClientSideThings.Robots.Robot(robot2Model, '2');

        return new io.swagger.client.RobotWarsClientSideThings.Robots.Robot[]{robot1, robot2};
    }


    // public static io.swagger.client.RobotWarsClientSideThings.Maps.Map createMapForRobotWars(Map)


    public static User[] createUsersForRobotWars(Game game, String userId) {
        User user1 = new User();
        User user2 = new User();
        List<PlayerRobot> playerRobots = game.getPlayer();
        user1.setUserId(playerRobots.get(0).getPlayerId());
        user1.setRobotId(playerRobots.get(0).getRobotId());
        user2.setUserId(playerRobots.get(1).getPlayerId());
        user2.setRobotId(playerRobots.get(1).getRobotId());
        if(Objects.equals(user1.getUserId(), userId)){
            user1.setName("You");
            user2.setName("Enemy");
        }else{
            user1.setName("Enemy");
            user2.setName("You");
        }
        return new User[]{user1, user2};
    }

    private static void printAllRobots(DefaultApi apiInstance) {
        try {
            System.out.println(apiInstance.apiRobotsGet());
        } catch (ApiException e) {
            System.out.println(e.getCode());
            System.out.println(e.getMessage());
        }
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

    public static String chooseGameId(Scanner scanner, DefaultApi apiInstance) throws ApiException {
        try {
            //Roboter Liste laden
            List<Game> gameList = apiInstance.apiGamesGet();
            List<String> idList = new ArrayList<>();
            String input;
            //Roboter Ids speichern
            for (Game game : gameList) {
                System.out.println(game.getId());
                idList.add(game.getId());
            }
            //Id auswählen lassen
            do {
                System.out.println("Welchem Spiel willst du beitreten?");
                input = scanner.nextLine();
            } while (!idList.contains(input));

            return input;

        } catch (ApiException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


    public static void createNewGameWithPrint(NewGame newGame, DefaultApi apiInstance) throws ApiException {
        try {
            apiInstance.apiGamesGamePost(newGame).getId();
            System.out.print(apiInstance.apiGamesGamePost(newGame));
        } catch (ApiException e) {
            System.out.println(e.getResponseBody());
        }

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


    public static String getFirstMapId(DefaultApi apiInstance) throws ApiException {
        List<Map> jsonResponse = apiInstance.apiMapsGet();
        if (jsonResponse == null || jsonResponse.isEmpty()) {
            System.out.println("Keine Daten gefunden.");
            return null;
        }
        Map mapData = jsonResponse.getFirst();

        return (String) mapData.get("id");
    }


    //----------------------------------------------------------Erste Map abrufen und ausgeben-----------------------------------------------
    public static io.swagger.client.RobotWarsClientSideThings.Maps.Map getMapById(DefaultApi apiInstance, String mapId) throws ApiException {
        try {
            Map mapData = apiInstance.apiMapsMapIdGet(mapId);

            int mapSizeX = ((Double) mapData.get("mapSizeX")).intValue();
            int mapSize = ((Double) mapData.get("mapSize")).intValue();

            //Liste von map Objects speichern
            List<Map<String, Object>> mapItems = (List<Map<String, Object>>) mapData.get("mapItems");

            //Neue ingame Map mit ausgelesenen Map größen erstellen
            io.swagger.client.RobotWarsClientSideThings.Maps.Map ingameMap = new io.swagger.client.RobotWarsClientSideThings.Maps.Map(mapSizeX, mapSize);

            // ingame map array füllen
            for (Map<String, Object> item : mapItems) {
                int index = ((Double) item.get("index")).intValue();
                String type = (String) item.get("type");
                switch (type) {
                    case "ROBOT":
                        ingameMap.setSpace1D(index, ingameMap.getRobotChar()); // für Robot
                        break;
                    case "WALL":
                        ingameMap.setSpace1D(index, ingameMap.getWallChar()); // für Wall
                        break;
                }
            }
            //MapView.drawMap(ingameMap);
            return ingameMap;
        } catch (ApiException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    //-----------------------------------------------------Robotermodel erstellen--------------------------------------------------------------
    public static io.swagger.client.model.NewRobot makeRobot(Scanner scanner) {
        io.swagger.client.model.NewRobot robot = new io.swagger.client.model.NewRobot();
        getRobotName(scanner, robot);
        getStatsForRobot(scanner, robot);
        return robot;
    }

    //-------------------------------------------------------Roboter Namen abfragen----------------------------------------------------------------
    private static void getRobotName(Scanner scanner, io.swagger.client.model.NewRobot robot) {
        System.out.println("Wählen sie einen Namen für Ihren Roboter aus:");
        String robotName = scanner.nextLine();
        robot.setName(robotName);
    }

    //----------------------------------------------------------Roboter Stats abfragen---------------------------------------------------------
    private static void getStatsForRobot(Scanner scanner, io.swagger.client.model.NewRobot robot) {
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

}

