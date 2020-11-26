package com.guilhermecallandprojects.myawesomephotos.adapter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.guilhermecallandprojects.myawesomephotos.R
import com.guilhermecallandprojects.myawesomephotos.general.showShortToast
import com.guilhermecallandprojects.myawesomephotos.model.Photo
import java.io.File

class PhotoAdapter(var context: Context, var arrayList: ArrayList<Photo>) : RecyclerView.Adapter<PhotoAdapter.PhotoHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoHolder {
        val photoHolder = LayoutInflater.from(parent.context).inflate(R.layout.grid_layout_list_item, parent, false)
        return PhotoHolder(photoHolder)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: PhotoHolder, position: Int) {
        var photo: Photo = arrayList.get(position)

        val imgFile = File(photo.image)

        if(imgFile.exists()){
            val myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath())
            val rotationMatrix: Matrix = Matrix()
            rotationMatrix.setRotate(-90.0f)
            val rotatedBitmap = rotateBitmap(myBitmap, rotationMatrix)
            holder.image.setImageBitmap(rotatedBitmap)
        }else {
            showShortToast(context,"Some Image files where not found")
        }
        holder.image.setOnClickListener{
            showShortToast(context, "you pressed this")
        }
    }

    private fun rotateBitmap(
        myBitmap: Bitmap,
        rotationMatrix: Matrix
    ): Bitmap? {
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

    class PhotoHolder(photoView: View) : RecyclerView.ViewHolder(photoView){
        var image : ImageView = photoView.findViewById<ImageView>(R.id.photo)
    }

}