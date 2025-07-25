package com.example.printngo.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.printngo.R
import com.example.printngo.adapter.StoreAdapter
import com.example.printngo.models.StoreProduct

class StoreFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView

    private fun loadFakeProducts(): List<StoreProduct> {
        return listOf(
            StoreProduct(1, "Support téléphone", "Support inclinable pour bureau", 3.5, R.drawable.support_phone),
            StoreProduct(2, "Boîte à vis", "Petite boîte compartimentée à visser", 2.5, R.drawable.boite_vis),
            StoreProduct(3, "Organiseur de bureau", "Range-stylos et accessoires", 4.0, R.drawable.organiseur),
            StoreProduct(4, "Porte-clés 3D", "Porte-clés personnalisé imprimé en PETG.", 1.5, R.drawable.porte_cles),
            StoreProduct(5, "Support Casque", "Support élégant pour casque audio, sans vis.", 7.9, R.drawable.support_casque),
            StoreProduct(6, "Range câbles", "Un petit outil pour organiser tes câbles.", 2.5, R.drawable.range_cables)
        )
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_store, container, false)
        recyclerView = view.findViewById(R.id.storeRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)

        recyclerView.adapter = StoreAdapter(loadFakeProducts())
        return view
    }
}
