package com.ifs21004.dinopedia

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ifs21004.dinopedia.databinding.ItemRowDinoBinding

class ListDinoAdapter(private val listDino: ArrayList<Dino>) :
    RecyclerView.Adapter<ListDinoAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRowDinoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val dino = listDino[position]
        holder.bind(dino)
    }

    override fun getItemCount(): Int = listDino.size

    inner class ListViewHolder(private val binding: ItemRowDinoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(dino: Dino) {
            binding.ivDetailImage.setImageResource(dino.image)

            binding.root.setOnClickListener {
                onItemClickCallback.onItemClicked(listDino[adapterPosition])
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Dino)
    }
}
