package com.ose4g.contactlist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.ose4g.contactlist.persistence.User
import com.ose4g.contactlist.persistence.UserDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ContactViewModel(val db: UserDatabase) : ViewModel()
{
    fun getUser(email:String):LiveData<User>
    {
        return db.userDao().findUser(email)
    }

    fun addUser(user: User)
    {
        viewModelScope.launch{
            val id = db.userDao().addUser(user)
            Log.i("wrong","id = ${id.toString()}")
        }
    }
}