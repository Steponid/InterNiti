package com.example.interniti.RegPG

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class AdapterPageReg(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

        private val fragment = listOf(FragmentRegistrPg1(), FragmentRegisterPg2())

        override fun getItemCount(): Int = 2

        override fun createFragment(position: Int): Fragment {
            return fragment[position]
        }

    }