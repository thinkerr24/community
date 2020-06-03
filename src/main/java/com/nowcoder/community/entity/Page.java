package com.nowcoder.community.entity;

/**
 *  Package page related information
 */
public class Page {

    // Current page
    private int current = 1;
    // Show upper limit
    private int limit = 10;
    // Total for calculating total pages
    private int rows;
    // Query path for reuse paging-link
    private String path;

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        if (current > 0)
            this.current = current;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        if (limit > 0 && limit < 101)
            this.limit = limit;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        if (rows >= 0)
            this.rows = rows;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    // Get currentPage item of begging
    public int getOffset() {
        return (current - 1) * limit;
    }

    // Get totalPage
    public int getTotal() {
        return rows % limit == 0 ? rows / limit : rows / limit + 1;
    }

    // Get start page
    public int getFrom() {
        int from = current - 2;
        return from < 1 ? 1 : from;
    }

    // Get end page
    public int getTo() {
        int to = current + 2;
        int total = getTotal();
        return to > total ? total : to;
    }
}
