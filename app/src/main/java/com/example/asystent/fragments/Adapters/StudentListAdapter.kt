package com.example.asystent.fragments.Adapters

import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.example.asystent.R
import com.example.asystent.data.Student.Student
import kotlinx.android.synthetic.main.custom_row.view.*

class StudentListAdapter: RecyclerView.Adapter<StudentListAdapter.MyViewHolder>() {
    private var studentList = emptyList<Student>()

    class MyViewHolder(itemView:View): RecyclerView.ViewHolder(itemView) {}


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
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
    }
    fun setData (student: List<Student>){
        this.studentList = student
        notifyDataSetChanged()
    }
}