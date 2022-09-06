package com.example.ukbankholidays.ui.details

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ukbankholidays.R
import com.example.ukbankholidays.model.EventModel
import kotlinx.android.synthetic.main.holiday_details.view.*

class DetailsAdapter(private val items:List<EventModel>,val context: Context):RecyclerView.Adapter<DetailsAdapter.RecyclerViewHolder>() {
    class RecyclerViewHolder(itemView:View):RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        return RecyclerViewHolder(LayoutInflater.from(context).inflate(R.layout.holiday_details,parent,false))
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        var curItem = items[position]

        holder.itemView.apply {
            holidayTitle.text = curItem.title
            holidayDate.text = curItem.date.toString()
            holidayNotes.text  = curItem.notes

        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}