package com.example.interniti

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.ImageView
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import com.example.interniti.databinding.ActivityMenuBinding
import com.example.interniti.ui.add.addFragment
import com.example.interniti.ui.admin.AdminFragment
import com.example.interniti.ui.home.HomeFragment
import com.google.firebase.Firebase
import com.google.firebase.auth.auth



class MenuActivity : AppCompatActivity() {

    private lateinit var ButProf: ImageView
    private lateinit var binding: ActivityMenuBinding
    private lateinit var bottomNav: BottomNavigationView
    private val userId by lazy {
        Firebase.auth.currentUser?.email ?: ""
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        val accessLevel = intent.getIntExtra("acess", 1)
        val Fname = intent.getStringExtra("Fname")
        val Lname = intent.getStringExtra("Lname")
        val Pname = intent.getStringExtra("Pname")

        Log.i("AccessLevel", "Access Level: $accessLevel")

        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView
        bottomNav = navView


        when (accessLevel) {

            1 -> setupNavigation(1)
            2 -> setupNavigation(2)
            3 -> setupNavigation(3)
            else -> Toast.makeText(this, "Нет доступа, обратитесь к менеджеру", Toast.LENGTH_SHORT).show()
        }

        Log.i("user", "Access Level: $accessLevel")
        Log.i("user", userId)

        ButProf = findViewById(R.id.profle)
        ButProf.setOnClickListener {
            val Intent = Intent(this@MenuActivity, Profle::class.java)
            Intent.putExtra("email", userId)
            Intent.putExtra("access", accessLevel)
            Intent.putExtra("Fname", Fname)
            Intent.putExtra("Lname", Lname)
            Intent.putExtra("Pname", Pname)
            startActivity(Intent)

        }



    }


    private fun setupNavigation(accessLevel: Int) {
        bottomNav.menu.clear()

        when (accessLevel) {
            1 -> {
                bottomNav.menu.add(Menu.NONE, R.id.homeFragment, Menu.NONE, "Главная", )
                    .setIcon(R.drawable.database)
            }
            2 -> {
                bottomNav.menu.add(Menu.NONE, R.id.homeFragment, Menu.NONE, "Главная")
                    .setIcon(R.drawable.database)
                bottomNav.menu.add(Menu.NONE, R.id.addFragment, Menu.NONE, "Профиль")
                    .setIcon(R.drawable.add)
            }
            3 -> {
                bottomNav.menu.add(Menu.NONE, R.id.homeFragment, Menu.NONE, "Главная")
                    .setIcon(R.drawable.database)
                bottomNav.menu.add(Menu.NONE, R.id.addFragment, Menu.NONE, "Профиль")
                    .setIcon(R.drawable.add)
                bottomNav.menu.add(Menu.NONE, R.id.adminFragment, Menu.NONE, "Админ")
                    .setIcon(R.drawable.admin)
            }
            else -> {
                Toast.makeText(this, "Доступ запрещён", Toast.LENGTH_SHORT).show()
            }
        }

        if (bottomNav.menu.size() > 0) {
            loadFragment(bottomNav.menu.getItem(0).itemId)
        }

        bottomNav.setOnItemSelectedListener { item ->
            loadFragment(item.itemId)
            true
        }
    }

    private fun loadFragment(fragmentId: Int) {
        val fragment = when (fragmentId) {
            R.id.homeFragment -> HomeFragment()
            R.id.addFragment -> addFragment()
            R.id.adminFragment -> AdminFragment()
            else -> HomeFragment()
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }
}