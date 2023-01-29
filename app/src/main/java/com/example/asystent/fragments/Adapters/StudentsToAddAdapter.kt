package com.example.asystent.fragments.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.asystent.R
import com.example.asystent.data.Classes.Classes
import com.example.asystent.data.Student.Student
import com.example.asystent.data.StudentClasses.StudentClasses
import com.example.asystent.data.StudentDatabase
import com.example.asystent.fragments.list.addStudentToClassDirections
import kotlinx.android.synthetic.main.custom_row.view.*
import kotlinx.android.synthetic.main.custom_row.view.albumNumber
import kotlinx.android.synthetic.main.custom_row.view.firstName
import kotlinx.android.synthetic.main.custom_row.view.id_txt
import kotlinx.android.synthetic.main.custom_row.view.lastName
import kotlinx.android.synthetic.main.custom_row2.view.*
import kotlinx.coroutines.runBlocking

class StudentsToAddAdapter: RecyclerView.Adapter<StudentsToAddAdapter.MyViewHolder>() {
    private var studentList = emptyList<Student>()
    private lateinit var studentDatabase:StudentDatabase
    private var classid = 0
    private var nazwa = ""
    private var dzien = ""
    private var godzina = ""

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {}


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            MyViewHolder {
        studentDatabase = StudentDatabase.getDatabase(parent.context)
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row,parent,false))
    }

    override fun getItemCount(): Int {
        return studentList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = studentList[position]
        holder.itemView.id_txt.text = currentItem.id.toString()
        holder.itemView.firstName.text = currentItem.firstName
        holder.itemView.lastName.text = currentItem.lastName
        holder.itemView.albumNumber.text = currentItem.album_number.toString()
        holder.itemView.custom_row.setOnClickListener{
           // Toast.makeText(holder.itemView.context, classid.toString()+" to jest id zajęć, a to: "+currentItem.id.toString()+" jest id osoby", Toast.LENGTH_SHORT).show()
            runBlocking {
                var studentClasses = StudentClasses(0,currentItem.id,classid)
                studentDatabase.studentClassesDao().insert(studentClasses)
                var myclass = Classes(classid,nazwa,dzien,godzina)
                val action = addStudentToClassDirections.actionAddStudentToClassSelf(myclass) //krzywy sposób na odświeżenie okna po przeniesieniu
                holder.itemView.findNavController().navigate(action)
            }

        }
    }
    fun setData (student: List<Student>,classid: Int,nazwa:String,dzien:String,godzina:String){
        this.studentList = student
        this.classid=classid
        this.nazwa=nazwa
        this.dzien=dzien
        this.godzina=godzina
        notifyDataSetChanged()
    }
}