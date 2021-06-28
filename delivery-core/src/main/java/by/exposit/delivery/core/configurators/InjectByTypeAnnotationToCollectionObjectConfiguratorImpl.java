package by.exposit.delivery.core.configurators;

import by.exposit.delivery.core.ApplicationContext;
import by.exposit.delivery.core.annotations.InjectByType;
import by.exposit.delivery.core.intefaces.ObjectConfigurator;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Presents {@link ObjectConfigurator}
 *
 * @see ObjectConfigurator
 * @see InjectByType
 */
public class InjectByTypeAnnotationToCollectionObjectConfiguratorImpl implements ObjectConfigurator {

    /**
     * Injects objects into collection's fields marked {@link InjectByType}, using {@link InjectByType#aClass()}.
     * @param t object for configuration
     * @param context the application context
     */
    @Override
    @SneakyThrows
    public void configure(Object t, ApplicationContext context) {
        for (Field field : t.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(InjectByType.class) && typeImplListInterface(field)) {
                String typeName = StringUtils.substringBetween(field.getGenericType().getTypeName(), "<", ">");
                InjectByType annotation = field.getAnnotation(InjectByType.class);
                if (!typeName.equals(annotation.aClass().getName())) {
                    throw new IllegalArgumentException(
                            "Generic type mismatch in class " + t.getClass() + " " + typeName +
                                    " and " + annotation.aClass().getName() + ".");
                }
                List<Object> objectsToInject = context.getObjects(annotation.aClass());
                Collection<Object> result = convertToFiledType(objectsToInject, field.getType());
                field.setAccessible(true);
                field.set(t, result);
            }
        }
    }

    private boolean typeImplListInterface(Field field) {
        for (Class<?> anInterface : field.getType().getInterfaces()) {
            if (anInterface.equals(Collection.class)) {
                return true;
            }
        }
        return false;
    }

    private Collection<Object> convertToFiledType(List<Object> source, Class<?> fieldType){
        if (fieldType.equals(List.class) || fieldType.equals(Queue.class)){
            return source;
        } else if (fieldType.equals(Set.class)){
            return source.parallelStream().collect(Collectors.toSet());
        } else {
            throw new IllegalArgumentException("Unknown type of collection " + fieldType + ", teach me how to do it.");
        }
    }
}
