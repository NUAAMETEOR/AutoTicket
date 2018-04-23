package cn.edu.nuaa.autoticket.model;

import java.lang.reflect.Field;

public class TicketInformation {
    private String trainId;
    private String startTime;
    private String endTime;
    private String totalTime;
    private String startDate;
    private String seatSoft;//软座
    private String seatSoft_;//软卧
    private String seatHard;//硬座
    private String seatHard_;//硬卧
    private String seat_normal;//二等座
    private String seat_advanced;//一等座
    private String seat_high_;//动卧
    private String seat_commercial;// 商务座|特等座
    private String seat_advance_soft_;//高级软卧
    private String seat_null;//无座
    private String seat_other;//其他

    public void setEmptyInformation() {
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field f :
                fields) {
            try {
                if (f.getType() == String.class) {
                    String val = (String) f.get(this);
                    if (val == null || val.length() == 0) {
                        f.set(this, "--");
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public String getTrainId() {
        return trainId;
    }

    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(String totalTime) {
        this.totalTime = totalTime;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getSeatSoft() {
        return seatSoft;
    }

    public void setSeatSoft(String seatSoft) {
        this.seatSoft = seatSoft;
    }

    public String getSeatSoft_() {
        return seatSoft_;
    }

    public void setSeatSoft_(String seatSoft_) {
        this.seatSoft_ = seatSoft_;
    }

    public String getSeatHard() {
        return seatHard;
    }

    public void setSeatHard(String seatHard) {
        this.seatHard = seatHard;
    }

    public String getSeatHard_() {
        return seatHard_;
    }

    public void setSeatHard_(String seatHard_) {
        this.seatHard_ = seatHard_;
    }

    public String getSeat_normal() {
        return seat_normal;
    }

    public void setSeat_normal(String seat_normal) {
        this.seat_normal = seat_normal;
    }

    public String getSeat_advanced() {
        return seat_advanced;
    }

    public void setSeat_advanced(String seat_advanced) {
        this.seat_advanced = seat_advanced;
    }

    public String getSeat_high_() {
        return seat_high_;
    }

    public void setSeat_high_(String seat_high_) {
        this.seat_high_ = seat_high_;
    }

    public String getSeat_commercial() {
        return seat_commercial;
    }

    public void setSeat_commercial(String seat_commercial) {
        this.seat_commercial = seat_commercial;
    }

    public String getSeat_advance_soft_() {
        return seat_advance_soft_;
    }

    public void setSeat_advance_soft_(String seat_advance_soft_) {
        this.seat_advance_soft_ = seat_advance_soft_;
    }

    public String getSeat_null() {
        return seat_null;
    }

    public void setSeat_null(String seat_null) {
        this.seat_null = seat_null;
    }

    public String getSeat_other() {
        return seat_other;
    }

    public void setSeat_other(String seat_other) {
        this.seat_other = seat_other;
    }
}
