package com.example.interniti.RegPG

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.viewpager2.widget.ViewPager2
import com.example.interniti.LoginActivity
import com.example.interniti.R

class FragmentRegistrPg1 : Fragment() {

    private lateinit var butNext: Button
    private lateinit var Phome: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_registr_pg1, container, false)

        butNext = view.findViewById(R.id.button_reg_pg1)
        Phome = view.findViewById(R.id.mail)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        butNext.setOnClickListener {



            if (Phome.text.isEmpty()){
                Toast.makeText(context,"ввидите почту", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val mailPub = requireActivity() as LoginActivity
            val mail = Phome.text.toString()
            mailPub.Mail = mail

            val VP = activity?.findViewById<ViewPager2>(R.id.PageReg)
            VP?.currentItem = 1

        }

    }
}