package com.guilhermecallandprojects.myawesomephotos.activities

import android.Manifest
import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.location.Location
import android.location.LocationManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import android.util.Log
import androidx.appcompat.app.AlertDialog
import com.google.android.gms.location.*
import com.guilhermecallandprojects.myawesomephotos.R
import com.guilhermecallandprojects.myawesomephotos.general.rotateBitmap
import com.guilhermecallandprojects.myawesomephotos.general.showShortToast
import com.guilhermecallandprojects.myawesomephotos.model.AwesomePhoto
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import kotlinx.android.synthetic.main.activity_map.*
import kotlinx.android.synthetic.main.activity_photo_info.*
import java.io.File

@Suppress("DEPRECATION")
class PhotoInfoActivity : AppCompatActivity() {

    var userLatitude:  Double = 0.0
    var userLongitude: Double = 0.0

    private lateinit var locationClient: FusedLocationProviderClient


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_info)

        setUpToolbar()

        var awesomePhoto : AwesomePhoto? = null

        if(intent.hasExtra(MainActivity.EXTRA_PLACE_DETAILS)){
            awesomePhoto = intent.getParcelableExtra(MainActivity.EXTRA_PLACE_DETAILS) as AwesomePhoto?
        }

        if(awesomePhoto != null){
            val imgFile = File(awesomePhoto.image)
            val bitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath())
            val rotatedBitmap = rotateBitmap(bitmap)
            iv_photo.setImageBitmap(rotatedBitmap)
            iv_date.text = awesomePhoto.date
        }

        btn_show_location.setOnClickListener {
            checkForPermissions()
            val intent = Intent(this@PhotoInfoActivity, MapActivity::class.java)
            intent.putExtra(MainActivity.EXTRA_PLACE_DETAILS,  awesomePhoto)
            startActivity(intent)
        }

        locationClient = LocationServices.getFusedLocationProviderClient(this)


    }

    private fun checkForPermissions() {
        if (!isLocationEnabled()) {
            showShortToast(this, "Your location is turned off, please turn it on.")
            val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            startActivity(intent)
        } else {
            Dexter.withActivity(this)
                .withPermissions(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
                .withListener(object : MultiplePermissionsListener {
                    override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
                        requestNewLocationData()
                    }

                    override fun onPermissionRationaleShouldBeShown(
                        permissions: MutableList<PermissionRequest>?,
                        token: PermissionToken?
                    ) {
                        showRationalDialogForPermissions()
                    }

                }).onSameThread().check()
        }
    }

    private fun setUpToolbar() {
        setSupportActionBar(toolbar_info)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = ""
        toolbar_info.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    private fun isLocationEnabled(): Boolean{
        val locationManager: LocationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)

    }

    private fun showRationalDialogForPermissions() {
        AlertDialog.Builder(this).setMessage("It looks like you have turned off permissions required. It can be enables under application settings.")
            .setPositiveButton("Go to settings")
            {
                    _, _ ->
                try {
                    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                    val uri = Uri.fromParts("package", packageName, null)
                    intent.data = uri
                    startActivity(intent)
                } catch (e: ActivityNotFoundException){
                    e.printStackTrace()
                }
            }.setNegativeButton("Cancel"){dialog, _ ->
                dialog.dismiss()
            }.show()
    }

    @SuppressLint("MissingPermission")
    private fun requestNewLocationData(){
        var locationRequest = LocationRequest()
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval = 1000
        locationRequest.numUpdates = 1

        locationClient.requestLocationUpdates( locationRequest, locationCallback, Looper.myLooper() )
    }

    private val locationCallback = object: LocationCallback(){
        override fun onLocationResult(locationResult: LocationResult?) {
            val lastLocation: Location = locationResult!!.lastLocation
            userLatitude = lastLocation.latitude
            Log.i("Current Location", "latitude: $userLatitude")
            userLongitude = lastLocation.longitude
            Log.i("Current Location", "longitude: $userLongitude")

        }
    }
}