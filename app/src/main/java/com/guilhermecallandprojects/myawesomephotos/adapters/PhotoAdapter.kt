package com.guilhermecallandprojects.myawesomephotos.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.guilhermecallandprojects.myawesomephotos.R
import com.guilhermecallandprojects.myawesomephotos.model.Photo

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

        holder.image.setImageResource(photo.image!!)

        holder.image.setOnClickListener{
            Toast.makeText(context, "you pressed this image", Toast.LENGTH_LONG).show()
        }

    }

    class PhotoHolder(photoView: View) : RecyclerView.ViewHolder(photoView){

        var image : ImageView = photoView.findViewById<ImageView>(R.id.photo)

    }

}