package com.example.notesapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.size
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class SecondFragment : Fragment() {
    lateinit var notesList: ArrayList<NotesData>
    lateinit var recyclerView: RecyclerView
    lateinit var btnAddNote: FloatingActionButton
    lateinit var tvIsNotesFound: TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_second, container, false)

        btnAddNote = view.findViewById(R.id.btnAddNote)
        recyclerView = view.findViewById(R.id.recyclerView)
        tvIsNotesFound = view.findViewById(R.id.tvIsNotesFound)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        notesList = arrayListOf<NotesData>()
        notesList.add(NotesData("First Note", "About Personal Details"))
        val adapter = MyAdapter(requireContext(), R.layout.notes_list, notesList)
        recyclerView.adapter = adapter

        if (adapter != null && adapter.itemCount == 0) {
            recyclerView.visibility = View.INVISIBLE
            tvIsNotesFound.visibility = View.VISIBLE
        }
        else {
            tvIsNotesFound.visibility = View.INVISIBLE
            recyclerView.visibility = View.VISIBLE
        }

        btnAddNote.setOnClickListener {
            val MainActivity = requireActivity() as MainActivity
            MainActivity.replaceFrameWithFragment(ThirdFragment())
        }

        return view
    }
}