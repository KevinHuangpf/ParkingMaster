package com.dfrobot.angelo.blunobasicdemo.Pojo;

import java.sql.Timestamp;
import java.util.Date;

public class Parkinglot {


    private Integer id;



    private Integer parkinglotId;
    private Integer zoneId;
    private  Double fee;
    private Integer spaceTotal;
    private Integer spaceAvailable;

    private Date createTime;
    private Date modifiedTime;

    public Integer getParkinglotId() {
        return parkinglotId;
    }

    public void setParkinglotId(Integer parkinglotId) {
        this.parkinglotId = parkinglotId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getZoneId() {
        return zoneId;
    }

    public void setZoneId(Integer zoneId) {
        this.zoneId = zoneId;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public Integer getSpaceTotal() {
        return spaceTotal;
    }

    public void setSpaceTotal(Integer spaceTotal) {
        this.spaceTotal = spaceTotal;
    }

    public Integer getSpaceAvailable() {
        return spaceAvailable;
    }

    public void setSpaceAvailable(Integer spaceAvailable) {
        this.spaceAvailable = spaceAvailable;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }



}
