package com.dfrobot.angelo.blunobasicdemo.Pojo;

import java.util.Date;

public class ParkingMessage {

    Integer zoneId;
    Integer parkinglotId;
    Integer parkingSpaceId ;
    Integer userId ;
    Date msgTime;
    Integer status;

    public Integer getZoneId() {
        return zoneId;
    }

    public void setZoneId(Integer zoneId) {
        this.zoneId = zoneId;
    }

    public Integer getParkinglotId() {
        return parkinglotId;
    }

    public void setParkinglotId(Integer parkinglotId) {
        this.parkinglotId = parkinglotId;
    }

    public Integer getParkingSpaceId() {
        return parkingSpaceId;
    }

    public void setParkingSpaceId(Integer parkingSpaceId) {
        this.parkingSpaceId = parkingSpaceId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getMsgTime() {
        return msgTime;
    }

    public void setMsgTime(Date msgTime) {
        this.msgTime = msgTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
