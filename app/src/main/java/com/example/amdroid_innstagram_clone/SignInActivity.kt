package com.example.amdroid_innstagram_clone

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.amdroid_innstagram_clone.handler.AuthHandler
import com.example.amdroid_innstagram_clone.handler.AuthManager
import com.example.amdroid_innstagram_clone.utils.Extensions.toast
import java.lang.Exception

/*
* In SignUpActivity, user can login using email, password*/

class SignInActivity : BaseActivity() {

    val TAG = SignInActivity::class.java.toString()
    lateinit var et_email: EditText
    lateinit var et_password: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        initViews()
    }

    private fun initViews() {
        et_email = findViewById(R.id.et_email)
        et_password = findViewById(R.id.et_password)

        val b_signin = findViewById<Button>(R.id.b_signin)
        b_signin.setOnClickListener {
            val email = et_email.text.toString().trim()
            val password = et_password.text.toString().trim()
            if (email.isNotEmpty() && password.isNotEmpty())
                firebaseSignIn(email,password)
        }

        val tv_signup = findViewById<TextView>(R.id.tv_signup)
        tv_signup.setOnClickListener { callSignUpActivity() }
    }

    fun firebaseSignIn(email: String, password: String) {
        showLoading(this)
        AuthManager.signIn(email, password, object : AuthHandler {
            override fun onSuccess(uid: String) {
                dismissLoading()
                toast(getString(R.string.str_signin_success))
                callMainActivity(context)
            }

            override fun onError(exception: Exception?) {
                dismissLoading()
                toast(getString(R.string.str_signin_failed))
            }
        })
    }

    override fun callMainActivity(context: Context) {
        val intent = Intent(context, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun callSignUpActivity() {
        val intent = Intent(this,SignUpActivity::class.java)
        startActivity(intent)
        finish()
    }
}