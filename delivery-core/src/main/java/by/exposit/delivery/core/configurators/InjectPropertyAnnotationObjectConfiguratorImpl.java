package by.exposit.delivery.core.configurators;

import by.exposit.delivery.core.ApplicationContext;
import by.exposit.delivery.core.annotations.InjectProperty;
import by.exposit.delivery.core.intefaces.ObjectConfigurator;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InjectPropertyAnnotationObjectConfiguratorImpl implements ObjectConfigurator {

    private Map<String, String> propertiesMap;

    /**
     * Reads all property prom resource application.property
     */
    @SneakyThrows
    public InjectPropertyAnnotationObjectConfiguratorImpl() {
        AtomicReference<URL> urlResources = new AtomicReference<>(ClassLoader.getSystemClassLoader().getResource("application.properties"));
        if (urlResources.get() != null) {
            initProperties(urlResources.get().getPath());
        } else {
            this.propertiesMap = Collections.emptyMap();
        }
    }

    @SneakyThrows
    @Override
    public void configure(Object t, ApplicationContext context) {
        Class<?> implClass = t.getClass();
        for (Field field : implClass.getDeclaredFields()) {
            InjectProperty annotation = field.getAnnotation(InjectProperty.class);
            if (annotation != null) {
                String value = annotation.value().isEmpty() ? propertiesMap.get(field.getName()) : propertiesMap.get(annotation.value());
                field.setAccessible(true);
                field.set(t, value);
            }
        }
    }

    private void initProperties(String path) {
        try (BufferedReader bf = new BufferedReader(new FileReader(path))) {
            Stream<String> lines = bf.lines();
            this.propertiesMap = lines
                    .map(line -> line.trim().split("="))
                    .collect(Collectors.toMap(arr -> arr[0], arr -> arr[1]));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
