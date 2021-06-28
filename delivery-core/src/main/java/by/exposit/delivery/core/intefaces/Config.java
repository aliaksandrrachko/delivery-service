package by.exposit.delivery.core.intefaces;

import org.reflections.Reflections;

import java.util.Set;

public interface Config {

    /**
     * Searches and returns the class implementing giving interface
     * @param <T> type of interface
     * @param ifc a interface
     * @return the class implementing giving interface
     */
    <T> Class<?> getImplClass(Class<T> ifc);

    /**
     * Searches and returns all found classes implementing giving interface
     * @param <T> type of interface
     * @param ifc a interface
     * @return set of classes implementing giving interface
     */
    <T> Set<Class<? extends T>> getImplClasses(Class<T> ifc);

    /**
     * Returns configuring scanner
     * @return the scanner
     */
    Reflections getScanner();
}
