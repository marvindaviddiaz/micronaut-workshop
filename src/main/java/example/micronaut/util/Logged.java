package example.micronaut.util;

import example.micronaut.service.LoggingInterceptor;
import io.micronaut.aop.Around;
import io.micronaut.context.annotation.Type;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Around
@Type(LoggingInterceptor.class)
public @interface Logged {
}