package com.ifs21004.dinopedia

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ifs21004.dinopedia.databinding.ItemRowFamilyBinding

class DinoFamilyAdapter(private val listDinoFamily: ArrayList<FamilyDino>) :
    RecyclerView.Adapter<DinoFamilyAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback:  OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRowFamilyBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val dinoFamily = listDinoFamily[position]
        holder.binding.tvDetailTitle.text = dinoFamily.family_name
        holder.binding.ivDetailImage.setImageResource(dinoFamily.family_images)
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listDinoFamily[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int = listDinoFamily.size
    class ListViewHolder(var binding: ItemRowFamilyBinding) : RecyclerView.ViewHolder(binding.root)

    interface OnItemClickCallback {
        fun onItemClicked(data: FamilyDino)
    }
}