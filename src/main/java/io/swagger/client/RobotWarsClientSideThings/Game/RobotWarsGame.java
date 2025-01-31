package io.swagger.client.RobotWarsClientSideThings.Game;

import io.swagger.client.ApiException;
import io.swagger.client.RobotWarsClientSideThings.Maps.Map;
import io.swagger.client.RobotWarsClientSideThings.Maps.MapController;
import io.swagger.client.RobotWarsClientSideThings.Maps.MapView;
import io.swagger.client.RobotWarsClientSideThings.Obstacles.Walls;
import io.swagger.client.RobotWarsClientSideThings.PowerUps.PowerUp;
import io.swagger.client.RobotWarsClientSideThings.PowerUps.PowerUpController;
import io.swagger.client.RobotWarsClientSideThings.Robots.Robot;
import io.swagger.client.RobotWarsClientSideThings.User.User;
import io.swagger.client.api.DefaultApi;
import io.swagger.client.model.Game;
import io.swagger.client.model.Move;

import java.util.List;

public class RobotWarsGame {
    private Map map;
    private Robot[] robots;
    private User[] users;
    private PowerUp[] powerUps;
    private Game game;
    private String localUser;
    private String localRobot;
    private DefaultApi api;


    //removed alignment setting from makeMove in GameController, moveRobot in RobotController


    public RobotWarsGame(Game game, Map map, Robot[] robots, User[] users, String robotId, String userId, DefaultApi api) {
        this.map = map;
        this.robots = robots;
        this.users = users;
        this.game = game;
        this.localUser = userId;
        this.localRobot = robotId;
        this.api = api;
    }

    public void play() throws ApiException {

        //Zähler variable für Züge erstellen und Roboter Startpositionen festlegen
        int TurnCount = 0;

        if (robots[1].getMovement() > robots[0].getMovement()) {
            TurnCount++;
        }

        //Powerups starten
        int numberOfPowerUps = MapController.getAmountOfThese(map.getPowerUpChar(), map);
        int[] locationsOfPowerUps = MapController.getLocationOfThese(map.getPowerUpChar(), map, numberOfPowerUps);

        PowerUp[] powerUps = new PowerUp[numberOfPowerUps];
        int i = 0;
        for (int location : locationsOfPowerUps) {
            int x;
            int y;

            x = location % map.getMaxX();


            y = location / (map.getMaxTotal() / map.getMaxX());

            powerUps[i] = new PowerUp(x, y);
            i++;
        }




//-------------------------------                        Walls-----------------------------
        int numberOfWalls = MapController.getAmountOfThese(map.getWallChar(), map);
        int[] locationOfWalls = MapController.getLocationOfThese(map.getWallChar(), map, numberOfWalls);

        Walls[] walls = new Walls[numberOfWalls];
        int j = 0;
        for (int location : locationOfWalls) {
            int x;
            int y;


            x = location % map.getMaxX();
            y = location / map.getMaxX();

            walls[j] = new Walls(x, y);
            j++;
        }
        //                  Roboter------------------------------------------------------------------------------
        List<Move> initialMoves = game.getMoves();

        List<io.swagger.client.RobotWarsClientSideThings.Move.Move> initialMovesUnpacked = GameController.unpackMoves(initialMoves, users);
        String firstMoveRobot = initialMovesUnpacked.getFirst().getRobotID();
        String secondMoveRobot = initialMovesUnpacked.getLast().getRobotID();
        GameController.getRobotById(robots, firstMoveRobot ).setCoords1D(initialMovesUnpacked.getFirst().getMapIndex().intValue(), map.getMaxX());
        GameController.getRobotById(robots,secondMoveRobot).setCoords1D(initialMovesUnpacked.getLast().getMapIndex().intValue(), map.getMaxX());


        // alignment und User ID --> Robot Id
        for (io.swagger.client.RobotWarsClientSideThings.Move.Move move : initialMovesUnpacked) {
            System.out.println("Move Id: " + move.getRobotID());
            System.out.println("Player Id: " + move.getMoveType());
            System.out.println("Movement Type: " + move.getAlignment());
            System.out.println("Alignment: " + move.getMapIndex());
        }
        map.setSpace1D(robots[0].getCoords1D(), robots[0].getAvatar());
        map.setSpace1D(robots[1].getCoords1D(), robots[1].getAvatar());


        GameController.executeMoves(initialMovesUnpacked,robots, walls, map);





        //Intro abspielen
        GameView.intro(users[0].getName(), users[1].getName());

        //Während niemand das Spiel gewonnen hat

        while (!GameValidationController.checkWin(robots)) {
            MapView.drawMap(map);
            if (users[(TurnCount % robots.length)].getName().equals("You")){
                GameController.takeTurn(game.getId(), map, users[(TurnCount % robots.length)], robots[TurnCount % robots.length], robots, powerUps, walls, api);

            }else{
                GameController.waitEnemyTurn(game.getId(), map, users, robots[TurnCount % robots.length], robots, powerUps, walls, api, getLastMoveId(api, game));
            }

            TurnCount++;
            for (PowerUp powerUp : powerUps) {
                PowerUpController.update(powerUp);
            }
        }
        //Nachdem einer gewonnen hat
        System.out.println();
        String winner = GameController.getWinner(robots[0], robots[1], users[0], users[1]);
        GameView.printWinMessage(winner);
    }

    public static String getLastMoveId(DefaultApi api, Game game) throws ApiException {
        List<Move> allMoves = api.apiGamesGameIdMoveGet(game.getId());
        return allMoves.getLast().getId();
    }


}