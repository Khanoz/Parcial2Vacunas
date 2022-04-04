package com.example.vacunas.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.vacunas.R
import com.example.vacunas.Vaccine
import com.example.vacunas.activities.ViewVaccineInfoActivity

class MyVaccineAdapter(val vaccineList: List<Vaccine>, val cont : Context?) : RecyclerView.Adapter<MyVaccineAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.vaccines, parent, false)
        return ViewHolder(view, cont)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(vaccineList[position])
    }

    override fun getItemCount(): Int {
        return vaccineList.size
    }


    class ViewHolder(itemView: View, val cont : Context?): RecyclerView.ViewHolder(itemView){
        fun bindItems(vaccine: Vaccine){
            val titulo = itemView.findViewById(R.id.vaccine_title) as TextView
            titulo.text = vaccine.title
            itemView.findViewById<ImageButton>(R.id.vaccine_btn).setOnClickListener {
                val intent = Intent(cont, ViewVaccineInfoActivity::class.java)
                intent.putExtra("uid", vaccine.uid)
                cont!!.startActivity(intent)
            }
        }
    }
}