package com.dfrobot.angelo.blunobasicdemo.Pojo;

import java.util.Date;

public class User {
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLicense_number() {
        return license_number;
    }

    public void setLicense_number(Integer license_number) {
        this.license_number = license_number;
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

    private Integer id;
    private Integer license_number;
    private Date createTime;
    private Date modifiedTime;
}
