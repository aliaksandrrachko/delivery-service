package by.exposit.delivery.core.configurators;

import by.exposit.delivery.core.intefaces.ProxyConfigurator;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InvocationHandler;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public abstract class AbstractProxyConfigurator implements ProxyConfigurator {

    /**
     * Replaces implement's class to specify proxy object
     * if {@link #checkConditionsForWrappingInProxy(Class)} returns {@code true}.
     *
     * If class implements interfaces, will be creat {@code Dynamic Proxy}, or else {@code CGLIB} proxy.
     * @param t the original object
     * @param implClass the implements class
     * @param <T> any type
     * @return proxy objects if method has {@link Deprecated} annotation, or else original object.
     */
    @Override
    public <T> Object replaceWithProxyIfNeeded(Object t, Class<T> implClass) {
        if (checkConditionsForWrappingInProxy(implClass)) {
            if (implClass.getInterfaces().length == 0){
                return Enhancer.create(implClass,
                        (InvocationHandler) (o, method, args) -> getInvocationHandlerLogic(t, method, args, implClass));
            }
            return Proxy.newProxyInstance(implClass.getClassLoader(), implClass.getInterfaces(),
                    (proxy, method, args) -> getInvocationHandlerLogic(t, method, args, implClass));
        } else {
            return t;
        }
    }

    /**
     * Checks conditions for wrapping object in The Proxy
     * @param implClass the class
     * @return true if object needs wrap in The Proxy, or else false
     */
    protected abstract <T> boolean checkConditionsForWrappingInProxy(Class<T> implClass);

    /**
     * Contains invocation handler logics.
     * @param t the original object
     * @param method invoked method
     * @param args original's method arguments
     */
    protected abstract Object getInvocationHandlerLogic(Object t, Method method, Object[] args, Class<?> implClass)
            throws IllegalAccessException, InvocationTargetException;
}
