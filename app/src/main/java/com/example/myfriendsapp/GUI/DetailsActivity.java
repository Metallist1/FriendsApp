package com.example.myfriendsapp.GUI;

import android.app.Activity;
import android.os.Bundle;


import com.example.myfriendsapp.BE.Friend;
import com.example.myfriendsapp.R;

public class DetailsActivity extends Activity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_details);
            if(getIntent().getExtras()!=null){
                Friend frends = (Friend)getIntent().getSerializableExtra("UserToDisplay");
                System.out.println(frends.getName());
            }
        }
}
