package com.ose4g.contactlist.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import androidx.room.migration.Migration
import com.ose4g.contactlist.ContactViewModel
import com.ose4g.contactlist.ContactViewModelFactory
import com.ose4g.contactlist.R
import com.ose4g.contactlist.databinding.ActivityLoginBinding
import com.ose4g.contactlist.persistence.User
import com.ose4g.contactlist.persistence.UserDatabase
import com.ose4g.contactlist.persistence.UserDatabaseInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Error

class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding
    val isLogin:MutableLiveData<Boolean> = MutableLiveData()
    private lateinit var viewModel: ContactViewModel
    private lateinit var viewModelFactory: ContactViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeValues()

        isLogin.observe(this,
            Observer<Boolean> {
                if(it)
                {
                    binding.switchAction.text = getString(R.string.new_user)
                    binding.materialButton.text = getString(R.string.login)
                }
                else
                {
                    binding.switchAction.text = getString(R.string.existing_user)
                    binding.materialButton.text = getString(R.string.register)
                }
            })


        setClickListeners()

    }

    fun setClickListeners()
    {
        binding.switchAction.setOnClickListener {
            isLogin.postValue(!isLogin.value!!)
        }


        //login process
        binding.materialButton.setOnClickListener {
            loginRegisterProcess()
        }


        //clear error message when the edittext is clicked so helper text can be seen
        binding.emailEditText.setOnClickListener{
            binding.emailInputLayout.error = null
        }


        binding.passwordEditText.setOnClickListener {
            binding.passwordInputLayout2.error = null
        }
    }



    fun initializeValues()
    {
        val  db = UserDatabaseInstance.getInstance(applicationContext)

        viewModelFactory = ContactViewModelFactory(db)
        viewModel = ViewModelProvider(this, viewModelFactory)
                .get(ContactViewModel::class.java)

        isLogin.postValue(true)
    }


    fun loginRegisterProcess()
    {
        //checks that email and password are ok
        val emailInput =  binding.emailEditText.text.toString()
        val passwordInput = binding.passwordEditText.text.toString()

        if(isLogin.value!!)
        {
            var user:User? = null
            try
            {
                user = viewModel.getUser(emailInput).value

                val condition1 = binding.emailEditText.text.toString().equals(user?.emailAddress)
                val condition2 = binding.passwordEditText.text.toString().equals(user?.password)

                //logs in if both condtions are met
                if(condition1 && condition2) {
                    startActivity(Intent(this@LoginActivity, CategoryListActivity::class.java))
                    finish()
                }
                //shows error message otherwise
                else
                {
                    binding.emailInputLayout.error =""
                    binding.passwordInputLayout2.error = getString(R.string.password_error_message)
                    Log.i("wrong","email = ${user?.emailAddress}, password = ${user?.password}")
                }
            }
            catch(e:Error)
            {
                Log.i("error",e.message.toString())
            }
        }
        else
        {
                val ans  = viewModel.addUser(User(emailInput,passwordInput))

        }

    }
}