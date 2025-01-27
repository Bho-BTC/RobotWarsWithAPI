package io.swagger.client.Client;

import io.swagger.client.ApiException;
import io.swagger.client.api.DefaultApi;

import java.util.List;
import java.util.Map;

public class MapService {
    public static String getFirstMapId(DefaultApi apiInstance) throws ApiException {
        List<Map> jsonResponse = apiInstance.apiMapsGet();
        if (jsonResponse == null || jsonResponse.isEmpty()) {
            System.out.println("Keine Daten gefunden.");
            return null;
        }
        Map mapData = jsonResponse.getFirst();

        return (String) mapData.get("id");
    }

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




}
