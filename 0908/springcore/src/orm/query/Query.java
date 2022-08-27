package orm.query;

import java.util.List;

/**
 * @author <a href="mailto:ngocmeu2000@gmail.com">NgocNB20</a>
 */
public interface Query<T> {
    String condition();

    Object value();

    List<Object> values();
}
