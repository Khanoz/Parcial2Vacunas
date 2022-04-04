package com.example.vacunas.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.vacunas.News
import com.example.vacunas.R
import java.text.SimpleDateFormat


class MyNewsAdapter(val newsList: List<News>, val cont : Context) : RecyclerView.Adapter<MyNewsAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news, parent, false)
        return ViewHolder(view, cont)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(newsList[position])
    }

    override fun getItemCount(): Int {
        return newsList.size
    }


    class ViewHolder(itemView:View, val cont : Context):RecyclerView.ViewHolder(itemView){
        fun bindItems(news: News){

            val nombre = itemView.findViewById(R.id.news_title) as TextView
            val hora = itemView.findViewById(R.id.news_date) as TextView
            val texto = itemView.findViewById(R.id.news_description) as TextView
            val formatter = SimpleDateFormat("dd/M/yyyy hh:mm")

            nombre.text = news.title
            hora.text = formatter.format(news.date)
            texto.text = news.description
            Log.d("uid", news.uid.toString())
        }
    }
}