package io.swagger.client.RobotWarsClientSideThings.PowerUps;


public class PowerUp {
    private int x = 0;
    private int y = 0;
    private final char avatar = 63;
    private int respawnDuration = 5;
    private boolean onField = true;
    private int roundsToRespawn = 5;


    public PowerUp(int x, int y) {
        this.x = x;
        this.y = y;
    }



    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public char getAvatar() {
        return avatar;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getRespawnDuration() {
        return respawnDuration;
    }

    public void setRespawnDuration(int respawnDuration) {
        this.respawnDuration = respawnDuration;
    }

    public boolean isOnField() {
        return onField;
    }

    public void setOnField(boolean onField) {
        this.onField = onField;
    }


    public int getRoundsToRespawn() {
        return roundsToRespawn;
    }

    public void setRoundsToRespawn(int roundsToRespawn) {
        this.roundsToRespawn = roundsToRespawn;
    }
}
