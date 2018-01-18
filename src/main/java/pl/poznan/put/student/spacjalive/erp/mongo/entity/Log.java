package pl.poznan.put.student.spacjalive.erp.mongo.entity;

import java.time.LocalDateTime;


public class Log {

    private String log;

    private LocalDateTime dateTime;

    public Log() {
    }

    public Log(String log, LocalDateTime dateTime) {
        this.log = log;
        this.dateTime = dateTime;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
