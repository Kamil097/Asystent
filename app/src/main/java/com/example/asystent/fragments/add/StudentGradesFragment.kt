package com.example.asystent.fragments.add

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.asystent.R
import com.example.asystent.data.Classes.Classes
import com.example.asystent.data.Student.Student
import com.example.asystent.data.Student.StudentViewModel
import com.example.asystent.data.StudentDatabase
import com.example.asystent.data.StudentGrades.StudentGrades
import com.example.asystent.fragments.Adapters.GradesAdapter
import kotlinx.android.synthetic.main.fragment_student_grades.*
import kotlinx.android.synthetic.main.fragment_student_grades.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

class StudentGradesFragment : Fragment() {
    private val args by navArgs<StudentGradesFragmentArgs>()
    private val grades = arrayOf("Choose Grade: ","Grade: 1","Grade: 2","Grade: 3","Grade: 4","Grade: 5","Grade: 6")
    private lateinit var studentDatabase: StudentDatabase
    private lateinit var mStudentViewModel : StudentViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_student_grades, container, false)
        studentDatabase = StudentDatabase.getDatabase(requireContext())

        //1 chcemy zajecia z danym id
        //2 chcemy ucznia z danym id
        var myStudent= Student(0,"","",0)
        var myClasses= Classes(0,"","","")
        var newGrade=1
        runBlocking(Dispatchers.IO) {
             myStudent = studentDatabase.studentGradesDao().returnIdStudent(args.studentClass.id_student)
             myClasses = studentDatabase.studentGradesDao().returnIdClasses(args.studentClass.id_classes)
        }


        view.subject_name2.text=myClasses.nazwa
        view.day_of_the_week2.text=myClasses.dzien
        view.hourly_block2.text=myClasses.godzina
        view.first_last_name.text=myStudent.firstName+" "+myStudent.lastName
        //Recyclerview
        val adapter2 = GradesAdapter()
        val recyclerView = view.recyclerviewGrades
        recyclerView.adapter = adapter2
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        var graades = emptyList<StudentGrades>()
        runBlocking(Dispatchers.IO) {
            graades = studentDatabase.studentGradesDao().runAllGrades(myClasses.id, myStudent.id)
        }
        adapter2.setData(graades)

        val spinner_grades = view.findViewById<Spinner>(R.id.spinner_grades)
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item,grades)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        var isInitialSelection = true
        spinner_grades.adapter = adapter

        spinner_grades.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                spinner_grades.setSelection(position)
                spinner_grades.setPrompt(grades[position])
                newGrade=position
                if(position>0) {
                        updateDatabase(newGrade, myStudent.id, myClasses.id)
                        runBlocking(Dispatchers.IO) {
                            graades = studentDatabase.studentGradesDao()
                                .runAllGrades(myClasses.id, myStudent.id)
                        }
                        adapter2.setData(graades)
                    }
                }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        return view
    }
    fun updateDatabase(newGrade:Int,studentId:Int,classId:Int){

        var grade = StudentGrades(0,studentId,classId,newGrade)
        runBlocking {
            studentDatabase.studentGradesDao().insert(grade)
        }
        spinner_grades.setSelection(0)
    }

}