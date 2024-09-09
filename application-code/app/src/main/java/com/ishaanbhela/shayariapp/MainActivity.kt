package com.ishaanbhela.shayariapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.ishaanbhela.shayariapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lateinit var binding: ActivityMainBinding
        lateinit var db: FirebaseFirestore

        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.drawerLayout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        db = FirebaseFirestore.getInstance()
        db.collection("Shayari").addSnapshotListener { value, error ->

            val list = arrayListOf<CategoryModel>()
            val data = value?.toObjects(CategoryModel::class.java)
            list.addAll(data!!)

            binding.rcvCategory.layoutManager = LinearLayoutManager(this)
            binding.rcvCategory.adapter = CategoryAdapter(this, list)
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}