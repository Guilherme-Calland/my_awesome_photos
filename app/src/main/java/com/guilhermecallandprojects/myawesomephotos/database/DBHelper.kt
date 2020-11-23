package com.guilhermecallandprojects.myawesomephotos.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.guilhermecallandprojects.myawesomephotos.model.Photo

class DBHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION)
{
    companion object{
        private const val DATABASE_NAME = "my_Awesome_Photos_Database"
        private const val DATABASE_VERSION = 1
        private const val TABLE_AWESOME_PHOTOS = "awesome_Photos_Table"

        private const val KEY_ID = "_id"
        private const val KEY_IMAGE = "image"
        private const val KEY_DATE = "date"
        private const val KEY_LOCATION = "location"
        private const val KEY_LATITUDE = "latitude"
        private const val KEY_LONGITUDE = "longitude"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_AWESOME_PHOTOS_TABLE = ("CREATE TABLE $TABLE_AWESOME_PHOTOS (" +
                "$KEY_ID INTEGER PRIMARY KEY," +
                "$KEY_IMAGE TEXT," +
                "$KEY_DATE TEXT," +
                "$KEY_LOCATION TEXT," +
                "$KEY_LATITUDE TEXT," +
                "$KEY_LONGITUDE TEXT)" )
        db?.execSQL(CREATE_AWESOME_PHOTOS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_AWESOME_PHOTOS")
        onCreate(db)
    }

    fun addAwesomePhoto(awesomePhoto: Photo) : Long {
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(KEY_IMAGE, awesomePhoto.image)
        contentValues.put(KEY_DATE, awesomePhoto.date)
        contentValues.put(KEY_LOCATION, awesomePhoto.location)
        contentValues.put(KEY_LATITUDE, awesomePhoto.latitude)
        contentValues.put(KEY_LONGITUDE, awesomePhoto.longitude)

        val result = db.insert(TABLE_AWESOME_PHOTOS, null, contentValues)

        db.close()
        return result;
    }
}


