package com.guilhermecallandprojects.myawesomephotos.screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.guilhermecallandprojects.myawesomephotos.R
import com.guilhermecallandprojects.myawesomephotos.adapters.PhotoAdapter
import com.guilhermecallandprojects.myawesomephotos.model.Photo
import kotlinx.android.synthetic.main.home_screen.*

//TODO: obrigar orientation a ser veritcal em todos as activities
//TODO: fazer a navegacao entre talas melhor, talvez use fragments, ja que queremos manter o appBar e os botoes de baixo
//TODO: fazer quando clicar em gallery e ja estiver na gallery, nao acontecer nada


class HomeScreen : AppCompatActivity() {

    private var recyclerView: RecyclerView? = null
    private var gridLayoutManager: GridLayoutManager?= null
    private var arrayList: ArrayList<Photo> ?= null
    private var photoAdapter:PhotoAdapter ? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_screen)

        recyclerView = findViewById(R.id.my_recycler_view)
        gridLayoutManager = GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL, false)
        recyclerView?.layoutManager = gridLayoutManager
        recyclerView?.setHasFixedSize(true)
        arrayList = ArrayList()
        arrayList = setDataInList()
        photoAdapter = PhotoAdapter(this, arrayList!!)
        recyclerView?.adapter = photoAdapter
    }

    fun changeScreen(view: View){

//        val buttonSelected = view as ImageView

        when(view.id){
            R.id.gallery_btn-> {
                val intent = Intent(this, HomeScreen::class.java)
                startActivity(intent)
            }

            R.id.map_btn-> {
                val intent = Intent(this, MapScreen::class.java)
                startActivity(intent)
            }

        }
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