package io.swagger.client.RobotWarsClientSideThings.Maps;

import java.util.Arrays;

public class Map {

    protected char[] arrayVersion;
    protected int maxX;
    protected int maxTotal;
    protected char powerUpChar = 63;
    protected char wallChar = '/';
    protected char robotChar = 'R';


    public Map(int maxX, int maxTotal) {
        this.arrayVersion = new char[maxTotal];
        Arrays.fill(arrayVersion, ' ');
        this.maxX = maxX;
        this.maxTotal = maxTotal;

    }

    public char getPowerUpChar() {
        return powerUpChar;
    }

    public char getWallChar() {
        return wallChar;
    }

    public char getRobotChar() {
        return robotChar;
    }

    public int getMaxX() {
        return maxX;
    }

    public int getMaxTotal() {
        return maxTotal;
    }

    public char getSpace(int x, int y) {
        int space = y * maxX + x;
        return arrayVersion[space-1];
    }

    public char getSpace1D(int x) {
        return arrayVersion[x];
    }

    public void setSpaceYX(int x, int y, char s) {
        int space = y * maxX + x;
        arrayVersion[space] = s;
    }

    public void setSpace1D(int x, char s) {
        arrayVersion[x] = s;
    }
}
