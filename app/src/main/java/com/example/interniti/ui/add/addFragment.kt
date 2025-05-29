package com.example.interniti.ui.add

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.interniti.R
import com.example.interniti.databinding.FragmentAddBinding


class addFragment : Fragment() {
    private  lateinit var ButAd: Button
    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentAddBinding.inflate(inflater, container, false)

        ButAd = _binding!!.root.findViewById(R.id.button_add_bd)
        ButAd.setOnClickListener {
            startActivity(Intent(requireContext(), PageAddActivity::class.java))
        }

        val root: View = binding.root


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}