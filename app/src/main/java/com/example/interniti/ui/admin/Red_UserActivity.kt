package com.example.interniti.ui.admin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.interniti.Load
import com.example.interniti.R
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore


class Red_UserActivity : AppCompatActivity() {

    lateinit var FIO_red: TextView
    lateinit var but_back: ImageView
    lateinit var mail: TextView
    lateinit var access: TextView
    lateinit var up: ImageView
    lateinit var down: ImageView
    lateinit var save: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_user)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        but_back = findViewById(R.id.back_red)
        FIO_red = findViewById(R.id.FIO_red)
        mail = findViewById(R.id.MAIL_red)
        access = findViewById(R.id.ACESS_red)
        up = findViewById(R.id.up_access)
        down = findViewById(R.id.down_access)
        save = findViewById(R.id.save_red)
        FIO_red.text = intent.getStringExtra("fio")
        mail.text = intent.getStringExtra("mail")

        var accessa = intent.getIntExtra("access", 0)
        access.text = "$accessa"

        but_back.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }



        save.setOnClickListener {
            Firebase.firestore.collection("Users")
                .document(mail.text.toString())
                .update("accessLevel", accessa)
                .addOnSuccessListener {
                    Toast.makeText(baseContext, "успешно", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@Red_UserActivity, Load::class.java))
                    finish()
                }
                .addOnFailureListener {
                    Toast.makeText(baseContext, "ошибка", Toast.LENGTH_SHORT).show()
                    Log.e("fairBase", "ошибка сохранения")
                }
        }

        up.setOnClickListener {
            if (accessa != 3){
                accessa ++
                access.text = "$accessa"
            }
        }
        down.setOnClickListener {
            if(accessa != 1){
                accessa --
                access.text = "$accessa"
            }
        }

    }
}