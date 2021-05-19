package com.ose4g.contactlist.persistence

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDatabase:RoomDatabase()
{
    abstract fun userDao():UserDao
    companion object
    {
        const val databaseName:String = "user-db"
    }

}