package com.odearmas.buscapelis.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.odearmas.buscapelis.adapter.PeliViewHolder
import com.odearmas.buscapelis.data.PeliThumb
import com.odearmas.buscapelis.databinding.ItemPeliBinding

class PeliAdapter(
    private var dataSet: List<PeliThumb> = emptyList(),
    private val onItemClickListener: (Int) -> Unit
) :
    RecyclerView.Adapter<PeliViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeliViewHolder {
        val binding = ItemPeliBinding.inflate(LayoutInflater.from(parent.context))
        return PeliViewHolder(binding)
    }

    override fun getItemCount(): Int = dataSet.size

    override fun onBindViewHolder(holder: PeliViewHolder, position: Int) {
        holder.render(dataSet[position])
        holder.itemView.setOnClickListener { onItemClickListener(holder.adapterPosition) }
    }

    fun updateData(dataSet: List<PeliThumb>?) {
        if (dataSet != null) {
            this.dataSet = dataSet
            notifyDataSetChanged()
        }
    }
}
