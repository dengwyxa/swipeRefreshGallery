package com.example.gallerydemo;

import java.util.List;

public class Pixabay {
    Integer total;
    Integer totalHits;
    List<PhotoItem> hits;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getTotalHits() {
        return totalHits;
    }

    public void setTotalHits(Integer totalHits) {
        this.totalHits = totalHits;
    }

    public List<PhotoItem> getHits() {
        return hits;
    }

    public void setHits(List<PhotoItem> hits) {
        this.hits = hits;
    }
}

class PhotoItem {
    String webformatURL;
    int id;
    String largeImageURL;

    public String getWebformatURL() {
        return webformatURL;
    }

    public void setWebformatURL(String webformatURL) {
        this.webformatURL = webformatURL;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLargeImageURL() {
        return largeImageURL;
    }

    public void setLargeImageURL(String largeImageURL) {
        this.largeImageURL = largeImageURL;
    }
}
