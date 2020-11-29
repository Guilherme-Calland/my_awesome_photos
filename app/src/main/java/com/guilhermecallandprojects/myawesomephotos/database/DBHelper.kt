package com.guilhermecallandprojects.myawesomephotos.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import com.guilhermecallandprojects.myawesomephotos.model.AwesomePhoto

class DBHelper(context: Context?) :
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

    fun addAwesomePhoto(awesomeAwesomePhoto: AwesomePhoto) : Long {
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(KEY_IMAGE, awesomeAwesomePhoto.image)
        contentValues.put(KEY_DATE, awesomeAwesomePhoto.date)
        contentValues.put(KEY_LOCATION, awesomeAwesomePhoto.location)
        contentValues.put(KEY_LATITUDE, awesomeAwesomePhoto.latitude)
        contentValues.put(KEY_LONGITUDE, awesomeAwesomePhoto.longitude)

        val result = db.insert(TABLE_AWESOME_PHOTOS, null, contentValues)

        db.close()
        return result;
    }

    fun getAwesomePhotosList():ArrayList<AwesomePhoto>{
        val awesomePhotosList = ArrayList<AwesomePhoto>()
        val selectQuery = "SELECT * FROM $TABLE_AWESOME_PHOTOS"
        val db = this.readableDatabase

        try{
            val cursor : Cursor = db.rawQuery(selectQuery, null)
            if(cursor.moveToFirst()){
                do{
                    val photo = AwesomePhoto(
                        cursor.getInt(cursor.getColumnIndex(KEY_ID)),
                        cursor.getString(cursor.getColumnIndex(KEY_IMAGE)),
                        cursor.getString(cursor.getColumnIndex(KEY_DATE)),
                        cursor.getString(cursor.getColumnIndex(KEY_LOCATION)),
                        cursor.getDouble(cursor.getColumnIndex(KEY_LATITUDE)),
                        cursor.getDouble(cursor.getColumnIndex(KEY_LONGITUDE))
                    )
                    awesomePhotosList.add(photo)
                } while (cursor.moveToNext())
            }
            cursor.close()
        }catch(e: SQLiteException){
            db.execSQL(selectQuery)
            return ArrayList()
        }

        return awesomePhotosList
    }

    fun deleteAwesomePhoto(awesomePhoto: AwesomePhoto): Int{
        val database = this.writableDatabase
        val success = database.delete(TABLE_AWESOME_PHOTOS, KEY_ID + "=" + awesomePhoto.id, null)
        database.close()
        return success
    }

}


