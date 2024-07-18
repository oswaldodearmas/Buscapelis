package com.odearmas.buscapelis.adapter

import androidx.recyclerview.widget.RecyclerView
import com.odearmas.buscapelis.data.PeliThumb
import com.odearmas.buscapelis.databinding.ItemPeliBinding
import com.squareup.picasso.Picasso

class PeliViewHolder (private val binding: ItemPeliBinding) : RecyclerView.ViewHolder(binding.root) {
    fun render(peliThumb: PeliThumb) {
        binding.titleTextView.text = peliThumb.title
        Picasso.get().load(peliThumb.imageURL).into(binding.posterImageView)
    }
}