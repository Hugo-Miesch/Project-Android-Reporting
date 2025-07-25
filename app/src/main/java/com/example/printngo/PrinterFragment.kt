package com.example.printngo

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.printngo.adapters.PrintersAdapter
import com.example.printngo.models.Printer
import com.example.printngo.network.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PrintersFragment : Fragment() {

    private lateinit var printerRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_printers, container, false)
        printerRecyclerView = view.findViewById(R.id.recyclerPrinters)
        printerRecyclerView.layoutManager = LinearLayoutManager(context)

        loadPrinters()
        return view
    }

    private fun loadPrinters() {
        val call = RetrofitInstance.api.getAllPrinters()
        call.enqueue(object : Callback<List<Printer>> {
            override fun onResponse(call: Call<List<Printer>>, response: Response<List<Printer>>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        printerRecyclerView.adapter = PrintersAdapter(it)
                    }
                } else {
                    Log.e("PrintersFragment", "Erreur API : ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<Printer>>, t: Throwable) {
                Log.e("PrintersFragment", "Erreur réseau : ${t.localizedMessage}")
            }
        })
    }
}
