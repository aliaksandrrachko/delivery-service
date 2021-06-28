package by.exposit.delivery.core.configurators;

import by.exposit.delivery.core.annotations.TimeBenchmark;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalTime;

@Slf4j
public class TimeBenchmarkHandlerProxyConfiguratorImpl extends AbstractProxyConfigurator {

    @Override
    protected <T> boolean checkConditionsForWrappingInProxy(Class<T> implClass) {
        for (Method method : implClass.getMethods()) {
            if (method.isAnnotationPresent(TimeBenchmark.class)) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected Object getInvocationHandlerLogic(Object t, Method method, Object[] args)
            throws InvocationTargetException, IllegalAccessException {
        Object result;
        if (method.isAnnotationPresent(TimeBenchmark.class)) {
            LocalTime startTime = LocalTime.now();
            result = method.invoke(t, args);
            LocalTime finishTime = LocalTime.now();
            printTimeOfWorkMethod(method, startTime, finishTime);
        } else {
            result = method.invoke(t, args);
        }
        return result;
    }

    private static final String TIME_BENCHMARK_MESSAGE_PATTERN =
                    "============================================\n" +
                    "                TIME BENCHMARK:              \n" +
                    " Method '{}' working {} nano sec.\n" +
                    "============================================\n";

    private void printTimeOfWorkMethod(Method method, LocalTime start, LocalTime finish) {
        long time = finish.getNano() - start.getNano();
        log.info(TIME_BENCHMARK_MESSAGE_PATTERN, method.getName(), time);
    }
}
