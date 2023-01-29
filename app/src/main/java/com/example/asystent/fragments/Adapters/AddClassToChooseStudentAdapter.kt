package com.example.asystent.fragments.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.asystent.R
import com.example.asystent.data.Classes.Classes
import com.example.asystent.fragments.add.addClassDirections
import kotlinx.android.synthetic.main.custom_row2.view.*

class AddClassToChooseStudentAdapter :RecyclerView.Adapter<AddClassToChooseStudentAdapter.MyViewHolder>() {

    private var classesList = emptyList<Classes>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {}


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row2,parent,false))
    }

    override fun getItemCount(): Int {
        return classesList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = classesList[position]
        holder.itemView.id_txt2.text = currentItem.id.toString()
        holder.itemView.firstName2.text = currentItem.nazwa
        holder.itemView.lastName2.text = currentItem.dzien
        holder.itemView.albumNumber2.text = currentItem.godzina
        holder.itemView.custom_row2.setOnClickListener{
            val action = addClassDirections.actionAddClassToAddStudentToClass(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }
    fun setData (classes: List<Classes>){
        this.classesList = classes
        notifyDataSetChanged()
    }
}