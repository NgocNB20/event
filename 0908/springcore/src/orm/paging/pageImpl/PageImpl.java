package orm.paging.pageImpl;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import orm.paging.Page;

public class PageImpl<T> implements Page<T> {
	private int size;
	private int page;
	private long total;
	private List<T> data;

	/* contructor private dung 1 ham static de new 1 doi tuong */
	private PageImpl(int size, int page, long total, List<T> data) {
		this.size = size;
		this.page = page;
		this.total = total;
		this.data = data;
	}

	public static <T> Page<T> of(int size, int page, long total, List<T> data) {
 
		return new PageImpl(size, page, total, data);
	}

	public void setSize(int size) {
		this.size = size;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	@Override
	public int getPage() {
		return page;
	}

	@Override
	public long getTotalItem() {
		return total;
	}

	@Override
	public int getSize() {
		return size;
	}

	@Override
	public long getTotalPage() {
		if (size == 0) {
			return 0;
		}
		if (total <= size) {
			return 1;
		}
		return (total / size) + 1;
	}

	@Override
	public List<T> getData() {
		return data;
	}

	@Override
	public <Q> List<Q> map(Function<T, Q> map) {
		return data.stream().map(map::apply).collect(Collectors.toList());
	}
}
