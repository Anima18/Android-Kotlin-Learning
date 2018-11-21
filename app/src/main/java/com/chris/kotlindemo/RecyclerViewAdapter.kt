package com.chris.kotlindemo

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView



class RecyclerViewAdapter(val items : List<Follower>) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.listview_main_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = items[position].login
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        var textView: TextView
        init {
            textView = itemView.findViewById(R.id.mainList_title_tv)
        }
    }
}