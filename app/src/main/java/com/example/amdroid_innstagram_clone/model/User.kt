package com.example.amdroid_innstagram_clone.model

class User {
    var uid: String = ""
    var fullname: String = ""
    var email: String = ""
    var password: String =""
    var userImg: String =""

    constructor(fullname: String, email: String) {
        this.email = email
        this.fullname = fullname
    }
    constructor(fullname: String, email: String, image: String) {
        this.email = email
        this.fullname = fullname
        this.userImg = image
    }
    constructor(fullname: String, email: String, password: String, image: String){
        this.email = email
        this.fullname = fullname
        this.password = password
        this.userImg = image
    }
}