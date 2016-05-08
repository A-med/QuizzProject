package com.example.iit.quizzproject.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.iit.quizzproject.database.tables.QuestionList;

/**
 * Created by Emna-Kallel on 08/05/2016.
 */

public class DataBaseHelper extends SQLiteOpenHelper {
    // database name
    private static final String DATABASE_NAME = "quizz.db";
    // data base version
    private static final int DATABASE_VERSION = 1;

    /**
     * Basic constructor
     *
     * @param context
     */
    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Constructor
     *
     * @param context
     * @param name
     * @param factory
     * @param version
     */
    public DataBaseHelper(Context context, String name,
                          SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        QuestionList.onCreate(db);

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        if (!db.isReadOnly()) {
            db.execSQL("PRAGMA foreign_keys = ON;");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        QuestionList.onUpgrade(db, oldVersion, newVersion);

    }
}
