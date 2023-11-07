package tests;

import java.lang.reflect.Method;

public class TestsMain {
    public static void main(String[] args) {
        TestsFighter testsFighter = new TestsFighter();
        Method[] methods = testsFighter.getClass().getDeclaredMethods();
        for (Method method : methods) {
            Test test = method.getAnnotation(Test.class);

            if (test.enabled()) {
                try {
                    method.invoke(testsFighter.getClass().getDeclaredConstructor().newInstance());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
