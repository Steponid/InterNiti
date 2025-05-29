package com.example.interniti.ui.add.addPG

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.viewpager2.widget.ViewPager2
import com.example.interniti.R
import com.example.interniti.ui.add.PageAddActivity


class Pg2addFragment : Fragment() {

    lateinit var ButBack: ImageView
    lateinit var Cargo: EditText
    lateinit var mass:EditText
    lateinit var ButNext:Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val Viev = inflater.inflate(R.layout.fragment_pg2add, container, false)

        ButBack = Viev.findViewById(R.id.But_back_pg4)
        Cargo = Viev.findViewById(R.id.Lname_us)
        mass = Viev.findViewById(R.id.Fname_us)
        ButNext = Viev.findViewById(R.id.button_add_pg2)

        return Viev
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)

        ButNext.setOnClickListener {
            if(Cargo.text.isEmpty() or mass.text.isEmpty()){
                Toast.makeText(context, "поля должны быть заполненные", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            else{
                (requireContext() as PageAddActivity).CargoM = Cargo.text.toString()
                (requireContext() as PageAddActivity).MassM = mass.text.toString()
                val VP = activity?.findViewById<ViewPager2>(R.id.Page_add)
                VP?.currentItem = 2
            }
        }

        ButBack.setOnClickListener {
            val VP = activity?.findViewById<ViewPager2>(R.id.Page_add)
            VP?.currentItem = 0
        }

    }
}