package com.example.ajisaputrars.madesubmission2.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.ajisaputrars.madesubmission2.model.movie.Movie;
import com.example.ajisaputrars.madesubmission2.model.tvShow.TvShow;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.example.ajisaputrars.madesubmission2.db.TvShowDatabaseContract.TvShowColumns.BACKDROP_PATH_STRING;
import static com.example.ajisaputrars.madesubmission2.db.TvShowDatabaseContract.TvShowColumns.FIRST_AIR_DATE;
import static com.example.ajisaputrars.madesubmission2.db.TvShowDatabaseContract.TvShowColumns.ID;
import static com.example.ajisaputrars.madesubmission2.db.TvShowDatabaseContract.TvShowColumns.NAME;
import static com.example.ajisaputrars.madesubmission2.db.TvShowDatabaseContract.TvShowColumns.TV_SHOW_TABLE_NAME;
import static com.example.ajisaputrars.madesubmission2.db.TvShowDatabaseContract.TvShowColumns.OVERVIEW;
import static com.example.ajisaputrars.madesubmission2.db.TvShowDatabaseContract.TvShowColumns.POSTER_PATH_STRING;
import static com.example.ajisaputrars.madesubmission2.db.TvShowDatabaseContract.TvShowColumns.VOTE_AVERAGE;

public class TvShowHelper {
    private static final String DATABASE_TABLE = TV_SHOW_TABLE_NAME;
    private static TvShowDatabaseHelper tvShowDatabaseHelper;
    private static TvShowHelper INSTANCE;

    private static SQLiteDatabase database;

    private TvShowHelper(Context context) {
        tvShowDatabaseHelper = new TvShowDatabaseHelper(context);
    }

    public static TvShowHelper getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (SQLiteOpenHelper.class) {
                if (INSTANCE == null) {
                    INSTANCE = new TvShowHelper(context);
                }
            }
        }
        return INSTANCE;
    }

    public void open() throws SQLException {
        database = tvShowDatabaseHelper.getWritableDatabase();
    }

    public void close() {
        tvShowDatabaseHelper.close();

        if (database.isOpen())
            database.close();
    }

    /**
     * Gunakan method ini untuk ambil semua note yang ada
     * Otomatis diparsing ke dalam model Note
     *
     * @return hasil getAllNotes berbentuk array model note
     */
    public ArrayList<TvShow> getAllMovies() {
        ArrayList<TvShow> arrayList = new ArrayList<>();
        Cursor cursor = database.query(DATABASE_TABLE, null,
                null,
                null,
                null,
                null,
                _ID + " ASC",
                null);
        cursor.moveToFirst();
        TvShow tvShow;
        if (cursor.getCount() > 0) {
            do {
                tvShow = new TvShow();
                tvShow.setId(cursor.getInt(cursor.getColumnIndexOrThrow(ID)));
                tvShow.setName(cursor.getString(cursor.getColumnIndexOrThrow(NAME)));
                tvShow.setOverview(cursor.getString(cursor.getColumnIndexOrThrow(OVERVIEW)));
                tvShow.setFirst_air_date(cursor.getString(cursor.getColumnIndexOrThrow(FIRST_AIR_DATE)));
                tvShow.setVote_average(cursor.getDouble(cursor.getColumnIndexOrThrow(VOTE_AVERAGE)));
                tvShow.setPoster_path_string(cursor.getString(cursor.getColumnIndexOrThrow(POSTER_PATH_STRING)));
                tvShow.setBackdrop_path_string(cursor.getString(cursor.getColumnIndexOrThrow(BACKDROP_PATH_STRING)));

                arrayList.add(tvShow);
                cursor.moveToNext();

            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    /**
     * Gunakan method ini untuk getAllNotes insertNote
     *
     * @para m movie model note yang akan dimasukkan
     * @return id dari data yang baru saja dimasukkan
     */
    public long insertMovie(TvShow tvShow) {
        ContentValues args = new ContentValues();
        args.put(ID, tvShow.getId());
        args.put(NAME, tvShow.getName());
        args.put(OVERVIEW, tvShow.getOverview());
        args.put(FIRST_AIR_DATE, tvShow.getFirst_air_date());
        args.put(VOTE_AVERAGE, tvShow.getVote_average());
        args.put(POSTER_PATH_STRING, tvShow.getPoster_path_string());
        args.put(BACKDROP_PATH_STRING, tvShow.getBackdrop_path_string());

        return database.insert(DATABASE_TABLE, null, args);
    }

    /**
     * Gunakan method ini untuk getAllNotes updateNote
     *
     * @param note model note yang akan diubah
     * @return int jumlah dari row yang ter-updateNote, jika tidak ada yang diupdate maka nilainya 0
     */
//    public int updateNote(Note note) {
//        ContentValues args = new ContentValues();
//        args.put(TITLE, note.getTitle());
//        args.put(DESCRIPTION, note.getDescription());
//        args.put(DATE, note.getDate());
//        return database.update(DATABASE_TABLE, args, _ID + "= '" + note.getId() + "'", null);
//    }

    /**
     * Gunakan method ini untuk getAllNotes deleteNote
     *
     * @param id id yang akan di deleteNote
     * @return int jumlah row yang di deleteNote
     */
    public int deleteNote(int id) {
        return database.delete(TV_SHOW_TABLE_NAME, _ID + " = '" + id + "'", null);
    }
}
