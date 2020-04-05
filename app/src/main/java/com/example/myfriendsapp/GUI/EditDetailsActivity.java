package com.example.myfriendsapp.GUI;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;

import com.example.myfriendsapp.BE.Friend;
import com.example.myfriendsapp.BLL.BLLManager;
import com.example.myfriendsapp.BLL.IBLLManager;
import com.example.myfriendsapp.GUI.Extra.AppLocationService;
import com.example.myfriendsapp.GUI.Extra.LocationAddress;
import com.example.myfriendsapp.R;

import java.io.Serializable;

public class EditDetailsActivity extends Activity {

    Friend friend = null;
    IBLLManager bllManager;
    AppLocationService appLocationService;
    EditText nameText;
    EditText addressText;
    EditText birthdayText;
    EditText emailText;
    EditText phoneText;
    EditText linkText;
    ImageView icon;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editdetails);
        bllManager = new BLLManager(this);
        appLocationService = new AppLocationService(
                this);
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

        nameText = findViewById(R.id.edit_name);
        addressText = findViewById(R.id.edit_address);
        birthdayText = findViewById(R.id.edit_birthday);
        emailText = findViewById(R.id.edit_email);
        phoneText = findViewById(R.id.edit_phone);
        linkText = findViewById(R.id.edit_link);
        icon = findViewById(R.id.profilePicture);

        if(getIntent().getExtras()!=null){
            friend = (Friend) getIntent().getSerializableExtra("UserToDisplay");
            setUpText();
        }
        setUpButtons();
    }



    private void setUpText(){
        nameText.setText(friend.getName());
        addressText.setText(friend.getAddress());
        birthdayText.setText(friend.getBirthday());
        emailText.setText(friend.getMail());
        phoneText.setText(friend.getPhone());
        linkText.setText(friend.getWeb());
        icon.setLayoutParams(new LinearLayout.LayoutParams(200, 200));
        icon.setImageResource(friend.getProfilePicture());
    }
    private void setUpButtons(){
        findViewById(R.id.btn_home).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Location location = appLocationService
                        .getLocation(LocationManager.GPS_PROVIDER);
                if (location != null) {
                    double latitude = location.getLatitude();
                    double longitude = location.getLongitude();
                    LocationAddress locationAddress = new LocationAddress();
                    locationAddress.getAddressFromLocation(latitude, longitude,
                            getApplicationContext(), new GeocoderHandler());
                }
            }
        });

        findViewById(R.id.btn_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(friend == null)
                 bllManager.addFriend(nameText.getText().toString(), addressText.getText().toString(), phoneText.getText().toString(), emailText.getText().toString(), birthdayText.getText().toString(), linkText.getText().toString(), 0);
                else{
                    friend.setName(nameText.getText().toString());
                    friend.setAddress(addressText.getText().toString());
                    friend.setPhone(phoneText.getText().toString());
                    friend.setMail(emailText.getText().toString());
                    friend.setBirthday(birthdayText.getText().toString());
                    friend.setWeb(linkText.getText().toString());
                    bllManager.updateFriend(friend);
                }
                setResult(Activity.RESULT_OK, new Intent());
                finish();

            }
        });
    }
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }
            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    private class GeocoderHandler extends Handler {
        @Override
        public void handleMessage(Message message) {
            String locationAddress;
            switch (message.what) {
                case 1:
                    Bundle bundle = message.getData();
                    locationAddress = bundle.getString("address");
                    break;
                default:
                    locationAddress = null;
            }
            ((EditText)findViewById(R.id.edit_address)).setText(locationAddress);
        }
    }
}
