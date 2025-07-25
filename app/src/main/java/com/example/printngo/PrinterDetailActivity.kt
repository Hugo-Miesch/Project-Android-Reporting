package com.example.printngo

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.printngo.adapter.PrintHistoryAdapter
import com.example.printngo.models.PrintJob
import com.example.printngo.models.PrinterDetail
import com.example.printngo.network.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.widget.Button


class PrinterDetailActivity : AppCompatActivity() {

    private lateinit var printerName: TextView
    private lateinit var printerStatus: TextView
    private lateinit var historyRecyclerView: RecyclerView
    private lateinit var allJobs: List<PrintJob>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_printer_detail)

        printerName = findViewById(R.id.printerName)
        printerStatus = findViewById(R.id.printerStatus)
        historyRecyclerView = findViewById(R.id.historyRecyclerView)
        historyRecyclerView.layoutManager = LinearLayoutManager(this)

        val printerId = intent.getIntExtra("printerId", -1)
        if (printerId != -1) {
            fetchPrinterDetail(printerId)
        } else {
            Toast.makeText(this, "Imprimante inconnue", Toast.LENGTH_SHORT).show()
        }

        // Actions sur les boutons de filtre
        findViewById<Button>(R.id.showAllBtn).setOnClickListener {
            historyRecyclerView.adapter = PrintHistoryAdapter(allJobs)
        }

        findViewById<Button>(R.id.showSuccessBtn).setOnClickListener {
            val filtered = allJobs.filter { it.result.equals("succès", ignoreCase = true) }
            historyRecyclerView.adapter = PrintHistoryAdapter(filtered)
        }

        findViewById<Button>(R.id.showFailedBtn).setOnClickListener {
            val filtered = allJobs.filter { it.result.equals("échec", ignoreCase = true) }
            historyRecyclerView.adapter = PrintHistoryAdapter(filtered)
        }
    }

    private fun fetchPrinterDetail(id: Int) {
        RetrofitInstance.api.getPrinterById(id).enqueue(object : Callback<PrinterDetail> {
            @SuppressLint("SetTextI18n")
            override fun onResponse(call: Call<PrinterDetail>, response: Response<PrinterDetail>) {
                if (response.isSuccessful) {
                    val printer = response.body()!!
                    printerName.text = printer.name
                    printerStatus.text = "Statut : ${printer.status}"
                    allJobs = printer.history ?: emptyList()
                    historyRecyclerView.adapter = PrintHistoryAdapter(allJobs)
                } else {
                    Toast.makeText(this@PrinterDetailActivity, "Erreur serveur", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<PrinterDetail>, t: Throwable) {
                Toast.makeText(this@PrinterDetailActivity, "Erreur réseau", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
