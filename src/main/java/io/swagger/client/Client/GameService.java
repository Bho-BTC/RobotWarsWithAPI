package io.swagger.client.Client;

import io.swagger.client.ApiException;
import io.swagger.client.api.DefaultApi;
import io.swagger.client.model.Game;
import io.swagger.client.model.NewGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameService {

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





}
