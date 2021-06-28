package by.exposit.delivery.core;

import by.exposit.delivery.core.intefaces.Config;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;

/**
 * Defines config by java classes.
 * Uses {@link Reflections} scans packages and searching classes that implement the given interface.
 *
 * @see Config
 */
@Slf4j
public class JavaConfig implements Config {

    private final Reflections scanner;

    /**
     * Map contains as key - interface, value - implements class.
     */
    private final Map<Class<?>, Class<?>> ifc2ImplClass;

    public JavaConfig(String packageToScan, Map<Class<?>, Class<?>> ifc2ImplClass) {
        this.scanner = new Reflections(packageToScan);
        this.ifc2ImplClass = ifc2ImplClass;
    }

    @Override
    public <T> Class<?> getImplClass(Class<T> ifc) {
        if (ifc2ImplClass != null && !ifc2ImplClass.isEmpty()) {
            return ifc2ImplClass.computeIfAbsent(ifc, aClass -> findClassInPackages(ifc).iterator().next());
        } else {
            return findClassInPackages(ifc).iterator().next();
        }
    }

    @Override
    public <T> Set<Class<? extends T>> getImplClasses(Class<T> ifc) {
        return findClassInPackages(ifc);
    }

    private <T> Set<Class<? extends T>> findClassInPackages(Class<T> ifc) {
        Set<Class<? extends T>> classes = scanner.getSubTypesOf(ifc);
        if (classes.isEmpty()) {
            throw new IllegalArgumentException(ifc + " has 0 impl please update your config");
        } else if (classes.size() > 1) {
            log.warn(ifc + " has more than one imp, there may be unexpected behavior, please update your config "
                    + Arrays.toString(classes.toArray()));
        }
        return classes;
    }

    @Override
    public Reflections getScanner() {
        return this.scanner;
    }
}
