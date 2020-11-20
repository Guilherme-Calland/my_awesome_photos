package com.guilhermecallandprojects.myawesomephotos.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.guilhermecallandprojects.myawesomephotos.R
import com.guilhermecallandprojects.myawesomephotos.general.showShortToast
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
            showShortToast(context, "you pressed this")
        }
    }

    class PhotoHolder(photoView: View) : RecyclerView.ViewHolder(photoView){
        var image : ImageView = photoView.findViewById<ImageView>(R.id.photo)
    }

}