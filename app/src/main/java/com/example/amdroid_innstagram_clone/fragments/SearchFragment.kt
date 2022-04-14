package com.example.amdroid_innstagram_clone.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.amdroid_innstagram_clone.R
import com.example.amdroid_innstagram_clone.adapter.SearchAdapter
import com.example.amdroid_innstagram_clone.model.User

/*
* In SearchFragment, all registered users can be found by searching keyyword and followed
* */

class SearchFragment : BaseFragment() {
    val TAG = SearchFragment::class.java.simpleName
    lateinit var rv_search: RecyclerView
    var items = ArrayList<User>()
    var users = ArrayList<User>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search,container,false)
        initViews(view)
        return view
    }

    private fun initViews(view: View) {
        rv_search = view.findViewById(R.id.rv_search)
        rv_search.layoutManager = GridLayoutManager(activity,1)
        val et_search = view.findViewById<EditText>(R.id.et_search)
        et_search.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(s: CharSequence, p1: Int, p2: Int, p3: Int) {
                val keyword = s.toString().trim()
                usersByKeyword(keyword)
            }

            override fun afterTextChanged(s: Editable) {

            }

        })
        loadUsers()
        refreshAdapter(items)
    }

    private fun refreshAdapter(items: ArrayList<User>) {
        val adapter = SearchAdapter(this,items)
        rv_search.adapter = adapter
    }

    private fun usersByKeyword(keyword: String) {
        if (keyword.isEmpty()) refreshAdapter(items)

        users.clear()

        for (user in items)
            if (user.fullname.toLowerCase().startsWith(keyword.toLowerCase()))
                users.add(user)

        refreshAdapter(users)
    }

    private fun loadUsers(): ArrayList<User> {
        items = ArrayList<User>()
        items.add(User("said","said1997@gmail.com"))
        items.add(User("murod43","murod17@gmail.com"))
        items.add(User("saidmurod","saidmurod7@gmail.com"))
        items.add(User("telekom","telekom@gmail.com"))
        items.add(User("Hasan","Hasan4@gmail.com"))
        items.add(User("Said1","said1@gmail.com"))
        items.add(User("begzod","begzod5@gmail.com"))
        items.add(User("begzod4","begzod6@gmail.com"))

        return items
    }

}