package pl.poznan.put.student.spacjalive.erp.mongo.dao;

import pl.poznan.put.student.spacjalive.erp.mongo.entity.Log;

import java.util.List;

public interface LogDAO {

    public List<Log> getLogs();

    public void saveLog(Log log);

}
