package com.guilhermecallandprojects.myawesomephotos.general

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Matrix
import android.net.Uri
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat.startActivity

fun showShortToast(context : Context, message:String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

fun rotateBitmap(
    myBitmap: Bitmap
): Bitmap? {
    val rotationMatrix: Matrix = Matrix()
    rotationMatrix.setRotate(90.0f)

    return Bitmap.createBitmap(
        myBitmap,
        0,
        0,
        myBitmap.getWidth(),
        myBitmap.getHeight(),
        rotationMatrix,
        true
    )
}


