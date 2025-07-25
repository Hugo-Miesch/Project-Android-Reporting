package com.example.printngo

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class ProductDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        val name = intent.getStringExtra("name")
        val desc = intent.getStringExtra("desc")
        val price = intent.getDoubleExtra("price", 0.0)

        findViewById<TextView>(R.id.detailName).text = name
        findViewById<TextView>(R.id.detailDesc).text = desc
        findViewById<TextView>(R.id.detailPrice).text = "$price €"

        val imageResId = getImageResId(name)
        findViewById<ImageView>(R.id.detailImage).setImageResource(imageResId)

        findViewById<Button>(R.id.detailPrintButton).setOnClickListener {
            Toast.makeText(this, "Fichier envoyé à l'imprimante", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getImageResId(name: String?): Int {
        return when (name?.lowercase()) {
            "support téléphone" -> R.drawable.support_phone
            "boîte à vis" -> R.drawable.boite_vis
            "organiseur de bureau" -> R.drawable.organiseur
            "support casque" -> R.drawable.support_casque
            "porte-clés 3d" -> R.drawable.porte_cles
            "range câbles" -> R.drawable.range_cables
            else -> R.drawable.support_phone
        }
    }
}
