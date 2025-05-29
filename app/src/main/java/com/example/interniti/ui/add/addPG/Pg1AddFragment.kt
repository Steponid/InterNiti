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


class Pg1AddFragment : Fragment() {

    lateinit var ButBack:ImageView
    lateinit var ButNext:Button
    lateinit var Train:EditText
    lateinit var Widg: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val View = inflater.inflate(R.layout.fragment_pg1_add, container, false)

        ButBack = View.findViewById(R.id.But_back_pg4)
        ButNext = View.findViewById(R.id.button_add_pg3)
        Train = View.findViewById(R.id.Lname_us)
        Widg = View.findViewById(R.id.Fname_us)

        return View
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)

        ButBack.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
        ButNext.setOnClickListener {
            if (Train.text.isEmpty() or Widg.text.isEmpty()){
                Toast.makeText(context, "поля должны быть заполненные", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            else{

                (requireContext() as PageAddActivity).TrainM = Train.text.toString()
                (requireContext() as PageAddActivity).widgM = Widg.text.toString()
                val VP = activity?.findViewById<ViewPager2>(R.id.Page_add)
                VP?.currentItem = 1
            }


        }

    }

}