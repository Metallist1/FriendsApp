package com.example.myfriendsapp.DAL.DB;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class FriendsDatabaseHelper extends SQLiteOpenHelper{


        private static final String DATABASE_NAME = "friends.db";
        private static final int DATABASE_VERSION = 1;

        public static final String TABLE_FRIENDS = "friends";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_ADDRESS = "address";
        public static final String COLUMN_PHONE = "phoneNumber";
        public static final String COLUMN_MAIL= "email";
        public static final String COLUMN_BIRTHDAY= "birthday";
        public static final String COLUMN_WEB= "website";
        public static final String COLUMN_AVATAR= "avatar";

        private static final String TABLE_CREATE =
                "CREATE TABLE " + TABLE_FRIENDS + " (" +
                        COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_NAME + " TEXT, " +
                        COLUMN_ADDRESS + " TEXT, " +
                        COLUMN_PHONE + " TEXT, " +
                        COLUMN_MAIL + " TEXT, " +
                        COLUMN_BIRTHDAY + " TEXT, " +
                        COLUMN_WEB + " TEXT, " +
                        COLUMN_AVATAR + " NUMERIC " +
                        ")";
        public FriendsDatabaseHelper(Context context){ super(context,DATABASE_NAME,null,DATABASE_VERSION); }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(TABLE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS "+TABLE_FRIENDS);
            db.execSQL(TABLE_CREATE);
        }
    }
