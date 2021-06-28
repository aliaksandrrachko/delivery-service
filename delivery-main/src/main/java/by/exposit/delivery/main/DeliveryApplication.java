package by.exposit.delivery.main;

import by.exposit.delivery.api.utils.IMainDemo;
import by.exposit.delivery.core.Application;
import by.exposit.delivery.core.ApplicationContext;

import java.util.Collections;

public class DeliveryApplication {
    public static void main(String[] args) {
        ApplicationContext context = Application.run("by.exposit", Collections.emptyMap());

        // get demo class from application context
        // never do that, it's very dangerous
        IMainDemo demo = (IMainDemo) context.getObject(IMainDemo.class);
        demo.mainDemo();
    }
}
