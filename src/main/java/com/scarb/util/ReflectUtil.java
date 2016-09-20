package com.scarb.util;

/**
 * Created by Scarb on 9/14/2016.
 */
import java.lang.reflect.Field;

public abstract class ReflectUtil {
    public static Object getFieldValue(Object obj, String fieldName) {
        Object result = null;
        Field field = ReflectUtil.getField(obj, fieldName);
        if (field != null) {
            field.setAccessible(true);
            try {
                result = field.get(obj);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    private static Field getField(Object obj, String fieldName) {
        if(obj == null){
            return null;
        }

        Field field = null;
        for (Class<?> clazz = obj.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                field = clazz.getDeclaredField(fieldName);
                break;
            } catch (NoSuchFieldException e) {

            }
        }
        return field;
    }

    public static void setFieldValue(Object obj, String fieldName,
                                     String fieldValue) {
        Field field = ReflectUtil.getField(obj, fieldName);
        if (field != null) {
            try {
                field.setAccessible(true);
                field.set(obj, fieldValue);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean hasField(Object obj, String fieldName){
        if(obj == null || ReflectUtil.getField(obj, fieldName) == null){
            return false;
        }
        return true;
    }
}
