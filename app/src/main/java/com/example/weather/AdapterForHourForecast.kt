package com.example.weather

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.example.weather.databinding.ItemForHourelyRcvBinding

class AdapterForHourForecast(): RecyclerView.Adapter<AdapterForHourForecast.MyVIdewHolder>() {
    private val listOfHours = mutableListOf<Hour>()

    fun updateData(newList: List<Hour>){
        listOfHours.clear()
        listOfHours.addAll(newList)
        notifyDataSetChanged()
    }

    class MyVIdewHolder(var binding: ItemForHourelyRcvBinding)
        : ViewHolder(binding.root) {
            fun bind(data: Hour){
                val data2 = data.time.drop(10)
                binding.tvHour.text = data2
                binding.imageView.load("https:" + data.condition.icon)
                binding.tvTempC.text = data.temp_c.toString()

            }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyVIdewHolder {
        val view = ItemForHourelyRcvBinding
            .inflate(LayoutInflater.from(parent.context),parent,false)
        return MyVIdewHolder(view)
    }

    override fun getItemCount() = listOfHours.size

    override fun onBindViewHolder(holder: MyVIdewHolder, position: Int) {
        holder.bind(listOfHours[position])
    }
}