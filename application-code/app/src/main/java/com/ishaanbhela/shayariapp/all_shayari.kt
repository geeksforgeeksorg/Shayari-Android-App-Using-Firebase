package com.ishaanbhela.shayariapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.ishaanbhela.shayariapp.databinding.ActivityAllShayariBinding

class all_shayari : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lateinit var binding: ActivityAllShayariBinding
        lateinit var db: FirebaseFirestore


        enableEdgeToEdge()
        binding = ActivityAllShayariBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.allShayari)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val name = intent.getStringExtra("name")
        val id = intent.getStringExtra("id")

        db = FirebaseFirestore.getInstance()

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
        binding.catName.text = name.toString()

        db.collection("Shayari").document(id!!).collection("all")
            .addSnapshotListener { value, error ->

                val shayariList = arrayListOf<ShayariModel>()
                val data = value?.toObjects(ShayariModel::class.java)
                shayariList.addAll(data!!)

                binding.rcvAllShayari.layoutManager = LinearLayoutManager(this)
                binding.rcvAllShayari.adapter = AllShayariAdapter(this, shayariList)
            }
    }
}