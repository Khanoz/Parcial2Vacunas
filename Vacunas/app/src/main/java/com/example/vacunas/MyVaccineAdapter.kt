package com.example.vacunas

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat

class MyVaccineAdapter(val vaccineList: List<Vaccine>) : RecyclerView.Adapter<MyVaccineAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.vaccines, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(vaccineList[position])
    }

    override fun getItemCount(): Int {
        return vaccineList.size
    }


    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bindItems(vaccine: Vaccine){

            val nombre = itemView.findViewById(R.id.vaccine_title) as TextView
            nombre.text = vaccine.title
        }
    }
}