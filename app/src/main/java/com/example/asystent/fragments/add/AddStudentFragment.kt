package com.example.asystent.fragments.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.asystent.R
import com.example.asystent.data.Student.Student
import com.example.asystent.data.Student.StudentViewModel
import com.example.asystent.fragments.Adapters.StudentListAdapter
import kotlinx.android.synthetic.main.fragment_add_student.*
import kotlinx.android.synthetic.main.fragment_add_student.view.*

class AddStudentFragment : Fragment() {

    private lateinit var mStudentViewModel: StudentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_student, container, false)

        mStudentViewModel = ViewModelProvider(this).get(StudentViewModel::class.java)

        view.add_student.setOnClickListener {
            insertDataToDatabase()
        }
        val adapter = StudentListAdapter()
        val recyclerView = view.recyclerview2
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        //UserViewModel
        mStudentViewModel = ViewModelProvider(this).get(StudentViewModel::class.java)
        mStudentViewModel.readAllData.observe(viewLifecycleOwner, Observer{student ->
            adapter.setData(student)

        })

        return view
    }


    private fun insertDataToDatabase() {
        val firstName = add_first_name.text.toString()
        val lastName = add_last_name.text.toString()
        val album_number = add_album_number.text

        if(inputCheck(firstName, lastName, album_number)){
            // Create Student Object
            val student = Student(0, firstName, lastName, Integer.parseInt(album_number.toString()),false)
            // Add Data to Database
            mStudentViewModel.addStudent(student)
            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_LONG).show()
            // Navigate Back
            findNavController().navigate(R.id.action_addStudent_to_management_panel)
        }else{
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(firstName: String, lastName: String, age: Editable): Boolean{
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }

}