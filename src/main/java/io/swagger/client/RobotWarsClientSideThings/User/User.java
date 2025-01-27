package io.swagger.client.RobotWarsClientSideThings.User;

public class User {
    protected String name;
    protected String userId;
    protected String robotId;
    //------------------------------------Holt sich Input vom Nutzer, braucht eine Nachricht zum Auffordern-------------------------------------------------------

    public User() {

    }

    public String getRobotId() {
        return robotId;
    }

    public void setRobotId(String robotId) {
        this.robotId = robotId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
