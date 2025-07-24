package com.example.printngo.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.printngo.R
import com.example.printngo.adapter.StoreAdapter
import com.example.printngo.models.StoreProduct
import com.example.printngo.network.RetrocfitClient
import com.example.printngo.network.StoreService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StoreFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var productList: List<StoreProduct>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_store, container, false)
        recyclerView = view.findViewById(R.id.storeRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)

        fetchProducts(view)

        return view
    }

    private fun fetchProducts(view: View) {
        val service = RetrocfitClient.client.create(StoreService::class.java)

        service.getAllProducts().enqueue(object : Callback<List<StoreProduct>> {
            override fun onResponse(call: Call<List<StoreProduct>>, response: Response<List<StoreProduct>>) {
                if (response.isSuccessful) {
                    productList = response.body() ?: emptyList()
                    recyclerView.adapter = StoreAdapter(productList) { index ->
                        val p = productList[index]
                        // Tu peux ouvrir une ProductDetailActivity ici
                        Log.d("StoreFragment", "Clicked: ${p.name}")
                    }
                }
            }

            override fun onFailure(call: Call<List<StoreProduct>>, t: Throwable) {
                Log.e("StoreFragment", "Erreur : ${t.message}")
            }
        })
    }
}
