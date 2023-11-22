package test;

import test.*;

import java.lang.reflect.Method;

public class TestsMain {
    public static void main(String[] args) {

        try {
            System.setOut(Assert.newOut);

            TestsFighter testsFighter = new TestsFighter();
            TestsWeapon testsWeapon = new TestsWeapon();
            TestsAttack testsAttack = new TestsAttack();
            TestsType testsType = new TestsType();
            TestsItem testsItem = new TestsItem();

            Method[] methodsFighter = testsFighter.getClass().getDeclaredMethods();
            Method[] methodsWeapon = testsWeapon.getClass().getDeclaredMethods();
            Method[] methodsAttack = testsAttack.getClass().getDeclaredMethods();
            Method[] methodsType = testsType.getClass().getDeclaredMethods();
            Method[] methodsItem = testsItem.getClass().getDeclaredMethods();

            System.setOut(Assert.actualOut);
            System.out.println("Tests on Fighter classes");
            System.setOut(Assert.newOut);
            ComparisonFailure.error = false;

            Assert.setActualOut(Assert.newOut);
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

            Assert.setActualOut(Assert.originalOut);
            if (!ComparisonFailure.error) {
                System.setOut(Assert.actualOut);
                System.out.println(Assert.green + "All Tests passed on Fighter class" + Assert.reset);
                System.setOut(Assert.newOut);
            } else {
                for (Method method : methodsFighter) {
                    Test test = method.getAnnotation(Test.class);

                    if (test.enabled()) {
                        try {
                            method.invoke(testsFighter.getClass().getDeclaredConstructor().newInstance());
                        } catch (Exception e) {
                            System.setOut(Assert.actualOut);
                            System.out.println(e.getMessage());
                            System.setOut(Assert.newOut);
                        }
                    }
                }
            }

            System.setOut(Assert.actualOut);
            System.out.println("Tests on Weapon classes");
            System.setOut(Assert.newOut);
            ComparisonFailure.error = false;

            Assert.setActualOut(Assert.newOut);
            for (Method method : methodsWeapon) {
                Test test = method.getAnnotation(Test.class);

                if (test.enabled()) {
                    try {
                        method.invoke(testsWeapon.getClass().getDeclaredConstructor().newInstance());
                    } catch (Exception e) {
                        System.setOut(Assert.actualOut);
                        System.out.println(e.getMessage());
                        System.setOut(Assert.newOut);
                    }
                }
            }

            Assert.setActualOut(Assert.originalOut);
            if (!ComparisonFailure.error) {
                System.setOut(Assert.actualOut);
                System.out.println(Assert.green + "All Tests passed on Weapon classes" + Assert.reset);
                System.setOut(Assert.newOut);
            } else {
                for (Method method : methodsWeapon) {
                    Test test = method.getAnnotation(Test.class);

                    if (test.enabled()) {
                        try {
                            method.invoke(testsWeapon.getClass().getDeclaredConstructor().newInstance());
                        } catch (Exception e) {
                            System.setOut(Assert.actualOut);
                            System.out.println(e.getMessage());
                            System.setOut(Assert.newOut);
                        }
                    }
                }
            }

            System.setOut(Assert.actualOut);
            System.out.println("Tests on Attack classes");
            System.setOut(Assert.newOut);
            ComparisonFailure.error = false;

            Assert.setActualOut(Assert.newOut);
            for (Method method : methodsAttack) {
                Test test = method.getAnnotation(Test.class);

                if (test.enabled()) {
                    try {
                        method.invoke(testsAttack.getClass().getDeclaredConstructor().newInstance());
                    } catch (Exception e) {
                        System.setOut(Assert.actualOut);
                        System.out.println(e.getMessage());
                        System.setOut(Assert.newOut);
                    }
                }
            }

            Assert.setActualOut(Assert.originalOut);
            if (!ComparisonFailure.error) {
                System.setOut(Assert.actualOut);
                System.out.println(Assert.green + "All Tests passed on Attack classes" + Assert.reset);
                System.setOut(Assert.newOut);
            } else {
                for (Method method : methodsAttack) {
                    Test test = method.getAnnotation(Test.class);

                    if (test.enabled()) {
                        try {
                            method.invoke(testsAttack.getClass().getDeclaredConstructor().newInstance());
                        } catch (Exception e) {
                            System.setOut(Assert.actualOut);
                            System.out.println(e.getMessage());
                            System.setOut(Assert.newOut);
                        }
                    }
                }
            }

            System.setOut(Assert.actualOut);
            System.out.println("Tests on Type class");
            System.setOut(Assert.newOut);
            ComparisonFailure.error = false;

            Assert.setActualOut(Assert.newOut);
            for (Method method : methodsType) {
                Test test = method.getAnnotation(Test.class);

                if (test.enabled()) {
                    try {
                        method.invoke(testsType.getClass().getDeclaredConstructor().newInstance());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            }

            Assert.setActualOut(Assert.originalOut);
            if (!ComparisonFailure.error) {
                System.setOut(Assert.actualOut);
                System.out.println(Assert.green + "All Tests passed on Type class" + Assert.reset);
                System.setOut(Assert.newOut);
            } else {
                for (Method method : methodsType) {
                    Test test = method.getAnnotation(Test.class);

                    if (test.enabled()) {
                        try {
                            method.invoke(testsType.getClass().getDeclaredConstructor().newInstance());
                        } catch (Exception e) {
                            System.setOut(Assert.actualOut);
                            System.out.println(e.getMessage());
                            System.setOut(Assert.newOut);
                        }
                    }
                }
            }

            System.setOut(Assert.actualOut);
            System.out.println("Tests on Item classes");
            System.setOut(Assert.newOut);
            ComparisonFailure.error = false;

            Assert.setActualOut(Assert.newOut);
            for (Method method : methodsItem) {
                Test test = method.getAnnotation(Test.class);

                if (test.enabled()) {
                    try {
                        method.invoke(testsItem.getClass().getDeclaredConstructor().newInstance());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            }

            Assert.setActualOut(Assert.originalOut);
            if (!ComparisonFailure.error) {
                System.setOut(Assert.actualOut);
                System.out.println(Assert.green + "All Tests passed on Item classes" + Assert.reset);
                System.setOut(Assert.newOut);
            } else {
                for (Method method : methodsItem) {
                    Test test = method.getAnnotation(Test.class);

                    if (test.enabled()) {
                        try {
                            method.invoke(testsItem.getClass().getDeclaredConstructor().newInstance());
                        } catch (Exception e) {
                            System.setOut(Assert.actualOut);
                            System.out.println(e.getMessage());
                            System.setOut(Assert.newOut);
                        }
                    }
                }
            }

        } finally {
            System.setOut(Assert.actualOut);
        }
    }
}
