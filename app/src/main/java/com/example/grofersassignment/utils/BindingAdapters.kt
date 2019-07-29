package com.example.grofersassignment.utils

import android.net.Uri
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.grofersassignment.utils.extensions.getParentActivity
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import java.net.URL


@BindingAdapter("adapter")
fun setAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
    view.adapter = adapter
}

@BindingAdapter("mutableVisibility")
fun setMutableVisibility(view: View, visibility: MutableLiveData<Int>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null && visibility != null) {
        visibility.observe(parentActivity, Observer { value -> view.visibility = value ?: View.VISIBLE })
    }
}

@BindingAdapter("mutableText")
fun setMutableText(view: TextView, text: MutableLiveData<String>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null && text != null) {
        text.observe(parentActivity, Observer { value -> view.text = value ?: "" })
    }
}


@BindingAdapter("bind:url")
fun setImage(view: AppCompatImageView, imageUrl: String?) {

    val thread = Thread(Runnable {
        val conn = URL(imageUrl).openConnection()
        conn.connect()
        val inputS = conn.getInputStream()

        val uri = Uri.parse(conn.url.toString())

        Handler(Looper.getMainLooper()).post(Runnable {
            Glide.with(view.context)
                .load(uri.getQueryParameter("imgurl"))
                .fitCenter()
                .into(view)
        })

        inputS.close()

    })
    thread.start()
}


