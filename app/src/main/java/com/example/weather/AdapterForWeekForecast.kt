package com.example.weather

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.example.weather.databinding.ItemForWeeklyRcvBinding

class AdapterForWeekForecast(val listener: Listener): RecyclerView.Adapter<AdapterForWeekForecast.MyViewHolder>() {

    private var binding: ItemForWeeklyRcvBinding? = null
    private val listOfDays = mutableListOf<ForecastDay>()

    var selectedDay = 0

    fun updateData(newList: List<ForecastDay>){
        listOfDays.clear()
        listOfDays.addAll(newList)
        notifyDataSetChanged()
    }



    class MyViewHolder(var binding: ItemForWeeklyRcvBinding): ViewHolder(binding.root) {

        fun bind(data: ForecastDay, listener: Listener){
            val data2 = data.date
            binding.tvDate.text = data2?.drop(5)
            binding.imageView.load("https:" + data.day.condition.icon)
            binding.tvTempC.text = data.day.maxtemp_c.toString()
            itemView.setOnClickListener {
                listener.onClick(data)

            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var view = ItemForWeeklyRcvBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount() = listOfDays.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(listOfDays[position], listener)




    }



    interface Listener{
        fun onClick(data: ForecastDay)
    }

}