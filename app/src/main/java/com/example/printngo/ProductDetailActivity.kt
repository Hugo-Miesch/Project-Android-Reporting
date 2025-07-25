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
        val imageResId = intent.getIntExtra("imageResId", R.drawable.ic_launcher_foreground)

        findViewById<TextView>(R.id.detailName).text = name
        findViewById<TextView>(R.id.detailDesc).text = desc
        findViewById<TextView>(R.id.detailPrice).text = "$price €"
        findViewById<ImageView>(R.id.detailImage).setImageResource(imageResId)

        findViewById<Button>(R.id.detailPrintButton).setOnClickListener {
            Toast.makeText(this, "Fichier envoyé à l'imprimante", Toast.LENGTH_SHORT).show()
        }
    }
}
