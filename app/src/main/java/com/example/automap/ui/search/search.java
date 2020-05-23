package com.example.automap.ui.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.automap.R;
import com.google.android.gms.maps.GoogleMap;

public class search extends Fragment  {

    private GoogleMap mMap;

    private SearchViewModel mViewModel;

    public static search newInstance() {
        return new search();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        final View view = inflater.inflate(R.layout.search_fragment, container, false);
/*
        Button searchButton;
        searchButton = (Button) view.findViewById(R.id.search_address);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText addressField = (EditText) view.findViewById(R.id.location_search);
                String address = addressField.getText().toString();

                List<Address> addressList = null ;
                MarkerOptions userMarkerOptions = new MarkerOptions();

                if(!TextUtils.isEmpty(address))
                {
                    Geocoder geocoder = new Geocoder(getActivity());
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

                            Toast.makeText(getActivity(), "Location not found!", Toast.LENGTH_SHORT).show();
                        }

                    }

                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }

                }
                else
                {
                    Toast.makeText(getActivity(),"Please write a location name...", Toast.LENGTH_SHORT).show();
                }



            }
        });



*/
        return view;
    }



}
