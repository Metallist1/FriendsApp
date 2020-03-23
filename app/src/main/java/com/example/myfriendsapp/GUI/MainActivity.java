package com.example.myfriendsapp.GUI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.myfriendsapp.GUI.Extra.CustomAdapter;
import com.example.myfriendsapp.BE.Friend;
import com.example.myfriendsapp.DAL.DalManager;
import com.example.myfriendsapp.DAL.IDalManager;
import com.example.myfriendsapp.R;

import java.io.Serializable;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private ListView friendListViewas;
    private List<Friend> friendList;
    private IDalManager dalManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dalManager = new DalManager(this);
        setUpInitialList();

    }

    private void setUpInitialList(){
        friendListViewas = (ListView)findViewById(R.id.friendListView);

        friendList = setUpFriendList();

        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), friendList);
        friendListViewas.setAdapter(customAdapter);

        friendListViewas.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {


                Intent myIntent = new Intent(arg0.getContext(), DetailsActivity.class); //Create intent
                Friend fren = friendList.get(position);

                myIntent.putExtra("UserToDisplay",  (Serializable)fren); //Add Extra info
                startActivityForResult(myIntent, 0); //Wait for results
            }
        });
    }

    //Version 2 of this method will switch from temp data to real data
    private List<Friend> setUpFriendList(){
        return dalManager.getAllFriends();
    }

    private void setUpNewDetails(){
        Intent myIntent = new Intent(this, DetailsActivity.class); //Create intent
        startActivityForResult(myIntent, 0); //Wait for results
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.game_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.createNew:
                setUpNewDetails();
                return true;
            case R.id.orderBy_new:
               // orderList(1);
                return true;
            case R.id.orderBy_distance:
               // orderList(2);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
