/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.eap.form;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    public static final String MyPREFERENCES = "myLocation" ;
    public static final String LAT = "Lat";
    public static final String LNG = "Lng";
    public static final String COUNTRY = "Cnt";
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.basic_demo);

        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(final GoogleMap map) {

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        LatLng  KALAMARIA = new LatLng (40.584536, 22.952606);
        map.addMarker(new MarkerOptions().position(KALAMARIA).title("Καλαμαρια"));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(KALAMARIA, 10));

        map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng loaction) {
                List<Address> addresses = null;
                Log.d("onMapReady", "loaction.longitude: "+loaction.longitude+" loaction.latitude:"+loaction.latitude);
                map.clear();
                map.addMarker(new MarkerOptions().position(new LatLng (loaction.latitude, loaction.longitude)).title("My Location"));
                Geocoder geocoder = new Geocoder(MapActivity.this, Locale.getDefault());
                try {
                    addresses = geocoder.getFromLocation(
                            loaction.latitude,
                            loaction.longitude,
                            // In this sample, get just a single address.
                            1);
                } catch (IOException ioException) {
                    // Catch network or other I/O problems.
                }
                String country = "";
                if(addresses!=null || addresses.size()>0){
                    for(Address add: addresses){
                        Log.d("address","address"+addresses.toString());
                        country = add.getLocality()+"-"+add.getCountryName();
                    }
                }

                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.clear();
                editor.putFloat(LAT, (float) loaction.latitude);
                editor.putFloat(LNG, (float) loaction.longitude);
                editor.putString(COUNTRY, country);
                editor.commit();
            }
        });
    }
}
