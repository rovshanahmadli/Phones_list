package com.rovshanahmadli.notes_retrofit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecycleViewAdapter(
    private val productList : ArrayList<ProductModel>,
    private val listener : Listener) : RecyclerView.Adapter<RecycleViewAdapter.RowHolder>(){

//class'imizin bir RecycleView Adapter oldugunu demeyimiz lazimdi
//RowHolder bizim ViewHolder class'imizdir.

    interface Listener{
        fun onItemClick(product: ProductModel)
    }

    class RowHolder(view: View) : RecyclerView.ViewHolder(view){

        fun bind(productModel: ProductModel,position: Int,listener: Listener){

            itemView.setOnClickListener {
                listener.onItemClick(productModel)
            }
            val textId = itemView.findViewById<TextView>(R.id.text_id)
            val textName = itemView.findViewById<TextView>(R.id.text_name)
            textId.text = productModel.id
            textName.text = productModel.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
//layout'la recyclerview'nu bir-birine baglayiriq

        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_layout,parent,false)
        return RowHolder(view)
    }

    override fun getItemCount(): Int {
//nece dene row yaranacq
//    return currencyList.count()
        return productList.size
    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {
//ViewHolder'daki view'lari baglama islerini eliyirik
//hansi item ne gosderecek onu yaziriq
        holder.bind(productList[position],position,listener)
    }
}