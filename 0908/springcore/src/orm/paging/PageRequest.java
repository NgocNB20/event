package orm.paging;

 
public class PageRequest implements PageAble {
    private int page;
    private int size;

    private PageRequest(int page, int size) {
        this.page = page;
        this.size = size;
    }

    public static PageAble of(int page, int size) {
        return new PageRequest(page, size);
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public int getPage() {
        return page;
    }

    @Override
    public int getOffSet() {
        return (page == 1 || page == 0) ? 0 : (page - 1) * size;
    }
}
