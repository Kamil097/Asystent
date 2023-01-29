package com.example.asystent.fragments.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.asystent.R
import com.example.asystent.data.Student.Student
import com.example.asystent.data.Student.StudentViewModel
import com.example.asystent.data.StudentClasses.StudentClasses
import com.example.asystent.data.StudentDatabase
import com.example.asystent.fragments.Adapters.CurrentStudentsAdapter
import com.example.asystent.fragments.Adapters.StudentsToAddAdapter
import kotlinx.android.synthetic.main.fragment_add_student_to_class.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

class addStudentToClass : Fragment() {
    private lateinit var mStudentViewModel : StudentViewModel
    private lateinit var studentDatabase: StudentDatabase
    private val args by navArgs<addStudentToClassArgs>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        studentDatabase=StudentDatabase.getDatabase(requireContext())

        val view =  inflater.inflate(R.layout.fragment_add_student_to_class, container, false)
        view.subject_name2.text = args.selectedClass.nazwa
        view.day_of_the_week2.text = args.selectedClass.dzien
        view.hourly_block2.text = args.selectedClass.godzina
        val studentClasses = StudentClasses(0,0,0)

        //Recyclerview
        val adapter = StudentsToAddAdapter()
        val recyclerView = view.recyclerviewStudentsAdd
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        var students = listOf<Student>()
        runBlocking(Dispatchers.IO) {
            students = studentDatabase.studentDao().studentsNotInClass(args.selectedClass.id)
        }
        adapter.setData(students,args.selectedClass.id,args.selectedClass.nazwa,args.selectedClass.dzien,args.selectedClass.godzina)

        val adapter2 = CurrentStudentsAdapter()
        val recyclerView2 = view.recyclerviewCurrentStudents
        recyclerView2.adapter = adapter2
        recyclerView2.layoutManager = LinearLayoutManager(requireContext())
        var students2 = listOf<Student>()
        runBlocking(Dispatchers.IO) {
            students2 = studentDatabase.studentDao().studentsInClass(args.selectedClass.id)
        }
        adapter2.setData(students2,args.selectedClass.id)

        return view
    }

}