package com.example.gallerydemo;

import android.os.Parcel;
import android.os.Parcelable;

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

class PhotoItem implements Parcelable {
    String webformatURL;
    int id;
    String largeImageURL;

    protected PhotoItem(Parcel in) {
        webformatURL = in.readString();
        id = in.readInt();
        largeImageURL = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(webformatURL);
        dest.writeInt(id);
        dest.writeString(largeImageURL);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PhotoItem> CREATOR = new Creator<PhotoItem>() {
        @Override
        public PhotoItem createFromParcel(Parcel in) {
            return new PhotoItem(in);
        }

        @Override
        public PhotoItem[] newArray(int size) {
            return new PhotoItem[size];
        }
    };

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
