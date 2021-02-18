package example.micronaut.service;

import io.micronaut.aop.MethodInterceptor;
import io.micronaut.aop.MethodInvocationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;

@Singleton
public class LoggingInterceptor implements MethodInterceptor<Object, Object> {
    @Override
    public Object intercept(MethodInvocationContext<Object, Object> context) {
        Logger log = LoggerFactory.getLogger(context.getDeclaringType());
        log.trace("Interceptor Executing : {}", context.getExecutableMethod());
        try {
            Object result = context.proceed();
            log.trace("Interceptor Method {} resulted in : {}", context.getExecutableMethod(), result);
            return result;
        } catch(RuntimeException e) {
            log.trace("Interceptor Method {} caused exception : {}",
                    context.getExecutableMethod(),
                    e.getMessage());
            throw e;
        }
    }
}