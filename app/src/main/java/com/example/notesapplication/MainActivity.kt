package com.example.notesapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()

//        if (auth.currentUser != null) {
//            Toast.makeText(this, auth.currentUser.toString(), Toast.LENGTH_SHORT).show()
//            replaceFrameWithFragment(SecondFragment())
//        }
//        else {
            replaceFrameWithFragment(FirstFragment())
//        }
    }
    internal fun replaceFrameWithFragment(frag: Fragment) {
        val fragManager = supportFragmentManager
        val fragTransaction = fragManager.beginTransaction()
        fragTransaction.replace(R.id.frameLayout, frag)
        fragTransaction.addToBackStack(null)
        fragTransaction.commit()
    }
}