package orm.utils;

import orm.exeption.OrmExcrption;

public class StringUtils {
    private StringUtils() {
    }

    public static boolean isEmpty(String value) {
        return value == null || value.length() == 0;
    }
    
    public static boolean notEmpty(String value) {
        return !isEmpty(value);
    }

    public static String convertToDatabaseRuleName(String value) {
        if (isEmpty(value)) {
            throw new OrmExcrption("Class is Empty");

        }
        StringBuilder database = new StringBuilder();
        char[] chars = value.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] >= 'A' && chars[i] <= 'Z') {
                if (i == 0) {
                    database.append((char) (chars[i] + 32));
                } else {
                    database.append("_").append((char) (chars[i] + 32));
                }
            } else {
                database.append(chars[i]);
            }
        }

        return database.toString();
    }


}
