package com.example.test.models.locations;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Victor on 01/03/2016.
 */
public class VoInfoLocations {

    @SerializedName("count")
    private int count;

    @SerializedName("pages")
    private int pages;

    @SerializedName("next")
    private String next;

    @SerializedName("prev")
    private String prev;

    @SerializedName("created")
    private String created;

    public VoInfoLocations() {
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrev() {
        return prev;
    }

    public void setPrev(String prev) {
        this.prev = prev;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }
}