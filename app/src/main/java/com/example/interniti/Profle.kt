package com.example.interniti

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class Profle : AppCompatActivity() {



    lateinit var ButBack: ImageView
    lateinit var Mail: TextView
    lateinit var ButtonOut: Button
    lateinit var ACESS: TextView
    lateinit var FIO: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        val MailUs = intent.getStringExtra("email").toString()
        val Fname = intent.getStringExtra("Fname")
        val Lname = intent.getStringExtra("Lname")
        val Pname = intent.getStringExtra("Pname")
        val access = intent.getIntExtra("access",1)

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profle)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }



        Mail = findViewById(R.id.MAIL_red)
        Mail.text = MailUs

        ACESS = findViewById(R.id.ACESS)
        ACESS.text = "$access"

        FIO = findViewById(R.id.FIO_red)
        FIO.text = buildString {
            append(Fname)
            append("\n")
            append(Lname)
            append("\n")
            append(Pname)
        }

        ButtonOut = findViewById(R.id.ButtonOut)
        ButtonOut.setOnClickListener {
            Firebase.auth.signOut()
            startActivity(Intent(this, Welcome::class.java))
            finish()
        }

        ButBack = findViewById(R.id.back_prof)
        ButBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }
}