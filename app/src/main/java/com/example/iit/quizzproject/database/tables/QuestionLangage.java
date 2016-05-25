package com.example.iit.quizzproject.database.tables;

import android.content.ContentResolver;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

/**
 * Created by Emna-Kallel on 08/05/2016.
 */
public class QuestionLangage implements BaseColumns {
    // questions database table
    public static final String TABLE_QUESTIONS = "questionLange";

    // table questions fields

    public static final String FRANCAIS = "francais";
    public static final String ANGLAIS = "anglais";




    // questions table creation statement
    private static final String CREATE_QUESTION_TABLE = "CREATE TABLE "
            + TABLE_QUESTIONS + " (" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + FRANCAIS
            + " TEXT , " +  ANGLAIS
            + " TEXT); " ;


    // info for content provider
    public static final String CONTENT_PATH = "questionLange";
    public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE
            + "/vnd.testprovider.questionLange";
    public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE
            + "/vnd.testprovider.questionLange";

    public static final String[] PROJECTION_ALL = { _ID, FRANCAIS, ANGLAIS};

    /**
     * create questions table
     *
     * @param database
     */
    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_QUESTION_TABLE);

    }

    /**
     * upgrade the questions table
     *
     * @param database
     * @param oldVersion
     * @param newVersion
     */
    public static void onUpgrade(SQLiteDatabase database, int oldVersion,
                                 int newVersion) {
        // TODO
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_QUESTIONS);
        onCreate(database);
    }



}
