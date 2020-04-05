package com.example.myfriendsapp.DAL;

import com.example.myfriendsapp.BE.Friend;

import java.util.List;

public interface IDalManager {

    public List<Friend> getAllFriends();

    public Friend addFriend (Friend friendToAdd);

    public void deleteFriend (Friend friendToDelete);

    public int updateFriend (Friend friendToUpdate);

    public void populateDb();


}
