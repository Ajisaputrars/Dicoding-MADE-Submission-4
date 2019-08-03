package com.example.ajisaputrars.madesubmission2.model.movie;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.ajisaputrars.madesubmission2.Constant;

import org.json.JSONObject;

public class Movie implements Parcelable {
    private int id;
    private String title;
    private String overview;
    private String release_date;
    private double vote_average;
    private int poster_path;
    private int backdrop_path;
    private String poster_path_string;
    private String backdrop_path_string;

    public String getPoster_path_string() {
        return poster_path_string;
    }

    public void setPoster_path_string(String poster_path_string) {
        this.poster_path_string = poster_path_string;
    }

    public String getBackdrop_path_string() {
        return backdrop_path_string;
    }

    public void setBackdrop_path_string(String backdrop_path_string) {
        this.backdrop_path_string = backdrop_path_string;
    }

    public int getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(int poster_path) {
        this.poster_path = poster_path;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    public int getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(int backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeString(this.overview);
        dest.writeString(this.release_date);
        dest.writeDouble(this.vote_average);
        dest.writeInt(this.poster_path);
        dest.writeString(this.poster_path_string);
    }

    public Movie() {
    }

    protected Movie(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.overview = in.readString();
        this.release_date = in.readString();
        this.vote_average = in.readDouble();
        this.poster_path = in.readInt();
        this.poster_path_string = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public Movie (JSONObject object) {
        try {
            int id = object.getInt("id");
            String title = object.getString("title");
            String overview = object.getString("overview");
            String release_date = object.getString("release_date");
            Double vote_average = object.getDouble("vote_average");
            String poster_path = Constant.URL_IMAGE_BASE + object.getString("poster_path");
            String backdrop_path = Constant.URL_IMAGE_BASE + object.getString("backdrop_path");
            this.id = id;
            this.title = title;
            this.overview = overview;
            this.release_date = release_date;
            this.vote_average = vote_average;
            this.poster_path_string = poster_path;
            this.backdrop_path_string = backdrop_path;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
