package com.example.asystent.fragments.add

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.asystent.R
import com.example.asystent.data.Classes.Classes
import com.example.asystent.data.Classes.ClassesViewModel
import com.example.asystent.fragments.Adapters.AddClassToChooseStudentAdapter
import kotlinx.android.synthetic.main.fragment_add_class.*
import kotlinx.android.synthetic.main.fragment_add_class.view.*

class addClass : Fragment() {
    private lateinit var mClassesViewModel : ClassesViewModel

    private val args by navArgs<addClassArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_class, container, false)

        //Recyclerview
        val adapter = AddClassToChooseStudentAdapter()
        val recyclerView = view.recyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        //UserViewModel
        mClassesViewModel = ViewModelProvider(this).get(ClassesViewModel::class.java)
        mClassesViewModel.readAllclassData.observe(viewLifecycleOwner, Observer{classes ->
            adapter.setData(classes)

        })

        view.add_class.setOnClickListener {

            insertDataToDatabase()
        }


        return view
    }

    private fun insertDataToDatabase() {
        val name = subject_name.text.toString()
        val day = day_of_the_week.text.toString()
        val hourlyblock = hourly_block.text.toString()

        if(inputCheck(name, day, hourlyblock)){
            // Create Student Object
            val classes = Classes(0,name, day, hourlyblock)
            // Add Data to Database
            mClassesViewModel.addClass(classes)
            Toast.makeText(requireContext(), "Class successfully added!", Toast.LENGTH_LONG).show()
            // Navigate Back
            findNavController().navigate(R.id.action_addClass_to_management_panel)
        }else{
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(name: String, day: String, hourlyblock: String): Boolean{
        return !(TextUtils.isEmpty(name) && TextUtils.isEmpty(day) && hourlyblock.isEmpty())
    }


}





