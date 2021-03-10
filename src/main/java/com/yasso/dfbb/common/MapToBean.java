package com.yasso.dfbb.common;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * Map转bean
 * @author guochaung
 * @version 1.0
 * @date 2020/11/24 17:02
 */
public class MapToBean {

    /**
     * 转java对象
     * @param source 源Map
     * @param clazz clazz
     * @param nullTochar 是否null转 ""
     * @param invalidAttributes 无视的属性
     * @param <T>
     * @return calzz 的实例
     */
    public static <T>T toJavaObject(Map<String, Object> source, Class<?> clazz
            , boolean nullTochar, String... invalidAttributes){
        String invalidAttributes2 = invalidAttributes != null ? StringUtils.join(invalidAttributes,",") : "";

        if (!(source instanceof Map)) {
            throw new IllegalArgumentException("source not is a map");
        }
        if (clazz == null) {
            throw new IllegalArgumentException("clazz is null");
        }

        Object object = null;
        try {
            object = clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        //遍历map元素
        for (Map.Entry<String, Object> sourceEntry : source.entrySet()) {
            String propertyName = sourceEntry.getKey();
            Object value = sourceEntry.getValue();
            String setMethodName = "set" + propertyName.substring(0,1).toUpperCase() + propertyName.substring(1);
            Field field = getClassField(clazz, propertyName);
            if (field == null){
                continue;
            }

            Class<?> fieldTypeClass = field.getType();
            value = convertValType(value, fieldTypeClass);

            if (nullTochar && value == null) {
                value = "";
            }

            if (invalidAttributes2.contains(propertyName)) {
                value = null;
            }

            try {
                //调用set方法 进行赋值
                clazz.getMethod(setMethodName, field.getType()).invoke(object,value);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        return (T) object;
    }


    /**
     * 获取属性
     * @param clazz
     * @param fieldName map中的列名
     * @return 获取列全属性
     */
    private static Field getClassField(Class<?> clazz, String fieldName) {

        if (Object.class.getName().equals(clazz.getName())){
            return null;
        }

        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {
            if (field.getName().equals(fieldName)){
                return field;
            }
        }

        Class<?> superclass = clazz.getSuperclass();
        if (superclass != null){
            return getClassField(superclass, fieldName);
        }
        return null;
    }


    /**
     * value转换为指定类型的值
     * @param value
     * @param fieldTypeClass 指定类型
     * @return
     */
    private static Object convertValType(Object value, Class<?> fieldTypeClass){
        Object retVal = null;

        if (Long.class.getName().equals(fieldTypeClass.getName())
                || long.class.getName().equals(fieldTypeClass.getName())) {
            retVal = Long.parseLong(value.toString());
        } else if (Integer.class.getName().equals(fieldTypeClass.getName())
                || int.class.getName().equals(fieldTypeClass.getName())) {
            retVal = Integer.parseInt(value.toString());
        } else if (Float.class.getName().equals(fieldTypeClass.getName())
                || float.class.getName().equals(fieldTypeClass.getName())) {
            retVal = Float.parseFloat(value.toString());
        } else if (Double.class.getName().equals(fieldTypeClass.getName())
                || double.class.getName().equals(fieldTypeClass.getName())) {
            retVal = Double.parseDouble(value.toString());
        } else {
            retVal = value;
        }
        return retVal;
    }




}
