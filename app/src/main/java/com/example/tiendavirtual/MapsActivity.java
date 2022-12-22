package com.example.tiendavirtual;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.tiendavirtual.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        LatLng usaquen = new LatLng(4.708337, -74.033095);
        mMap.addMarker(new MarkerOptions().position(usaquen).title("Sede Usaquen"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(usaquen));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(usaquen, 12));

        LatLng chapinero = new LatLng(4.651563, -74.057407);
        mMap.addMarker(new MarkerOptions().position(chapinero).title("Sede chapinero"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(chapinero));

        LatLng cedritos = new LatLng(4.723053, -74.039546);
        mMap.addMarker(new MarkerOptions().position(cedritos).title("Sede cedritos"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(cedritos));
    }
}