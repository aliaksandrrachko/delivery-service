package by.exposit.delivery.core;

import java.util.Map;

public class Application {

    private Application(){ }

    public static ApplicationContext run(String packageToScan, Map<Class<?>, Class<?>> ifc2ImplClass) {
        JavaConfig javaConfig = new JavaConfig(packageToScan, ifc2ImplClass);
        ApplicationContext applicationContext = new ApplicationContext(javaConfig);
        ObjectFactory objectFactory = new ObjectFactory(applicationContext);
        applicationContext.setFactory(objectFactory);
        return applicationContext;
    }
}
