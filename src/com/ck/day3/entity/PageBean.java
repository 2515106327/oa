package com.ck.day3.entity;

import java.util.List;

/**
 * 用于封装分页查询
 * 
 * @author Admin
 *
 */
public class PageBean<T> {
	private List<T> data; // 数据
	private int page;
	private int pageSize;
	private int totalPage;
	private int totalNum;
	private int actualSize; // 实际大小

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
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

	public int getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}

	public int getActualSize() {
		return actualSize;
	}

	public void setActualSize(int actualSize) {
		this.actualSize = actualSize;
	}

	@Override
	public String toString() {
		return "PageBean [data=" + data + ", page=" + page + ", pageSize=" + pageSize + ", totalPage=" + totalPage
				+ ", totalNum=" + totalNum + ", actualSize=" + actualSize + "]";
	}

}
