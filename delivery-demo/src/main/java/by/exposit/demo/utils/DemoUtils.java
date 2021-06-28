package by.exposit.demo.utils;

public final class DemoUtils {

    private DemoUtils(){}

    private static final String DEMO_MESSAGE =
            "\n===============================================\n" +
            "-- %s %s\n" +
            "===============================================";

    public static String getStartDemoMessage(Class<?> clazz){
        return String.format(DEMO_MESSAGE, "start" , clazz.getSimpleName());
    }

    public static String getStopDemoMessage(Class<?> clazz){
        return String.format(DEMO_MESSAGE, "finish", clazz.getSimpleName());
    }
}
