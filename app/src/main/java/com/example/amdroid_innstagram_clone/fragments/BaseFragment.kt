package com.example.amdroid_innstagram_clone.fragments

import android.app.Activity
import android.content.Intent
import androidx.fragment.app.Fragment
import com.example.amdroid_innstagram_clone.SignUpActivity

/*
* BaseFragment is paarent for all Fragment*/
open class BaseFragment : Fragment() {
    fun callSignInActivity(activity: Activity) {
        val intent = Intent(context, SignUpActivity::class.java)
        startActivity(intent)
        activity.finish()
    }
}