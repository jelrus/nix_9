package csvio.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

public class ObjectConverter {

    public static String[] getObjectFields(Class<?> cls) throws NoSuchMethodException, InvocationTargetException,
            InstantiationException, IllegalAccessException {
        Object object = cls.getDeclaredConstructor().newInstance();
        Field[] fields = object.getClass().getDeclaredFields();
        Field[] ancestorFields = object.getClass().getSuperclass().getDeclaredFields();
        StringBuilder fieldBuilder = new StringBuilder();
        for (int i = 0; i<ancestorFields.length; i++) {
            fieldBuilder.append(ancestorFields[i].getName()).append(",");
        }
        for (int i = 0; i< fields.length; i++) {
            if (i == fields.length - 1) {
                fieldBuilder.append(fields[i].getName());
            } else {
                fieldBuilder.append(fields[i].getName()).append(",");
            }
        }
        return fieldBuilder.toString().split(",");
    }

    public static Object toObject(Class<?> cls, String[] input, String[] headers)
            throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Object result = cls.getDeclaredConstructor().newInstance();
        Field[] fields = cls.getDeclaredFields();
        Field[] ancestorField = cls.getSuperclass().getDeclaredFields();
        for (int i = 0; i< headers.length; i++) {
            if (i > 0) {
                if (fields[i-1].getName().equals(headers[i])) {
                    Field field = fields[i-1];
                    Object obj = getFieldType(field.getType(), input[i]);
                    setValue(result, fields[i-1], obj);
                }
            }
            if (i == 0) {
                if (ancestorField[i].getName().equals(headers[i])) {
                    Field field = ancestorField[i];
                    Object obj = getFieldType(field.getType(), input[i]);
                    setValue(result, ancestorField[i], obj);
                }
            }
        }
        return result;
    }

    public static Object getFieldType(Class<?> type, String input) {
        switch (type.getSimpleName()) {
            case "Integer", "int" -> {
                return Integer.parseInt(input);
            }
            case "String" -> {
                return input;
            }
            case "BigDecimal" -> {
                return BigDecimal.valueOf(Double.parseDouble(input));
            }
            default -> {
                return null;
            }
        }
    }

    public static void setValue(Object updatable, Field field, Object value) throws IllegalAccessException {
        if (!field.canAccess(updatable)) {
            field.setAccessible(true);
            field.set(updatable, value);
            field.setAccessible(false);
        } else {
            field.set(updatable, value);
        }
    }
}