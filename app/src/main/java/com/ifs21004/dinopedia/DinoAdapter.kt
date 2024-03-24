package com.ifs21004.dinopedia

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ifs21004.dinopedia.databinding.ItemRowDinoBinding

class DinoAdapter(private val listDino: ArrayList<Dino>) :
    RecyclerView.Adapter<DinoAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback:  OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRowDinoBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val dino = listDino[position]
        holder.binding.ivItemImage.setImageResource(dino.dino_image)
        holder.binding.s.text = dino.dinosaurs_name
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listDino[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int = listDino.size
    class ListViewHolder(var binding: ItemRowDinoBinding) : RecyclerView.ViewHolder(binding.root)

    interface OnItemClickCallback {
        fun onItemClicked(data: Dino)
        
    }

}