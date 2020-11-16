package com.guilhermecallandprojects.myawesomephotos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.guilhermecallandprojects.myawesomephotos.adapters.PhotoAdapter
import com.guilhermecallandprojects.myawesomephotos.model.Photo

class MainActivity : AppCompatActivity() {

    private var recyclerView: RecyclerView? = null
    private var gridLayoutManager: GridLayoutManager?= null
    private var arrayList: ArrayList<Photo> ?= null
    private var photoAdapter:PhotoAdapter ? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.my_recycler_view)
        gridLayoutManager = GridLayoutManager(applicationContext, 3, LinearLayoutManager.VERTICAL, false)
        recyclerView?.layoutManager = gridLayoutManager
        recyclerView?.setHasFixedSize(true)
        arrayList = ArrayList()
        arrayList = setDataInList()
        photoAdapter = PhotoAdapter(applicationContext, arrayList!!)
        recyclerView?.adapter = photoAdapter
    }

    private fun setDataInList() : ArrayList<Photo>{
        var items:ArrayList<Photo> = ArrayList()

        items.add(Photo(R.drawable.bensonwillems))
        items.add(Photo(R.drawable.bensonwillems))
        items.add(Photo(R.drawable.bensonwillems))
        items.add(Photo(R.drawable.bensonwillems))
        items.add(Photo(R.drawable.bensonwillems))
        items.add(Photo(R.drawable.bensonwillems))
        items.add(Photo(R.drawable.bensonwillems))
        items.add(Photo(R.drawable.bensonwillems))
        items.add(Photo(R.drawable.bensonwillems))
        items.add(Photo(R.drawable.bensonwillems))
        items.add(Photo(R.drawable.bensonwillems))
        items.add(Photo(R.drawable.bensonwillems))
        items.add(Photo(R.drawable.bensonwillems))
        items.add(Photo(R.drawable.bensonwillems))
        items.add(Photo(R.drawable.bensonwillems))
        items.add(Photo(R.drawable.bensonwillems))
        items.add(Photo(R.drawable.bensonwillems))
        items.add(Photo(R.drawable.bensonwillems))
        items.add(Photo(R.drawable.bensonwillems))
        items.add(Photo(R.drawable.bensonwillems))
        items.add(Photo(R.drawable.bensonwillems))
        items.add(Photo(R.drawable.bensonwillems))
        items.add(Photo(R.drawable.bensonwillems))
        items.add(Photo(R.drawable.bensonwillems))
        items.add(Photo(R.drawable.bensonwillems))
        items.add(Photo(R.drawable.bensonwillems))
        items.add(Photo(R.drawable.bensonwillems))
        items.add(Photo(R.drawable.bensonwillems))
        items.add(Photo(R.drawable.bensonwillems))
        items.add(Photo(R.drawable.bensonwillems))
        items.add(Photo(R.drawable.bensonwillems))
        items.add(Photo(R.drawable.bensonwillems))
        items.add(Photo(R.drawable.bensonwillems))
        items.add(Photo(R.drawable.bensonwillems))
        items.add(Photo(R.drawable.bensonwillems))
        items.add(Photo(R.drawable.bensonwillems))
        items.add(Photo(R.drawable.bensonwillems))
        items.add(Photo(R.drawable.bensonwillems))
        items.add(Photo(R.drawable.bensonwillems))
        items.add(Photo(R.drawable.bensonwillems))
        items.add(Photo(R.drawable.bensonwillems))
        items.add(Photo(R.drawable.bensonwillems))
        items.add(Photo(R.drawable.bensonwillems))
        items.add(Photo(R.drawable.bensonwillems))
        items.add(Photo(R.drawable.bensonwillems))
        items.add(Photo(R.drawable.bensonwillems))
        items.add(Photo(R.drawable.bensonwillems))
        items.add(Photo(R.drawable.bensonwillems))
        items.add(Photo(R.drawable.bensonwillems))
        items.add(Photo(R.drawable.bensonwillems))
        items.add(Photo(R.drawable.bensonwillems))
        items.add(Photo(R.drawable.bensonwillems))
        items.add(Photo(R.drawable.bensonwillems))
        items.add(Photo(R.drawable.bensonwillems))
        items.add(Photo(R.drawable.bensonwillems))
        items.add(Photo(R.drawable.bensonwillems))
        items.add(Photo(R.drawable.bensonwillems))
        items.add(Photo(R.drawable.bensonwillems))
        items.add(Photo(R.drawable.bensonwillems))
        items.add(Photo(R.drawable.bensonwillems))
        items.add(Photo(R.drawable.bensonwillems))
        items.add(Photo(R.drawable.bensonwillems))
        items.add(Photo(R.drawable.bensonwillems))
        items.add(Photo(R.drawable.bensonwillems))
        items.add(Photo(R.drawable.bensonwillems))
        items.add(Photo(R.drawable.bensonwillems))

        return items
    }
}