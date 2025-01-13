package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "accounts.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "accounts";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NICKNAME = "nickname";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_RANK = "rank";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " TEXT PRIMARY KEY, " +
                COLUMN_NICKNAME + " TEXT, " +
                COLUMN_PASSWORD + " TEXT, " +
                COLUMN_RANK + " TEXT)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public long addAccount(String id, String nickname, String password, String rank) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, id);
        values.put(COLUMN_NICKNAME, nickname);
        values.put(COLUMN_PASSWORD, password);
        values.put(COLUMN_RANK, rank);

        return db.insert(TABLE_NAME, null, values);
    }

    public int updateAccount(String id, String nickname, String password, String rank) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NICKNAME, nickname);
        values.put(COLUMN_PASSWORD, password);
        values.put(COLUMN_RANK, rank);

        return db.update(TABLE_NAME, values, COLUMN_ID + " = ?", new String[]{id});
    }

    public int deleteAccount(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, COLUMN_ID + " = ?", new String[]{id});
    }

    public ArrayList<String> getAllAccounts() {
        ArrayList<String> accountsList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                String accountDetails = "KOD : " + cursor.getString(cursor.getColumnIndex(COLUMN_ID)) +
                        " , KULLANICI ADI : " + cursor.getString(cursor.getColumnIndex(COLUMN_NICKNAME)) +
                        " , ŞİFRE : " + cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD)) +
                        " , LİG : " + cursor.getString(cursor.getColumnIndex(COLUMN_RANK));
                accountsList.add(accountDetails);
            }
            cursor.close();
        }
        return accountsList;
    }
}
