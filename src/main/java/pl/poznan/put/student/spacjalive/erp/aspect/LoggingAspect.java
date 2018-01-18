package pl.poznan.put.student.spacjalive.erp.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.poznan.put.student.spacjalive.erp.mongo.dao.LogDAO;
import pl.poznan.put.student.spacjalive.erp.mongo.entity.Log;

import java.time.LocalDateTime;
import java.util.logging.Logger;

@Aspect
@Component
public class LoggingAspect {

    @Autowired
    LogDAO logDao;

    private Logger logger = Logger.getLogger(LoggingAspect.class.getSimpleName());


    @Pointcut("execution(* pl.poznan.put.student.spacjalive.erp.dao.*Impl.save*(..))")
    public void daoSaveActions() {}

    @Pointcut("execution(* pl.poznan.put.student.spacjalive.erp.dao.*Impl.delete*(..))")
    public void daoDeleteActions() {}

    @AfterReturning("daoSaveActions() || daoDeleteActions()")
    public void before(JoinPoint joinPoint) {

        String method = joinPoint.getSignature().toShortString();

        Log log = new Log();

        String message = "";

        if(method.contains("save")) {
            message += "Zapisano: ";
        } else {
            message += "Usunięto: ";
        }

        Object[] args = joinPoint.getArgs();
        for(Object obj : args) {
            message += obj.toString() + "\n";
        }

        log.setDateTime(LocalDateTime.now());
        log.setLog(message);

        logDao.saveLog(log);
    }

}
