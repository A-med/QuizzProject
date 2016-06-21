package com.example.iit.quizzproject.database;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;

import com.example.iit.quizzproject.database.tables.QuestionLangage;
import com.example.iit.quizzproject.database.tables.QuestionList;
import com.example.iit.quizzproject.database.tables.QuestionListSqlite;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by Emna-Kallel on 08/05/2016.
 */
public class TestContentProvider extends ContentProvider {
    // database
    private DataBaseHelper mOpenHelper;

    private static final String AUTHORITY = "com.example.iit.quizzproject.database.provider";

    public static final Uri RECORDS_CONTENT_URI_Question = Uri.parse("content://"
            + AUTHORITY + "/" + QuestionListSqlite.CONTENT_PATH);

    public static final Uri RECORDS_CONTENT_URI_LANGAGE = Uri.parse("content://"
            + AUTHORITY + "/" + QuestionLangage.CONTENT_PATH);


    private static final int RECORDS_ALL = 10;
    private static final int RECORD_ID = 11;
    private static final int LANGAGE_ALL = 12;
    private static final int LANGAGE_ID = 13;


    private static final UriMatcher TEST_PROVIDER_URI_MATCHER;

    static {
        TEST_PROVIDER_URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
        TEST_PROVIDER_URI_MATCHER.addURI(AUTHORITY, QuestionListSqlite.CONTENT_PATH,
                RECORDS_ALL);
        TEST_PROVIDER_URI_MATCHER.addURI(AUTHORITY, QuestionListSqlite.CONTENT_PATH
                + "/#", RECORD_ID);

        TEST_PROVIDER_URI_MATCHER.addURI(AUTHORITY, QuestionLangage.CONTENT_PATH,
                LANGAGE_ALL);
        TEST_PROVIDER_URI_MATCHER.addURI(AUTHORITY, QuestionLangage.CONTENT_PATH
                + "/#", LANGAGE_ID);

    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase sqlDB = mOpenHelper.getWritableDatabase();
        int rowsDeleted = 0;
        String id;
        switch (TEST_PROVIDER_URI_MATCHER.match(uri)) {
            case RECORDS_ALL:
                rowsDeleted = sqlDB.delete(QuestionListSqlite.TABLE_QUESTIONS, selection,
                        selectionArgs);
                break;
            case LANGAGE_ALL:
                rowsDeleted = sqlDB.delete(QuestionLangage.TABLE_QUESTIONS, selection,
                        selectionArgs);
                break;
            case LANGAGE_ID:
                //retrieve the record id to delete
                id = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    rowsDeleted = sqlDB.delete(QuestionLangage.TABLE_QUESTIONS,
                            QuestionList._ID + "=" + id, null);
                } else {
                    rowsDeleted = sqlDB.delete(QuestionLangage.TABLE_QUESTIONS,
                            QuestionList._ID + "=" + id + " and " + selection,
                            selectionArgs);
                }
                break;
            case RECORD_ID:
                //retrieve the record id to delete
                id = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    rowsDeleted = sqlDB.delete(QuestionListSqlite.TABLE_QUESTIONS,
                            QuestionList._ID + "=" + id, null);
                } else {
                    rowsDeleted = sqlDB.delete(QuestionListSqlite.TABLE_QUESTIONS,
                            QuestionList._ID + "=" + id + " and " + selection,
                            selectionArgs);
                }
                break;
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return rowsDeleted;
    }

    @Override
    public String getType(Uri uri) {
        switch (TEST_PROVIDER_URI_MATCHER.match(uri)) {
            case RECORDS_ALL:
                return QuestionListSqlite.CONTENT_TYPE;
            case RECORD_ID:
                return QuestionListSqlite.CONTENT_ITEM_TYPE;
            case LANGAGE_ALL:
                return QuestionLangage.CONTENT_TYPE;
            case LANGAGE_ID:
                return QuestionLangage.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase database = mOpenHelper.getWritableDatabase();
        Uri itemUri = null;

        long id = 0;


        switch (TEST_PROVIDER_URI_MATCHER.match(uri)) {
            case RECORDS_ALL:

                id = database.insert(QuestionListSqlite.TABLE_QUESTIONS, null, values);
                if (id > 0) {
                    // notify all listeners of changes and return itemUri:
                    itemUri = ContentUris.withAppendedId(uri, id);
                    getContext().getContentResolver().notifyChange(itemUri, null);
                } else {
                    // something went wrong:
                    throw new SQLException("Problem while inserting into "
                            + QuestionListSqlite.TABLE_QUESTIONS + ", uri: " + uri);
                }
                break;
            case LANGAGE_ALL:
                id = database.insert(QuestionLangage.TABLE_QUESTIONS, null, values);
                if (id > 0) {
                    // notify all listeners of changes and return itemUri:
                    itemUri = ContentUris.withAppendedId(uri, id);
                    getContext().getContentResolver().notifyChange(itemUri, null);
                } else {
                    // something went wrong:
                    throw new SQLException("Problem while inserting into "
                            + QuestionLangage.TABLE_QUESTIONS + ", uri: " + uri);
                }
                break;
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
        return itemUri;
    }



    @Override
    public boolean onCreate() {
        mOpenHelper = new DataBaseHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Log.v("iit", "ContentProvider query method: CallingPackage = " + getCallingPackage());

        } else {
            //TODO

        }

        // Using SQLiteQueryBuilder instead of query() method
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        switch (TEST_PROVIDER_URI_MATCHER.match(uri)) {
            case RECORDS_ALL:
                // Check if the caller has requested a column which does not
                // exists
                checkRecordsTableColumnsQuestion(projection);
                // Set the table
                queryBuilder.setTables(QuestionListSqlite.TABLE_QUESTIONS);
                break;
            case LANGAGE_ALL:
                checkRecordsTableColumnsLangage(projection);
                // Set the table
                queryBuilder.setTables(QuestionLangage.TABLE_QUESTIONS);
                break;

            case RECORD_ID:
                // Check if the caller has requested a column which does not
                // exists
                checkRecordsTableColumnsQuestion(projection);
                // Set the table
                queryBuilder.setTables(QuestionListSqlite.TABLE_QUESTIONS);
                // Adding the ID to the original query
                queryBuilder.appendWhere(QuestionListSqlite._ID + "="
                        + uri.getLastPathSegment());
                break;
            case LANGAGE_ID:
                // Check if the caller has requested a column which does not
                // exists
                checkRecordsTableColumnsLangage(projection);
                // Set the table
                queryBuilder.setTables(QuestionLangage.TABLE_QUESTIONS);
                // Adding the ID to the original query
                queryBuilder.appendWhere(QuestionListSqlite._ID + "="
                        + uri.getLastPathSegment());
                break;
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }

        SQLiteDatabase db = mOpenHelper.getReadableDatabase();
        Cursor cursor = queryBuilder.query(db, projection, selection,
                selectionArgs, null, null, sortOrder);
        // Make sure that potential listeners are getting notified
        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        SQLiteDatabase database = mOpenHelper.getWritableDatabase();
        int rowsUpdated = 0;
        String id;
        switch (TEST_PROVIDER_URI_MATCHER.match(uri)) {
            case RECORD_ID:

                id = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    rowsUpdated = database.update(QuestionListSqlite.TABLE_QUESTIONS,
                            values, QuestionListSqlite._ID + "=" + id, null);
                } else {
                    rowsUpdated = database.update(QuestionListSqlite.TABLE_QUESTIONS,
                            values, QuestionListSqlite._ID + "=" + id + " and "
                                    + selection, selectionArgs);
                }
                break;
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return rowsUpdated;
    }

    private void checkRecordsTableColumnsQuestion(String[] projection) {

        if (projection != null) {
            HashSet<String> requestedColumns = new HashSet<String>(
                    Arrays.asList(projection));
            HashSet<String> availableColumns = new HashSet<String>(
                    Arrays.asList(QuestionListSqlite.PROJECTION_ALL));
            // Check if all columns which are requested are available
            if (!availableColumns.containsAll(requestedColumns)) {
                throw new IllegalArgumentException(
                        "Unknown columns in projection");
            }
        }
    }
    private void checkRecordsTableColumnsLangage(String[] projection) {

        if (projection != null) {
            HashSet<String> requestedColumns = new HashSet<String>(
                    Arrays.asList(projection));
            HashSet<String> availableColumns = new HashSet<String>(
                    Arrays.asList(QuestionLangage.PROJECTION_ALL));
            // Check if all columns which are requested are available
            if (!availableColumns.containsAll(requestedColumns)) {
                throw new IllegalArgumentException(
                        "Unknown columns in projection");
            }
        }
    }
}