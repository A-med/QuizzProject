package com.example.iit.quizzproject.core;

import android.database.sqlite.SQLiteDatabase;

import com.example.iit.quizzproject.database.tables.QuestionList;

/**
 * Created by Emna-Kallel on 08/05/2016.
 */
public class QuestionADO {

    public static void insert(SQLiteDatabase database, Question question){



        String sqlInsert ="INSERT or replace INTO"+ QuestionList.TABLE_QUESTIONS +"("+QuestionList.QUESTION+","+QuestionList.PROPOSITION1+","+QuestionList.PROPOSITION2+","+QuestionList.PROPOSITION3+","+QuestionList.ANSWER+") VALUES('"+question.getText_question()+"','"+question.getProposition1()+"','"+question.getProposition2()+"','"+question.getProposition3()+"','"+question.getAnswer()+"')";
        database.execSQL(sqlInsert);
    }

    public static void delete(SQLiteDatabase database, Question question,int id_question){



        String sqlInsert ="DELETE FROM"+ QuestionList.TABLE_QUESTIONS +" WHERE "+QuestionList._ID+"="+id_question;
        database.execSQL(sqlInsert);
    }

    public static void update(SQLiteDatabase database, Question question,int id_question){



        String sqlInsert ="UPDATE"+ QuestionList.TABLE_QUESTIONS +"SET "+QuestionList.QUESTION+"="+question.getText_question()+","+QuestionList.PROPOSITION1+"="+question.getProposition1()+","+QuestionList.PROPOSITION2+"="+question.getProposition2()+","+QuestionList.PROPOSITION3+"="+question.getProposition3()+","+QuestionList.ANSWER+"="+question.getAnswer()+" WHERE "+QuestionList._ID+"="+id_question;
        database.execSQL(sqlInsert);
    }
}
