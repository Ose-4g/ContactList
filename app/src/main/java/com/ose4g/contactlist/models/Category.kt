package com.ose4g.contactlist.models

import androidx.annotation.ColorRes

class Category(val name:String, val color:Int)
{
    private lateinit var contacts:MutableList<Contact>

    private fun getAllContacts():MutableList<Contact>
    {
        if(!(this::contacts.isInitialized))
        {
            contacts = mutableListOf()
        }

        return contacts
    }

    fun addContact(contact: Contact)
    {
        getAllContacts().add(contact)
    }

    fun getContactList(): MutableList<Contact>
    {
        return getAllContacts()
    }
}