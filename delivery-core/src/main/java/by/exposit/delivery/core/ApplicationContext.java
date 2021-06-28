package by.exposit.delivery.core;

import by.exposit.delivery.core.annotations.Singleton;
import by.exposit.delivery.core.intefaces.Config;
import lombok.Setter;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * This class presents IoC container. Analogue Spring's framework {@code ApplicationContext}.
 */
public class ApplicationContext {

    @Setter
    private ObjectFactory factory;

    /**
     * Map of Beans (all singleton classes, marked {@link Singleton})
     */
    private final Map<Class<?>, Object> cache = new ConcurrentHashMap<>();

    private final Config config;

    public ApplicationContext(Config config) {
        this.config = config;
    }

    public <T> Object getObject(Class<T> type){
        Class<? extends T> implClass = type;
        if (this.cache.containsKey(type)){
            return this.cache.get(type);
        }

        if (type.isInterface()){
            implClass = (Class<? extends T>) this.config.getImplClass(type);
        }

        T t = this.factory.createObject(implClass);

        if (implClass.isAnnotationPresent(Singleton.class)){
            this.cache.put(type, t);
        }
        return t;
    }

   public <T> List<Object> getObjects(Class<T> type){
        List<Class<? extends T>> implClasses = new LinkedList<>();
        if (this.cache.containsKey(type)){
            implClasses.add((Class<? extends T>) this.cache.get(type));
        }

        if (type.isInterface()){
            implClasses.addAll(this.config.getImplClasses(type));
        }

        List<Object> result = new LinkedList<>();
       for (Class<? extends T> implClass : implClasses) {
           T t = this.factory.createObject(implClass);
           result.add(t);
       }
       return result;
    }

    public Config getConfig() {
        return this.config;
    }
}
