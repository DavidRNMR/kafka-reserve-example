package com.hotel.springboot.payload;

public class Search {

    private String searchId;

    public void setSearchId(String searchId) {
        this.searchId = searchId;
    }
    public String getSearchId() {
        return searchId;
    }

    public Search(String searchId) {
        this.searchId = searchId;
    }
}
