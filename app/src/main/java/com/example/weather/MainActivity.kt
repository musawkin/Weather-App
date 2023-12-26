package com.example.weather

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.voice.VoiceInteractionSession.ActivityId
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import coil.load
import com.example.weather.databinding.ActivityMainBinding
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), AdapterForWeekForecast.Listener {
    private var binding: ActivityMainBinding? = null
    private var adapterHour = AdapterForHourForecast()
    private var adapterForWeek = AdapterForWeekForecast(this)
    private val viewModel by viewModels<MainVM>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.rvForecastHour?.adapter = adapterHour
        binding?.rvForecastWeek?.adapter = adapterForWeek




        binding?.spCity?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                viewModel.coroutine(binding?.spCity?.selectedItem.toString())

                viewModel.liveData.observe(this@MainActivity){data->

                    when(data) {
                        is AlbumState.Success -> {

                            binding?.tvCurrentTemp?.text = data.albumState?.current?.temp_c?.toInt().toString()
                            binding?.tvAirHumidity?.text = data.albumState?.current?.humidity.toString() + " %"
                            binding?.tvWindCondition?.text = data.albumState?.current?.wind_kph.toString() + " km/h"
                            binding?.tvCondition1?.text = data.albumState?.current?.condition?.text
                            binding?.Image1?.load("https:" + data.albumState?.current?.condition?.icon)
                            adapterHour.updateData(data.albumState?.forecast!!.forecastday[0].hour)
                            adapterForWeek.updateData(data.albumState?.forecast!!.forecastday)
                        }

                        is AlbumState.Error ->{
                            Toast.makeText(this@MainActivity,data.message, Toast.LENGTH_SHORT).show()
                        }

                        is AlbumState.Loading -> {

                        }
                        else ->{}
                    }
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }




    }

    override fun onClick(data: ForecastDay) {

        viewModel.liveData.observe(this) { data2 ->
            data2?.let {
                binding?.tvCurrentTemp?.text = data.day.maxtemp_c?.toInt().toString()
                binding?.tvAirHumidity?.text = data.day.avghumidity.toString() + " %"
                binding?.tvWindCondition?.text = data.day.maxwind_kph.toString() + " km/h"
                binding?.tvCondition1?.text = data.day.condition.text
                binding?.Image1?.load("https:" + data.day.condition.icon)
            }
        }
        adapterHour.updateData(data.hour)
    }
}