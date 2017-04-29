package com.enenim.movies.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.orm.dsl.Table;
import com.orm.dsl.Unique;

import java.io.Serializable;

/**
 * Created by enenim on 4/19/17.
 */
@Table
public class Review implements Parcelable{
    @SerializedName("db_id")
    private transient Long id = null;

    @SerializedName("id")
    @Expose
    @Unique
    private String reviewId;

    @SerializedName("author")
    @Expose
    private String author;

    @SerializedName("content")
    @Expose
    private String content;

    @SerializedName("url")
    @Expose
    private String url;

    public Review(){

    }

    //Parcel Constructor
    public Review(Parcel in) {
        setReviewId(in.readString());
        setAuthor(in.readString());
        setContent(in.readString());
        setUrl(in.readString());
    }

    public static final Creator<Review> CREATOR = new Creator<Review>() {
        @Override
        public Review createFromParcel(Parcel in) {
            return new Review(in);
        }

        @Override
        public Review[] newArray(int size) {
            return new Review[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(getReviewId());
        dest.writeString(getAuthor());
        dest.writeString(getContent());
        dest.writeString(getUrl());
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }
}
