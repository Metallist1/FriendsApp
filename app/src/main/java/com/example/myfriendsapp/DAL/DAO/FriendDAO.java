package com.example.myfriendsapp.DAL.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.myfriendsapp.BE.Friend;
import com.example.myfriendsapp.DAL.DB.FriendsDatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class FriendDAO {
    public static final String LOGTAG = "EMP_MNGMNT_SYS";
    private Context context;
    private SQLiteDatabase friendsDatabase;
    private SQLiteOpenHelper dbHelper;

    private static final String[] allColumns = {
            FriendsDatabaseHelper.COLUMN_ID,
            FriendsDatabaseHelper.COLUMN_NAME,
            FriendsDatabaseHelper.COLUMN_ADDRESS,
            FriendsDatabaseHelper.COLUMN_PHONE,
            FriendsDatabaseHelper.COLUMN_MAIL,
            FriendsDatabaseHelper.COLUMN_BIRTHDAY,
            FriendsDatabaseHelper.COLUMN_WEB,
            FriendsDatabaseHelper.COLUMN_AVATAR
    };

    public FriendDAO(Context current){
        this.context = current;
        this.dbHelper = new FriendsDatabaseHelper(context);
    }
    public void open(){
        Log.i(LOGTAG,"Database Opened");
        friendsDatabase = dbHelper.getWritableDatabase();
    }
    public void close(){
        Log.i(LOGTAG, "Database Closed");
        dbHelper.close();
    }
    public void clearDb(){
        dbHelper.getWritableDatabase().execSQL("DELETE FROM " + FriendsDatabaseHelper.TABLE_FRIENDS);
    };

    public Friend addFriend(Friend friend){
        ContentValues values  = new ContentValues();
        values.put(FriendsDatabaseHelper.COLUMN_NAME, friend.getName());
        values.put(FriendsDatabaseHelper.COLUMN_ADDRESS, friend.getAddress());
        values.put(FriendsDatabaseHelper.COLUMN_BIRTHDAY, friend.getBirthday());
        values.put(FriendsDatabaseHelper.COLUMN_MAIL, friend.getMail());
        values.put(FriendsDatabaseHelper.COLUMN_PHONE, friend.getPhone());
        values.put(FriendsDatabaseHelper.COLUMN_WEB, friend.getWeb());
        values.put(FriendsDatabaseHelper.COLUMN_AVATAR, friend.getProfilePicture());
        long insertId = friendsDatabase.insert(FriendsDatabaseHelper.TABLE_FRIENDS,null,values);
        friend.setId(insertId);
        return friend;
    }
    public Friend getFriend(long id) {

        Cursor cursor = friendsDatabase.query(FriendsDatabaseHelper.TABLE_FRIENDS,allColumns,FriendsDatabaseHelper.COLUMN_ID + "=?",new String[]{String.valueOf(id)},null,null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        return new Friend(Long.parseLong(cursor.getString(0)),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getInt(7));
    }

    public List<Friend> getAllFriends() {
        Cursor cursor = friendsDatabase.query(FriendsDatabaseHelper.TABLE_FRIENDS,allColumns,null,null,null, null, null);
        List<Friend> friends = new ArrayList<>();
        if(cursor.getCount() > 0){
            while(cursor.moveToNext()){
                Friend f = new Friend(cursor.getLong(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getInt(7));
                friends.add(f);
            }
        }
        return friends;
    }

    public int updateFriend(Friend friend) {

        ContentValues values = new ContentValues();

        System.out.println("Name:"+friend.getName());
        System.out.println("Address:"+friend.getAddress());
        System.out.println("Phone:"+friend.getPhone());
        System.out.println("Mail:"+friend.getMail());
        System.out.println("Birthday:"+friend.getBirthday());
        System.out.println("Web:"+friend.getWeb());

        values.put(FriendsDatabaseHelper.COLUMN_NAME, friend.getName());
        values.put(FriendsDatabaseHelper.COLUMN_BIRTHDAY, friend.getBirthday());
        values.put(FriendsDatabaseHelper.COLUMN_ADDRESS, friend.getAddress());
        values.put(FriendsDatabaseHelper.COLUMN_MAIL, friend.getMail());
        values.put(FriendsDatabaseHelper.COLUMN_PHONE, friend.getPhone());
        values.put(FriendsDatabaseHelper.COLUMN_WEB, friend.getWeb());

        return friendsDatabase.update(FriendsDatabaseHelper.TABLE_FRIENDS, values,
                FriendsDatabaseHelper.COLUMN_ID + "=?",new String[] { String.valueOf(friend.getId())});
    }

    public void removeFriend(Friend friend) {
        friendsDatabase.delete(FriendsDatabaseHelper.TABLE_FRIENDS, FriendsDatabaseHelper.COLUMN_ID + "=" + friend.getId(), null);
        System.out.println(getAllFriends().size());
    }
    }

