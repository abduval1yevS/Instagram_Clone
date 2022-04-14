package com.example.amdroid_innstagram_clone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.amdroid_innstagram_clone.handler.AuthHandler
import com.example.amdroid_innstagram_clone.handler.AuthManager
import com.example.amdroid_innstagram_clone.model.User
import com.example.amdroid_innstagram_clone.utils.Extensions.toast

/*
* In SignUpActivity, user can signup fullname, email, password*/

/*
in SignUpActivity user can sign up using fullname ,password ,email
 */
class SignUpActivity : BaseActivity() {

    val TAG = SignUpActivity::class.java.toString()
    lateinit var et_fullname: EditText
    lateinit var et_password: EditText
    lateinit var et_email: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        initViews()
    }

    fun initViews() {
        et_fullname = findViewById(R.id.et_fullname)
        et_email = findViewById(R.id.et_email)
        et_password = findViewById(R.id.et_password)

        val b_signup = findViewById<Button>(R.id.b_signup)
        b_signup.setOnClickListener {
            val fullname = et_fullname.text.toString().trim()
            val email = et_email.text.toString().trim()
            val password = et_password.text.toString().trim()
            if (fullname.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
                val user = User(fullname,email,password,"")
                firebaseSignUp(user)
            }
        }
        val tv_signin = findViewById<TextView>(R.id.tv_signin)
        tv_signin.setOnClickListener { finish() }
    }

    fun firebaseSignUp(user: User) {
        showLoading(this)
        AuthManager.signUp(user.email,user.password, object : AuthHandler {
            override fun onSuccess(uid : String) {
                user.uid = uid
                storeUserToDB(user)
                toast(getString(R.string.str_signup_succes))
                dismissLoading()
                callMainActivity(context)
            }

            override fun onError(exception: Exception?) {
                dismissLoading()
                toast(getString(R.string.str_signup_failed))
            }
        })
    }

    private fun storeUserToDB(user: User) {
        callMainActivity(context)
    }
}