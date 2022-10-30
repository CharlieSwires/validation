package validation;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.UUID;

public class Validation {

    private static final Object[][] rules = { { "id", "MISSING_ID", null, null },
            { "first", "MISSING_FIRST", null, null },
            { "second", "MISSING_SECOND", "type", Enum.THIRD },
            { "uuid", "MISSING_UUID", null, null } };

    public static Boolean isValid(Bean input) throws ClassNotFoundException, InstantiationException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException,
            NoSuchMethodException, SecurityException {
        Class<?> beanClass = Class.forName("validation.Bean");
        Bean bean = (Bean) beanClass.getConstructor().newInstance();
        for (int i = 0; i < rules.length; i++) {
            Method beanMethod = beanClass.getDeclaredMethod((String) rules[i][0]);
            Object contains = (Object) beanMethod.invoke(bean);
            if (rules[i][2] == null) {
                if (contains == null) {
                    System.out.println(rules[i][1]);
                    return false;
                }
            } else {
                Method beanMethod2 = beanClass.getDeclaredMethod((String) rules[i][2]);
                Object contains2 = (Object) beanMethod2.invoke(bean);
                if (contains2.equals(rules[i][3])) {
                    continue;
                } else if (contains == null) {
                    System.out.println(rules[i][1]);
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {

        Bean test1 = new Bean();
        test1.setFirst("first");
        test1.setSecond(null);
        test1.setId(1L);
        test1.setType(Enum.THIRD);
        test1.setUuid(UUID.randomUUID());

        try {
            System.out.println("Result=" + isValid(test1));
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
                | SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
