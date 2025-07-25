package com.example.printngo.adapter

import android.view.*
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.printngo.R
import com.example.printngo.models.PrintJob

class PrintHistoryAdapter(private val history: List<PrintJob>) :
    RecyclerView.Adapter<PrintHistoryAdapter.HistoryViewHolder>() {

    class HistoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val file: TextView = view.findViewById(R.id.printFile)
        val date: TextView = view.findViewById(R.id.printDate)
        val duration: TextView = view.findViewById(R.id.printDuration)
        val result: TextView = view.findViewById(R.id.printResult)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_print_job, parent, false)
        return HistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val job = history[position]
        holder.file.text = job.file
        holder.date.text = job.date
        holder.duration.text = job.duration
        holder.result.text = "Résultat : ${job.result}"
    }

    override fun getItemCount(): Int = history.size
}
