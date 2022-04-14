package com.example.amdroid_innstagram_clone.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.amdroid_innstagram_clone.R
import com.example.amdroid_innstagram_clone.adapter.FavoriteAdapter
import com.example.amdroid_innstagram_clone.model.Post

class FavoriteFragment : BaseFragment() {
    private lateinit var recyclerView : RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_favorite,container,false)
        initViews(view)
        return view
    }

    private fun initViews(view: View) {
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(activity,1)
        refresfAdapter(loadPosts())
    }

    private fun loadPosts(): ArrayList<Post> {
        val items = ArrayList<Post>()
        items.add(Post("https://images.unsplash.com/photo-1649663016279-3fe60e2038ba?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=387&q=80"))
        items.add(Post("https://images.unsplash.com/photo-1558227576-0f0612e5cf3a?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1161&q=80"))
        items.add(Post("https://images.unsplash.com/photo-1612596551578-9c81c9de1b3f?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=774&q=80"))
        return items
    }

    private fun refresfAdapter(items: ArrayList<Post>) {
        val adapter = FavoriteAdapter(this,items)
        recyclerView.adapter = adapter
    }
}