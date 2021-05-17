package com.ose4g.contactlist.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.ose4g.contactlist.R
import com.ose4g.contactlist.adapters.CategoryListAdapter
import com.ose4g.contactlist.data.ContactDataManager
import com.ose4g.contactlist.databinding.ActivityCategoryListBinding

class CategoryListActivity : AppCompatActivity() {

    lateinit var binding: ActivityCategoryListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCategoryListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val context = this@CategoryListActivity

        binding.categoryList.let {
            it.adapter = CategoryListAdapter(context,
                ContactDataManager.getCategories(context))
            it.layoutManager = GridLayoutManager(context,2)
        }
    }
}