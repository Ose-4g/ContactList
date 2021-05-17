package com.ose4g.contactlist.data

import android.content.Context
import com.ose4g.contactlist.R
import com.ose4g.contactlist.models.Category
import com.ose4g.contactlist.models.Contact

object ContactDataManager
{
    private lateinit var context: Context
    private lateinit var allCategories:MutableList<Category>


    fun getCategories(context: Context): List<Category>
    {
        if(!this::allCategories.isInitialized)
        {
            allCategories = mutableListOf(
                Category(context.getString(R.string.family),android.R.color.darker_gray),
                Category(context.getString(R.string.friends),android.R.color.holo_purple),
                Category(context.getString(R.string.colleagues), android.R.color.holo_green_light),
                Category(context.getString(R.string.tutors),android.R.color.holo_orange_dark),
                Category(context.getString(R.string.zuri_training), android.R.color.holo_blue_light)
            )
        }
        return  allCategories
    }


}