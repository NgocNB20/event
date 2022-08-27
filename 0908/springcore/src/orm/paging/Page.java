package orm.paging;

import java.util.List;
import java.util.function.Function;

 
public interface Page<T> {
    int getPage();
    long getTotalItem();

    int getSize();

    long getTotalPage();

    List<T> getData();
    <Q> List<Q> map(Function<T,Q> map);
}
