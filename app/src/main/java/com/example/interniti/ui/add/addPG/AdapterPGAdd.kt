package com.example.interniti.ui.add.addPG

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter


class AdapterPGAdd(fragmentActivity: FragmentActivity)  :
    FragmentStateAdapter(fragmentActivity) {

    private val fragment = listOf(Pg1AddFragment(), Pg2addFragment(), Pg3addFragment(), Pg4addFragment())

    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        return fragment[position]
    }
}



