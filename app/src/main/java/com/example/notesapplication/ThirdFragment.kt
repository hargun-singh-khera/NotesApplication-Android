package com.example.notesapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton

class ThirdFragment : Fragment() {
    lateinit var btnSave: ImageButton
    lateinit var etAddNoteTitle: EditText
    lateinit var etNoteDesc: EditText
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_third, container, false)
        etAddNoteTitle = view.findViewById(R.id.etAddNoteTitle)
        etNoteDesc = view.findViewById(R.id.etAddNoteTitle)
        btnSave = view.findViewById(R.id.btnSave)

        btnSave.setOnClickListener{
            val MainActivity = requireActivity() as MainActivity
            MainActivity.replaceFrameWithFragment(SecondFragment())
        }
        return view
    }
}