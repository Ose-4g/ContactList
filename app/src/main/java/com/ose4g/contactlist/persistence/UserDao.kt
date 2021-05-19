package com.ose4g.contactlist.persistence

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUser(user:User)

    @Query("SELECT * from users WHERE emailAddress = (:email)  LIMIT 1")
    fun findUser(email:String):LiveData<User>
}