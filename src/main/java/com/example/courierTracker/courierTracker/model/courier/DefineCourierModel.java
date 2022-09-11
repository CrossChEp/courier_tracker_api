package com.example.courierTracker.courierTracker.model.courier;

public class DefineCourierModel {
    private long UserId;
    private long typeId;
    private long regionId;

    public DefineCourierModel() {
    }

    public long getId() {
        return UserId;
    }

    public void setId(long id) {
        this.UserId = id;
    }

    public long getTypeId() {
        return typeId;
    }

    public void setTypeId(long typeId) {
        this.typeId = typeId;
    }

    public long getRegionId() {
        return regionId;
    }

    public void setRegionId(long regionId) {
        this.regionId = regionId;
    }
}
