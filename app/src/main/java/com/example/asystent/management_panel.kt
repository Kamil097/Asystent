package com.example.asystent

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.asystent.data.StudentDatabase
import kotlinx.android.synthetic.main.fragment_management_panel.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

class management_panel : Fragment() {
    private lateinit var studentDatabase: StudentDatabase
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        studentDatabase = StudentDatabase.getDatabase(requireContext())
        val view = inflater.inflate(R.layout.fragment_management_panel, container, false)

        view.add_new_student.setOnClickListener {
            findNavController().navigate(R.id.action_management_panel_to_addStudent)
        }
        view.add_class.setOnClickListener {
            findNavController().navigate(R.id.action_management_panel_to_addClass)
        }
        view.student_list.setOnClickListener {
            findNavController().navigate(R.id.action_management_panel_to_studentListFragment)
        }
        view.class_list.setOnClickListener {
            findNavController().navigate(R.id.action_management_panel_to_classes_list)
        }
        view.delete.setOnClickListener {
            runBlocking(Dispatchers.IO) {
                studentDatabase.studentDao().deleteStudents()
                studentDatabase.classesDao().deleteClasses()
                studentDatabase.studentClassesDao().deleteStudentsClasses()
                studentDatabase.studentGradesDao().deleteGrades()
            }
            Toast.makeText(context, "Wyczyszczono dane", Toast.LENGTH_SHORT).show()
        }
        return view
    }
}