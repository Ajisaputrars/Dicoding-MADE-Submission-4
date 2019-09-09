package com.example.ajisaputrars.madesubmission2.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.ajisaputrars.madesubmission2.model.movie.Movie;
import static com.example.ajisaputrars.madesubmission2.db.MovieDatabaseContract.MovieColumns.BACKDROP_PATH_STRING;
import static com.example.ajisaputrars.madesubmission2.db.MovieDatabaseContract.MovieColumns.ID;
import static com.example.ajisaputrars.madesubmission2.db.MovieDatabaseContract.MovieColumns.MOVIE_TABLE_NAME;
import java.util.ArrayList;
import static com.example.ajisaputrars.madesubmission2.db.MovieDatabaseContract.MovieColumns.OVERVIEW;
import static com.example.ajisaputrars.madesubmission2.db.MovieDatabaseContract.MovieColumns.POSTER_PATH_STRING;
import static com.example.ajisaputrars.madesubmission2.db.MovieDatabaseContract.MovieColumns.RELEASE_DATE;
import static com.example.ajisaputrars.madesubmission2.db.MovieDatabaseContract.MovieColumns.TITLE;
import static com.example.ajisaputrars.madesubmission2.db.MovieDatabaseContract.MovieColumns.VOTE_AVERAGE;

public class MovieHelper {

    private static final String DATABASE_TABLE = MOVIE_TABLE_NAME;
    private static MovieDatabaseHelper movieDatabaseHelper;
    private static MovieHelper INSTANCE;

    private static SQLiteDatabase database;

    private MovieHelper(Context context) {
        movieDatabaseHelper = new MovieDatabaseHelper(context);
    }

    public static MovieHelper getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (SQLiteOpenHelper.class) {
                if (INSTANCE == null) {
                    INSTANCE = new MovieHelper(context);
                }
            }
        }
        return INSTANCE;
    }

    public void open() throws SQLException {
        database = movieDatabaseHelper.getWritableDatabase();
    }

    public void close() {
        movieDatabaseHelper.close();

        if (database.isOpen())
            database.close();
    }

    public ArrayList<Movie> getAllMovies() {
        ArrayList<Movie> arrayList = new ArrayList<>();
        Cursor cursor = database.query(DATABASE_TABLE, null,
                null,
                null,
                null,
                null,
                ID + " ASC",
                null);
        cursor.moveToFirst();
        Movie movie;
        if (cursor.getCount() > 0) {
            do {
                movie = new Movie();
                movie.setId(cursor.getInt(cursor.getColumnIndexOrThrow(ID)));
                movie.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(TITLE)));
                movie.setOverview(cursor.getString(cursor.getColumnIndexOrThrow(OVERVIEW)));
                movie.setRelease_date(cursor.getString(cursor.getColumnIndexOrThrow(RELEASE_DATE)));
                movie.setVote_average(cursor.getDouble(cursor.getColumnIndexOrThrow(VOTE_AVERAGE)));
                movie.setPoster_path_string(cursor.getString(cursor.getColumnIndexOrThrow(POSTER_PATH_STRING)));
                movie.setBackdrop_path_string(cursor.getString(cursor.getColumnIndexOrThrow(BACKDROP_PATH_STRING)));

                arrayList.add(movie);
                cursor.moveToNext();

            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public long insertMovie(Movie movie) {
        ContentValues args = new ContentValues();
        args.put(ID, movie.getId());
        args.put(TITLE, movie.getTitle());
        args.put(OVERVIEW, movie.getOverview());
        args.put(RELEASE_DATE, movie.getRelease_date());
        args.put(VOTE_AVERAGE, movie.getVote_average());
        args.put(POSTER_PATH_STRING, movie.getPoster_path_string());
        args.put(BACKDROP_PATH_STRING, movie.getBackdrop_path_string());

        return database.insert(DATABASE_TABLE, null, args);
    }

    public int deleteMovie(int id) {
        return database.delete(MOVIE_TABLE_NAME, ID + " = '" + id + "'", null);
    }
}
