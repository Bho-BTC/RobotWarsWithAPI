package io.swagger.client.RobotWarsClientSideThings.Game;

import io.swagger.client.ApiException;
import io.swagger.client.RobotWarsClientSideThings.Maps.Map;
import io.swagger.client.RobotWarsClientSideThings.Maps.MapView;
import io.swagger.client.RobotWarsClientSideThings.Move.Move;
import io.swagger.client.RobotWarsClientSideThings.Obstacles.Walls;
import io.swagger.client.RobotWarsClientSideThings.PowerUps.PowerUp;
import io.swagger.client.RobotWarsClientSideThings.PowerUps.PowerUpController;
import io.swagger.client.RobotWarsClientSideThings.RobotPowerUp.RobotPowerUpController;
import io.swagger.client.RobotWarsClientSideThings.Robots.Robot;
import io.swagger.client.RobotWarsClientSideThings.Robots.RobotController;
import io.swagger.client.RobotWarsClientSideThings.Robots.RobotView;
import io.swagger.client.RobotWarsClientSideThings.Robots.alignment;
import io.swagger.client.RobotWarsClientSideThings.User.User;
import io.swagger.client.api.DefaultApi;
import io.swagger.client.model.Align;
import io.swagger.client.model.MovementType;
import io.swagger.client.model.NewMove;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GameController {

    public static void takeTurn(String gameId, Map map, User mainUser, Robot mainRobot, Robot[] robots, PowerUp[] powerUps, Walls[] walls, DefaultApi api) throws ApiException {
        mainRobot.setMovesLeft(mainRobot.getMovement());
        mainRobot.setHasAttackedThisRound(false);
        RobotPowerUpController.updateBuffs(mainRobot);
        io.swagger.client.model.Move newMove = null;
        while (mainRobot.getMovesLeft() > 0 && !GameValidationController.checkWin(robots)) {
            System.out.println("Postition: " + mainRobot.getX() + " / " + mainRobot.getY());
            newMove = GameController.makeMove(gameId, mainRobot, robots, mainUser, map, powerUps, walls, api);

            mainRobot.setMovesLeft(mainRobot.getMovesLeft() - 1);
        }
        if (mainRobot.getMovesLeft() == 0 && newMove.getMovementType() != MovementType.END) {
            try {
                api.apiGamesGameIdMovePlayerPlayerIdPost(makeEndMove(newMove, mainUser), gameId, mainUser.getUserId());
            } catch (ApiException e) {
                System.out.println(e.getResponseBody());
            }

        }
    }



    public static Robot notTurningRobot (Robot turningRobot, Robot[] robots){
        for (Robot robot: robots){
            if (robot != turningRobot){
                return robot;
            }
        }
        return null;
    }


    public static io.swagger.client.model.Move makeMove(String gameId, Robot turningRobot, Robot[] robots, User user, Map map, PowerUp[] powerUps, Walls[] walls, DefaultApi api) throws ApiException {
        Move newMove = new Move();
        newMove.setRobotID(turningRobot.getRobotId());

        RobotView.printStats2Robots(turningRobot, notTurningRobot(turningRobot, robots));
        MovementType actionType = GameView.getActionType(user.getName(), turningRobot, robots, walls, map);
        newMove.setMoveType(actionType);

        switch (actionType) {
            //Bewegen
            case MOVE:
                newMove.setAlignment(turningRobot.getAlignment());
                break;

            //Angreifen
            case ATTACK:
                newMove.setAlignment(turningRobot.getAlignment());
                newMove.setMapIndex(BigDecimal.valueOf(turningRobot.getMapIndex(map.getMaxX())));
                break;
            //Manuell Ausrichten
            case ALIGN:
                newMove.setAlignment(GameView.getAlignDirection(user.getName()));
                newMove.setMapIndex(BigDecimal.valueOf(turningRobot.getMapIndex(map.getMaxX())));
                break;

            case END:
                newMove.setAlignment(turningRobot.getAlignment());
                newMove.setMapIndex(BigDecimal.valueOf(turningRobot.getMapIndex(map.getMaxX())));
                break;
        }

        executeMove(newMove, turningRobot, robots, walls, map);
//        System.out.println("Robot ID: " + newMove.getRobotID());
//        System.out.println("Movement Type: " + newMove.getMoveType());
//        System.out.println("Alignment: " + newMove.getAlignment());
//        System.out.println("Map Index: " + newMove.getMapIndex());

        NewMove apiNewMove = formatMoveToApiNewMove(newMove, user);
//        System.out.println("Player ID: " + apiNewMove.getPlayerId());
//        System.out.println("Movement Type: " + apiNewMove.getMovementType());
//        System.out.println("Alignment: " + apiNewMove.getAlign());
//        System.out.println("Map Index: " + apiNewMove.getMapIndex());


        checkPowerUp(turningRobot, powerUps);

        MapView.drawMap(map);
        System.out.println();


        try {
            return api.apiGamesGameIdMovePlayerPlayerIdPost(apiNewMove, gameId, user.getUserId());
        } catch (ApiException e) {
            System.out.println(e.getResponseBody());
        }

        return null;


    }


    public static void waitEnemyTurn(String gameId, Map map, User[] users, Robot mainRobot, Robot[] robots, PowerUp[] powerUps, Walls[] walls, DefaultApi api, String lastMoveId) throws ApiException {
        RobotPowerUpController.updateBuffs(mainRobot);
        mainRobot.setMovesLeft(mainRobot.getMovement());
        mainRobot.setHasAttackedThisRound(false);
        List<io.swagger.client.model.Move> movesList;
        System.out.println("Postition: " + mainRobot.getX() + " / " + mainRobot.getY());
        RobotView.printStats(mainRobot);
        while (mainRobot.getMovesLeft() > 0 && !GameValidationController.checkWin(robots)) {
            try {
                movesList = api.apiGamesGameIdMoveMoveIdAfterGet(gameId, lastMoveId);
                if ((movesList.size()) > 1) {
                    List<Move> unpackedMoves = unpackMoves(movesList, users);
                    for (Move move : unpackedMoves) {
                        if(!move.getMoveId().equals(lastMoveId)){
                            mainRobot.setMovesLeft(mainRobot.getMovesLeft() - 1);
                            executeMove(move, mainRobot, robots, walls, map);
                            checkPowerUp(mainRobot, powerUps);
                            MapView.drawMap(map);
                            System.out.println("Postition: " + mainRobot.getX() + " / " + mainRobot.getY());
                            RobotView.printStats(mainRobot);
                            System.out.println();
                        }
                    }
                    lastMoveId = movesList.getLast().getId();
                    movesList.clear();
                }
                Thread.sleep(3000);
            } catch (ApiException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }


    }


    public static List<Move> unpackMoves(List<io.swagger.client.model.Move> movesList, User[] users) {
        List<Move> unpackedMoves = new ArrayList<>();

        for (io.swagger.client.model.Move move : movesList) {
            Move unpackedMove = new Move();
            unpackedMove.setMoveId(move.getId());
            unpackedMove.setMoveType(move.getMovementType());
            unpackedMove.setRobotID(getRobotIdFromPlayer(users, move.getPlayerId()));
            unpackedMove.setAlignment(invertAlign(move.getAlign()));
            unpackedMove.setMapIndex(move.getMapIndex());
            unpackedMoves.add(unpackedMove);
        }
        return unpackedMoves;
    }


    public static String getRobotIdFromPlayer(User[] users, String userId) {
        for (User user : users) {
//            System.out.println("user:" + user.getName());
//            System.out.println("userId:" + userId);
//            System.out.println("robot ID: " + user.getRobotId());
            if (user.getUserId().equals(userId)) {
//                System.out.println("user found");
                return user.getRobotId();
            }
//            System.out.println("user not found");
        }
        return null;
    }


    public static void executeMoves(List<Move> moves2Execute, Robot[] robots, Walls[] walls, Map map) {
        for (Move move : moves2Execute) {
            executeMove(move, getIngameRobotById(robots, move.getRobotID()), robots, walls, map);
        }

    }

    public static Robot getIngameRobotById(Robot[] robots, String id) {
        for (Robot robot : robots) {
            if (id.equals(robot.getRobotId())) {
                return robot;
            }
        }
        return null;
    }


    public static Align invertAlignment(alignment alignment) {
        for (Align align : Align.values()) {
            if (align.getValue().equals(alignment.freddyKey)) {
                return align;
            }
        }
        return null;
    }

    public static alignment invertAlign(Align align) {
        for (alignment alignment : alignment.values()) {
            if (alignment.freddyKey.equals(align.getValue())) {
                return alignment;
            }
        }
        return null;
    }


    public static NewMove formatMoveToApiNewMove(Move move, User user) {
        NewMove newMove = new NewMove();
        newMove.playerId(user.getUserId());
        newMove.movementType(move.getMoveType());
        newMove.align(invertAlignment(move.getAlignment()));
        newMove.mapIndex(move.getMapIndex());
        return newMove;

    }

    public static NewMove makeEndMove(io.swagger.client.model.Move move, User user) {
        NewMove newMove = new NewMove();
        newMove.playerId(user.getUserId());
        newMove.movementType(MovementType.END);
        newMove.align(move.getAlign());
        newMove.mapIndex(move.getMapIndex());
        return newMove;

    }

    public static void executeMove(Move move, Robot turningRobot, Robot[] robots, Walls[] walls, Map map) {
        switch (move.getMoveType()) {
            case MOVE:
                map.setSpaceYX(turningRobot.getX(), turningRobot.getY(), ' ');
                RobotController.moveRobot(turningRobot, move.getAlignment(), map.getMaxX());
                map.setSpaceYX(turningRobot.getX(), turningRobot.getY(), turningRobot.getAvatar());
                move.setMapIndex(BigDecimal.valueOf(turningRobot.getMapIndex(map.getMaxX())));
                break;

            case ATTACK:
                RobotController.attack(turningRobot, robots, walls);
                break;

            case ALIGN:
                RobotController.align(turningRobot, move.getAlignment());
                break;

            case END:
                RobotController.endTurn(turningRobot);
                break;
        }
    }


    public static void checkPowerUp(Robot robot, PowerUp[] powerUps) {
        for (PowerUp powerUp : powerUps) {
            if (powerUp.isOnField() && powerUp.getY() == robot.getY() && powerUp.getX() == robot.getX()) {
                PowerUpController.pickedUpBy(robot, powerUp);
            }
        }
    }

    public static String getWinner(Robot robot1, Robot robot2, User user1, User user2) {
        if (robot1.getCurrentHp() < 1 && robot1.getCurrentHp() < robot2.getCurrentHp()) {
            return user2.getName();
        } else if (robot2.getCurrentHp() < 1 && robot2.getCurrentHp() < robot1.getCurrentHp()) {
            return user1.getName();
        } else {
            return "unentschieden";
        }
    }

    public static Robot getRobotById(Robot[] robots, String id) {
        for (Robot robot : robots) {
            if (id.equals(robot.getRobotId())) {
                return robot;
            }
        }
        return null;
    }
}
