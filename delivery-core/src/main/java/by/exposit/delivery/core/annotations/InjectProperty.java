package by.exposit.delivery.core.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Annotation used at the field that indicates the value of field should be inject.
 *
 * @see by.exposit.delivery.core.configurators.InjectPropertyAnnotationObjectConfiguratorImpl
 */
@Documented
@Target(value = {FIELD})
@Retention(value = RUNTIME)
public @interface InjectProperty {
    String value() default "";
}
