package pl.poznan.put.student.spacjalive.erp.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.logging.Logger;

@Aspect
@Component
public class LoggingAspect {

    private Logger logger = Logger.getLogger(LoggingAspect.class.getSimpleName());


    @Pointcut("execution(* pl.poznan.put.student.spacjalive.erp.dao.*Impl.save*(..))")
    public void daoSaveActions() {}

    @Pointcut("execution(* pl.poznan.put.student.spacjalive.erp.dao.*Impl.delete*(..))")
    public void daoDeleteActions() {}

    @AfterReturning("daoSaveActions() || daoDeleteActions()")
    public void before(JoinPoint joinPoint) {

        String method = joinPoint.getSignature().toShortString();

        String message = LocalDateTime.now().toString();

        if(method.contains("save")) {
            message += "Saved: ";
        } else {
            message += "Deleted: ";
        }

        Object[] args = joinPoint.getArgs();
        for(Object obj : args) {
            message += obj.toString() + "\n";
        }

        logger.info(message);

    }

}
