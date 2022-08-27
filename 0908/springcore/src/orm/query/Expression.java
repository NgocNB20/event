package orm.query;

/**
 * @author <a href="mailto:ngocmeu2000@gmail.com">NgocNB20</a>
 */
public interface Expression<T> {
    String expression();

    static String eq(){
        return "=";
    }

    static String notEqual(){
        return "<>";
    }

    static String gt(){
        return ">";
    }

    static String gte(){
        return ">=";
    }

    static String like(){
        return " LIKE ";
    }

    static String lt(){
        return "<";
    }

    static String lte(){
        return "<=";
    }

    static String between(){
        return " BETWEEN ";
    }

    static String in(){
        return " IN ";
    }

    static String and(){
        return " and ";
    }

    static String or(){
        return " or ";
    }

    static String isNull(){
        return " IS NULL ";
    }

    static String isNotNull(){
        return " IS NOT NULL ";
    }

}
