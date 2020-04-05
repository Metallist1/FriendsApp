package com.example.myfriendsapp.BLL;

import com.example.myfriendsapp.BE.Friend;

import java.util.List;

public interface IBLLManager {

    public List<Friend> getAllFriends();

    public Friend addFriend(String name, String address, String phone, String mail, String birthday, String web, int profilePicture);

    public void deleteFriend(Friend friendToDelete);

    public int updateFriend(Friend friendToUpdate);

    public void populateDb();

}
