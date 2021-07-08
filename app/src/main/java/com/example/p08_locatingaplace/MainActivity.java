package com.example.p08_locatingaplace;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PermissionChecker;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

//    Button btn1, btn2, btn3;
    Spinner spinner;
    ArrayList<String> al;
    ArrayAdapter<String> aa;
    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment)
                fm.findFragmentById(R.id.map);

        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;

                LatLng northHQ = new LatLng(1.464679, 103.829093);
                LatLng centralHQ = new LatLng(1.303413, 103.847628);
                LatLng eastHQ = new LatLng(1.350067, 103.932895);
                LatLng singapore = new LatLng(1.3521, 103.8198);

                map.moveCamera(CameraUpdateFactory.newLatLngZoom(singapore,
                        11));


                UiSettings ui = map.getUiSettings();
                ui.setCompassEnabled(true);
                ui.setZoomControlsEnabled(true);

                int permissionCheck =   ContextCompat.checkSelfPermission(MainActivity.this,
                        android.Manifest.permission.ACCESS_FINE_LOCATION);

                if (permissionCheck == PermissionChecker.PERMISSION_GRANTED) {
                    map.setMyLocationEnabled(true);
                } else {
                    Log.e("GMap - Permission", "GPS access has not been granted");
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);
                }

                Marker north = map.addMarker(new
                        MarkerOptions()
                        .position(northHQ)
                        .title("HQ - North")
                        .snippet("Block 333, Admiralty Ave 3, 765654")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));

                Marker central = map.addMarker(new
                        MarkerOptions()
                        .position(centralHQ)
                        .title("HQ - Central")
                        .snippet("Block 3A, Orchard Ave 3, 134542")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

                Marker east = map.addMarker(new
                        MarkerOptions()
                        .position(eastHQ)
                        .title("HQ - East")
                        .snippet("Block 555, Tampines Ave 3, 287788")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));

                map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {

                        Toast.makeText(getApplicationContext(),marker.getTitle(),Toast.LENGTH_SHORT).show();

                        return false;
                    }
                });
            }
        });

        spinner = findViewById(R.id.spinner);

        al = new ArrayList<>();
        aa = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, al);

        String[] str = getResources().getStringArray(R.array.spinner);
        al.addAll(Arrays.asList(str));

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                al.clear();
                switch (position) {
                    case 0:
                        if (map != null){
                            map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(1.464679, 103.829093), 15));
                        }
                        break;
                    case 1:
                        if (map != null){
                            map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(1.303413, 103.847628), 15));
                        }
                        break;
                    case 2:
                        if (map != null){
                            map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(1.350067, 103.932895), 15));
                        }
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


/*
        btn1 = findViewById(R.id.btnNorth);
        btn2 = findViewById(R.id.btnCentral);
        btn3 = findViewById(R.id.btnEast);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (map != null){
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(1.464679, 103.829093), 15));
                }
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (map != null){
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(1.303413, 103.847628), 15));
                }
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (map != null){
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(1.350067, 103.932895), 15));
                }
            }
        });
*/

    }
}