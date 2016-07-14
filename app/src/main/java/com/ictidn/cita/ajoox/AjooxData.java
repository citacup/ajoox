package com.ictidn.cita.ajoox;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import static com.ictidn.cita.ajoox.Constant.*;

import static android.provider.BaseColumns._ID;

/**
 * Created by ajou on 7/14/2016.
 */
public class AjooxData extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "ajoox.db";
    private static final int DATABASE_VERSION = 1;

    /** Create a helper object for the Events database */
    public AjooxData(Context ctx) {
        super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("CREATED","    CREATED");

        db.execSQL("CREATE TABLE " + ARTIST + " ("
                + _ID               + "INTEGER PRIMARY KEY AUTOINCREMENT,"
                + ARTIST_NAME       + "TEXT,"
                + SEX               + "SEX);"
        );

        db.execSQL("CREATE TABLE " + SONGS + " ("
                + _ID      + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + TITLE    + " TEXT,"
                + GENRE   + " TEXT,"
                + PATH   + " TEXT,"
                + ID_ARTIST    + " INTEGER,"
                + ID_ALBUM + " INTEGER,"
                + "FOREIGN KEY("+ID_ARTIST+") REFERENCES "+ARTIST+"("+_ID+"));"
        );

        db.execSQL("CREATE TABLE " + ALBUMS + " ("
                + _ID           + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + ALBMNAME      + " TEXT,"
                + RELEASE_DATE  + " DATE,"
                + LABEL         + " TEXT,"
                + ID_ALBUM_ARTIST + "INTEGER,"
                + "FOREIGN KEY("+ID_ALBUM_ARTIST+") REFERENCES "+ARTIST+"("+_ID+"));"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS " + SONGS);
        onCreate(db);
    }

}
