package com.ose4g.contactlist.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ose4g.contactlist.data.ContactDataManager
import com.ose4g.contactlist.databinding.ContactListItemBinding
import com.ose4g.contactlist.models.Category
import com.ose4g.contactlist.models.Contact

class ContactListAdapter (private val context: Context, private val category: Category):
    RecyclerView.Adapter<ContactListAdapter.ContactViewHolder>()
{


    class ContactViewHolder(private val binding:ContactListItemBinding)
        :RecyclerView.ViewHolder(binding.root)
    {
        fun bind(contact: Contact)
        {
            binding.contactName.text = contact.name
            binding.contactNumber.text = contact.number
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val binding = ContactListItemBinding.inflate(
            LayoutInflater.from(context),
            parent,
            false
        )
        return ContactViewHolder(binding)

    }

    override fun getItemCount(): Int {
       return category.getContactList().size
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {

        holder.bind(category.getContactList()[position])
    }

    fun addContact(contact: Contact)
    {
        category.addContact(contact)
        notifyItemInserted(itemCount-1)
    }
}