package com.example.myfriendsapp.BE;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myfriendsapp.BE.Friend;
import com.example.myfriendsapp.R;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends BaseAdapter {
    Context context;
    List<Friend> friendList = new ArrayList<>();
    LayoutInflater inflter;

    public CustomAdapter(Context applicationContext, List<Friend> friendList) {
        this.context = applicationContext;
        this.friendList = friendList;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return friendList.size();
    }

    @Override
    public Object getItem(int i) {
        return friendList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        try {

            convertView = inflter.inflate(R.layout.activity_listview, null);
            TextView name = (TextView) convertView.findViewById(R.id.nameText);
            TextView location = (TextView) convertView.findViewById(R.id.locationText);
            ImageView icon = (ImageView) convertView.findViewById(R.id.profileView);


            name.setText(friendList.get(position).getName());

            location.setText(friendList.get(position).getAddress());

            icon.setLayoutParams(new LinearLayout.LayoutParams(200, 200));
            icon.setImageResource(friendList.get(position).getProfilePicture());
            return convertView;
        } catch (Exception ex) {
            return null;
        }
    }
}

