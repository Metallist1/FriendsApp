package com.example.myfriendsapp.DAL;

import android.content.Context;

import com.example.myfriendsapp.BE.Friend;
import com.example.myfriendsapp.DAL.DAO.FriendDAO;

import java.sql.SQLOutput;
import java.util.List;

public class DalManager implements IDalManager {

    private FriendDAO friendDAO;
    private Context context;
    int profile1;
    int profile2;
    int profile3;

    public DalManager(Context current) {
        context = current;
        friendDAO = new FriendDAO(current);
        profile1 = context.getResources().getIdentifier("@drawable/profile1", null, context.getPackageName());
        profile2 = context.getResources().getIdentifier("@drawable/profile2", null, context.getPackageName());
        profile3 = context.getResources().getIdentifier("@drawable/profile3", null, context.getPackageName());
        //populateDb();
    }
    @Override
    public void populateDb(){
        System.out.println("Populated");
        friendDAO.clearDb();
        addFriend(new Friend("Edward Acki", "W Pizdzie", "20202020", "dupa@chuj.cipa", "2137", "ssd.xd", profile1));
    }

    @Override
    public List<Friend> getAllFriends() {
        friendDAO.open();
        List<Friend> f = friendDAO.getAllFriends();
        friendDAO.close();
        return f;
    }

    @Override
    public Friend addFriend(Friend friendToAdd) {
        friendDAO.open();
        Friend f = friendDAO.addFriend(friendToAdd);
        System.out.println("Friend added");
        friendDAO.close();
        return f;
    }

    @Override
    public void deleteFriend(Friend friendToDelete) {
        friendDAO.open();
        friendDAO.removeFriend(friendToDelete);
        friendDAO.close();
    }

    @Override
    public int updateFriend(Friend friendToUpdate) {
        friendDAO.open();
        int i =  friendDAO.updateFriend(friendToUpdate);
        friendDAO.close();
        return i;
    }
}
