package com.guilhermecallandprojects.myawesomephotos.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.guilhermecallandprojects.myawesomephotos.MainActivity
import com.guilhermecallandprojects.myawesomephotos.R
import com.guilhermecallandprojects.myawesomephotos.adapter.PhotoAdapter
import com.guilhermecallandprojects.myawesomephotos.database.DBHelper
import com.guilhermecallandprojects.myawesomephotos.model.Photo
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.fragment_gallery.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [GalleryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
private var recyclerView: RecyclerView? = null
private var gridLayoutManager: GridLayoutManager?= null
private var photos: ArrayList<Photo> ?= null
private var photoAdapter: PhotoAdapter? = null

class GalleryFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view = inflater.inflate(R.layout.fragment_gallery, container, false)

        gridLayoutManager = GridLayoutManager(inflater.context, 3, LinearLayoutManager.VERTICAL, false)
        recyclerView = view.findViewById(R.id.my_recycler_view);
        recyclerView?.layoutManager = gridLayoutManager
        recyclerView?.setHasFixedSize(true)
        photos = ArrayList()
        photos = readHappyPlacesFromDatabase()
        photoAdapter = PhotoAdapter(inflater.context, photos!!)
        recyclerView?.adapter = photoAdapter

        return view
    }




    private fun readHappyPlacesFromDatabase() : ArrayList<Photo>{
        val dbHelper = DBHelper(context)
        val awesomePhotosList : java.util.ArrayList<Photo> = dbHelper.getAwesomePhotosList()
        return awesomePhotosList
    }


}