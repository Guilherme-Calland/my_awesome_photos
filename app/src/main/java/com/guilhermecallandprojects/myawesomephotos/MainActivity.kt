package com.guilhermecallandprojects.myawesomephotos

import android.Manifest
import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.guilhermecallandprojects.myawesomephotos.adapter.PhotoAdapter
import com.guilhermecallandprojects.myawesomephotos.database.DBHelper
import com.guilhermecallandprojects.myawesomephotos.general.showShortToast
import com.guilhermecallandprojects.myawesomephotos.model.Photo
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import java.util.*

class MainActivity : AppCompatActivity() {
    private var saveImageToInternalStorage : Uri? = null
    private var mLatitude : Double = 0.0
    private var mLongitude : Double = 0.0
    private var recyclerView: RecyclerView? = null
    private var gridLayoutManager: GridLayoutManager?= null
    private var photos: ArrayList<Photo> ?= null
    private var photoAdapter: PhotoAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadPhotosToGrid()

        fab_btn.setOnClickListener{
            askForPermissions()
        }
    }

    private fun loadPhotosToGrid() {
        recyclerView = findViewById(R.id.recycler_view)
        gridLayoutManager = GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL, false)
        recyclerView?.layoutManager = gridLayoutManager
        recyclerView?.setHasFixedSize(true)
        photos = ArrayList()
        photos = readPhotosFromDatabase()
        photoAdapter = PhotoAdapter(this, photos!!)
        recyclerView?.adapter = photoAdapter
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK){
            if(requestCode == CAMERA){
                val photoTaken : Bitmap = data!!.extras!!.get("data") as Bitmap

                saveImageToInternalStorage = saveImageToInternalStorage(photoTaken)

                addPhotoToDatabase()

                Log.e("Saved Image: ", "Path :: ${saveImageToInternalStorage}")
            }
        }
    }

    private fun addPhotoToDatabase() {
        val date = getCurrentDate()
        val awesomePhoto = Photo(
            0,
            saveImageToInternalStorage.toString(),
            date,
            "",
            mLatitude,
            mLongitude
        )
        val database = DBHelper(this)
        val addAwesosomePhoto = database.addAwesomePhoto(awesomePhoto)

        //function return a long, so if it returns > 0, it means no errors ocurred
        if(addAwesosomePhoto > 0){
            showShortToast(this, "The photo details were added sucessfully")
        } else {
            showShortToast(this, "Failed to save photo")
        }

        loadPhotosToGrid()
    }

    private fun readPhotosFromDatabase() : ArrayList<Photo>{
        val dbHelper = DBHelper(this)
        val awesomePhotosList : java.util.ArrayList<Photo> = dbHelper.getAwesomePhotosList()
        return awesomePhotosList
    }

    private fun getCurrentDate(): String {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)

        val date = "$day/$month/$year  $hour : $minute"
        return date
    }

    private fun askForPermissions() {
        Dexter.withContext(this).withPermissions(
            Manifest.permission.CAMERA
        ).withListener(object : MultiplePermissionsListener {
            override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
                if (report!!.areAllPermissionsGranted()) {
                    showShortToast(this@MainActivity, "Camera usage is granted!")

                    val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    startActivityForResult(cameraIntent, CAMERA)
                }
            }
            override fun onPermissionRationaleShouldBeShown(
                p0: MutableList<PermissionRequest>?,
                p1: PermissionToken?
            ) {
                showRationalDialogForPermissions()
            }

        }).onSameThread().check()
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

    private fun saveImageToInternalStorage(bitmap: Bitmap) : Uri{
        val wrapper = ContextWrapper(applicationContext)
        var file = wrapper.getDir(IMAGE_DIRECTORY, Context.MODE_PRIVATE)
        //UUID -> Unique User ID
        //UUID.randomUUID gives a random user id
        file = File(file, "${UUID.randomUUID()}.jpg")

        try{
            val stream : OutputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
            stream.flush()
            stream.close()
        }catch(e: IOException){
            e.printStackTrace()
        }

        return Uri.parse(file.absolutePath)
    }

    companion object {
        private const val CAMERA = 0
        private const val IMAGE_DIRECTORY = "myAwesomePhotosImages"
    }


}