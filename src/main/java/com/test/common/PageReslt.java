package com.test.common;

import java.util.List;

public class PageReslt<T> {

    //当前页数
    private int page;
    //记录
    private List<T> rows;
    //总记录数
    private long total;

    public int getPage() {
        return page;
    }

    public PageReslt<T> setPage(int page) {
        this.page = page;
        return this;
    }

    public List<T> getRows() {
        return rows;
    }

    public PageReslt<T> setRows(List<T> rows) {
        this.rows = rows;
        return this;
    }

    public long getTotal() {
        return total;
    }

    public PageReslt<T> setTotal(long total) {
        this.total = total;
        return this;
    }

}
