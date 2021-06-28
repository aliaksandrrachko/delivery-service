package by.exposit.delivery.core.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Marks a field as to be autowired by dependency injection facilities.
 *
 * @see by.exposit.delivery.core.configurators.InjectByTypeAnnotationObjectConfiguratorImpl
 */
@Documented
@Target(value = {FIELD})
@Retention(value = RUNTIME)
public @interface InjectByType {

    /**
     * Class for inject or interface.
     * If you inject into collection you need add generic class.
     * TODO will be implemented in the future
     */
    Class<?> aClass() default Object.class;

    /**
     * Name a class for inject
     * TODO will be implemented in the future
     */
    String name() default "";
}
