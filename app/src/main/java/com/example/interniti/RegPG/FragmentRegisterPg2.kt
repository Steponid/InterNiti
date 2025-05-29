package com.example.interniti.RegPG

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.viewpager2.widget.ViewPager2
import com.example.interniti.Load
import com.example.interniti.LoginActivity
import com.example.interniti.R
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class FragmentRegisterPg2 : Fragment() {
    lateinit var  button: Button
    lateinit var Password: EditText
    lateinit var buttonBack: Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_register_pg2, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val act = requireActivity() as LoginActivity


        Password = view.findViewById(R.id.Pasword)
        button = view.findViewById(R.id.button_reg_pg2)
        button.setOnClickListener {
            if (Password.text.isEmpty()){
                Toast.makeText(context,"ввидите пароль", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val mail = act.Mail

            Firebase.auth.signInWithEmailAndPassword(mail, Password.text.toString())
                .addOnSuccessListener {
                    startActivity(Intent(requireContext(), Load::class.java))

                }
                .addOnFailureListener {
                    Toast.makeText(context, "данный аккаунт не найден, обратитесь к менеджеру", Toast.LENGTH_LONG).show()
                }

        }
        buttonBack = view.findViewById(R.id.button_back_pg2_reg)
        buttonBack.setOnClickListener {
            val VP = activity?.findViewById<ViewPager2>(R.id.PageReg)
            VP?.currentItem = 0
        }

    }
}