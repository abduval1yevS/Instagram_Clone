package com.example.amdroid_innstagram_clone.handler

import com.example.amdroid_innstagram_clone.model.User

interface DBUsersHandler {
    fun onSuccess(users: ArrayList<User>)
    fun onError(e: Exception)
}