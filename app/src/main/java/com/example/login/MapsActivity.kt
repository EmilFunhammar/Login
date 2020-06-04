package com.example.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions


class MapsActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener{

    private lateinit var mMap: GoogleMap


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.setOnInfoWindowClickListener (this)

        val work1 = LatLng(59.478669, 18.318465)
        mMap.addMarker(MarkerOptions().position(work1).title("Jobb1"))

        val work2 = LatLng(59.479319, 18.322222)
        mMap.addMarker(MarkerOptions().position(work2).title("Jobb2"))

        val work3 = LatLng(59.483901, 18.306179)
        mMap.addMarker(MarkerOptions().position(work3).title("Jobb3"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(work3))

        val work4 = LatLng(59.485804, 18.302778)
        mMap.addMarker(MarkerOptions().position(work4).title("Jobb4"))

        val work5 = LatLng(59.485104, 18.295584)
        mMap.addMarker(MarkerOptions().position(work5).title("Jobb5"))

        val work6 = LatLng(59.486313, 18.293181)
        mMap.addMarker(MarkerOptions().position(work6).title("Jobb6"))
        mMap.setMinZoomPreference(13.0f)
    }

    override fun onInfoWindowClick(p0: Marker?) {


    }

}
