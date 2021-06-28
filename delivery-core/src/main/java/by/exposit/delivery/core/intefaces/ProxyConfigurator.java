package by.exposit.delivery.core.intefaces;

/**
 * This class is analogue Spring's BeanPostProcessor (method - {@code postProcessAfterInitialization(Object bean, String beanName)}).
 * Wraps objects in a proxy if needing, to add additional logic
 */
public interface ProxyConfigurator {

    /**
     * Replace object in context to proxy if needing (to add additional logic).
     * @param t the original object
     * @param implClass the implements class
     * @param <T> type of class
     * @return the proxy of object {@code T} if needing
     */
    <T> Object replaceWithProxyIfNeeded(Object t, Class<T> implClass);
}