package com.ishaanbhela.shayariapp

import android.content.Intent
import android.graphics.Color.parseColor
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.collection.LLRBNode.Color
import com.ishaanbhela.shayariapp.databinding.ItemCategoryBinding

class CategoryAdapter(val mainActivity: MainActivity, val list: ArrayList<CategoryModel>) : RecyclerView.Adapter<CategoryAdapter.CatViewHolder>() {

    class CatViewHolder(val binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        return CatViewHolder(
            ItemCategoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {

        holder.binding.itemTxt.text = list[position].name.toString()
        holder.binding.root.setOnClickListener {

            val intent = Intent(mainActivity, all_shayari::class.java)
            intent.putExtra("id", list[position].id)
            intent.putExtra("name", list[position].name)
            mainActivity.startActivity(intent)
        }
    }

    override fun getItemCount() = list.size
}
