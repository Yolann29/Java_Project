package tests;

import java.lang.reflect.Method;

public class TestsMain {
    public static void main(String[] args) {
        Output.initial("");
        TestsFighter testsFighter = new TestsFighter();
        TestsWeapon testsWeapon = new TestsWeapon();

        Method[] methodsFighter = testsFighter.getClass().getDeclaredMethods();
        Method[] methodsWeapon = testsWeapon.getClass().getDeclaredMethods();

        Output.update("Tests Fighter");

        for (Method method : methodsFighter) {
            Test test = method.getAnnotation(Test.class);

            if (test.enabled()) {
                try {
                    method.invoke(testsFighter.getClass().getDeclaredConstructor().newInstance());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        Output.update("Tests Weapon");

        for (Method method : methodsWeapon) {
            Test test = method.getAnnotation(Test.class);

            if (test.enabled()) {
                try {
                    method.invoke(testsWeapon.getClass().getDeclaredConstructor().newInstance());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
