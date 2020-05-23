package com.example.automap;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.IOException;
import java.util.List;

public class AfterLoggedActivity extends AppCompatActivity {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_logged);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_favories, R.id.navigation_explore)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
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
