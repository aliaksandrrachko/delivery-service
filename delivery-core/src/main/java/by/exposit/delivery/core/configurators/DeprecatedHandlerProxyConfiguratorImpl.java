package by.exposit.delivery.core.configurators;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Replaces implement's class to specify proxy object if any method has {@link Deprecated} annotation.
 */
@Slf4j
public class DeprecatedHandlerProxyConfiguratorImpl extends AbstractProxyConfigurator {

    @Override
    protected <T> boolean checkConditionsForWrappingInProxy(Class<T> implClass) {
        return implClass.isAnnotationPresent(Deprecated.class);
    }

    @Override
    protected Object getInvocationHandlerLogic(Object t, Method method, Object[] args)
            throws IllegalAccessException, InvocationTargetException {
        log.warn("You using deprecated method: {}", method.getName());
        return method.invoke(t, args);
    }
}
