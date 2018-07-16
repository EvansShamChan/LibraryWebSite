package com.softserve.edu.library.dto;

public class BookSearchDto {
    private int rowsPerPage;
    private String checkBy;
    private String searchKey;
    private int currentPage;
    private String sort;

    public BookSearchDto(int rowsPerPage, String checkBy, String searchKey, int currentPage, String sort) {
        this.rowsPerPage = rowsPerPage;
        this.checkBy = checkBy;
        this.searchKey = searchKey;
        this.currentPage = currentPage;
        this.sort = sort;
    }

    public int getRowsPerPage() {
        return rowsPerPage;
    }

    public void setRowsPerPage(int rowsPerPage) {
        this.rowsPerPage = rowsPerPage;
    }

    public String getCheckBy() {
        return checkBy;
    }

    public void setCheckBy(String checkBy) {
        this.checkBy = checkBy;
    }

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sortBy) {
        this.sort = sortBy;
    }
}