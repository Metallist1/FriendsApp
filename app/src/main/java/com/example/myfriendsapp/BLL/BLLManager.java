package com.example.myfriendsapp.BLL;

import android.content.Context;

import com.example.myfriendsapp.BE.Friend;
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
    public List<Friend> getAllFriends() {
        return dalManager.getAllFriends();
    }

    @Override
    public Friend addFriend(String name, String address, String phone, String mail, String birthday, String web, int profilePicture) {
        return dalManager.addFriend(name,address,phone,mail,birthday,web,profilePicture);
    }

    @Override
    public Friend deleteFriend(Friend friendToDelete) {
        return dalManager.deleteFriend(friendToDelete);
    }

    @Override
    public Friend updateFriend(Friend friendToUpdate) {
        return dalManager.deleteFriend(friendToUpdate);
    }
}
