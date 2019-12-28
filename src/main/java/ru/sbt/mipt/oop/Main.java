package ru.sbt.mipt.oop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.sbt.mipt.oop.configs.NewAPIConfig;
import ru.sbt.mipt.oop.configs.TestConfig;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(NewAPIConfig.class);
        Application app = context.getBean(Application.class);
        app.run();
    }
}
