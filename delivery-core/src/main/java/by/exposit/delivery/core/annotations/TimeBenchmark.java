package by.exposit.delivery.core.annotations;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Marks a method whose running time is to be measured.
 *
 * @see by.exposit.delivery.core.configurators.TimeBenchmarkHandlerProxyConfiguratorImpl
 */
@Documented
@Target(value = {METHOD})
@Retention(value = RUNTIME)
public @interface TimeBenchmark {
}
