package com.example.iit.quizzproject.database.tables;

import android.content.ContentResolver;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

/**
 * Created by Emna-Kallel on 08/05/2016.
 */
public class QuestionListSqlite implements BaseColumns {
    // questions database table
    public static final String TABLE_QUESTIONS = "questions";

    // table questions fields

    public static final String id_QUESTION = "id_question"  ;
    public static final String id_PROPOSITION1 = "id_proposition1";
    public static final String id_PROPOSITION2 = "id_proposition2";
    public static final String id_PROPOSITION3 = "id_proposition3";
    public static final String id_ANSWER = "id_answer";



    // questions table creation statement
    private static final String CREATE_QUESTION_TABLE = "CREATE TABLE "
            + TABLE_QUESTIONS
            + " ("
            + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + id_QUESTION + " INTEGER   " + "REFERENCES "+ QuestionLangage.TABLE_QUESTIONS +" ( "+QuestionLangage._ID + " ) ,"
            +  id_PROPOSITION1 + " INTEGER " + "REFERENCES "+ QuestionLangage.TABLE_QUESTIONS +" ( "+QuestionLangage._ID + " ) ,"
            +  id_PROPOSITION2 + " INTEGER " + "REFERENCES "+ QuestionLangage.TABLE_QUESTIONS +" ( "+QuestionLangage._ID + " ) ,"
            +  id_PROPOSITION3 + " INTEGER " + "REFERENCES "+ QuestionLangage.TABLE_QUESTIONS +" ( "+QuestionLangage._ID + " ) ,"
            +  id_ANSWER + " INTEGER " + "REFERENCES "+ QuestionLangage.TABLE_QUESTIONS +" ( "+QuestionLangage._ID + " ) );";


    // info for content provider
    public static final String CONTENT_PATH = "questions";
    public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE
            + "/vnd.testprovider.questions";
    public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE
            + "/vnd.testprovider.questions";

    public static final String[] PROJECTION_ALL = {  _ID,  id_QUESTION,id_PROPOSITION1,id_PROPOSITION2,id_PROPOSITION3,id_ANSWER};

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
