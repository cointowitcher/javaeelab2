package main.Services.Interceptors;


import main.Services.Logger.LoggerInterface;

import javax.annotation.PreDestroy;
import javax.inject.Inject;
import javax.interceptor.*;

public class LoginTracking {
    @Inject
    private LoggerInterface logger;

    @PreDestroy
    private void preDestroy(InvocationContext context) throws Exception {
        logger.log("Default Login destroyed");
        context.proceed();
    }

    @AroundInvoke
    public Object aroundInvoke(InvocationContext context) throws Exception {
        logger.log("Login method invoked " + context.getMethod().toString());
        return context.proceed();
    }
}
