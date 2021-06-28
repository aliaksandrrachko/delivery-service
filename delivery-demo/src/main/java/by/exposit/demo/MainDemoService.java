package by.exposit.demo;

import by.exposit.delivery.api.utils.IDemo;
import by.exposit.delivery.api.utils.IMainDemo;
import by.exposit.delivery.core.annotations.InjectByType;
import by.exposit.delivery.core.annotations.Singleton;

import java.util.List;

@Singleton
public class MainDemoService implements IMainDemo {

    @InjectByType(aClass = IDemo.class)
    private List<IDemo> demos;

    @Override
    public void mainDemo() {
        if (this.demos != null && !demos.isEmpty()){
            demos.forEach(IDemo::demo);
        }
    }
}
