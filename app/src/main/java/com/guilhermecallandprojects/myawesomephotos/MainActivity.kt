package com.guilhermecallandprojects.myawesomephotos

import android.Manifest
import android.Manifest.permission.CAMERA
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
import androidx.fragment.app.Fragment
import com.guilhermecallandprojects.myawesomephotos.fragments.GalleryFragment
import com.guilhermecallandprojects.myawesomephotos.fragments.MapFragment
import com.guilhermecallandprojects.myawesomephotos.general.showShortToast
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

//TODO: make the app navigate through diferent screens
//TODO: (opcional) make map/ gallera buttuns light up when are selected

class MainActivity : AppCompatActivity() {
    private val manager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setButtons()
    }

    private fun setButtons() {
        gallery_btn.setOnClickListener {
            showFragment(GalleryFragment())
        }
        map_btn.setOnClickListener {
            showFragment(MapFragment())
        }
        fab_btn.setOnClickListener {
            askForPermissions()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK){
            if(requestCode == CAMERA){
                val photoTaken : Bitmap = data!!.extras!!.get("data") as Bitmap
                val savedImage = saveImageToInternalStorage(photoTaken)
                Log.e("Saved Image: ", "Path :: ${savedImage}")
                iv_test.setImageBitmap(photoTaken)
            }
        }
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

    private fun showFragment(fragment: Fragment) {
        val transaction = manager.beginTransaction()
        transaction.replace(R.id.fragment_holder,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
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