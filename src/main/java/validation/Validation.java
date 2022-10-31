package validation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.UUID;

import java.lang.reflect.Field;

public class Validation {


    public static Boolean isValid(Bean input) throws ClassNotFoundException, InstantiationException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException,
            NoSuchMethodException, SecurityException {
        Class<?> beanClass = Class.forName("validation.Bean");
        Field[] fields = beanClass.getFields();
        ArrayList<Object[]> dataList = new ArrayList<Object[]>();
        for (Field field: fields) {
            Object[] data = new Object[4];
            Required anno = field.getAnnotation(Required.class);
            System.out.println(anno);
            if (anno != null) {
            data[0] = (Object)field.getName();
            data[1] = (Object)anno.error();
            data[2] = (Object)anno.type();
            data[3] = (Object)anno.value();
            dataList.add(data);
            }
        }
        Object[][] rules = new Object[dataList.size()][4];
        int i = 0;
        for (Object[] data : dataList) {
            rules[i][0] = data[0];
            rules[i][1] = data[1];
            rules[i][2] = data[2];
            rules[i][3] = data[3];
            i++;
        }
        for (i = 0; i < rules.length; i++) {
            Method beanMethod = beanClass.getDeclaredMethod(
                    "get"+(""+(""+rules[i][0]).charAt(0)).toUpperCase()
                    +(""+rules[i][0]).substring(1));
            Object contains = (Object) beanMethod.invoke(input);
            if (rules[i][2].equals("")) {
                if (contains == null) {
                    System.out.println(rules[i][1]);
                    return false;
                }
            } else {
                Method beanMethod2 = beanClass.getDeclaredMethod(
                        "get"+(""+(""+rules[i][2]).charAt(0)).toUpperCase()
                        +(""+rules[i][2]).substring(1));
                Object contains2 = (Object) beanMethod2.invoke(input);
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
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
