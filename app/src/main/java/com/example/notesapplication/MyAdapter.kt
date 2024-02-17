package com.example.notesapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(var context: Context, val resource: Int, val arrayList: ArrayList<NotesData>): RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val notesTitle = itemView.findViewById<TextView>(R.id.notesTitle)
        val notesDesc = itemView.findViewById<TextView>(R.id.notesDesc)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(resource, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.notesTitle.text = arrayList[position].title
        holder.notesDesc.text = arrayList[position].desc
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }
}