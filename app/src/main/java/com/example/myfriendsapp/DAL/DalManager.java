package com.example.myfriendsapp.DAL;

import android.content.Context;

import com.example.myfriendsapp.BE.Friend;
import com.example.myfriendsapp.DAL.DAO.FriendDAO;

import java.util.List;

public class DalManager implements IDalManager {

    FriendDAO friendDAO;
    private Context context;

    public DalManager(Context current) {
        this.context = current;
        friendDAO = new FriendDAO(current);
    }

    @Override
    public List<Friend> getAllFriends() {
        return friendDAO.getAllFriends();
    }

    @Override
    public Friend addFriend(String name, String address, String phone, String mail, String birthday, String web, int profilePicture) {
        return null;
    }

    @Override
    public Friend deleteFriend(Friend friendToDelete) {
        return null;
    }

    @Override
    public Friend updateFriend(Friend friendToUpdate) {
        return null;
    }
}
