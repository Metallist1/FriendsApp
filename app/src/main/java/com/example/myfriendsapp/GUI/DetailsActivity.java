package com.example.myfriendsapp.GUI;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;


import com.example.myfriendsapp.BE.Friend;
import com.example.myfriendsapp.DAL.DalManager;
import com.example.myfriendsapp.DAL.IDalManager;
import com.example.myfriendsapp.R;

public class DetailsActivity extends Activity {

    IDalManager dalManager = new DalManager(this);
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
            findViewById(R.id.btn_save).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                  String name =   ((EditText)findViewById(R.id.edit_name)).getText().toString();
                  String address = ((EditText)findViewById(R.id.edit_address)).getText().toString();
                    String birthday =  ((EditText)findViewById(R.id.edit_birthday)).getText().toString();
                    String email =   ((EditText)findViewById(R.id.edit_email)).getText().toString();
                    String phone =  ((EditText)findViewById(R.id.edit_phone)).getText().toString();
                    String link =   ((EditText)findViewById(R.id.edit_link)).getText().toString();
                    dalManager.addFriend(name,address,phone,email,birthday,link,profilepicture);
                }
            });
        }

}
