package io.swagger.client.RobotWarsClientSideThings.Robots;

public class Robot {
    private String robotID;
    private int x;
    private int y;
    private int coords1D;
    private char avatar;
    private boolean dmgBuffActive;
    private boolean rangeBuffActive;
    private boolean movementBuffActive;

    private alignment alignment;
    private boolean hasAttackedThisRound = false;

    private int dmgBuffDuration;
    private int rangeBuffDuration;
    private int movementBuffDuration;

    private int gainedDmg;
    private int gainedRange;
    private int gainedMovement;

    private int MaxLifePoints = 1;

    private int currentHp;
    private int dmg;
    private int range;
    private int skillPoints = 0;
    private int movement;
    private int movesLeft;


    public Robot(io.swagger.client.model.Robot robotModel, char robotAvatar) {
        this.alignment = alignment.NORTH;
        this.avatar = robotAvatar;
        this.robotID = robotModel.getId();
        this.MaxLifePoints = robotModel.getHealth().intValue();
        this.currentHp = robotModel.getHealth().intValue();
        this.dmg = robotModel.getAttackDamage().intValue();
        this.range = robotModel.getAttackRange().intValue();
        this.movement = robotModel.getMovementRate().intValue();
        this.movesLeft = robotModel.getMovementRate().intValue();

    }

    public int getCoords1D() {
        return coords1D;
    }

    public void setCoords1D(int coords1D, int maxX) {
        this.coords1D = coords1D;
        if (this.y * maxX + this.x != this.coords1D) {
            this.x = this.coords1D % maxX;
            this.y = this.coords1D / maxX;
        }
    }

    public void setX(int x, int maxX) {
        this.x = x;
        this.coords1D = this.y * maxX + this.x;


    }

    public void setY(int y, int maxX) {
        this.y = y;
        this.coords1D = this.y * maxX + this.x;
    }


    public int getMapIndex(int mapSizeX) {
        return y * mapSizeX + x;
    }

    public int getMovesLeft() {
        return movesLeft;
    }

    public void setMovesLeft(int movesLeft) {
        this.movesLeft = movesLeft;
    }

    public int getX() {
        return x;
    }

    public String getRobotId() {
        return robotID;
    }

    public int getY() {
        return y;
    }

    public char getAvatar() {
        return avatar;
    }

    public int getMaxLifePoints() {
        return MaxLifePoints;
    }

    public int getCurrentHp() {
        return currentHp;
    }

    public int getDmg() {
        return dmg;
    }

    public int getRange() {
        return range;
    }

    public int getMovement() {
        return movement;
    }

    public void setAvatar(char avatar) {
        this.avatar = avatar;
    }

    public void setMaxLifePoints(int maxLifePoints) {
        MaxLifePoints = maxLifePoints;
    }

    public void setCurrentHp(int currentHp) {
        this.currentHp = currentHp;
    }

    public void setDmg(int dmg) {
        this.dmg = dmg;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public void setSkillPoints(int skillPoints) {
        this.skillPoints = skillPoints;
    }

    public int getSkillPoints() {
        return skillPoints;
    }

    public boolean isDmgBuffActive() {
        return dmgBuffActive;
    }

    public boolean isRangeBuffActive() {
        return rangeBuffActive;
    }

    public boolean isMovementBuffActive() {
        return movementBuffActive;
    }

    public void setDmgBuffActive(boolean dmgBuffActive) {
        this.dmgBuffActive = dmgBuffActive;
    }

    public void setRangeBuffActive(boolean rangeBuffActive) {
        this.rangeBuffActive = rangeBuffActive;
    }

    public void setMovementBuffActive(boolean movementBuffActive) {
        this.movementBuffActive = movementBuffActive;
    }

    public void setMovement(int movement) {
        this.movement = movement;
    }

    public int getGainedDmg() {
        return gainedDmg;
    }

    public int getGainedRange() {
        return gainedRange;
    }

    public int getGainedMovement() {
        return gainedMovement;
    }

    public void setGainedDmg(int gainedDmg) {
        this.gainedDmg = gainedDmg;
    }

    public void setGainedRange(int gainedRange) {
        this.gainedRange = gainedRange;
    }

    public void setGainedMovement(int gainedMovement) {
        this.gainedMovement = gainedMovement;
    }

    public void setDmgBuffDuration(int dmgBuffDuration) {
        this.dmgBuffDuration = dmgBuffDuration;
    }

    public void setRangeBuffDuration(int rangeBuffDuration) {
        this.rangeBuffDuration = rangeBuffDuration;
    }

    public void setMovementBuffDuration(int movementBuffDuration) {
        this.movementBuffDuration = movementBuffDuration;
    }

    public int getDmgBuffDuration() {
        return dmgBuffDuration;
    }

    public int getRangeBuffDuration() {
        return rangeBuffDuration;
    }

    public int getMovementBuffDuration() {
        return movementBuffDuration;
    }

    public boolean isHasAttackedThisRound() {
        return hasAttackedThisRound;
    }

    public void setHasAttackedThisRound(boolean hasAttackedThisRound) {
        this.hasAttackedThisRound = hasAttackedThisRound;
    }

    public alignment getAlignment() {
        return alignment;
    }

    public void setAlignment(alignment alignment) {
        this.alignment = alignment;
    }
}