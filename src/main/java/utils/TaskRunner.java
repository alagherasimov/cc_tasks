package utils;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;
import java.lang.reflect.Method;

public class TaskRunner extends Runner {

    private Class runClass;

    public TaskRunner(Class runClass) {
        super();
        this.runClass = runClass;
    }

    @Override
    public Description getDescription() {
        return Description.createTestDescription(runClass, "Runner description");
    }

    @Override
    public void run(RunNotifier runNotifier) {
        try {
            Object runObject = runClass.newInstance();
            for (Method method : runClass.getMethods()) {
                if (method.isAnnotationPresent(Test.class) && !method.isAnnotationPresent(Ignore.class)) {
                    runNotifier.fireTestStarted(Description.createTestDescription(runClass, method.getName()));
                    method.invoke(runObject);
                    runNotifier.fireTestFinished(Description.createTestDescription(runClass, method.getName()));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}


