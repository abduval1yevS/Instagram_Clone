package com.example.amdroid_innstagram_clone.manager

import com.example.amdroid_innstagram_clone.handler.DBUserHandler
import com.example.amdroid_innstagram_clone.model.User
import com.google.firebase.firestore.FirebaseFirestore

private var USER_PATH = "users"
private var POST_PATH = "posts"
private var FEED_PATH = "feeds"
private var FOLLOWING_PATH = "following"
private var FOLLOWERS_PATH = "followers"

object DatabaseManager {
    private var database = FirebaseFirestore.getInstance()

    fun storeUser(user: User, handler: DBUserHandler) {
        database.collection(USER_PATH).document(user.uid).set(user).addOnSuccessListener {
            handler.onSuccess()
        }.addOnFailureListener {
            handler.onError(it)
        }
    }

    fun loadUser(uid: String, handler: DBUserHandler) {
        database.collection(USER_PATH).document(uid).get().addOnSuccessListener {
            if (it.exists()) {
                val fullname: String? = it.getString("fullname")
                val email: String? = it.getString("email")
                val userImg: String? = it.getString("userImg")

                val user = User(fullname!!, email!!, userImg!!)
                user.uid = uid
                handler.onSuccess(user)
            } else {
                handler.onSuccess(null)
            }
        }.addOnFailureListener {  }
    }

}