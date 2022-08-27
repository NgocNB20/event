package orm.utils;

import anotation.Column;
import anotation.Entity;
import anotation.Id;
import orm.exeption.OrmExcrption;

public class AnnotationUtil {
    private AnnotationUtil() {

    }

    public static <T> String getClassName(Class<T> tClass) {
        String name = tClass.getAnnotation(Entity.class).name();
        if (StringUtils.isEmpty(name)) {
            StringUtils.convertToDatabaseRuleName(tClass.getSimpleName());
        }
        return name;

    }

    public static <T> String getColumnName(Class<T> tClass, String fieldName) {

        try {
            String name = tClass.getDeclaredField(fieldName).getAnnotation(Column.class).name();
            if (StringUtils.isEmpty(name)) {
                return StringUtils.convertToDatabaseRuleName(fieldName);
            }
            return name;
        } catch (NoSuchFieldException e) {
            throw new OrmExcrption(e.getMessage());
        }
    }

    public static <T> boolean isAutoIncrement(Class<T> tClass, String fieldName) {
        try {
            return tClass.getDeclaredField(fieldName).getAnnotation(Id.class).autoIncrement();
        } catch (NoSuchFieldException e) {
            throw new OrmExcrption(e.getMessage());
        }
    }

    public static <T> String primaryColumn(Class<T> tClass, String fieldName) {
        try {
            String name = tClass.getDeclaredField(fieldName).getAnnotation(Id.class).name();
            if (StringUtils.isEmpty(name)) {
                return StringUtils.convertToDatabaseRuleName(fieldName);
            }
            return name;
        } catch (NoSuchFieldException e) {
            throw new OrmExcrption(e.getMessage());
        }
    }
}
