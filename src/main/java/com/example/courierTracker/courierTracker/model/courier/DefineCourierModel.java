package com.example.courierTracker.courierTracker.model.courier;

public class DefineCourierModel {
    private long UserId;
    private long typeId;
    private long regionId;
    private TimeTableAddModel timeTable;

    public DefineCourierModel() {
    }

    public TimeTableAddModel getTimeTable() {
        return timeTable;
    }

    public void setTimeTable(TimeTableAddModel timeTable) {
        this.timeTable = timeTable;
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
