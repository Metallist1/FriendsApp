package com.example.myfriendsapp.GUI;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.myfriendsapp.BE.Friend;
import com.example.myfriendsapp.R;

public class DetailsActivity extends Activity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_inserteddetails);
            if(getIntent().getExtras()!=null){
                Friend frends = (Friend)getIntent().getSerializableExtra("UserToDisplay");

                ((TextView)findViewById(R.id.txt_name)).setText(frends.getName());
                ((TextView)findViewById(R.id.txt_address)).setText(frends.getAddress());
                ((TextView)findViewById(R.id.txt_birthday)).setText(frends.getBirthday());
                ((TextView)findViewById(R.id.txt_mail)).setText(frends.getMail());
                ((TextView)findViewById(R.id.txt_number)).setText(frends.getPhone());
                ((TextView)findViewById(R.id.txt_web)).setText(frends.getWeb());
                ImageView icon = (ImageView) findViewById(R.id.profilePicture);
                icon.setLayoutParams(new LinearLayout.LayoutParams(200, 200));
                icon.setImageResource(frends.getProfilePicture());
            }
        }

}
