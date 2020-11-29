package com.guilhermecallandprojects.myawesomephotos.adapter

import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.guilhermecallandprojects.myawesomephotos.R
import com.guilhermecallandprojects.myawesomephotos.general.rotateBitmap
import com.guilhermecallandprojects.myawesomephotos.general.showShortToast
import com.guilhermecallandprojects.myawesomephotos.model.AwesomePhoto
import java.io.File

class PhotoAdapter(var context: Context, var arrayList: ArrayList<AwesomePhoto>) : RecyclerView.Adapter<PhotoAdapter.PhotoHolder>(){

    private var onClickListener: OnClickListener? = null
    private var onLongClickListener: OnLongClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoHolder {
        val photoHolder = LayoutInflater.from(parent.context).inflate(R.layout.grid_layout_list_item, parent, false)
        return PhotoHolder(photoHolder)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: PhotoHolder, position: Int) {
        val awesomePhoto: AwesomePhoto = arrayList.get(position)

        val imgFile = File(awesomePhoto.image)

        if(imgFile.exists()){
            val myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath())
            val rotatedBitmap = rotateBitmap(myBitmap)
            holder.image.setImageBitmap(rotatedBitmap)
        }else {
            showShortToast(context,"Some Image files where not found")
        }
        holder.image.setOnClickListener{
            onClickListener!!.onClick(position, awesomePhoto)
        }
        holder.image.setOnLongClickListener{
            onLongClickListener!!.onLongClick(position, awesomePhoto)
            arrayList.removeAt(position)
            notifyItemRemoved(position)
            true
        }
    }

    fun setOnClickListener(onClickListener: OnClickListener){
        this.onClickListener = onClickListener
    }

    fun setOnLongClickListener(onLongClickListener: OnLongClickListener){
        this.onLongClickListener = onLongClickListener
    }

    interface OnClickListener {
        fun onClick(position: Int, model: AwesomePhoto)
    }

    interface OnLongClickListener {
        fun onLongClick(position: Int, model: AwesomePhoto)
    }

    class PhotoHolder(photoView: View) : RecyclerView.ViewHolder(photoView){
        var image : ImageView = photoView.findViewById<ImageView>(R.id.photo)
    }

}