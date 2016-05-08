package com.example.iit.quizzproject.database.tables;

import android.content.ContentResolver;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

/**
 * Created by Emna-Kallel on 08/05/2016.
 */
public class QuestionList  implements BaseColumns {
    // questions database table
    public static final String TABLE_QUESTIONS = "questions";

    // table questions fields
    public static final String QUESTION = "question";
    public static final String PROPOSITION1 = "propostion1";
    public static final String PROPOSITION2 = "propostion2";
    public static final String PROPOSITION3 = "propostion3";
    public static final String ANSWER = "answer";



    // questions table creation statement
    private static final String CREATE_QUESTION_TABLE = "CREATE TABLE "
            + TABLE_QUESTIONS + " (" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + QUESTION
            + " TEXT , " +  PROPOSITION1
            + " TEXT , " +  PROPOSITION2
            + " TEXT , " +  PROPOSITION3
            + " TEXT , " +  ANSWER
            + " TEXT); " ;


    // info for content provider
    public static final String CONTENT_PATH = "questions";
    public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE
            + "/vnd.testprovider.questions";
    public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE
            + "/vnd.testprovider.questions";

    public static final String[] PROJECTION_ALL = { _ID, QUESTION, PROPOSITION1,PROPOSITION2,PROPOSITION3,ANSWER};

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
