package com.example.amdroid_innstagram_clone.handler

import com.example.amdroid_innstagram_clone.model.User

interface DBUserHandler {
    fun onSuccess(user: User? = null)
    fun onError(e: Exception)
}