package com.example.myfriendsapp.GUI;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Telephony;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;


import com.example.myfriendsapp.BE.Friend;
import com.example.myfriendsapp.BE.FriendList;
import com.example.myfriendsapp.BLL.BLLManager;
import com.example.myfriendsapp.BLL.IBLLManager;
import com.example.myfriendsapp.R;

import java.io.Serializable;

public class DetailsActivity extends Activity {

    private Friend friend ;
    private IBLLManager bllManager;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_inserteddetails);
            bllManager = new BLLManager(this);

            if(getIntent().getExtras() != null){
                setUpText();
            }
            setUpButtons();

        }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            if(resultCode == Activity.RESULT_OK){
                setResult(Activity.RESULT_OK, new Intent());
                finish();
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }

        private void setUpText(){
            friend = (Friend)getIntent().getSerializableExtra("UserToDisplay");

            ((TextView)findViewById(R.id.txt_name)).setText(friend.getName());
            ((TextView)findViewById(R.id.txt_address)).setText(friend.getAddress());
            ((TextView)findViewById(R.id.txt_birthday)).setText(friend.getBirthday());
            ((TextView)findViewById(R.id.txt_mail)).setText(friend.getMail());
            ((TextView)findViewById(R.id.txt_number)).setText(friend.getPhone());
            ((TextView)findViewById(R.id.txt_web)).setText(friend.getWeb());
            ImageView icon = findViewById(R.id.profilePicture);
            icon.setLayoutParams(new LinearLayout.LayoutParams(200, 200));
            icon.setImageResource(friend.getProfilePicture());
        }

        private void setUpButtons(){
            findViewById(R.id.btn_text).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent smsIntent = new Intent(Intent.ACTION_SENDTO);
                    smsIntent.addCategory(Intent.CATEGORY_DEFAULT);
                    smsIntent.setType("vnd.android-dir/mms-sms");
                    smsIntent.setData(Uri.parse("sms:" + ((TextView)findViewById(R.id.txt_number)).getText().toString()));
                    startActivity(smsIntent);
                }
            });

            findViewById(R.id.btn_show).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + ((TextView)findViewById(R.id.txt_address)).getText().toString());
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    mapIntent.setPackage("com.google.android.apps.maps");
                    startActivity(mapIntent);
                }
            });

            findViewById(R.id.btn_delete).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    bllManager.deleteFriend(friend);
                    System.out.println(friend.getId());
                    setResult(Activity.RESULT_OK, new Intent());
                    finish();
                }
            });

            findViewById(R.id.btn_update).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent myIntent = new Intent(view.getContext(), EditDetailsActivity.class); //Create intent
                    myIntent.putExtra("UserToDisplay", friend); //Add Extra info
                    startActivityForResult(myIntent, 0); //Wait for results
                }
            });
        }

}
