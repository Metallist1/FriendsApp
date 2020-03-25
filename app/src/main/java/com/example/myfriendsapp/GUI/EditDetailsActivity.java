package com.example.myfriendsapp.GUI;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.myfriendsapp.BE.Friend;
import com.example.myfriendsapp.R;

public class EditDetailsActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editdetails);
        if(getIntent().getExtras()!=null){
            Friend frends = (Friend)getIntent().getSerializableExtra("UserToDisplay");

            ((EditText)findViewById(R.id.edit_name)).setText(frends.getName());
            ((EditText)findViewById(R.id.edit_address)).setText(frends.getAddress());
            ((EditText)findViewById(R.id.edit_birthday)).setText(frends.getBirthday());
            ((EditText)findViewById(R.id.edit_email)).setText(frends.getMail());
            ((EditText)findViewById(R.id.edit_phone)).setText(frends.getPhone());
            ((EditText)findViewById(R.id.edit_link)).setText(frends.getWeb());
            ImageView icon = (ImageView) findViewById(R.id.profilePicture);
            icon.setLayoutParams(new LinearLayout.LayoutParams(200, 200));
            icon.setImageResource(frends.getProfilePicture());
        }
    }
}
