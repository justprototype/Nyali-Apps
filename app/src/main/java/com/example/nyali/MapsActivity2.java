package com.example.nyali;

import android.media.MediaPlayer;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity2 extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    MediaPlayer mediaPlayer;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps2);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.musikkeraton);
        final Button play = (Button)findViewById(R.id.main);
        final boolean[] isPressed = {false};
        initToolBar();

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isPressed[0] || mediaPlayer.isPlaying() == true) {
                    play.setBackgroundResource(R.drawable.play);
                    mediaPlayer.pause();
                }

                else{
                    play.setBackgroundResource(R.drawable.stop);
                    mediaPlayer.start();
                }

                isPressed[0] = !isPressed[0];


//                // If the music is playing
//                if(mediaPlayer.isPlaying() == true){
//                    // Pause the music player
//                    mediaPlayer.pause();
//                    // If it's not playing
//                }
//
//                else{
//                    // Resume the music player
//                    mediaPlayer.start();
//                }

            }
        });


    }

    public void initToolBar() {
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Nyali");

        toolbar.setNavigationIcon(R.drawable.icon2);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-7.8052845, 110.3642031);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Keraton Yogyakarta"));
        float zoomLevel = 16.0f; //This goes up to 21
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, zoomLevel));
    }
}
