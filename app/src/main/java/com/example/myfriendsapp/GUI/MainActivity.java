package com.example.myfriendsapp.GUI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.myfriendsapp.BE.CustomAdapter;
import com.example.myfriendsapp.BE.Friend;
import com.example.myfriendsapp.DAL.DalManager;
import com.example.myfriendsapp.DAL.IDalManager;
import com.example.myfriendsapp.R;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    private ListView friendListViewas;

    private IDalManager dalManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        dalManager = new DalManager(this);

        friendListViewas = (ListView)findViewById(R.id.friendListView);

        List<Friend> friendList = setUpFriendList();

        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), friendList);
        friendListViewas.setAdapter(customAdapter);

    }


    //Version 2 of this method will switch from temp data to real data
    private List<Friend> setUpFriendList(){
        return dalManager.getAllFriends();
    }



}
