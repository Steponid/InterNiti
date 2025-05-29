package com.example.interniti.ui.add.addPG

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.viewpager2.widget.ViewPager2
import com.example.interniti.Load
import com.example.interniti.R
import com.example.interniti.ui.add.PageAddActivity
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore


class Pg4addFragment : Fragment() {

    lateinit var ButBack:ImageView
    lateinit var ButNext:Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_pg4add, container, false)

        ButBack = root.findViewById(R.id.But_back_pg4)
        ButNext = root.findViewById(R.id.button_add_pg4)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ButNext.setOnClickListener {
            val r = (requireContext() as PageAddActivity)
            val data = hashMapOf(
                "Start" to r.StartM,
                "Finish" to r.finishM,
                "CargoType"  to r.CargoM,
                "Train" to r.TrainM,
                "Time" to r.StimeM,
                "TimeF" to r.FtimeM,
                "Function…" to r.MassM,
                "Van" to r.widgM
            )

            Firebase.firestore.collection("BD").add(data)
                .addOnSuccessListener {
                    Toast.makeText(context, "добавлено", Toast.LENGTH_LONG).show()
                    requireContext().startActivity(Intent(requireContext(), Load::class.java))
                }
                .addOnFailureListener {
                    Toast.makeText(context, "ошибка", Toast.LENGTH_LONG).show()
                }
        }

        ButBack.setOnClickListener {
            val VP = activity?.findViewById<ViewPager2>(R.id.Page_add)
            VP?.currentItem = 2
        }

    }
}