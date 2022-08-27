package model;

import java.util.List;

public class PageEventList {
	private int pageSize;
	private int totalPage;
	private List<EventList> searchData;
	private int currentPage;

	public PageEventList() {

	}

 

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public List<EventList> getSearchData() {
		return searchData;
	}

	public void setSearchData(List<EventList> searchData) {
		this.searchData = searchData;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public PageEventList(int pageSize, int totalPage, List<EventList> searchData, int currentPage) {

		this.pageSize = pageSize;
		this.totalPage = totalPage;
		this.searchData = searchData;
		this.currentPage = currentPage;
	}



	@Override
	public String toString() {
		return "PageEventList [pageSize=" + pageSize + ", totalPage=" + totalPage + ", searchData=" + searchData
				+ ", currentPage=" + currentPage + "]";
	}
	
	

}
