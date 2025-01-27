package io.swagger.client.RobotWarsClientSideThings.Obstacles;

public abstract class Obstacles {
    int x;
    int y;
    char avatar;
    boolean onField;


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public char getAvatar() {
        return avatar;
    }

    public boolean isOnField() {
        return onField;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setAvatar(char avatar) {
        this.avatar = avatar;
    }

    public void setOnField(boolean onField) {
        this.onField = onField;
    }
}
