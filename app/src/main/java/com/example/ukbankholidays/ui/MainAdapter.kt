package com.example.ukbankholidays.ui

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ukbankholidays.R
import com.example.ukbankholidays.model.EventModel
import com.example.ukbankholidays.model.HolidayModel
import kotlinx.android.synthetic.main.type_layout_details.view.*

class MainAdapter(private val items:MutableList<HolidayModel>, val context: Context):RecyclerView.Adapter<MainAdapter.RecyclerViewHolder>() {
    class RecyclerViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        return RecyclerViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.type_layout_details,parent,false))
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
       var curItems =items[position]

        holder.itemView.apply {
           Log.d("MainAdapter"," England and Wales"+ curItems.englandAndWales.division)

//            curItems.northernIreland1
//            curItems.scotland
        }
    }

    override fun getItemCount(): Int {
       return items.size
    }
}