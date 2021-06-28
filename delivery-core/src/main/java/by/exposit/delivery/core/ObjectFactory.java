package by.exposit.delivery.core;

import by.exposit.delivery.core.annotations.PostConstruct;
import by.exposit.delivery.core.intefaces.ObjectConfigurator;
import by.exposit.delivery.core.intefaces.ProxyConfigurator;
import lombok.SneakyThrows;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * Creates objects. Analogue Spring's framework BeanFactory
 */
public class ObjectFactory {

    private final ApplicationContext context;
    private final List<ObjectConfigurator> configurators = new ArrayList<>();
    private final List<ProxyConfigurator> proxyConfigurators = new ArrayList<>();

    @SneakyThrows
    public ObjectFactory(ApplicationContext context){
        this.context = context;
        for (Class<? extends ObjectConfigurator> aClass :
                this.context.getConfig().getScanner().getSubTypesOf(ObjectConfigurator.class)) {
            configurators.add(aClass.getDeclaredConstructor().newInstance());
        }
        for (Class<? extends ProxyConfigurator> aClass :
                this.context.getConfig().getScanner().getSubTypesOf(ProxyConfigurator.class)) {
            if (!Modifier.isAbstract(aClass.getModifiers())){
                proxyConfigurators.add(aClass.getDeclaredConstructor().newInstance());
            }
        }
    }

    @SneakyThrows
    public <T> T createObject(Class<T> implClass) {
        var t = create(implClass);
        configure(t);
        invokeInit(implClass, t);
        t = wrapWithProxyIfNeeded(implClass, t);
        return t;
    }

    private <T> T wrapWithProxyIfNeeded(Class<T> implClass, T t) {
        for (ProxyConfigurator proxyConfigurator : proxyConfigurators) {
            t = (T) proxyConfigurator.replaceWithProxyIfNeeded(t, implClass);
        }
        return t;
    }

    private <T> void invokeInit(Class<T> implClass, T t) throws IllegalAccessException, InvocationTargetException {
        for (Method method : implClass.getMethods()) {
            if (method.isAnnotationPresent(PostConstruct.class)){
                method.invoke(t);
            }
        }
    }

    private <T> void configure(T t) {
        configurators.forEach(objectConfigurator -> objectConfigurator.configure(t, context));
    }

    private <T> T create(Class<T> implClass) throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        return implClass.getDeclaredConstructor().newInstance();
    }
}
