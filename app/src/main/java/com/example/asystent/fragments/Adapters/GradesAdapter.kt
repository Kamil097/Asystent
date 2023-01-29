package com.example.asystent.fragments.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.asystent.R
import com.example.asystent.data.StudentGrades.StudentGrades
import kotlinx.android.synthetic.main.custom_row_3.view.*

class GradesAdapter:RecyclerView.Adapter<GradesAdapter.MyViewHolder>() {
    private var gradesList = emptyList<StudentGrades>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {}


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row_3,parent,false))
    }

    override fun getItemCount(): Int {
        return gradesList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = gradesList[position]
        holder.itemView.grade_place.text = currentItem.grade.toString()
    }
    fun setData (grades: List<StudentGrades>){
        this.gradesList = grades
        notifyDataSetChanged()
    }
}