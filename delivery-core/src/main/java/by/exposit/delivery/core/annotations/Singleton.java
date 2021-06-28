package by.exposit.delivery.core.annotations;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Scopes a single bean definition to a single object instance per IoC container.
 *
 * @see by.exposit.delivery.core.ApplicationContext
 * @see by.exposit.delivery.core.ApplicationContext#getObject(Class)
 */
@Documented
@Target(value = {TYPE})
@Retention(value = RUNTIME)
public @interface Singleton {
}
