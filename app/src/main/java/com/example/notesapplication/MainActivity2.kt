package com.example.notesapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth

class MainActivity2 : AppCompatActivity() {
    lateinit var name: TextView
    lateinit var email: TextView
    lateinit var auth: FirebaseAuth
    lateinit var btnSignOut: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        name = findViewById(R.id.name)
        email = findViewById(R.id.email)
        auth = FirebaseAuth.getInstance()
        btnSignOut = findViewById(R.id.btnSignOut)

        setValueToViews()

        btnSignOut.setOnClickListener {
            auth.signOut()
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
    private fun setValueToViews() {
        name.text = intent.getStringExtra("name")
        email.text = intent.getStringExtra("email")
    }

}