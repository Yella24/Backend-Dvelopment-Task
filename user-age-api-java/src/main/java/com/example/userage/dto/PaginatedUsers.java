package com.example.userage.dto;

import java.util.List;

/**
 * Pagination wrapper used when callers pass {@code ?page=&pageSize=}.
 */
public class PaginatedUsers {

    private List<UserResponse> data;
    private int page;
    private int pageSize;
    private long totalItems;

    public PaginatedUsers() {
    }

    public PaginatedUsers(List<UserResponse> data, int page, int pageSize, long totalItems) {
        this.data = data;
        this.page = page;
        this.pageSize = pageSize;
        this.totalItems = totalItems;
    }

    public List<UserResponse> getData() {
        return data;
    }

    public int getPage() {
        return page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public long getTotalItems() {
        return totalItems;
    }

    public void setData(List<UserResponse> data) {
        this.data = data;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setTotalItems(long totalItems) {
        this.totalItems = totalItems;
    }
}
