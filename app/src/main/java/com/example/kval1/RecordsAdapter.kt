package com.example.kval1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Spinner
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecordsAdapter(
    var records: List<UserRecord>,
    var context: Context
) : RecyclerView.Adapter<RecordsAdapter.MyViewHolder>() {

    class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val type: TextView = view.findViewById(R.id.record_type)
        val date: TextView = view.findViewById(R.id.record_date)
        val category: TextView = view.findViewById(R.id.record_category)
        val text: TextView = view.findViewById(R.id.record_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.record_in_list, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return records.count()
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.type.text = records[position].type
        holder.date.text = records[position].date
        holder.category.text = records[position].category
        holder.text.text = records[position].text
    }
}