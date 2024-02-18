package com.example.notesapplication

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(var context: Context, val resource: Int, val arrayList: ArrayList<NotesData>, val mainActivity: MainActivity): RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    lateinit var title: String
    lateinit var desc: String
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val notesTitle = itemView.findViewById<TextView>(R.id.notesTitle)
        val notesDesc = itemView.findViewById<TextView>(R.id.notesDesc)
        val btnUpdate = itemView.findViewById<ImageButton>(R.id.btnUpdate)
        val btnDelete = itemView.findViewById<ImageButton>(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(resource, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.notesTitle.text = arrayList[position].title
        holder.notesDesc.text = arrayList[position].desc
        holder.btnUpdate.setOnClickListener {
            updateData(position)
        }
        holder.btnDelete.setOnClickListener {
            confirmDelete(it.context, position)
        }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    private fun updateData(position: Int) {
        val bundle = Bundle()
        bundle.putString("notesId", arrayList[position].id.toString())
        bundle.putString("notesTitle", arrayList[position].title)
        bundle.putString("notesDesc", arrayList[position].desc)
        val frag = FourthFragment()
        frag.arguments = bundle
        mainActivity.replaceFrameWithFragment(frag)
    }
    private fun confirmDelete(context: Context, position: Int) {
        val builder = AlertDialog.Builder(context)
            .setTitle("Confirm Delete")
            .setMessage("Are you sure to delete this record?")
            .setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, which ->
                deleteNotes(context, position)
                dialog.dismiss()
            })
            .setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which ->
                dialog.dismiss()
            })
            .show()
    }
    private fun deleteNotes(context: Context, position: Int) {
        val db = DBHelper(context, null)
        db.deleteNotesData(arrayList[position].id)
        db.close()
        arrayList.removeAt(position)
        notifyDataSetChanged()
    }
}