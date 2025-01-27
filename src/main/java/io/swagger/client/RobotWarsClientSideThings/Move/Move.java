package io.swagger.client.RobotWarsClientSideThings.Move;

import io.swagger.client.RobotWarsClientSideThings.Robots.alignment;
import io.swagger.client.model.MovementType;

import java.math.BigDecimal;

public class Move {
    private String moveId;
    private MovementType moveType;
    private String RobotID;
    private alignment alignment;
    private BigDecimal mapIndex;


    public String getMoveId() {
        return moveId;
    }

    public void setMoveId(String moveId) {
        this.moveId = moveId;
    }

    public MovementType getMoveType() {
        return moveType;
    }

    public String getRobotID() {
        return RobotID;
    }

    public BigDecimal getMapIndex() {
        return mapIndex;
    }

    public void setMapIndex(BigDecimal mapIndex) {
        this.mapIndex = mapIndex;
    }


    public alignment getAlignment() {
        return alignment;
    }

    public void setMoveType(MovementType moveType) {
        this.moveType = moveType;
    }

    public void setRobotID(String robotID) {
        this.RobotID = robotID;
    }

    public void setAlignment(alignment alignment) {
        this.alignment = alignment;
    }
}

