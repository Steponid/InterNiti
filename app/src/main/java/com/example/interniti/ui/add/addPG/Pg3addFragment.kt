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


class Pg3addFragment : Fragment() {

    lateinit var ButBack: ImageView
    lateinit var ButNext: Button

    lateinit var start:EditText
    lateinit var Stime:EditText
    lateinit var finish:EditText
    lateinit var Ftime:EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val View = inflater.inflate(R.layout.fragment_pg3, container, false)

        ButBack = View.findViewById(R.id.But_back_pg4)
        ButNext = View.findViewById(R.id.button_add_pg3)
        start = View.findViewById(R.id.Lname_us)
        Stime = View.findViewById(R.id.start_time)
        finish = View.findViewById(R.id.finish_add)
        Ftime = View.findViewById(R.id.Fname_us)

        return View
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)

        ButNext.setOnClickListener {
            if(start.text.isEmpty() or Stime.text.isEmpty() or finish.text.isEmpty() or Ftime.text.isEmpty()){
                Toast.makeText(context, "поля должны быть заполненные", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            else{
                (requireContext() as PageAddActivity).StartM = start.text.toString()
                (requireContext() as PageAddActivity).StimeM = Stime.text.toString()
                (requireContext() as PageAddActivity).finishM = finish.text.toString()
                (requireContext() as PageAddActivity).FtimeM = Ftime.text.toString()
                val VP = activity?.findViewById<ViewPager2>(R.id.Page_add)
                VP?.currentItem = 3
            }
        }

        ButBack.setOnClickListener {
            val VP = activity?.findViewById<ViewPager2>(R.id.Page_add)
            VP?.currentItem = 1
        }

    }

}