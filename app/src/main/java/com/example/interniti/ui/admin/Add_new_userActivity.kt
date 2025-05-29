package com.example.interniti.ui.admin

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Im
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.interniti.Load
import com.example.interniti.R
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore

class Add_new_userActivity : AppCompatActivity() {

    lateinit var butBack: ImageView
    lateinit var Lname: EditText
    lateinit var Fname: EditText
    lateinit var Pname: EditText
    lateinit var Mail: EditText
    lateinit var Password: EditText
    lateinit var up:ImageView
    lateinit var down:ImageView
    lateinit var butAdd:Button
    lateinit var close:Button
    lateinit var access: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_new_user)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val builder = AlertDialog.Builder(this)
        builder.setTitle("будьте внимательны")
            .setMessage("После создания нового пользователя вы будете автоматически авторизованы под ним. Чтобы вернуться к своему аккаунту, потребуется выйти и снова войти. ")
            .setPositiveButton("ОК") {
                    dialog, id ->  dialog.cancel()
            }
        builder.create()
        builder.show()

        butBack = findViewById(R.id.But_back_pg4)
        Lname = findViewById(R.id.Lname_us)
        Fname = findViewById(R.id.Fname_us)
        Pname = findViewById(R.id.Pname_us)
        Mail = findViewById(R.id.Mail_us)
        Password = findViewById(R.id.password_us)
        up = findViewById(R.id.up_access)
        down = findViewById(R.id.down_access)
        butAdd = findViewById(R.id.button_add_pg3)
        close = findViewById(R.id.Button_us)
        access = findViewById(R.id.ACESS_red)

        var accesa: Int = 1

        access.text = "$accesa"

        butAdd.setOnClickListener {
            if (Lname.text.isEmpty() or Fname.text.isEmpty() or Pname.text.isEmpty() or Mail.text.isEmpty() or Password.text.isEmpty()){
                Toast.makeText(baseContext, "все поля должны быть заполнены", Toast.LENGTH_LONG).show()
            }
            else{
                Firebase.auth.createUserWithEmailAndPassword(Mail.text.toString(), Password.text.toString())
                    .addOnSuccessListener {authResult ->

                        val userData = hashMapOf(
                            "Lname" to Lname.text.toString(),
                            "Fname" to Fname.text.toString(),
                            "Pname" to Pname.text.toString(),
                            "mail" to Mail.text.toString(),
                            "accessLevel" to accesa
                        )

                        Firebase.firestore.collection("Users")
                            .document(Mail.text.toString())
                            .set(userData)
                            .addOnSuccessListener {
                                Toast.makeText(baseContext, "Пользователь создан и данные сохранены", Toast.LENGTH_SHORT).show()
                                startActivity(Intent(this@Add_new_userActivity, Load::class.java))
                                finish()
                            }
                            .addOnFailureListener { e ->
                                Toast.makeText(baseContext, "Ошибка при сохранении данных", Toast.LENGTH_SHORT).show()
                                Log.e("user", "Ошибка записи данных: $e")
                            }
                    }
                    .addOnFailureListener {
                        Toast.makeText(baseContext, "ошибка", Toast.LENGTH_SHORT).show()
                        Log.e("user","ошибка создания пользывателя")
                    }
            }
        }

        butBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
        close.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
        up.setOnClickListener {
            if (accesa !=3 ){
                accesa ++
                access.text = "$accesa"
            }
        }
        down.setOnClickListener {
            if (accesa != 1){
                accesa --
                access.text = "$accesa"
            }
        }
    }



}