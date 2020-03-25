package com.example.myfriendsapp.DAL.DAO;

import android.content.Context;

import com.example.myfriendsapp.BE.Friend;

import java.util.ArrayList;
import java.util.List;

public class FriendDAO {
    private Context context;

    public FriendDAO(Context current){
        this.context = current;
    }
    public List<Friend> getAllFriends() {
        //name, address , phone ,mail, birthday,web,profile picture url
        List<Friend> newFriendList = new ArrayList<>();
        for(int x = 0; x < 20; x++){
            String name = "Nedas";
            String phone= "59191";
            String mail= "nedas";
            String birthday= "1999/07/01";
            String web= "www.wikipedia.com/";
            String address= "Springsbjerg Kirkavej 2";

            for( int i = 0 ; i < x ; i++) {
                name = name + "s";
                phone= phone + "9";
                mail= mail+"a";
                web = web +"a";
            }
            mail = mail + "@gmail.com";


            String uri = "@drawable/profile1";

            int profile = context.getResources().getIdentifier(uri, null, context.getPackageName());

            Friend fren = new Friend(name,address,phone,mail,birthday,web,profile);

            newFriendList.add(fren);
        }
        return newFriendList;

    }
}
