package by.exposit.delivery.core.intefaces;

import by.exposit.delivery.core.ApplicationContext;

/**
 * This class is analogue Spring's BeanPostProcessor.
 * Configures objects for use.
 */
public interface ObjectConfigurator {

    /**
     * Configures object (ex. inject properties, inject fields ect.)
     * @param t object for configuration
     * @param context the application context
     */
    void configure(Object t, ApplicationContext context);
}
