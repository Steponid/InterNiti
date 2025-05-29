package com.example.interniti.ui.add

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.interniti.R
import com.example.interniti.RegPG.AdapterPageReg
import com.example.interniti.ui.add.addPG.AdapterPGAdd

class PageAddActivity : AppCompatActivity() {

    var TrainM:String = ""
    var widgM: String =""
    var CargoM:String =""
    var MassM:String = ""
    var StartM:String = ""
    var StimeM:String = ""
    var finishM:String = ""
    var FtimeM:String = ""




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_page_add)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val viewPage2 = findViewById<ViewPager2>(R.id.Page_add)
        val adapter = AdapterPGAdd(this)
        viewPage2.isUserInputEnabled = false
        viewPage2.adapter = adapter

    }

}