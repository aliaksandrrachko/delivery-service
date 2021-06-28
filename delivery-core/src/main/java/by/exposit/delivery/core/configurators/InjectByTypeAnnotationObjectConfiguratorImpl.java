package by.exposit.delivery.core.configurators;

import by.exposit.delivery.core.ApplicationContext;
import by.exposit.delivery.core.annotations.InjectByType;
import by.exposit.delivery.core.intefaces.ObjectConfigurator;
import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;

/**
 * @see ObjectConfigurator
 * @see InjectByType
 */
public class InjectByTypeAnnotationObjectConfiguratorImpl implements ObjectConfigurator {

    /**
     * Injects dependencies into field marked {@link InjectByType} annotation.
     * @param t object for configuration
     * @param context the application context
     */
    @Override
    @SneakyThrows
    public void configure(Object t, ApplicationContext context) {
        for (Field field : t.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(InjectByType.class) && !typeImplCollectionOrMapInterface(field)){
                field.setAccessible(true);
                Object object = context.getObject(field.getType());
                field.set(t, object);
            }
        }
    }

    private boolean typeImplCollectionOrMapInterface(Field field){
        for (Class<?> anInterface : field.getType().getInterfaces()) {
            if (anInterface.equals(Collection.class) || anInterface.equals(Map.class)){
                return true;
            }
        }
        return false;
    }
}
