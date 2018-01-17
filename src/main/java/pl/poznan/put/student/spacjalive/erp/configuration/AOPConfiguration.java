package pl.poznan.put.student.spacjalive.erp.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan("pl.poznan.put.student.spacjalive.erp")
public class AOPConfiguration {

}
