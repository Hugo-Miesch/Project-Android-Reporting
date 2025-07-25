package com.example.printngo.adapters

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.printngo.PrinterDetailActivity
import com.example.printngo.R
import com.example.printngo.models.Printer

@Suppress("NAME_SHADOWING")
class PrintersAdapter(private val printers: List<Printer>) : RecyclerView.Adapter<PrintersAdapter.PrinterViewHolder>() {

    class PrinterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.printerName)
        val status: TextView = itemView.findViewById(R.id.printerStatus)
        val image: ImageView = itemView.findViewById(R.id.printerImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PrinterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_printer, parent, false)
        return PrinterViewHolder(view)
    }

    @SuppressLint("DiscouragedApi")
    override fun onBindViewHolder(holder: PrinterViewHolder, position: Int) {
        val printer = printers[position]
        holder.name.text = printer.name
        holder.status.text = printer.status

        val context = holder.image.context
        val resId = context.resources.getIdentifier(printer.imageName, "drawable", context.packageName)
        holder.image.setImageResource(resId)

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, PrinterDetailActivity::class.java)
            intent.putExtra("printerId", printer.id)
            context.startActivity(intent)
        }

    }

    override fun getItemCount() = printers.size
}
