package com.ose4g.contactlist.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ose4g.contactlist.R
import com.ose4g.contactlist.adapters.ContactListAdapter
import com.ose4g.contactlist.data.ContactDataManager
import com.ose4g.contactlist.databinding.ActivityContactListBinding
import com.ose4g.contactlist.databinding.AlertAddContactBinding
import com.ose4g.contactlist.models.Contact

class ContactListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityContactListBinding
    private lateinit var contactListAdapter: ContactListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val context = this@ContactListActivity
        val index = intent.getIntExtra(INTEGER_INTENT_FROM_RECYCLER_VIEW,0)
        val category = ContactDataManager.getCategories(context)[index]
        contactListAdapter = ContactListAdapter(context,category)

        supportActionBar?.title = category.name

        val decoration = DividerItemDecoration(context, LinearLayoutManager.VERTICAL)

        getDrawable(R.color.black)?.let { decoration.setDrawable(it) }

        binding.contactList.let {
            it.adapter = contactListAdapter
            it.layoutManager = LinearLayoutManager(context)
            it.addItemDecoration(decoration)
        }

        binding.addContact.setOnClickListener {
            loadAlertDialog()
        }
    }

    private fun loadAlertDialog() {
        val builder = AlertDialog.Builder(this)
        val alertBinding = AlertAddContactBinding.inflate(layoutInflater,null,false)
        val view = this.layoutInflater.inflate(R.layout.alert_add_contact,null)
        builder.setView(alertBinding.root)
        builder.setCancelable(false)
        val alertDialog = builder.create()
        alertDialog.window?.setBackgroundDrawable(getDrawable(android.R.color.transparent))
        alertDialog.setCanceledOnTouchOutside(true)
        alertDialog.show()

        alertBinding.editTextPhone.doOnTextChanged { text, start, before, count ->
            alertBinding.buttonAddContact.isEnabled = !(alertBinding.editTextName.text.toString().isEmpty() ||
                    text?.length!! <11)
        }

        alertBinding.editTextName.doOnTextChanged { text, start, before, count ->
            alertBinding.buttonAddContact.isEnabled = (text?.length!!>0 &&  alertBinding.editTextPhone.text!!.length>+11)
        }

        alertBinding.buttonAddContact.setOnClickListener {
            contactListAdapter.addContact(Contact(
                alertBinding.editTextName.text.toString(),
                alertBinding.editTextPhone.text.toString()
            ))

            alertDialog.dismiss()
        }

    }

    companion object{
        const val INTEGER_INTENT_FROM_RECYCLER_VIEW = "INDEX_OF_CATEGORY"
    }
}