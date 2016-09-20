package com.scarb.pagination;

import java.util.List;

/**
 * Created by Scarb on 9/14/2016.
 */
public class Page<T> {
    public static final String ORDER_ASC = "asc";
    public static final String ORDER_DESC = "desc";

    public Page(int pageNo, int pageSize){
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public Page(){}

    private boolean checkFirstFrom = true;      // 执行count的时候，会找第一个from

    private int pageNo = 1;
    private int pageSize = 10;

    private int totalRecord;
    private int totalPage;
    private List<T> results;

    private String sortColumn;
    private String sortOrder = ORDER_ASC;

    public boolean isCheckFirstFrom() {
        return checkFirstFrom;
    }

    public void setCheckFirstFrom(boolean checkFirstFrom) {
        this.checkFirstFrom = checkFirstFrom;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
        int totalPage = totalRecord % pageSize == 0 ? totalRecord/pageSize : totalRecord/pageSize + 1;
        this.setTotalPage(totalPage);

        if(pageNo > totalPage)
            pageNo = 1;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }

    public String getSortColumn() {
        return sortColumn;
    }

    public void setSortColumn(String sortColumn) {
        this.sortColumn = sortColumn;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }
}
