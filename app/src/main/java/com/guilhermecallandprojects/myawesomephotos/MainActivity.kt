package com.guilhermecallandprojects.myawesomephotos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.guilhermecallandprojects.myawesomephotos.fragments.GalleryFragment
import com.guilhermecallandprojects.myawesomephotos.fragments.MapFragment
import com.guilhermecallandprojects.myawesomephotos.general.showShortToast
import kotlinx.android.synthetic.main.activity_main.*

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
    }

    private fun showFragment(fragment: Fragment) {
        val transaction = manager.beginTransaction()
        transaction.replace(R.id.fragment_holder,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}