package com.example.notesapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView

class FourthFragment : Fragment() {
    lateinit var btnSave: ImageButton
    lateinit var etAddNoteTitle: EditText
    lateinit var etNoteDesc: EditText
    lateinit var id: String
    lateinit var title: String
    lateinit var desc: String
    lateinit var dbHelper: DBHelper
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_fourth, container, false)
        id = arguments?.getString("notesId").toString()
        title = arguments?.getString("notesTitle").toString()
        desc = arguments?.getString("notesDesc").toString()

        btnSave = view.findViewById(R.id.btnSave)
        etAddNoteTitle = view.findViewById(R.id.etAddNoteTitle)
        etNoteDesc = view.findViewById(R.id.etNoteDesc)
//
        setValuesToViews()
//
        btnSave.setOnClickListener {
            updateNotesData()
        }
        return view
    }
    private fun setValuesToViews() {
        etAddNoteTitle.setText(title)
        etNoteDesc.setText(desc)
    }
    private fun updateNotesData() {
        dbHelper = DBHelper(requireContext(), null)
        dbHelper.updateNotes(id.toLong(),etAddNoteTitle.text.toString(),etNoteDesc.text.toString())
        dbHelper.close()
        val  mainActivity = requireActivity() as MainActivity
        mainActivity.replaceFrameWithFragment(SecondFragment())
    }
}