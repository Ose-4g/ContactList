package com.ose4g.contactlist.persistence

import android.content.Context
import androidx.room.Room

object UserDatabaseInstance {
    private lateinit var db:UserDatabase

    fun getInstance(applicationContext: Context):UserDatabase
    {
        //Get instance of database
        if((!this::db.isInitialized))
        {
            db = Room.databaseBuilder(
                applicationContext,
                UserDatabase::class.java, UserDatabase.databaseName
            ).build()
        }

        return db

    }
}