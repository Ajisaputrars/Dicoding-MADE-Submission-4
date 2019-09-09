package com.example.ajisaputrars.madesubmission2.db;

import android.provider.BaseColumns;

class MovieDatabaseContract {

    static final class MovieColumns implements BaseColumns {
        static final String MOVIE_TABLE_NAME = "movie";

        static final String ID = "id";
        static final String TITLE = "title";
        static final String OVERVIEW = "overview";
        static final String RELEASE_DATE= "release_date";
        static final String VOTE_AVERAGE = "vote_average";
        static final String POSTER_PATH_STRING = "poster_path_string";
        static final String BACKDROP_PATH_STRING = "backdrop_path_string";
    }
}
