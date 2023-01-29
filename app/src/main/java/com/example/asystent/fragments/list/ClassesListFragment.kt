package com.example.asystent.fragments.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.asystent.R
import com.example.asystent.data.Classes.ClassesViewModel
import com.example.asystent.fragments.Adapters.ClassListAdapter
import kotlinx.android.synthetic.main.fragment_classes_list.view.*

class ClassListFragment: Fragment() {
    private lateinit var mClassesViewModel : ClassesViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_classes_list, container, false)

        //Recyclerview
        val adapter = ClassListAdapter()
        val recyclerView = view.recyclerviewClass
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        //UserViewModel
        mClassesViewModel = ViewModelProvider(this).get(ClassesViewModel::class.java)
        mClassesViewModel.readAllclassData.observe(viewLifecycleOwner, Observer{classes ->
            adapter.setData(classes)

        })

        view.floatingActionButtonClass.setOnClickListener {
            findNavController().navigate(R.id.action_classes_list_to_addClass)
        }

        return view
    }

}