package com.example.vacunas.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.vacunas.R
import com.example.vacunas.Utils
import com.example.vacunas.Vaccine

class MyAddVaccineAdapter(val vaccineList: List<Vaccine>, val cont : Context) : RecyclerView.Adapter<MyAddVaccineAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.vaccine_add, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(vaccineList[position], position)
    }

    override fun getItemCount(): Int {
        return vaccineList.size
    }


    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bindItems(vaccine: Vaccine, position: Int){
            val titulo = itemView.findViewById<TextView>(R.id.vaccine_title)
            titulo.text = vaccine.title

            val radioBtn = itemView.findViewById<RadioButton>(R.id.vaccine_radiobtn)
            radioBtn.setOnClickListener {
                Utils.selectedVaccine = Utils.missingVaccinesList[position]
            }
        }
    }
}