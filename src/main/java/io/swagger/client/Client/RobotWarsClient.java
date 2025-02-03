package io.swagger.client.Client;

import io.swagger.client.ApiException;
import io.swagger.client.RobotWarsClientSideThings.Game.RobotWarsGame;
import io.swagger.client.RobotWarsClientSideThings.Maps.Map;
import io.swagger.client.RobotWarsClientSideThings.Maps.MapView;
import io.swagger.client.RobotWarsClientSideThings.User.User;
import io.swagger.client.api.DefaultApi;
import io.swagger.client.model.*;

import java.util.*;

public class RobotWarsClient {

    public static void main(String[] args) throws ApiException, InterruptedException {
        DefaultApi apiInstance = new DefaultApi();
        Scanner scanner = new Scanner(System.in);
        int toDo = 0;

        while (toDo != 5) {
            toDo = UserInputView.getActionInput(scanner);
            scanner.nextLine();
            switch (toDo) {
                //Roboter erstellen
                case 1:
                    io.swagger.client.model.NewRobot newRobot = RobotService.makeRobot(scanner);
                    Robot postedRobot = apiInstance.apiRobotsRobotPost(newRobot);
                    System.out.println(postedRobot);
                    break;

                //Map laden
                case 2:
                    Map map = MapService.chooseMap(scanner, apiInstance);
                    System.out.println(map);
                    MapView.drawMap(map);
                    break;

                //Spiel erstellen
                case 3:
                    NewGame newGame = new NewGame();
                    newGame.mapId(MapService.getFirstMapId(apiInstance));
                    GameService.createNewGameWithPrint(newGame, apiInstance);
                    break;

                //Spiel beitreten
                case 4:
                    Robot chosenRobot = RobotService.chooseRobot(scanner, apiInstance);
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
                        do {
                            System.out.println(apiInstance.apiGamesGameIdGet(game.getId()).getStatus());
                            Thread.sleep(500);
                            game = apiInstance.apiGamesGameIdGet(game.getId());
                        }while (game.getStatus() != Game.StatusEnum.STARTED);

                        game = apiInstance.apiGamesGameIdGet(game.getId());
                        User[] users = UserService.createUsersForRobotWars(game, userId);
                        io.swagger.client.RobotWarsClientSideThings.Robots.Robot[] robots = RobotService.createRobotsForRobotWars(apiInstance, users, joinGame.getRobotId());



                        RobotWarsGame RobotWars = new RobotWarsGame(game, MapService.getGameMapById(apiInstance, game.getMap()),robots, users, chosenRobot.getId(), userId, apiInstance);

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




}

