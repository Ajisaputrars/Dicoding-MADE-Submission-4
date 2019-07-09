package com.example.ajisaputrars.madesubmission2;

import android.os.Parcel;
import android.os.Parcelable;

public class TvShow implements Parcelable {
    private int id;
    private double vote_average;
    private String overview;
    private int poster_path;
    private String first_air_date;
    private int backdrop_path;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public int getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(int poster_path) {
        this.poster_path = poster_path;
    }

    public String getFirst_air_date() {
        return first_air_date;
    }

    public void setFirst_air_date(String first_air_date) {
        this.first_air_date = first_air_date;
    }

    public int getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(int backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeDouble(this.vote_average);
        dest.writeString(this.overview);
        dest.writeInt(this.poster_path);
        dest.writeString(this.first_air_date);
        dest.writeInt(this.backdrop_path);
        dest.writeString(this.name);
    }

    public TvShow() {
    }

    protected TvShow(Parcel in) {
        this.id = in.readInt();
        this.vote_average = in.readDouble();
        this.overview = in.readString();
        this.poster_path = in.readInt();
        this.first_air_date = in.readString();
        this.backdrop_path = in.readInt();
        this.name = in.readString();
    }

    public static final Parcelable.Creator<TvShow> CREATOR = new Parcelable.Creator<TvShow>() {
        @Override
        public TvShow createFromParcel(Parcel source) {
            return new TvShow(source);
        }

        @Override
        public TvShow[] newArray(int size) {
            return new TvShow[size];
        }
    };
}
