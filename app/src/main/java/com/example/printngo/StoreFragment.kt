package com.example.printngo

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.printngo.adapters.StoreAdapter
import com.example.printngo.models.StoreProduct
import com.example.printngo.network.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.util.Log


class StoreFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_store, container, false)

        recyclerView = view.findViewById(R.id.storeRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)

        RetrofitInstance.api.getAllProducts().enqueue(object : Callback<List<StoreProduct>> {
            override fun onResponse(
                call: Call<List<StoreProduct>>,
                response: Response<List<StoreProduct>>
            ) {
                if (response.isSuccessful) {
                    val products = response.body() ?: emptyList()
                    recyclerView.adapter = StoreAdapter(products)
                }
            }

            override fun onFailure(call: Call<List<StoreProduct>>, t: Throwable) {
                Log.e("StoreFragment", "API Error: ${t.message}")
            }
        })

        return view
    }

}
