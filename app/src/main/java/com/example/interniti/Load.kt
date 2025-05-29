package com.example.interniti

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore


class Load : AppCompatActivity() {
    private val userId by lazy {
        Firebase.auth.currentUser?.email ?: ""
    }
    var acess: Int = 0
    var Fname: String = ""
    var Lname: String = ""
    var Pname: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_load)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val timer = object: CountDownTimer(1000, 1){
            override fun onTick(millisUntilFinished: Long) {}
            override fun onFinish() {
                if (Firebase.auth.currentUser != null) {
                    loadAccessLevelAndSetupNav(userId)
                    Log.e("user", userId)
                }
                else{
                    startActivity(Intent(this@Load, Welcome::class.java))
                    finish()
                }

            }
        }
        timer.start()
    }

    private fun loadAccessLevelAndSetupNav(userId: String) {
        Firebase.firestore.collection("Users").document(userId).get()
            .addOnSuccessListener { document ->
                acess = document.getLong("accessLevel")?.toInt() ?: 1
                Fname = document.get("Fname").toString()
                Lname = document.get("Lname").toString()
                Pname = document.get("Pname").toString()
                Log.i("user", "$acess")

                val i = Intent(this@Load, MenuActivity::class.java)
                i.putExtra("acess", acess)
                i.putExtra("Fname", Fname)
                i.putExtra("Lname", Lname)
                i.putExtra("Pname", Pname)
                startActivity(i)
                finish()

            }
            .addOnFailureListener { exception ->
                Log.e("user", "Ошибка получения уровня доступа", exception)
                startActivity(Intent(this@Load, Welcome::class.java))
                finish()
            }
    }
}