package com.example.myfriendsapp.GUI;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.myfriendsapp.BE.FriendList;
import com.example.myfriendsapp.BLL.BLLManager;
import com.example.myfriendsapp.BLL.IBLLManager;
import com.example.myfriendsapp.GUI.Extra.CustomAdapter;
import com.example.myfriendsapp.BE.Friend;
import com.example.myfriendsapp.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private ListView friendListView;
    private IBLLManager bllManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bllManager = new BLLManager(this);
        bllManager.populateDb();
        setUpInitialList();

        System.out.println("xDDD");

    }

    private void setUpInitialList(){
        friendListView = findViewById(R.id.friendListView);

        System.out.println(setUpFriendList().size());


        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext());
        friendListView.setAdapter(customAdapter);

        friendListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Intent myIntent = new Intent(arg0.getContext(), DetailsActivity.class);
                myIntent.putExtra("UserToDisplay", FriendList.list.get(position));
                startActivityForResult(myIntent, 0);
            }
        });
    }

     public List<Friend> setUpFriendList(){
        FriendList.list = new ArrayList<>();
        return bllManager.getAllFriends();
    }

    private void setUpNewDetails(){
        Intent myIntent = new Intent(this, EditDetailsActivity.class); //Create intent
        startActivityForResult(myIntent, 0); //Wait for results
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.game_menu, menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            if(resultCode == Activity.RESULT_OK){
                System.out.println("ResultOK");
                setUpInitialList();
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
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
