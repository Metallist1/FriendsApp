package com.example.myfriendsapp.BLL;

import android.content.Context;

import com.example.myfriendsapp.BE.Friend;
import com.example.myfriendsapp.BE.FriendList;
import com.example.myfriendsapp.DAL.DalManager;
import com.example.myfriendsapp.DAL.IDalManager;

import java.util.List;

public class BLLManager implements IBLLManager {

    private Context context;
    private IDalManager dalManager;

    public BLLManager(Context current) {
        this.context = current;
        dalManager = new DalManager(context);
    }
    @Override
    public void populateDb(){
       dalManager.populateDb();
    }


    @Override
    public List<Friend> getAllFriends() {
        FriendList.list = dalManager.getAllFriends();
        return FriendList.list;
    }



    @Override
    public Friend addFriend(String name, String address, String phone, String mail, String birthday, String web, int profilePicture) {
        return dalManager.addFriend(new Friend(name, address, phone, mail, birthday, web, profilePicture));
    }

    @Override
    public void deleteFriend(Friend friendToDelete) {
         dalManager.deleteFriend(friendToDelete);
    }

    @Override
    public int updateFriend(Friend friendToUpdate) {
         return dalManager.updateFriend(friendToUpdate);
    }
}
