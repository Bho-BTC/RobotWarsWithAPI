package io.swagger.client.Client;

import io.swagger.client.RobotWarsClientSideThings.User.User;
import io.swagger.client.model.Game;
import io.swagger.client.model.PlayerRobot;

import java.util.List;
import java.util.Objects;

public class UserService {

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






}
