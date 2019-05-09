package pl.poznan.put.student.projectsmanager.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
	
	private static final Logger LOGGER = LogManager.getLogger(LoggingAspect.class);
	
	@Pointcut("execution(* pl.poznan.put.student.projectsmanager.dao.*Impl.save*(..))")
	public void daoSaveActions() {
	}
	
	@Pointcut("execution(* pl.poznan.put.student.projectsmanager.dao.*Impl.delete*(..))")
	public void daoDeleteActions() {
	}
	
	@Pointcut("execution(* pl.poznan.put.student.projectsmanager.dao.*Impl.add*(..))")
	public void daoAddActions() {
	}
	
	@AfterReturning("daoSaveActions() || daoDeleteActions() || daoAddActions()")
	public void before(JoinPoint joinPoint) {
		String method = joinPoint.getSignature().toShortString();
		String message = "";
		
		Object[] args = joinPoint.getArgs();
		for (Object obj : args) {
			message += obj.toString() + "\n";
		}
		
		LOGGER.info(method + " with args\n" + message);
	}
	
}
