package com.personal.jw.java.spring.aop.app;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;

import java.util.Arrays;
import java.util.List;

/**
 * Created by jww on 2020/02/08.
 * Describe 日志切面.
 */
@Aspect
public class LogAspect {

    //声明一个切点，支持通配符
    @Pointcut("execution(public int com.personal.jw.java.spring.aop.app.Calculator.*(..) )")
    private void pointCut() {
    }

    ;

    //@before代表在目标方法执行切入，并指定在哪个方法前切入
    @Before("pointCut()")
    public void logStart(JoinPoint joinPoint) {
        //用joinpoint可以拿到，切面方法的方法名称、及入参
        String methodName = joinPoint.getSignature().getName();
        List args = Arrays.asList(joinPoint.getArgs());
        System.out.println("方法名称是："+methodName);
        System.out.println("除法运行……参数列表:{"+args+"}");
    }

    @After("pointCut()")
    public void logEnd() {
        System.out.println("除法结束……");
    }

    //有返回值的时候调用
    @AfterReturning(value = "pointCut()", returning = "result")
    public void logReturn(Object result) {

        System.out.println("除法正常……运行结果是：{"+result+"}");
    }

    //抛出异常的时候调用
    @AfterThrowing(value = "pointCut()", throwing = "exception")
    public void logException(Exception exception) {
        System.out.println("除法运行异常……异常信息是：{" + exception + "}");

    }

    @Around("pointCut()")
    public Object Around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("around_before……");
        Object obj = proceedingJoinPoint.proceed();
        System.out.println("around_after……");
        return obj;
    }
}
