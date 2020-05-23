package com.example.automap;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements
        OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {
    private GoogleMap mMap;
    private GoogleApiClient googleApiClient;
    private LocationRequest locationRequest;
    private Location lastLocation;
    private Marker currentUserLocationMarker;
    private static final int Request_User_Location_Code = 99;
    double latitide, longitude;
    private int ProximityRadius = 10000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            checkUserLocationPermission();
        }


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }



    public void onClick (View v)
    {

        String carGalleries = "car_dealer", carRental = "car_rental", carWash = "car_wash", electricCharging = "electric_vehicle_charging_station" , gasStations = "gas_station", parkingSpaces = "parking";
        Object transferData[] = new Object[2];
        GetNearbyPlaces getNearbyPlaces = new GetNearbyPlaces();





        switch (v.getId())
        {
            case R.id.search_address:
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
                break;

            case R.id.carGalleries:

                mMap.clear();

                String url = getUrl(latitide, longitude, carGalleries);
                transferData[0] = mMap;
                transferData[1] = url;


                getNearbyPlaces.execute(transferData);
                Toast.makeText(this, "Searching for nearby car galleries...", Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "Showing nearby car galleries...", Toast.LENGTH_SHORT).show();

                break;

            case R.id.carRental:

                mMap.clear();

                String url2 = getUrl(latitide, longitude, carRental);
                transferData[0] = mMap;
                transferData[1] = url2;


                getNearbyPlaces.execute(transferData);
                Toast.makeText(this, "Searching for nearby car rentals...", Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "Showing nearby car rentals...", Toast.LENGTH_SHORT).show();

                break;

            case R.id.carWash:

                mMap.clear();

                String url3 = getUrl(latitide, longitude, carWash);
                transferData[0] = mMap;
                transferData[1] = url3;


                getNearbyPlaces.execute(transferData);
                Toast.makeText(this, "Searching for nearby car washes...", Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "Showing nearby car washes...", Toast.LENGTH_SHORT).show();

                break;

            case R.id.electricCharging:

                mMap.clear();

                String url4 = getUrl(latitide, longitude, electricCharging);
                transferData[0] = mMap;
                transferData[1] = url4;


                getNearbyPlaces.execute(transferData);
                Toast.makeText(this, "Searching for nearby electric charging stations...", Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "Showing nearby electric charging stations...", Toast.LENGTH_SHORT).show();

                break;


            case R.id.gasStations:

                mMap.clear();

                String url5 = getUrl(latitide, longitude, gasStations);
                transferData[0] = mMap;
                transferData[1] = url5;


                getNearbyPlaces.execute(transferData);
                Toast.makeText(this, "Searching for nearby gas stations...", Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "Showing nearby gas stations...", Toast.LENGTH_SHORT).show();

                break;

            case R.id.parkingSpaces:

                mMap.clear();

                String url6 = getUrl(latitide, longitude, parkingSpaces);
                transferData[0] = mMap;
                transferData[1] = url6;


                getNearbyPlaces.execute(transferData);
                Toast.makeText(this, "Searching for nearby parking spaces...", Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "Showing nearby parking spaces...", Toast.LENGTH_SHORT).show();

                break;



            case R.id.profile_button:
                Intent i = new Intent(MapsActivity.this, ProfilePage.class);
                startActivity(i);

            case R.id.favories_button:
                Intent j = new Intent(MapsActivity.this, FavoriesActivity.class);
                startActivity(j);


        }



    }


    private String getUrl(double latitide,double longitude, String nearbyPlace)

    {

        StringBuilder googleURL = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
        googleURL.append("location="+ latitide + "," + longitude);
        googleURL.append("&radius=" + ProximityRadius );
        googleURL.append("&type=" + nearbyPlace);
        googleURL.append("&sensor=true");
        googleURL.append("&key=" + "AIzaSyC2fAO2Z1Nf91WDoO1qWjDyqpZpWUHHbcc");


        Log.d("MapsActivity", "url = " + googleURL.toString());

        return googleURL.toString();





    }






    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED)
        {


            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);
        }



    }


    public boolean checkUserLocationPermission()
    {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION))
            {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, Request_User_Location_Code);
            }
            else
            {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, Request_User_Location_Code);

            }
            return false;
        }
        else
        {
            return true;

        }


    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        switch (requestCode)
        {
            case Request_User_Location_Code:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED)
                    {
                        if (googleApiClient == null)
                        {
                            buildGoogleApiClient();
                        }
                        mMap.setMyLocationEnabled(true);


                    }
                }
                else
                {
                    Toast.makeText(this, "Permission Denied...", Toast.LENGTH_SHORT).show();
                }
                return;
        }

    }




    protected synchronized void buildGoogleApiClient()
    {

        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        googleApiClient.connect();
    }


    @Override
    public void onLocationChanged(Location location) {



        latitide = location.getLatitude();
        longitude = location.getLongitude();

        lastLocation = location;

        if(currentUserLocationMarker != null)
        {
            currentUserLocationMarker.remove();
        }

        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("user Current Location");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));


        currentUserLocationMarker = mMap.addMarker(markerOptions);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomBy(12));

        if(googleApiClient != null)
        {

            LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient,this);


        }





    }



    @Override
    public void onConnected(@Nullable Bundle bundle) {

        locationRequest = new LocationRequest();
        locationRequest.setInterval(1100);
        locationRequest.setFastestInterval(1100);
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED)

        {
            LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest,this);
        }


    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }




















    }

