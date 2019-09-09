package com.example.ajisaputrars.madesubmission2.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.ajisaputrars.madesubmission2.db.TvShowDatabaseContract.TvShowColumns.TV_SHOW_TABLE_NAME;
import com.example.ajisaputrars.madesubmission2.db.TvShowDatabaseContract.TvShowColumns;

public class TvShowDatabaseHelper extends SQLiteOpenHelper {

    private static final String TV_SHOW_DATABASE_NAME = "dbtvshow";

    private static final int TV_SHOW_DATABASE_VERSION = 1;

    private static final String SQL_CREATE_TABLE_TV_SHOW = String.format("CREATE TABLE %s" +
                    " (%s TEXT NULL," +
                    " %s TEXT NULL," +
                    " %s TEXT NULL," +
                    " %s TEXT NULL," +
                    " %s TEXT NULL," +
                    " %s TEXT NULL," +
                    " %s TEXT NULL)",
            TV_SHOW_TABLE_NAME,
            TvShowColumns.ID,
            TvShowColumns.NAME,
            TvShowColumns.OVERVIEW,
            TvShowColumns.FIRST_AIR_DATE,
            TvShowColumns.VOTE_AVERAGE,
            TvShowColumns.POSTER_PATH_STRING,
            TvShowColumns.BACKDROP_PATH_STRING
    );

    TvShowDatabaseHelper(Context context) {
        super(context, TV_SHOW_DATABASE_NAME, null, TV_SHOW_DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_TV_SHOW);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TV_SHOW_TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
