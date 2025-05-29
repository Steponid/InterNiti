package com.example.interniti.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.interniti.databinding.FragmentHomeBinding
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and


    private lateinit var trainAdapter: Adapter
    private val trainList = mutableListOf<TrainItem>()
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setupRecyclerView()
        loadFromFirebase()
        //loadTestData()

        return root
    }
    private fun setupRecyclerView() {
        trainAdapter = Adapter(trainList)
        binding.RecyMain.layoutManager = LinearLayoutManager(requireContext())
        binding.RecyMain.adapter = trainAdapter
    }

    private fun loadTestData() {
        val testData = listOf(
            TrainItem("Лесоматериалы", "Владивосток", "Красноярск", "~7 дней 8 часов", "Сибирский Лесовоз-127", "65В", "4200т"),
            TrainItem("Уголь", "Москва", "Новосибирск", "~3 дня", "Сибирский Экспресс", "22С", "3000т")
        )
        trainList.clear()
        trainList.addAll(testData)
        trainAdapter.notifyDataSetChanged()
    }

    private fun loadFromFirebase() {
        val db = Firebase.firestore
        db.collection("BD")
            .get()
            .addOnSuccessListener { result ->
                trainList.clear()
                for (document in result) {
                    document.toObject(TrainItem::class.java).let {
                        trainList.add(it)
                    }
                }
                trainAdapter.notifyDataSetChanged()
            }
            .addOnFailureListener { exception ->

            }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}