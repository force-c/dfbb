package com.yasso.dfbb.aop;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.yasso.dfbb.annotation.Log;
import com.yasso.dfbb.utils.IPUtil;
import com.yasso.dfbb.utils.JackSonUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * @author guochaung
 * @version 1.0
 * @date 2020/12/19 17:13
 */
@Aspect
@Component
public class LogAspect {

    private static final Logger log = LoggerFactory.getLogger(LogAspect.class);

    /**
     * 定义日志操作切点
     */
    @Pointcut("@annotation(com.yasso.dfbb.annotation.Log)")
    public void logPointCut() {
    }

    /**
     * 切入点是controller包下的所有方法
     */
    @Pointcut("execution(* com.yasso.dfbb.web..*.*(..))")
    public void exceptionLogPointCut() {
    }


    /**
     * 正常返回通知，如果切入点抛出异常，不会执行
     *
     * @param joinPoint 切入点
     * @param response  返回结果
     */
    @AfterReturning(value = "logPointCut()", returning = "response")
    public void saveLog(JoinPoint joinPoint, Object response) {

        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Log customLog = method.getAnnotation(Log.class);
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = method.getName();
        methodName = className + "." + methodName + "()";

        //请求URL
        String requestURI = request.getRequestURI();
        //操作IP
        String operIP = IPUtil.getIpFromRequest(request);
        //请求参数
        Object[] args = joinPoint.getArgs();

//        log.info("方法:{}, 入参:{}, uri:{}, 请求ip:{}, 响应值：{}", methodName, args, requestURI, operIP, response);
        this.processRes(methodName, args, requestURI, operIP, response);
    }

    @SneakyThrows
    private void processRes(String methodName, Object[] args, String requestURI, String operIP, Object response){
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("方法", methodName);
        hashMap.put("入参", args);
        hashMap.put("uri", requestURI);
        hashMap.put("operIP", operIP);
        hashMap.put("response", response);
        String json = JackSonUtil.objectMapper().writeValueAsString(hashMap);
        log.info(json);
    }

    @AfterThrowing(pointcut = "exceptionLogPointCut()", throwing = "error")
    public void saveException(JoinPoint joinPoint, Throwable error) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = signature.getMethod().getName();


        methodName = className + "." + methodName + "()";

        String requestURI = request.getRequestURI();
        String operIP = IPUtil.getIpFromRequest(request);
        Object[] args = joinPoint.getArgs();
        String exceptionName = error.getClass().getName();
        String errorMessage = stackTraceToString(exceptionName, error.getMessage(), error.getStackTrace());

        log.error("方法:{}, 入参:{}, uri:{}, 请求ip:{}, 异常信息：{}", methodName, args, requestURI, operIP, errorMessage);

    }

    /**
     * 获取堆栈信息
     * @param exceptionName 异常名称
     * @param exceptionMessage 异常信息
     * @param stackTraceElements 异常元素
     * @return 堆栈信息
     */
    private String stackTraceToString(String exceptionName, String exceptionMessage, StackTraceElement[] stackTraceElements) {
        StringBuffer stringBuffer = new StringBuffer();
        for (StackTraceElement stackTraceElement : stackTraceElements) {
            stringBuffer.append(stackTraceElement).append("\n");
        }
        //String message = exceptionName + ":" + exceptionMessage + "\n\t" + stringBuffer.toString();
        String message = exceptionName + ":" + exceptionMessage + "\n\t";
        return message;
    }

    public static void main(String[] args) {

    }

}
