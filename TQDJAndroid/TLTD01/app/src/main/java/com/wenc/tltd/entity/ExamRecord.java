package com.wenc.tltd.entity;

/**
 * Created by WenC on 2016-06-16.
 */
public class ExamRecord {
    private String record;
    private String time;

    public ExamRecord() {

    }

    public ExamRecord(String record, String time) {
        this.record = record;
        this.time = time;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "ExamRecord{" +
                "record='" + record + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
