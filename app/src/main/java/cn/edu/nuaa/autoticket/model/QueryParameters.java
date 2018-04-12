/**
 * @ClassName: QueryParameters.java
 * @Description:
 * @author Meteor
 * @version V1.0
 * @Date 2018/4/9 22:44
 */
package cn.edu.nuaa.autoticket.model;

import com.google.gson.Gson;

import java.sql.Timestamp;

public class QueryParameters {
    private String    startCity;
    private String    destCity;
    private Timestamp deliverTimestamp;
    private SeatType  seatType;
    private boolean   studentFlag;
    private TrainType trainType;

    public String getStartCity() {
        return startCity;
    }

    public void setStartCity(String startCity) {
        this.startCity = startCity;
    }

    public String getDestCity() {
        return destCity;
    }

    public void setDestCity(String destCity) {
        this.destCity = destCity;
    }

    public Timestamp getDeliverTimestamp() {
        return deliverTimestamp;
    }

    public void setDeliverTimestamp(Timestamp deliverTimestamp) {
        this.deliverTimestamp = deliverTimestamp;
    }

    public SeatType getSeatType() {
        return seatType;
    }

    public void setSeatType(SeatType seatType) {
        this.seatType = seatType;
    }

    public boolean getStudentFlag() {
        return studentFlag;
    }

    public void setStudentFlag(boolean studentFlag) {
        this.studentFlag = studentFlag;
    }

    public TrainType getTrainType() {
        return trainType;
    }

    public void setTrainType(TrainType trainType) {
        this.trainType = trainType;
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
