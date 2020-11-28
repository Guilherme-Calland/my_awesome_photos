package com.guilhermecallandprojects.myawesomephotos.activities

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.guilhermecallandprojects.myawesomephotos.R
import com.guilhermecallandprojects.myawesomephotos.general.rotateBitmap
import com.guilhermecallandprojects.myawesomephotos.general.showShortToast
import com.guilhermecallandprojects.myawesomephotos.model.Photo
import kotlinx.android.synthetic.main.activity_photo_info.*
import java.io.File

class PhotoInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_info)

        var awesomePhoto : Photo? = null

        if(intent.hasExtra(MainActivity.EXTRA_PLACE_DETAILS)){
            awesomePhoto = intent.getSerializableExtra(MainActivity.EXTRA_PLACE_DETAILS) as Photo
        }

        if(awesomePhoto != null){
            val imgFile = File(awesomePhoto.image)
            val bitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath())
            val rotatedBitmap = rotateBitmap(bitmap)
            iv_photo.setImageBitmap(rotatedBitmap)
            iv_date.text = awesomePhoto.date
        }

    }
}