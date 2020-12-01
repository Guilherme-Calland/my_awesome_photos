package com.guilhermecallandprojects.myawesomephotos.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.guilhermecallandprojects.myawesomephotos.R
import com.guilhermecallandprojects.myawesomephotos.model.AwesomePhoto
import kotlinx.android.synthetic.main.activity_map.*

class MapActivity : AppCompatActivity(), OnMapReadyCallback {

    private var awesomePhoto  : AwesomePhoto? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        if(intent.hasExtra(MainActivity.EXTRA_PLACE_DETAILS)){
            awesomePhoto = intent.getParcelableExtra(MainActivity.EXTRA_PLACE_DETAILS) as AwesomePhoto?
        }

        if(awesomePhoto != null){
            setUpToolbar()

            val supportMapFragment: SupportMapFragment =
                supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
            supportMapFragment.getMapAsync(this)
        }
    }

    private fun setUpToolbar() {
        setSupportActionBar(toolbar_map)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = ""
        toolbar_map.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    override fun onMapReady(map: GoogleMap?) {
        val tempLat: Double = awesomePhoto!!.latitude
        val tempLng: Double = awesomePhoto!!.longitude
        val position = LatLng(tempLat, tempLng)
        map!!.addMarker(MarkerOptions().position(position))
        val newLatLngZoom = CameraUpdateFactory.newLatLngZoom(position, 15f)
        map.animateCamera(newLatLngZoom)
    }
}