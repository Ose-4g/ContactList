package com.ose4g.contactlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ose4g.contactlist.persistence.UserDatabase

class ContactViewModelFactory(private val db: UserDatabase): ViewModelProvider.Factory
{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ContactViewModel::class.java)) {
            return ContactViewModel(db) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}