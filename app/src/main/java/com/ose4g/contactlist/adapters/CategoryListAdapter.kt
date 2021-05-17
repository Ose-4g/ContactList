package com.ose4g.contactlist.adapters

import android.content.Context
import android.content.Intent
import android.media.MediaRouter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.ose4g.contactlist.R
import com.ose4g.contactlist.activities.ContactListActivity
import com.ose4g.contactlist.databinding.CategoryListItemBinding
import com.ose4g.contactlist.models.Category
import com.ose4g.contactlist.models.Contact

class CategoryListAdapter(val context: Context, val categoryList:List<Category>):RecyclerView.Adapter<CategoryListAdapter.CategoryViewHolder>()
{

    class CategoryViewHolder(val binding: CategoryListItemBinding):RecyclerView.ViewHolder(binding.root)
    {
        fun bind( context: Context, category: Category)
        {
            binding.background.setBackgroundColor(
                ContextCompat.getColor(context,category.color))
            binding.bigLetterTv.text = category.name.substring(0,1)
            binding.fullTextTv.text = category.name

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = CategoryListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CategoryViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(context, categoryList[position])

        holder.binding.background.setOnClickListener {
            val intent = Intent(context,ContactListActivity::class.java)
            intent.putExtra(ContactListActivity.INTEGER_INTENT_FROM_RECYCLER_VIEW,position)
            context.startActivity(intent)
        }
    }
}