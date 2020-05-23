package com.example.automap;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    com.example.automap.DatabaseHelper db;
    EditText e1, e2, e3;
    Button b1, b2, b3;
    private GoogleMap mMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new com.example.automap.DatabaseHelper(this);
        e1 = (EditText) findViewById(R.id.email);
        e2 = (EditText) findViewById(R.id.pass);
        e3 = (EditText) findViewById(R.id.cpass);
        b1 = (Button) findViewById(R.id.registerbutton);
        b2 = (Button) findViewById(R.id.button2);
        b3 = (Button) findViewById(R.id.search_address);



        b2.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {

                                      Intent i = new Intent(MainActivity.this, LoginActivity.class);
                                      startActivity(i);

                                  }
                              });



        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s1 = e1.getText().toString();
                String s2 = e2.getText().toString();
                String s3 = e3.getText().toString();

                RadioButton radioButton = findViewById(R.id.radioButton);

                String result = pass_mailCheck(s1,s2,s3);

                if (radioButton.isChecked()) {


                    switch(result){

                        case "Fields are empty":
                            Toast.makeText(getApplicationContext(), "Fields are empty", Toast.LENGTH_SHORT).show();

                            break;

                        case "Registered Successfully":
                            Toast.makeText(getApplicationContext(), "Registered Successfully", Toast.LENGTH_SHORT).show();

                            break;

                        case "E-mail already exists":
                            Toast.makeText(getApplicationContext(), "E-mail already exists", Toast.LENGTH_SHORT).show();

                            break;

                        case "Password do not match":
                            Toast.makeText(getApplicationContext(), "Password do not match", Toast.LENGTH_SHORT).show();

                            break;

                    }

                    }
                 else {
                    Toast.makeText(getApplicationContext(), "Please check accept registering button", Toast.LENGTH_SHORT).show();
                }









            }

        });
    }

    public String pass_mailCheck(String s1, String s2, String s3) {

        s1 = e1.getText().toString();
        s2 = e2.getText().toString();
        s3 = e3.getText().toString();


        if (s1.equals("") || s2.equals("") || s3.equals("")) {
            return  "Fields are empty";
        }
        else {
            if (s2.equals(s3)) {
                Boolean checkEmail = db.checkEmail(s1);
                if (checkEmail == true) {
                    Boolean insert = db.insert(s1, s2);
                    if (insert == true) {
                        return "Registered Successfully";
                    }
                } else {
                    return  "E-mail already exists";
                }
            } else {
                return  "Password do not match";

            }

        }

     return "";

    }


    public void searchClick(View view) {

        EditText addressField = (EditText) findViewById(R.id.location_search);
        String address = addressField.getText().toString();

        List<Address> addressList = null ;
        MarkerOptions userMarkerOptions = new MarkerOptions();

        if(!TextUtils.isEmpty(address))
        {
            Geocoder geocoder = new Geocoder(this);
            try
            {
                addressList = geocoder.getFromLocationName(address, 6);

                if(addressList != null)
                {
                    for (int i=0; i<addressList.size(); i++)
                    {

                        Address userAddress = addressList.get(i);
                        LatLng latLng = new LatLng(userAddress.getLatitude(), userAddress.getLongitude());

                        userMarkerOptions.position(latLng);
                        userMarkerOptions.title(address);
                        userMarkerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));

                        mMap.addMarker(userMarkerOptions);

                        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                        mMap.animateCamera(CameraUpdateFactory.zoomTo(10));


                    }


                }
                else
                {

                    Toast.makeText(this, "Location not found!", Toast.LENGTH_SHORT).show();
                }

            }

            catch (IOException e)
            {
                e.printStackTrace();
            }

        }
        else
        {
            Toast.makeText(this,"Please write a location name...", Toast.LENGTH_SHORT).show();
        }
    }
}